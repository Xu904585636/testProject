package com.kingleadsw.ysm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kingleadsw.ysm.base.Parameter;
import com.kingleadsw.ysm.need.Need;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.request.RequestPayload;
import com.kingleadsw.ysm.utils.Beans;
import com.kingleadsw.ysm.utils.Dates;
import com.kingleadsw.ysm.utils.Elogs;
import com.kingleadsw.ysm.utils.ObjectCopys;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author  zhoujie
 *请求参数日志
 */
@Log4j2
public class EventLogInterceptor
        extends HandlerInterceptorAdapter
{

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        RequestPayload req = RequestCache.get();
        if (req != null) {
            log.info("请求URL: " + req.getUrl() + " , 客户端IP：" + req.getIp() + " ,请求头参数 : platformType ->" + req
                    .getReqBody() + ",deviceType -> " + req.getDeviceType() + ",deviceNo ->" + req.getDeviceNo() + ",userAgent->" + req
                    .getUserAgent() + ",platformCode->" + req.getPlatformCode() + " , \r\n 请求Body : " + req
                    .getReqBody());
        }
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        RequestPayload payload = RequestCache.get();
        if (payload == null)
        {
            super.afterCompletion(request, response, handler, ex);
            return;
        }
        Long startTime = payload.getStartTime();
        Long endTime = Dates.now();
        if (!(handler instanceof HandlerMethod)) {
            log.error("............handler error:" + handler.getClass().getName());
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Need need =handlerMethod.getMethod().getAnnotation(Need.class);

        payload.setClassName(handlerMethod.getClass().getName() + "." + handlerMethod.getMethod().getName());
        payload.setDuration(Long.valueOf(endTime.longValue() - startTime.longValue()));
        payload.setHttpCode(Integer.valueOf(response.getStatus()));
        if ((need != null) && (need.log() != 0))
        {
            payload.setOptType(Integer.valueOf(need.log()));

            payload.setOptName(Elogs.getName(Integer.valueOf(need.log())));

            Parameter parameter = new Parameter("operationLogService", "addOperationLog");
            parameter.setParam(new Object[] { ObjectCopys.toMap(payload) });
            Object bean = Beans.getBean(parameter.getService());
            if (bean != null) {
                Beans.execute(parameter);
            } else {
                log.warn(parameter.getService() + " is Null !!");
            }
        }
        log.info("本次请求耗时: {}毫秒; URI: {};  \r\n 返回参数：" + payload.getResBody(), payload.getDuration(), payload
                .getUri());
        super.afterCompletion(request, response, handler, ex);
    }
}
