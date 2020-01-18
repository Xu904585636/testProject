package com.kingleadsw.ysm.filters;


import com.kingleadsw.ysm.utils.Asserts;
import com.kingleadsw.ysm.utils.Jsons;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Log4j2
public final class Webs
{


    public static final void setSession(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        if (null != session) {
            session.setAttribute(key, value);
        }
    }

    public static final String getApplicationResource(String key, HttpServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
        return resourceBundle.getString(key);
    }

    public static final Map<String, Object> getParameterMap(HttpServletRequest request) {
        return WebUtils.getParametersStartingWith(request, null);
    }

    public static Map<String, Object> getParameter(HttpServletRequest request) {
        String wholeStr = "";
        try {
            BufferedReader br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                wholeStr = wholeStr + str;
            }
            if (Asserts.notNull(wholeStr)) {
                return (Map) Jsons.toObj(wholeStr, Map.class);
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return getParameterMap(request);
    }

    public static <T> T getParameter(HttpServletRequest request, Class<T> cls) {
        String wholeStr = "";
        try {
            BufferedReader br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                wholeStr = wholeStr + str;
            }
            if (Asserts.notNull(wholeStr)) {
                return Jsons.toObj(wholeStr, cls);
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return Request2Models.covert(cls, request);
    }

    public static <T> List<T> getParameters(HttpServletRequest request, Class<T> cls) {
        String wholeStr = "";
        try {
            BufferedReader br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                wholeStr = wholeStr + str;
            }
            if (Asserts.notNull(wholeStr)) {
                return (List) Jsons.toObj(wholeStr, List.class);
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return Request2Lists.covert(cls, request);
    }

    public static final String getHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if ((ip != null) && (ip.indexOf(",") > 0)) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        if ((Asserts.isNull(ip)) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((Asserts.isNull(ip)) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((Asserts.isNull(ip)) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if ((Asserts.isNull(ip)) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if ((Asserts.isNull(ip)) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("X-Real-IP");
        }
        if ((Asserts.isNull(ip)) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getRemoteAddr();
        }
        if (("127.0.0.1".equals(ip)) || ("0:0:0:0:0:0:0:1".equals(ip))) {
            InetAddress inet = null;
            try {
                inet = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                log.error("Unknown Host", e);
            }
            ip = inet.getHostAddress();
        }
        return ip;
    }
}
