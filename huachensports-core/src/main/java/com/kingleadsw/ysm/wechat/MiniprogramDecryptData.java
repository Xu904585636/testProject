package com.kingleadsw.ysm.wechat;

import net.sf.json.JSONObject;

/**
 * 小程序获取用户信息后，用户数据的签名验证和加解密
 * @author ltp
 *
 */
public class MiniprogramDecryptData {

	private int status;
	
	private JSONObject userInfo;	//解密后的用户信息
	
	private String unionId;
	
	private JSONObject watermark;	//数据水印
	
	private String msg;
	
	private String purePhoneNumber;
	
	private String phoneNumber;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public JSONObject getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(JSONObject userInfo) {
		this.userInfo = userInfo;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public JSONObject getWatermark() {
		return watermark;
	}

	public void setWatermark(JSONObject watermark) {
		this.watermark = watermark;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getPurePhoneNumber() {
		return purePhoneNumber;
	}

	public void setPurePhoneNumber(String purePhoneNumber) {
		this.purePhoneNumber = purePhoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
