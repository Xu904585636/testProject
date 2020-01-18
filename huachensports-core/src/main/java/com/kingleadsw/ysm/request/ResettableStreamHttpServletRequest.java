package com.kingleadsw.ysm.request;


import com.kingleadsw.ysm.utils.Ios;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * @Auther: zhoujie
 * @Description:
 */
public class ResettableStreamHttpServletRequest
        extends HttpServletRequestWrapper
{
    private byte[] rawData;
    private String uri;
    private HttpServletRequest request;
    private ResettableServletInputStream servletStream;
    private Map<String, String[]> params;
    private Map<String, String> overridedHeaders;

    public ResettableStreamHttpServletRequest(HttpServletRequest request)
    {
        super(request);
        this.request = request;
        this.servletStream = new ResettableServletInputStream();
        this.overridedHeaders = new HashMap();
    }

    public void overrideContentType(String contentType)
    {
        Enumeration enumeration = super.getHeaderNames();
        while (enumeration.hasMoreElements())
        {
            String name = (String)enumeration.nextElement();
            this.overridedHeaders.put(name, this.request.getHeader(name));
        }
        this.overridedHeaders.put("Content-Type".toLowerCase(), contentType);
    }

    public String getContentType()
    {
        String contentType = (String)this.overridedHeaders.get("Content-Type".toLowerCase());
        if (contentType != null) {
            return contentType;
        }
        return super.getContentType();
    }

    public String getParameter(String name)
    {
        String result = "";

        Object v = this.params.get(name);
        if (v == null)
        {
            result = null;
        }
        else if ((v instanceof String[]))
        {
            String[] strArr = (String[])v;
            if (strArr.length > 0) {
                result = strArr[0];
            } else {
                result = null;
            }
        }
        else if ((v instanceof String))
        {
            result = (String)v;
        }
        else
        {
            result = v.toString();
        }
        return result;
    }

    public Map<String, String[]> getParameterMap()
    {
        return this.params;
    }

    public void setParameterMap(Map<String, String[]> newParams)
    {
        this.params = newParams;
    }

    public Enumeration getParameterNames()
    {
        return new Vector(this.params.keySet()).elements();
    }

    public String getHeader(String name)
    {
        String headerValue = (String)this.overridedHeaders.get(name);
        if ((headerValue != null) && (!headerValue.isEmpty())) {
            return headerValue;
        }
        return super.getHeader(name);
    }

    public Enumeration<String> getHeaders(String name)
    {
        return super.getHeaders(name);
    }

    public String[] getParameterValues(String name)
    {
        String[] result = null;

        Object v = this.params.get(name);
        if (v == null) {
            result = null;
        } else if ((v instanceof String[])) {
            result = (String[])v;
        } else if ((v instanceof String)) {
            result = new String[] { (String)v };
        } else {
            result = new String[] { v.toString() };
        }
        return result;
    }

    public void resetInputStream()
    {
        this.servletStream.stream = new ByteArrayInputStream(this.rawData);
    }

    public void resetInputStream(byte[] newData)
    {
        this.servletStream.stream = new ByteArrayInputStream(newData);
    }

    public Enumeration<String> getHeaderNames()
    {
        return super.getHeaderNames();
    }

    public ServletInputStream getInputStream()
            throws IOException
    {
        if (this.rawData == null)
        {
            this.rawData = Ios.toByteArray(this.request.getReader(), Charset.defaultCharset());
            if (this.rawData != null) {
                this.servletStream.stream = new ByteArrayInputStream(this.rawData);
            }
        }
        return this.servletStream;
    }

    public String getRequestURI()
    {
        return this.uri;
    }

    public void setRequestURI(String urix)
    {
        this.uri = urix;
    }

    public BufferedReader getReader()
            throws IOException
    {
        this.request.setCharacterEncoding("utf-8");
        if (this.rawData == null)
        {
            this.rawData = Ios.toByteArray(this.request.getReader(), Charset.defaultCharset());
            if (this.rawData == null) {
                return null;
            }
            this.servletStream.stream = new ByteArrayInputStream(this.rawData);
        }
        return new BufferedReader(new InputStreamReader(this.servletStream));
    }

    private class ResettableServletInputStream
            extends ServletInputStream
    {
        private InputStream stream;

        private ResettableServletInputStream() {}

        public int read()
                throws IOException
        {
            return this.stream.read();
        }

        public boolean isFinished()
        {
            return true;
        }

        public boolean isReady()
        {
            return true;
        }

        public void setReadListener(ReadListener readListener) {}
    }
}
