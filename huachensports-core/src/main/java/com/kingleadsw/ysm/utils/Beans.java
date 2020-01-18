package com.kingleadsw.ysm.utils;

import java.util.List;
import java.util.Map;

import com.kingleadsw.ysm.base.Parameter;
import com.kingleadsw.ysm.base.dto.BaseDTO;
import com.kingleadsw.ysm.lang.Instances;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
/**
 * @Auther: zhoujie
 * @Description:
 */
@Configuration
@Order(-2147483648)
public class Beans
        implements ApplicationContextAware
{
    private static final Logger log = LogManager.getLogger(Beans.class);
    private static ApplicationContext app;

    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException
    {
        app = applicationContext;
    }

    public static <T> T getBean(Class<T> requiredType)
    {
        return app.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType)
    {
        return app.getBean(name, requiredType);
    }

    public static Object getBean(String name)
    {
        try
        {
            return app.getBean(name);
        }
        catch (Exception e) {}
        return null;
    }

    public static ApplicationContext getApplicationContext()
    {
        return app;
    }

    public static Parameter execute(Parameter parameter)
    {
        String no = parameter.getNo();
        log.info("{} request：{}", no, Jsons.toJson(parameter));
        Object service = app.getBean(parameter.getService());
        Long id = parameter.getId();
        BaseDTO model = parameter.getModel();
        List<?> list = parameter.getList();
        Map<?, ?> map = parameter.getMap();
        String method = parameter.getMethod();
        Object[] param = parameter.getParam();
        Object result = null;
        if (param != null) {
            result = Instances.invokeMethod(service, method, param);
        } else if (id != null) {
            result = Instances.invokeMethod(service, method, new Object[] { id });
        } else if (model != null) {
            result = Instances.invokeMethod(service, method, new Object[] { model });
        } else if (list != null) {
            result = Instances.invokeMethod(service, method, new Object[] { list });
        } else if (map != null) {
            result = Instances.invokeMethod(service, method, new Object[] { map });
        } else {
            result = Instances.invokeMethod(service, method, new Object[0]);
        }
        if (result != null)
        {
            Parameter response = new Parameter(result);
            log.info("{} response：{}", no, Jsons.toJson(response));
            return response;
        }
        log.info("{} response empty.", no);
        return null;
    }
}