package com.huiuoo.pc.common.advice;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.CommonError;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.response.CommonResponse;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {

    // 定义ExceptionHandler解决未被Controller层吸收的Exception
    @ExceptionHandler(Exception.class)
    public CommonResponse<String> handlerException(Exception ex, HttpServletRequest request) {

        if (ex instanceof BusinessException) {
            //自定义的异常
            BusinessException business = (BusinessException) ex;
            return new CommonResponse<>(EmBusinessError.RESPONSE_ERROR,business.getErrMsg());
        }else if(ex instanceof HttpMessageNotReadableException){
            //参数缺失异常
            return new CommonResponse<>(EmBusinessError.REQUEST_PARAM_ERROR,"请求参数缺失");
        }else if (ex instanceof BindException){
            //validation校验参数未通过
            return new CommonResponse<>(EmBusinessError.REQUEST_PARAM_ERROR,((BindException) ex).getAllErrors().get(0).getDefaultMessage());
        }else if(ex instanceof HttpRequestMethodNotSupportedException){
            //请求方法类型错误
            return new CommonResponse<>(EmBusinessError.RESPONSE_ERROR,"请求方法类型错误");
        }else if(ex instanceof NullPointerException){
            //空指针异常
            return new CommonResponse<>(EmBusinessError.RESPONSE_ERROR,"空指针异常");
        }else if(ex instanceof QiniuException){
            //操作图片失败
            log.error("操作文件发生异常 请求URL:{},错误信息:{}",request.getRequestURI(),ex.getMessage());
            return new CommonResponse<>(EmBusinessError.RESPONSE_ERROR,"使用七牛云发生异常");
        }else{
            //未知错误
            log.error("未知错误 请求URL:{},错误信息:{}",request.getRequestURI(),ex.getMessage());
            ex.printStackTrace();
            return new CommonResponse<>(EmBusinessError.UN_KNOW_ERROR,ex.getMessage());
        }
    }
}
