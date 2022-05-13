package com.csc510.resa.security;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Contains helpful methods for hashing
 *
 * @author Steven Green
 */
@Component
public class Hashing {

    /**
     * The hashing algorithm to use on the password
     */
    private static final String HASH_ALG = "SHA-256";

    /**
     * Hashes a password with a salt
     *
     * @param password The original password
     * @param salt     The salt for the password
     * @return The hash converted to a hex string
     */
    public static String hash(String password, int salt) {
        try {
            byte[] hash = hashPassword(password, salt);
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static byte[] hashPassword(String password, int salt) throws NoSuchAlgorithmException {

        String saltedPw = password + salt;

        MessageDigest digest = MessageDigest.getInstance(HASH_ALG);

        return digest.digest(saltedPw.getBytes());
    }

    /**
     * Helper method to convert the bytes from the hash into a string of hex.
     *
     * @param bytes The bytes to convert to a hex string
     * @return The String of equivalent hex
     */
    private static String bytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789abcdef".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
