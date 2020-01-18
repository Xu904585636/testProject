package com.kingleadsw.ysm.http;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;

import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.request.RequestPayload;
import com.kingleadsw.ysm.utils.Jsons;
import com.kingleadsw.ysm.utils.Strings;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * @author  zhoujie
 */
public class EventLogJackson2HttpMessageConverter
        extends MappingJackson2HttpMessageConverter
{
    protected void writePrefix(JsonGenerator generator, Object object)
            throws IOException
    {
        super.writePrefix(generator, object);
        RequestPayload req = RequestCache.get();
        if (req != null)
        {
            String resBody = Strings.substring(Jsons.toJson(object), 0, 2040);
            req.setResBody(resBody);
        }
    }
}
