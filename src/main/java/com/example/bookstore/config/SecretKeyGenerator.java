package com.example.bookstore.config;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class SecretKeyGenerator {
    public static SecretKey generateHmacSha256Key() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        return keyGenerator.generateKey();
    }
}
