package com.lizk.blocks.common.jwt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JWTUtilTest {

    @Test
    void createKey() {
        System.out.println(new JWTUtil("").createKey());
    }
}