package com.kingleadsw.ysm.base.controller;


import com.kingleadsw.ysm.base.vo.ResultVOBuilder;
import com.kingleadsw.ysm.result.ResultVO;

public abstract class BaseController {
    public BaseController() {
    }

    public <E> ResultVO<E> success() {
        return ResultVOBuilder.ok();
    }

    public <E> ResultVO<E> success(int code) {
        return (ResultVO<E>) ResultVOBuilder.result(code, (Object)null);
    }

    public <E> ResultVO<E> success(E data) {
        return ResultVOBuilder.result(200001, data);
    }

    public <E> ResultVO<E> success(int code, E data) {
        return ResultVOBuilder.result(code, data);
    }
}
