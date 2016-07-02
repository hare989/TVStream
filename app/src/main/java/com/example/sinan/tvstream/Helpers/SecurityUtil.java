package com.example.sinan.tvstream.Helpers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Sinan on 18.5.2016.
 */
public class SecurityUtil {

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String computeSHAHash(String stringToCompute) {
        byte[] sha1hash = new byte[0];
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(stringToCompute.getBytes("ASCII"));
            sha1hash = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            return convertToHex(sha1hash);
        }
    }
}
