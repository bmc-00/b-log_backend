package com.bmc.b_log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hasher {
    private static final String SALT = "고정된_값"; // ✅ 고정된 salt 설정
    
    public Hasher () {
    	
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedPassword = password + SALT; // ✅ salt 추가
            byte[] hash = digest.digest(saltedPassword.getBytes());
            return Base64.getEncoder().encodeToString(hash); // ✅ Base64 인코딩 후 반환
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 알고리즘이 지원되지 않습니다.", e);
        }
    }

    public static boolean matches(String savedPassword, String hashedPassword) {
        return savedPassword.equals(hashedPassword);
    }
}