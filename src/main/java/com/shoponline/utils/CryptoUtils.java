package com.shoponline.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Damian on 2017-02-09.
 */
public class CryptoUtils {

    public static final String SUPER_USER_KEY = "G9KfVfNRx95E8wz5sWjrd9MWE1Gp6AKT";

    public static String SHA256(String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = messageDigest.digest(text.getBytes());
            return Base64.encodeBase64String(bytes);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
