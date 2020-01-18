package com.kingleadsw.ysm.base.vo;


import com.kingleadsw.ysm.result.ResultVO;
import com.kingleadsw.ysm.sequence.Sequences;
import com.kingleadsw.ysm.utils.Dates;
import com.kingleadsw.ysm.utils.Statuss;

public class ResultVOBuilder
{
    public static <E> ResultVO<E> ok()
    {
        return result(200001);
    }

    public static <E> ResultVO<E> result(int code)
    {
        return result(code, null);
    }

    public static <E> ResultVO<E> ok(E data)
    {
        return result(200001, data);
    }

    public static <E> ResultVO<E> result(int code, E data)
    {
        ResultVO.ResultVOBuilder<E> r = ResultVO.builder();
        return r.code(Integer.valueOf(code)).msg(Statuss.getMsg(Integer.valueOf(code))).data(data).currentTime(Long.valueOf(Dates.now())).traceId(Sequences.getNo()).build();
    }
}