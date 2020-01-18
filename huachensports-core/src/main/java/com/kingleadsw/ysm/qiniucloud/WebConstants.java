package com.kingleadsw.ysm.qiniucloud;

public class WebConstants {
	
	public WebConstants() {
		super();
	}
/*    public static ResourceBundle config = ResourceBundle
            .getBundle("web-constants");*/

    public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_ADMIN_USER = "admin";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
    

    /**
     * 项目启动检查是否liunx系统 flase 不是liunx 系统 true 是liunx系统.
     */
    public static final boolean SYSTEM_ISLIUNX = false;

    /**
     * 生成数据库导入SQL文件地址.
     */
    public static final String IMPORT_SQL_PATH = "";

	/**
	 * 域名
	 */
	public final static String MAIN_URL = "";
    
    /**
     * 主server名，即首页.
     */
    public static  String MAIN_SERVER ="";
    
    /**
     * 静态html地址 
     */
    public static final String HTML_ADDRESS_PATH = "";
    
    
    /**
     * 微信前端页面资源路径
     */
    public static final String RESOURCE_PATH = "";

    /**
     * CSS文件server名.
     */
    public static final String CSS_FILE_SERVER = "";

    /**
     * JS文件server名.
     */
    public static final String JS_FILE_SERVER = "";

    /**
     * 超过图片大小值压缩
     */
    public static final String IMAGE_THAN_SIZE_TO_COMPRESS = "";
    /**
     * 图片文件server名.
     */
    public static final String IMG_FILE_SERVER = "";
    
 
    
    /**
     * 图片上传路径.
     */
    public static   String UPLOAD_FILE_PATH = "";
    
    /**
     * 图片文件访问虚拟路径
     */
    public static final String ADVER_IMAGE_FILE_PATH = "";

    /**
     * 服务器图片存放相对于与UPLOAD_FILE_PATH位置.
     */
    public static final String STORE_IMG_PATH ="";
 
    
    /**
     * 上传图片预览路径
     */
    public static final String UPLOAD_IMG_PATH_VIEW = "images/temp/";
    
    /**
     * 上传图片预览路径
     */
    public static final String UPLOAD_IMG_PATH_DB = "images/upload/";
    
    /**
     * admin控制器访问路径
     */
    public static final String ADMIN_PATH = "/admin";
    
    /**
     * token时间
     */
    public static String TOKEN_TIME="604800";
    
    /**
     * 忘记密码，验证码时间
     */
    public static final String CHANGE_PWD_TIME="";
    
    /**
	 * 是否启用默认的用户
	 */
	public static final String ISMEMBER = "false";
	
	/**
	 * 免费推广账户，每个账户推广时只需要支付一分钱
	 */
	public static final String FREE_MEMBER = "";


    /**
     * 7牛账号密码
     */
    public  static String QINIU_ACCESS_KEY = "";
    public  static String QINIU_SECRET_KEY = "";
    public  static String QINIU_CLOUD_KEY = "";
    public  static String QINIU_LINK = "";
    
    /**
     * 苹果支付认证测试环境跟正式环境切换标志位
     */
    public final static String IAP_CHOOSEENV="";

    /**签到领取的分数*/
    public final static int QIANDAO_SCORE=10;
    
	/** 微信客户端调用微信支付的指定包名 */
	public static final String WEIXIN_PREPAY_PACKAGE = "Sign=WXPay";
	
	/** 微信交易类型 APP支付 */
	public static final String WEIXIN_PREPAY_TRADE_TYPE_APP = "APP";

	/** 微信交易类型 网页支付 */
	public static final String WEIXIN_PREPAY_TRADE_TYPE_JSAPI = "JSAPI";
	
	/** 微信交易类型 微信扫码支付 */
	public static final String WEIXIN_PREPAY_TRADE_TYPE_NATIVE = "NATIVE";
	
	public static final String WX_WEB_SECRET="f285a981a549f09a1ebf1ad9e3e8f830";
	
	/**
	 * 网页授权地址
	 */
	public static final String WX_ACCESS_TOKEN_URL="https://api.weixin.qq.com/sns/oauth2/access_token";
	
	public static final String WX_WEB_APPID="wx65d8d08c2df8da84";
	
	
	/**
	 * encodingAesKey 公众平台上，公众号消息加解密Key
	 */
	public static final String WXOPEN_ENCODINGAESKEY = "HwqysfbQAx6ULjlLZ7hNii3ghxTto75nmITDekS8jRQ";

	
	
    /**
	 * 小程序appid
	 */
	public static  String WX_MINI_APPID = "";
	
	
    /**
	 * 小程序appsecret
	 */
	public static  String WX_MINI_APPSECRET = "";
	
	
	public static final String WX_CERTFILEPATH = "/home/kingleadsw/program-jar/trainlive/";
}
