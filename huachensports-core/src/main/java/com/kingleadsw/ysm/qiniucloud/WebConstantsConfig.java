package com.kingleadsw.ysm.qiniucloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/**
 * 
 * 系统参数配置
 * @author ptz
 *
 */
@Configuration
public class WebConstantsConfig {
	private String qiniu_access_key;

	private String qiniu_secret_key;

	private String qiniu_cloud_key;

	private String qiniu_link;

	private String uploadPath;


	public String getQiniu_access_key() {
		return qiniu_access_key;
	}

	@Value("${sysconfig.constants.qiniu.qiniu_access_key}")
	public void setQiniu_access_key(String qiniu_access_key) {
		WebConstants.QINIU_ACCESS_KEY = qiniu_access_key;
		this.qiniu_access_key = qiniu_access_key;
	}

	public String getQiniu_secret_key() {
		return qiniu_secret_key;
	}

	@Value("${sysconfig.constants.qiniu.qiniu_secret_key}")
	public void setQiniu_secret_key(String qiniu_secret_key) {
		WebConstants.QINIU_SECRET_KEY=qiniu_secret_key;
		this.qiniu_secret_key = qiniu_secret_key;
	}

	public String getQiniu_cloud_key() {
		return qiniu_cloud_key;
	}

	@Value("${sysconfig.constants.qiniu.qiniu_cloud_key}")
	public void setQiniu_cloud_key(String qiniu_cloud_key) {
		WebConstants.QINIU_CLOUD_KEY=qiniu_cloud_key;
		this.qiniu_cloud_key = qiniu_cloud_key;
	}

	public String getQiniu_link() {


		return qiniu_link;
	}

	@Value("${sysconfig.constants.qiniu.qiniu_link}")
	public void setQiniu_link(String qiniu_link) {
		WebConstants.QINIU_LINK = qiniu_link;
		this.qiniu_link = qiniu_link;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	@Value("${sysconfig.constants.upload-path}")
	public void setUploadPath(String uploadPath) {
		WebConstants.UPLOAD_FILE_PATH=uploadPath;
		this.uploadPath = uploadPath;
	}



}
