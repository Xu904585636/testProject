package com.kingleadsw.ysm.security;


import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.exception.IllegalParameterException;

import java.io.*;
import java.security.Key;

/**
 * @Auther: zhoujie
 * @Description:
 */
public class Securitys {

    /**
     * 编码
     *
     * @param content
     * @return
     */
    public static String encode(byte[] content) {
        return new sun.misc.BASE64Encoder().encode(content);
    }

    /**
     * 解码
     *
     * @param source
     * @return
     */
    public static byte[] decode(String source) {
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            return decoder.decodeBuffer(source);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static final String encryptAES(String data, String passwd)
    {
        return encryptAES(data, AESCoder.getAESKey(passwd));
    }

    public static final String encryptAES(String data, Key key)
    {
        String encryptedData = null;
        try
        {
            encryptedData =  encode(AESCoder.aesEncode(data, key));
        }
        catch (Exception e)
        {
            throw new IllegalParameterException(ServerCode.ServerError.ENCODE_FAILED,"解密错误");
        }
        return encryptedData;
    }


    public static final String decryptAES(String cryptData, String passwd)
    {
        return decryptAES(cryptData, AESCoder.getAESKey(passwd));
    }

    public static final String decryptAES(String cryptData, Key key)
    {
        String decryptedData = null;
        try
        {
            decryptedData = new String(AESCoder.decrypt(decode(cryptData), key));
        }
        catch (Exception e)
        {
            throw new IllegalParameterException(ServerCode.ServerError.ENCODE_FAILED,"解密错误");
        }
        return decryptedData;
    }
}
