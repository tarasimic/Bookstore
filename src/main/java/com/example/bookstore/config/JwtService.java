package com.example.bookstore.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {


    private static final SecretKey SECRET_KEY;

    static {
        try {
            SECRET_KEY = SecretKeyGenerator.generateHmacSha256Key();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
//
//    static {
//        try {
//            char[] password = "QE41hQae7CUsTKkEPZz".toCharArray();
//            byte[] salt = generateSalt();
//            int iterationCount = 65536;
//            int keyLength = 256;
//            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
//            SecretKey secretKey = secretKeyFactory.generateSecret(new PBEKeySpec(password, salt, iterationCount, keyLength));
//            SECRET_KEY = new SecretKeySpec(secretKey.getEncoded(), "AES");
//        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    private static byte[] generateSalt() {
//        SecureRandom secureRandom = new SecureRandom();
//        byte[] salt = new byte[120];
//        secureRandom.nextBytes(salt);
//        return salt;
//    }

    public String extractUsername(String token) throws NoSuchAlgorithmException {
            return extractOneClaim(token, Claims::getSubject);
    }

//    public SecretKey secretKey() throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
//        return keyGenerator.generateKey();
//    }

    private Claims extractClaims(String token) throws NoSuchAlgorithmException {
        return Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();
    }

    public <T> T extractOneClaim(String token, Function<Claims, T> claimResolver) throws NoSuchAlgorithmException {
        final Claims claim = extractClaims(token);
        return claimResolver.apply(claim);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().subject(userDetails.getUsername()).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)).signWith(SECRET_KEY, Jwts.SIG.HS256).compact();
    }
    public String generatesToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) throws NoSuchAlgorithmException {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token) throws NoSuchAlgorithmException {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) throws NoSuchAlgorithmException {
        return extractOneClaim(token, Claims::getExpiration);
    }
}
