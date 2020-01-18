package com.kingleadsw.ysm.interceptor;

/**
 *
 * @author  zhoujie
 *
 * 针对MTP
 */
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kingleadsw.ysm.exception.NotfoundException;
import com.kingleadsw.ysm.need.Need;
import com.kingleadsw.ysm.need.token.Token;
import com.kingleadsw.ysm.need.token.Tokens;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.request.RequestPayload;
import com.kingleadsw.ysm.utils.Dates;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Log4j2
public class TokenMtpAuthenticationInterceptor
        extends HandlerInterceptorAdapter
{

    private boolean validIp = false;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception
    {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Need need = (Need)handlerMethod.getMethod().getAnnotation(Need.class);
        if ((need != null) && (need.token() == true))
        {
            RequestPayload payload = RequestCache.get();
            Token token = Tokens.deToken(payload.getToken());
            payload.setUserId(token.getUserId());
           // validTime(token.getCreatedTime());
        }

        return true;
    }

    /**
     *  token 是否过期
     *
     * @param time
     */
    protected void validTime(Long time)
    {
        Long expires = Long.valueOf(Dates.addDay(new Date(), -7).getTime());

        if (expires.longValue() > time.longValue()) {
            throw new NotfoundException(Integer.valueOf(403002));
        }
    }

    protected void validIp(String ip, String reqIp)
    {
        if (!ip.equals(reqIp))
        {
            log.error("token Ip校验失败");
            throw new NotfoundException(Integer.valueOf(403003));
        }
    }
}
