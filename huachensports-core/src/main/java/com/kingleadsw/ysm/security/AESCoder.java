package com.kingleadsw.ysm.security;

/**
 * @Auther: zhoujie
 * @Date: 2018/11/9 19:26
 * @Description: the token AES Secuity
 */
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


import com.kingleadsw.ysm.exception.UnknowException;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AESCoder
{

    private static final Key DEFAULT_AES_KEY = getAESKey("mekou");
    private static final Key LOTTERY_AES_KEY = getAESKey("mekou");

    public static String aesEncode(String source)
    {
        Key k = DEFAULT_AES_KEY;
        byte[] encryptData = null;
        try
        {
            encryptData = encrypt(source.getBytes(), k);
        }
        catch (Exception e)
        {
            log.error("aesEncode faild!", e);
            throw new UnknowException(Integer.valueOf(500003));
        }
        return bytes2Hex(encryptData);
    }

    public static String lotteryEncode(String source)
    {
        Key k = LOTTERY_AES_KEY;
        byte[] encryptData = null;
        try
        {
            encryptData = encrypt(source.getBytes(), k);
        }
        catch (Exception e)
        {
            log.error("aesEncode faild!", e);
            throw new UnknowException(Integer.valueOf(500003));
        }
        return bytes2Hex(encryptData);
    }

    public static String aesDecode(String encryptData)
    {
        Key k = DEFAULT_AES_KEY;
        byte[] decryptData = null;
        try
        {
            byte[] encryptByte = hex2Bytes(encryptData);
            decryptData = decrypt(encryptByte, k);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("aesDecode faild!", e);
            throw new UnknowException(Integer.valueOf(500004));
        }
        return new String(decryptData);
    }

    public static Key getAESKey(String password)
    {
        byte[] key = initSecretKey(password);
        Key k = toKey(key);
        return k;
    }

    public static byte[] aesEncode(String source, Key k)
    {
        try
        {
            return encrypt(source.getBytes(), k);
        }
        catch (Exception e)
        {
            log.error("aesEncode faild!", e);
            throw new UnknowException(Integer.valueOf(500003));
        }
    }

    public static byte[] initSecretKey(String password)
    {
        KeyGenerator kg = null;
        SecureRandom secureRandom=null;
        try
        {
             secureRandom = SecureRandom.getInstance("SHA1PRNG");
            kg = KeyGenerator.getInstance("AES");
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return new byte[0];
        }

        secureRandom.setSeed(password.getBytes());

        kg.init(128, secureRandom);

        SecretKey secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }

    private static Key toKey(byte[] key)
    {
        return new SecretKeySpec(key, "AES");
    }

    public static byte[] encrypt(byte[] data, Key key) throws Exception
    {
        return encrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception
    {
        return encrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm)
            throws Exception
    {
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }

    public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);

        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, byte[] key)
            throws Exception
    {
        return decrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] decrypt(byte[] data, Key key)
            throws Exception
    {
        return decrypt(data, key, "AES/ECB/PKCS5Padding");
    }

    public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm)
            throws Exception
    {
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }

    public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm)
            throws Exception
    {
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);

        cipher.init(2, key);

        return cipher.doFinal(data);
    }

    public static String aesDecode(byte[] encrypt, Key k)
    {
        byte[] decryptData = null;
        try
        {
            decryptData = decrypt(encrypt, k);
        }
        catch (Exception e)
        {
            log.error("aesDecode faild!", e);
            throw new UnknowException(Integer.valueOf(500004));
        }
        return new String(decryptData);
    }

    public static String bytes2Hex(byte[] bts)
    {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++)
        {
            tmp = Integer.toHexString(bts[i] & 0xFF);
            if (tmp.length() == 1) {
                des = des+"0";
            }
            des = des + tmp;
        }
        return des.toUpperCase();
    }

    private static byte toByte(char c)
    {
        byte b = (byte)"0123456789ABCDEF".indexOf(c);
        return b;
    }

    public static byte[] hex2Bytes(String hex)
    {
        int len = hex.length() / 2;
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++)
        {
            int pos = i * 2;
            result[i] = ((byte)(toByte(achar[pos]) << 4 | toByte(achar[(pos + 1)])));
        }
        return result;
    }
}
