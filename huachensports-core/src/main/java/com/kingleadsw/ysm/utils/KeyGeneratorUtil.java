package com.kingleadsw.ysm.utils;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.util.encoders.Base64Encoder;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @Author shenbo
 * @Date 2019-11-27 15:41
 * <p>描述：DES密钥生成</p>
 */
public class KeyGeneratorUtil {
    private static KeyGenerator desKG;

    static {
        try {
            desKG = KeyGenerator.getInstance("DES");
            desKG.init(new SecureRandom());//初始化秘钥生成器
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private KeyGeneratorUtil() {
    }


    public static String getSecretKey() {
        return Hex.encodeHexString(desKG.generateKey().getEncoded());
    }

    public static String getSecretKey(int len) {
        String secretKey = getSecretKey();
        return secretKey.substring(0, len);
    }


    public static void main(String[] args) {
        System.out.println(getSecretKey(8));
    }
}
