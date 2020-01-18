package com.kingleadsw.ysm.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;

import com.kingleadsw.ysm.base.dto.BaseDTO;
import com.kingleadsw.ysm.base.po.BasePO;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class Parameter
        implements Serializable
{
    private String service;
    private String method;
    private Object[] param;
    private Long id;
    private BaseDTO model;
    private Map<?, ?> map;
    private Page<?> page;
    private List<?> list;
    private Object result;
    private BaseDTO dto;

    private final String no = "[" + IdWorker.getId() + "]";

    public Parameter(String service, String method)
    {
        this.service = service;
        this.method = method;
    }

    public Parameter(Object result)
    {
        if ((result instanceof Long)) {
            this.id = ((Long)result);
        } else if ((result instanceof BasePO)) {
            this.model = ((BaseDTO)result);
        } else if ((result instanceof BaseDTO)) {
            this.dto = ((BaseDTO)result);
        } else if ((result instanceof Page)) {
            this.page = ((Page)result);
        } else if ((result instanceof Map)) {
            this.map = ((Map)result);
        } else if ((result instanceof List)) {
            this.list = ((List)result);
        } else if ((result instanceof Object[])) {
            this.param = ((Object[])result);
        } else {
            this.result = result;
        }
    }


    public Parameter() {}
}
