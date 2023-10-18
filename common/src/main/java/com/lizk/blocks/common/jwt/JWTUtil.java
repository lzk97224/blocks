package com.lizk.blocks.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JWTUtil {
    /*@Value("jwt.secretKey")
    @Setter
    @Getter*/
    String secretKeyStr;

    public JWTUtil(String secretKeyStr) {
        this.secretKeyStr = secretKeyStr;
    }

    public String createKey() {
        return Encoders.BASE64.encode(Jwts.SIG.HS256.key().build().getEncoded());
    }

    public SecretKey loadKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeyStr));
    }


    public String createJWT(String id, String subject, long ttlMillis, Map<String, Object> map) {

        JwtBuilder jwtBuilder = Jwts
                .builder()
                .id(id)
                .subject(subject)
                .issuedAt(new Date())
                .signWith(loadKey());


        if (map != null && !map.isEmpty()) {
            jwtBuilder.claims(map);
        }
        if (ttlMillis > 0) {
            jwtBuilder.expiration(new Date(System.currentTimeMillis() + ttlMillis));
        }

        return jwtBuilder.compact();
    }


    public Claims parseJWT(String jwtString) {
        return Jwts
                .parser()
                .verifyWith(loadKey())
                .build()
                .parseSignedClaims(jwtString)
                .getPayload();
    }
}
