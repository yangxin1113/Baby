package com.zyx.baby.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/8/15 0015.
 */
public class MD5Util {

    private static final String TAG = MD5Util.class.getSimpleName();
    private static final int STREAM_BUFFER_LENGHT = 1024;

    public static MessageDigest getDigest(final String agorithm) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(agorithm);
    }

    public static byte[] md5(String txt){
        return md5(txt.getBytes());
    }

    private static byte[] md5(byte[] bytes) {
        try {
            MessageDigest digest = getDigest("MD5");
            digest.update(bytes);
            return digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] md5(InputStream is) throws NoSuchAlgorithmException,IOException{
        return updateDigest(getDigest("MD5"),is).digest();
    }

    private static MessageDigest updateDigest(final MessageDigest digest, InputStream data) throws IOException{
        final byte[] buffer = new byte[STREAM_BUFFER_LENGHT];
        int read = data.read(buffer, 0, STREAM_BUFFER_LENGHT);

        while (read > -1){
            digest.update(buffer, 0, read);
            read = data.read(buffer, 0, STREAM_BUFFER_LENGHT);
        }

        return digest;
    }
}
