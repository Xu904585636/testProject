package com.kingleadsw.ysm.security;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

import com.kingleadsw.ysm.exception.ConversionException;
import com.kingleadsw.ysm.exception.UnknowException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * @Auther: zhoujie
 * @Date: 2018/10/30 11:05
 * @Description: the rsa
 */
public class RsaPemCoder
{
    private static final Logger log = LogManager.getLogger(RsaPemCoder.class);

    public static PrivateKey getPrivateKey(InputStream is)
    {

        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            PEMKeyPair pemKeyPair = (PEMKeyPair)new PEMParser(br).readObject();
            KeyPair kp = new JcaPEMKeyConverter().getKeyPair(pemKeyPair);
            return kp.getPrivate();
        }
        catch (Exception e)
        {

            log.error(e);
            throw new UnknowException(Integer.valueOf(500007));
        }

    }

    public static String getPublicKey(InputStream is)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String s = br.readLine();
            StringBuffer publickey = new StringBuffer();
            while (s != null)
            {
                if (s.charAt(0) != '-') {
                    publickey.append(s + "\r");
                }
                s = br.readLine();
            }
            return publickey.toString();
        }
        catch (Exception e)
        {
            log.error(e);
            throw new UnknowException(Integer.valueOf(500007));
        }
    }

    public static RSAPublicKey getPublicKey(String publicKeyStr)
    {
        try
        {
            byte[] buffer = new BASE64Decoder().decodeBuffer(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey)keyFactory.generatePublic(keySpec);
        }
        catch (NoSuchAlgorithmException e)
        {
            log.error("无此算法", e);
            throw new UnknowException(Integer.valueOf(500007));
        }
        catch (InvalidKeySpecException e)
        {
            log.error("公钥非法", e);
            throw new UnknowException(Integer.valueOf(500007));
        }
        catch (IOException e)
        {
            log.error("公钥数据内容读取错误", e);
            throw new UnknowException(Integer.valueOf(500007));
        }
        catch (NullPointerException e)
        {
            log.error("公钥数据为空", e);
            throw new UnknowException(Integer.valueOf(500007));
        }
    }

    public static String decryptPemByPrivateKey(String value, PrivateKey privateKey)
    {
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(2, privateKey);
            byte[] v = cipher.doFinal(new BASE64Decoder().decodeBuffer(value));
            return new String(v);
        }
        catch (Exception e)
        {
            log.error("Failed to decryptPemByPrivateKey", e);
            throw new ConversionException(Integer.valueOf(500002));
        }
    }

    public static String encryptPemByPublicKey(String value, RSAPublicKey publicKey)
    {
        try
        {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());

            cipher.init(1, publicKey);
            return new BASE64Encoder().encode(cipher.doFinal(value.getBytes()));
        }
        catch (Exception e)
        {
            log.error("Failed to getSmsKey", e);
            throw new ConversionException(Integer.valueOf(500002));
        }
    }

    public static void main(String[] args)
            throws FileNotFoundException
    {
        String org = "{phone: \"13046851473\", type: 1}";
        String key = getPublicKey(new FileInputStream(new File("D:\\1\\rsa_1024_pub.pem")));
       //System.out.println(key);
        String en = encryptPemByPublicKey(org, getPublicKey(key));



        System.out.println(en);

        String org2 = decryptPemByPrivateKey(en, getPrivateKey(new FileInputStream(new File("D:\\1\\rsa_1024_priv.pem"))));

      //  System.out.println(org2);
        // kfzo4XKtGQRiOCUh1KaqAMukgZ7QM/PBb0Bsci8I+HwxQAjqzMmlLFz/0Ovctyge39mwZQ1RGsEK
        //Oo5jwNK73QzAhhOxsCO5b5mcVB17ExStPo1XIpnZEaeGKPPh2Olo08VRqskTwYlRJeChsAbAr9Ir
        //XXRbZUqmqZCZWrx5YKE=
        String c = "d5U8z7cLTU0ixG7txdf1CWf1nJl9P5k3fvoJ/QhEHhRPyLEFqTRrxw8yGgx72j5oUd5SaoYt1CXllhRqV9TWzJh/xe3zfsC/F11a42EziohwXQGfYLnZnSTrKzutiusGTCdmns78Zxeh0NOqu7xva6/+EwYI3xXFusJiYSzLuDw=";
        String org3 = decryptPemByPrivateKey(c,
                getPrivateKey(new FileInputStream(new File("D:\\1\\rsa_1024_priv.pem"))));
        System.out.println(org3);
    }
}

