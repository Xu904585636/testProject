package com.kingleadsw.ysm.constants;

/**
 * @author  zhoujie
 *
 * 服务器返回code码
 *
 */
public class ServerCode {

    public static abstract interface BussinessError
    {
        public static final int QUERY_PRODUCT_FAILED = 600001;
        public static final int APTITUDED_FAILED = 600002;
    }

    public static abstract interface ServerError
    {
        public static final int UNKNOW_ERROR = 500001;
        public static final int CONVERT_FAILED = 500002;
        public static final int ENCODE_FAILED = 500003;
        public static final int DECODE_FAILED = 500004;
        public static final int INSTANCE_FAILED = 500005;
        public static final int SERIALIZE_FAILED = 500006;
        public static final int DESERIALIZE_FAILED = 500007;
        public static final int FILE_UPLOAD_FAILED = 500007;
        public static final int REQUEST_THIRDPARTY_FAILED = 500007;
    }

    public static abstract interface Conflict
    {
        public static final int RESOURCE_EXIST = 409001;
    }

    public static abstract interface NotFound
    {
        public static final int NOT_FOUND = 404001;
    }

    public static abstract interface Forbidden
    {
        public static final int FORBIDDEN_PERMISSION = 403001;
        public static final int TOKEN_EXPIRE = 403002;
        public static final int TOKEN_INVALID = 403003;
        public static final int REPEAT_REQUEST = 403004;
    }

    public static abstract interface Unauthorized
    {
        public static final int NOT_LOGIN = 401001;
        public static final int ACCOUNT_FAULT = 401002;
        public static final int ACCOUNT_INVALID = 401003;
        public static final int ACCOUNT_EXPIRE = 401004;
        public static final int ACCOUNT_EXSIT = 401005;
        public static final int NOT_COMPLETEDINFO = 401006;
        public static final int NOT_APTITUDED = 401007;
        public static final int APTITUDED_NOT_MATCH = 401008;
    }

    public static abstract interface BadRequest
    {
        public static final int BAD_REQUEST_NULL = 400001;
        public static final int BAD_REQUEST_FORMAT = 400002;
        public static final int BAD_REQUEST_LENGTH_MIN = 400003;
        public static final int BAD_REQUEST_LENGTH_MAX = 400004;
        public static final int BAD_REQUEST_SIZE_MIN = 400005;
        public static final int BAD_REQUEST_SIZE_MAX = 400006;
        public static final int BAD_REQUEST_TYPE = 400007;
        public static final int BAD_REQUEST_INVALID = 400008;
    }

    public static abstract interface OK
    {
        public static final int OK_DEFAULT = 200001;
        public static final int OK_ADD = 200002;
        public static final int OK_UPDATE = 200003;
        public static final int OK_DELETE = 200004;
    }
}
