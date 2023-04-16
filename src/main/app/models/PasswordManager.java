package main.app.models;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordManager {
    static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    static final Integer SALT_LENGTH = 64;
    static final Integer ITERATIONS = 1000;
    static final Integer KEY_LENGTH = 256;

    public static void main(String[] args) {
        String hash = generateHash("password");
        System.out.println(hash);
        System.out.println(isCorrectPassword("password", hash));
    }

    public static String createSalt(Integer length){
        SecureRandom random = new SecureRandom();
        StringBuilder salt = new StringBuilder();

        for (int i = 1; i <= length; i++) {
            int index = random.nextInt(ALPHANUMERIC.length());
            salt.append(ALPHANUMERIC.charAt(index));
        }
        return salt.toString();
    }

    /* generateHash method that generates its own salt */
    public static String generateHash(String password){
        return generateHash(password, createSalt(SALT_LENGTH));
    }

    /* generateHash method that takes salt as a parameter */
    public static String generateHash(String password, String saltString){
        KeySpec specifications = new PBEKeySpec(password.toCharArray(), saltString.getBytes(), ITERATIONS, KEY_LENGTH);
        SecretKey key;
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            key = factory.generateSecret(specifications);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        String hashPasswordString = Base64.getEncoder().encodeToString(key.getEncoded());
        return hashPasswordString + '$' + saltString;
    }

    public static boolean isCorrectPassword(String passwordToCheck, String hashToCheckAgainst){
        String salt = hashToCheckAgainst.split("\\$")[1];
        String newHash = generateHash(passwordToCheck, salt);
        return newHash.equals(hashToCheckAgainst);
    }
}