package com.kingleadsw.ysm.interceptor;

import com.google.common.base.Objects;

import com.kingleadsw.ysm.caches.Cache;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.exception.IllegalParameterException;
import com.kingleadsw.ysm.props.ApplicationProps;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.utils.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class RepeatRequestProbe implements IRepeatRequestProbe {

    @Autowired
    Cache redisson;

    @Autowired
    ApplicationProps applicationProps;

    @Override
    public void addSerialNo(String serialNo) {

        String userId = RequestCache.userId().toString();
        Serializable serialNoDB = redisson.hget(getKey(), userId);
        if (Asserts.isNull(serialNoDB) || !Objects.equal(serialNoDB, serialNo)) {
            redisson.hset(getKey(), userId, serialNo);
            return;
        }

        throw new IllegalParameterException(ServerCode.Forbidden.REPEAT_REQUEST);
    }

    public String getKey() {
        return applicationProps.getApplicationName() + "repeat:request";
    }


}
