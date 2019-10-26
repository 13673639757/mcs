package com.mcs.exception;

import com.mcs.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ControllerAdvice
 * @Description 异常捕捉类
 * @Author szq
 * @Date 2019/8/13 16:28
 * @Version 1.0
 */
@ResponseBody
@RestControllerAdvice
public class ControllerAdvice {
    private static final Logger log= LoggerFactory.getLogger(ControllerAdvice.class);
    @Autowired
    private HttpServletRequest request;
    @ExceptionHandler(value = Exception.class)
    public Result errorHandle(Exception e){
        StringBuilder stringBuilder=new StringBuilder();
        for (String key:request.getParameterMap().keySet()){
            stringBuilder.append("["+key+"="+request.getParameter("key"+"]"));
        }
        log.error(request.getRequestURI()+"-"+stringBuilder.toString(),e);
        return Result.createFailResult(e.getMessage());
    }
}
