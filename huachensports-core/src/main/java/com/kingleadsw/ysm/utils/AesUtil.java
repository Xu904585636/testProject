package com.kingleadsw.ysm.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * @ Description : AES加解密工具类 @ Author : @ CreateDate : 2019/10/25 11:48 @
 * Version : 1.0
 */
public class AesUtil {
	private static final String CHARSET = "UTF-8";
	private static final String KEY_ALGORITHM = "AES";
	public static boolean initialized = false;
	/**
	 * 默认的加密算法 aes-192-cbc
	 */
	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
	// private static final String IV_PARAMETER_SPEC_STRING =
	// "0000000000000000";
	// 对应nodejs的buffer.alloc(16,0)
	private static final byte[] IV_PARAMETER_SPEC_BYTE = new byte[] { 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00,
			00, 00, 00, 00 };
	private static final Integer RADIX = 16;

	public static void main(String[] args) {
		String testStr = "";
		String aesKey = "BMKp20Omcv9u7TqGAhydCw66";// 密钥
		try {
			String aesEncrypt = aesEncrypt(aesKey, testStr);
			System.out.println("========success:===488944dc7dca74786f21fe9b98420038==================");
			System.out.println(aesEncrypt);
			String aesEncryptStr = "69a4b18f1156e6d8eddf95998c74ad35a7ad80d8cf6571e4c9c1ed8d2a60e46c7684cb6519520ec9b8df476c1a1518cbd3ce8d700ac806fbe7d742dd4476869d7120b0fd2eb9c9af8a0a65ee834bb0e9f7b51f93dda372ac5c870b419aeebe03fef1e3b20f7285539be334a0e2ee9dbc531a25d6c639906ce2412d260dd1c5df672f5be35ff953c33b14c3d5e088fcbdd3fad47291d9435e34c75a7d7efacc347136c0fa3130f1913b2454924b6c9c82a05bbff69983cd5d4c190b9815c74df1b04c5734d94a17e43d05777bf84d07aed1364124c9727d16729ec27c56dc65aab6a521f72ea216991989063b96fa1af835ce222d985f0dfb60b7dcc30a0ab6d07f437a566f87cc69fc59fdd32ae30afdc64602d23e02132ebf8c9f33382de7196fe5e1957485e081e5a68fe578ddb9e0";
			String aesDecrypt = aesDecrypt(aesKey, aesEncryptStr);
			System.out.println(aesDecrypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * AES 加密操作
	 *
	 * @param aesKey
	 *            密钥
	 * @param data
	 *            待加密内容
	 * @return 加密数据
	 */
	public static String aesEncrypt(String aesKey, String data) throws Exception {
		return byte2HexString(encrypt(aesKey.getBytes(CHARSET), data.getBytes(CHARSET)));
	}

	/**
	 * AES 解密操作
	 *
	 * @param aesKey
	 *            密钥
	 * @param data
	 *            待解密内容
	 * @return 解密数据
	 */
	public static final String aesDecrypt(String aesKey, String data) throws Exception {
		return new String(decrypt(aesKey.getBytes(CHARSET), hexString2Byte(data)), CHARSET);
	}

	private static byte[] decrypt(byte[] key, byte[] data) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
		IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER_SPEC_BYTE);
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
		return cipher.doFinal(data);
	}

	private static byte[] hexString2Byte(String hexString) {
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] b = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			b[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return b;
	}

	private static String byte2HexString(byte[] src) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xff;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				sb.append("0");
			}
			sb.append(hv);
		}
		return sb.toString();
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	private static byte[] encrypt(byte[] key, byte[] data) throws Exception {
		SecretKeySpec secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
		Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
		IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER_SPEC_BYTE);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
		return cipher.doFinal(data);
	}

	/**
	 * AES解密
	 * 
	 * @param content
	 *            密文
	 * @return
	 * @throws InvalidAlgorithmParameterException
	 * @throws NoSuchProviderException
	 */
	public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte)
			throws InvalidAlgorithmParameterException {
		initialize();
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			Key sKeySpec = new SecretKeySpec(keyByte, "AES");

			cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
			byte[] result = cipher.doFinal(content);
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void initialize() {
		if (initialized)
			return;
		Security.addProvider(new BouncyCastleProvider());
		initialized = true;
	}

	// 生成iv
	public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
		AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
		params.init(new IvParameterSpec(iv));
		return params;
	}
}
