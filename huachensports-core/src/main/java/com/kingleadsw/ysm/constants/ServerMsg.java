package com.kingleadsw.ysm.constants;

public abstract interface ServerMsg
{
    public static abstract interface BussinessError
    {
        public static final String QUERY_PRODUCT_FAILED = "查询产品信息失败";
        public static final String APTITUDED_FAILED = "资质匹配报错了";
    }

    public static abstract interface ServerError
    {
        public static final String UNKNOW_ERRPR = "系统错误,请联系管理员";
        public static final String CONVERT_FAILED = "数据转换错误";
        public static final String ENCODE_FAILED = "加密失败";
        public static final String DECODE_FAILED = "解密失败";
        public static final String INSTANCE_FAILED = "初始化错误";
        public static final String SERIALIZE_FAILED = "序列化失败";
        public static final String DESERIALIZE_FAILED = "反序列化失败";
        public static final String FILE_UPLOAD_FAILED = "文件上传失败";
        public static final String REQUEST_THIRDPARTY_FAILED = "请求{}系统失败";
    }

    public static abstract interface Conflict
    {
        public static final String RESOURCE_EXIST = "{}已经存在";
    }

    public static abstract interface NotFound
    {
        public static final String NOT_FOUND = "{}没有找到";
    }

    public static abstract interface Forbidden
    {
        public static final String FORBIDDEN_PERMISSION = "没有权限";
        public static final String TOKEN_EXPIRE = "登陆已过期";
        public static final String TOKEN_INVALID = "无效的令牌";
        public static final String REPEAT_REQUEST = "重复请求";
    }

    public static abstract interface Unauthorized
    {
        public static final String NOT_LOGIN = "您还没有登陆";
        public static final String ACCOUNT_FAULT = "用户名或密码错误";
        public static final String ACCOUNT_INVALID = "您的账户已冻结";
        public static final String ACCOUNT_EXPIRE = "您的账户已过期";
        public static final String ACCOUNT_EXSIT = "您注册的账户已存在";
        public static final String NOT_COMPLETEDINFO = "您还没有完善信息";

    }

    public static abstract interface BadRequest
    {
        public static final String BAD_REQUEST_NULL = "{}不能为空";
        public static final String BAD_REQUEST_FORMAT = "{}格式错误";
        public static final String BAD_REQUEST_LENGTH_MIN = "{}长度不能小于{}";
        public static final String BAD_REQUEST_LENGTH_MAX = "{}长度不能大于{}";
        public static final String BAD_REQUEST_SIZE_MIN = "{}值不能小于{}";
        public static final String BAD_REQUEST_SIZE_MAX = "{}值不能大于{}";
        public static final String BAD_REQUEST_TYPE = "{}类型不正确 ";
        public static final String BAD_REQUEST_INVALID = "{}无效的参数";
    }

    public static abstract interface OK
    {
        public static final String OK_DEFAULT = "操作成功";
        public static final String OK_ADD = "新增成功";
        public static final String OK_UPDATE = "修改成功";
        public static final String OK_DELETE = "删除成功";
    }
}