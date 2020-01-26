package net.sytes.jaraya.security;

import net.sytes.jaraya.exception.UtilException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Cypher {

    public static byte[] encrypt(byte[] message, byte[] key) throws UtilException {

        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            throw new UtilException(e);
        } catch (NoSuchPaddingException e) {
            throw new UtilException(e);
        }
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        } catch (InvalidKeyException e) {
            throw new UtilException(e);
        }
        try {
            return cipher.doFinal(message);
        } catch (IllegalBlockSizeException e) {
            throw new UtilException(e);
        } catch (BadPaddingException e) {
            throw new UtilException(e);
        }
    }

    public static byte[] decrypt(byte[] encrypted, byte[] key) throws UtilException {

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            throw new UtilException(e);
        } catch (NoSuchPaddingException e) {
            throw new UtilException(e);
        }
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
        } catch (InvalidKeyException e) {
            throw new UtilException(e);
        }
        try {
            return cipher.doFinal(encrypted);
        } catch (IllegalBlockSizeException e) {
            throw new UtilException(e);
        } catch (BadPaddingException e) {
            throw new UtilException(e);
        }
    }
}
