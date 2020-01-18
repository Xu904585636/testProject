package com.kingleadsw.ysm.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kingleadsw.ysm.constants.Status;
import com.kingleadsw.ysm.exception.IllegalParameterException;
import com.kingleadsw.ysm.need.Need;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.request.RequestPayload;
import com.kingleadsw.ysm.utils.Asserts;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Log4j2
public class RepeatRequestInterceptor extends HandlerInterceptorAdapter
{
    @Autowired()
    IRepeatRequestProbe repeatRequestProbe;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception
    {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Need need = (Need)handlerMethod.getMethod().getAnnotation(Need.class);
        if ((need != null) && (need.repeat() == true))
        {
            if (need.token() != true) {
                throw new IllegalParameterException(Status.builder().code(Integer.valueOf(400001)).params(new String[] { "令牌" }).build());
            }
            RequestPayload payload = RequestCache.get();
            String serialNo = payload.getSerialNo();
            if (Asserts.isNull(serialNo))
            {
                log.error("请求接口{}.{}流水号为空", handlerMethod.getClass(), handlerMethod.getMethod().getName());
                throw new IllegalParameterException(Status.builder().code(Integer.valueOf(400001)).params(new String[] { "请求流水号" }).build());
            }
            this.repeatRequestProbe.addSerialNo(serialNo);
        }
        return true;
    }
}
