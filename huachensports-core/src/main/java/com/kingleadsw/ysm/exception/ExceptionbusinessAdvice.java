package com.kingleadsw.ysm.exception;

import com.kingleadsw.ysm.result.ResultVO;
import com.kingleadsw.ysm.sequence.Sequences;
import com.kingleadsw.ysm.utils.Dates;
import com.kingleadsw.ysm.utils.Statuss;
import com.kingleadsw.ysm.utils.Strings;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

@ControllerAdvice
@Log4j2
public class ExceptionbusinessAdvice {


    @ResponseBody
    @ExceptionHandler({BaseException.class})
    public ResultVO<Void> baseHandler(BaseException ex){

        int code = ex.getCode().intValue();
        String msg = Statuss.getMsg(Integer.valueOf(code));

        if (ex.getDesc()!=null) {
            msg = ex.getDesc();
        }
        if (ex.getParams() != null ) {
            msg = Strings.format(msg, ex.getParams());
        }

        ResultVO.ResultVOBuilder<Void> rs = ResultVO.builder();

        return rs.code(Integer.valueOf(code)).traceId(Sequences.getNo()).currentTime(Long.valueOf(Dates.now())).msg(msg).build();
    }
  //ConstraintViolationException

    @ResponseBody
    @ExceptionHandler({ConstraintViolationException.class})
    public ResultVO<Void> constraintvaildHandler(ConstraintViolationException ex){

        int code = 400001;

        Set<ConstraintViolation<?>> set =  ex.getConstraintViolations();

        StringBuilder sb = new StringBuilder();
        set.forEach(constraintViolation -> {

           sb.append(constraintViolation.getMessageTemplate());
        });

        log.error(getStackTrace(ex));

        ResultVO.ResultVOBuilder<Void> rs = ResultVO.builder();
        return rs.code(Integer.valueOf(code)).msg(sb.toString()).traceId(Sequences.getNo()).currentTime(Long.valueOf(Dates.now())).build();
    }

    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResultVO<Void> vaildHandler(MethodArgumentNotValidException ex){

        int code = 400001;

       BindingResult bindingResult =  ex.getBindingResult();
        StringBuilder errorMesssage=new StringBuilder();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage.append(fieldError.getDefaultMessage());
        }

        log.error(getStackTrace(ex));

        ResultVO.ResultVOBuilder<Void> rs = ResultVO.builder();
        return rs.code(Integer.valueOf(code)).msg(errorMesssage.toString()).traceId(Sequences.getNo()).currentTime(Long.valueOf(Dates.now())).build();
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResultVO<Void> errorHandler(Exception ex)
    {

        int code = 500001;
        log.error(getStackTrace(ex));
        String msg = Statuss.getMsg(Integer.valueOf(code));

        ResultVO.ResultVOBuilder<Void> rs = ResultVO.builder();
        return rs.code(Integer.valueOf(code)).msg(msg).traceId(Sequences.getNo()).currentTime(Long.valueOf(Dates.now())).build();
    }

    /**
     * 自动输出堆栈信息 线上屏蔽使用
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        return sw.toString();
    }


}
