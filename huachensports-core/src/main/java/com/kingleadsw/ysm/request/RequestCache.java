package com.kingleadsw.ysm.request;

/**
 * @author  zhoujie
 */
public class RequestCache
{
    private static ThreadLocal<RequestPayload> request = new ThreadLocal()
    {
        protected RequestPayload initialValue()
        {
            return new RequestPayload();
        }
    };

    public static void set(RequestPayload v)
    {
        request.set(v);
    }

    public static void clean()
    {
        if ((request == null) || (request.get() == null)) {
            return;
        }
        request.set(null);
    }

    public static RequestPayload get()
    {
        return request.get();
    }

    public static Long userId()
    {
        RequestPayload req = get();
        if (req != null) {
            return req.getUserId();
        }
        return null;
    }
}
