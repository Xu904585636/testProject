package com.kingleadsw.ysm.wechat;

import com.kingleadsw.ysm.qiniucloud.MD5Util;
import com.kingleadsw.ysm.utils.AesUtil;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class WxSign {


	/**
	 * 获取解密的微信用户信息
	 * 
	 * @param sessionKey
	 * @param encryptedData
	 * @param iv
	 * @return
	 */
	public static MiniprogramDecryptData ecryptMiniprogramUserData(String sessionKey, String encryptedData, String iv) {
		MiniprogramDecryptData miniprogramDecryptData = null;
		byte[] resultByte;
		try {
			resultByte = AesUtil.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey),
					Base64.decodeBase64(iv));
			if (null != resultByte && resultByte.length > 0) {
				String userInfo = new String(resultByte, "UTF-8");
				JSONObject jsonObj = JSONObject.fromObject(userInfo);
				miniprogramDecryptData = (MiniprogramDecryptData) JSONObject.toBean(jsonObj,
						MiniprogramDecryptData.class);
				return miniprogramDecryptData;
			}
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
