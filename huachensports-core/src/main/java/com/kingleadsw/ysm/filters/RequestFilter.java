package com.kingleadsw.ysm.filters;

import com.google.common.base.Charsets;
import com.kingleadsw.ysm.http.HttpHead;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.request.RequestPayload;
import com.kingleadsw.ysm.request.ResettableStreamHttpServletRequest;
import com.kingleadsw.ysm.utils.Asserts;
import com.kingleadsw.ysm.utils.Dates;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;


public class RequestFilter extends GenericFilterBean {

    private static final Logger log = LogManager.getLogger(RequestFilter.class);

    private String name;

    public RequestFilter(String name) {
        this.name = name;
    }

    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String token = request.getHeader(HttpHead.TOKEN.getCode());
        String serialNo = request.getHeader(HttpHead.SERIAL_NO.getCode());
        String ip = Webs.getHost(request);

        String uri = request.getRequestURI();
        String contentType = request.getHeader("Content-Type");
        String queryString = request.getQueryString();
        String originJson = "";
        Long startTime = Dates.now();
        String method = request.getMethod().toUpperCase();
        String userAgent = request.getHeader("User-Agent");
        Byte deviceType = this.getDeviceType(request);
        String url = this.getUrl(uri, queryString);
        String channelSource = request.getHeader(HttpHead.CHANNEL_SOURCE.getCode());
        String traceId = request.getHeader(HttpHead.TRACE_ID.getCode());
        if (Asserts.isNull(traceId)) {
            traceId = ip + "_" + Dates.now() + "S";
        }

        Thread.currentThread().setName(traceId);
        if (contentType != null && contentType.toLowerCase().contains("application/json")) {
            ResettableStreamHttpServletRequest requestWrapper = new ResettableStreamHttpServletRequest(request);
            requestWrapper.setCharacterEncoding(Charsets.UTF_8.name());
            StringBuffer json = new StringBuffer();
            BufferedReader reader = requestWrapper.getReader();

            try {
                if (reader != null) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        json.append(line);
                    }

                    originJson = json.toString();
                    if (originJson.isEmpty()) {
                        originJson = "";
                    }
                }

                requestWrapper.resetInputStream(originJson.getBytes(Charsets.UTF_8));
                requestWrapper.overrideContentType(contentType);
                requestWrapper.setParameterMap(request.getParameterMap());
                requestWrapper.setRequestURI(uri);
                req = requestWrapper;
            } finally {
                if (Collections.singletonList(reader).get(0) != null) {
                    reader.close();
                }

            }
        }

        RequestCache.set(RequestPayload.builder().deviceType(deviceType).channelSource(channelSource).url(url).traceId(traceId).userAgent(userAgent).ip(ip).token(token).serialNo(serialNo).uri(uri).contentType(contentType).queryString(queryString).reqBody(originJson).startTime(startTime).method(method).platformCode(this.name).build());

        try {
            chain.doFilter(req, response);
        } finally {
            RequestCache.clean();
        }

    }

    private String getUrl(String uri, String query) {
        String query_ = "";
        if (Asserts.notNull(query)) {
            query_ = "?" + query;
        }

        return uri + query_;
    }

    private Byte getDeviceType(HttpServletRequest request) {
        String deviceTypeStr = request.getHeader(HttpHead.DEVICE_TYPE.getCode());
        return Asserts.isNull(deviceTypeStr) ? 0 : Byte.valueOf(deviceTypeStr);
    }
}