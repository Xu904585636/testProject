package com.kingleadsw.ysm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.common.base.Objects;

import com.kingleadsw.ysm.exception.UnknowException;
import com.kingleadsw.ysm.need.Need;
import com.kingleadsw.ysm.need.token.Token;
import com.kingleadsw.ysm.need.token.TokenService;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.request.RequestPayload;
import com.kingleadsw.ysm.utils.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TokenOdpAuthenticationInterceptor
        extends HandlerInterceptorAdapter
{
    private static final Logger log = LoggerFactory.getLogger(TokenOdpAuthenticationInterceptor.class);
    private TokenService tokenService;

    public TokenOdpAuthenticationInterceptor(TokenService tokenService)
    {
        this.tokenService = tokenService;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Need need = handlerMethod.getMethod().getAnnotation(Need.class);
        if ((need != null) && (need.token() == true))
        {
            RequestPayload payload = RequestCache.get();


            Token token = getToken(payload.getToken());
            payload.setUserId(token.getUserId());
        }
        return true;
    }

    protected Token getToken(String tokenStr)
    {
        if (Asserts.isNull(tokenStr))
        {
            log.error("token 为空");
            throw new UnknowException(Integer.valueOf(403003));
        }
        Token token = this.tokenService.getToken(tokenStr);

        if (token == null)
        {
            log.error("token 为空");
            throw new UnknowException(Integer.valueOf(403002));
        }
        return token;
    }

    protected void validDevice(Token token, RequestPayload payload)
    {
        if ((!Objects.equal(Byte.valueOf(token.getDeviceType()), payload.getDeviceType())) ||
                (!Objects.equal(token.getDeviceNo(), payload.getDeviceNo())))
        {
            log.error("token Ip校验失败");
            throw new UnknowException(Integer.valueOf(403003));
        }
    }
}
