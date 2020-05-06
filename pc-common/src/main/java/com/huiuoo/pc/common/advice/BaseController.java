package com.huiuoo.pc.common.advice;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.response.CommonReturnType;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class BaseController{

	// 定义ExceptionHandler解决未被Controller层吸收的Exception
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public Object handlerException(Exception ex, HttpServletRequest request) {

		int code;
		String message;

		if (ex instanceof BusinessException) {
			//自定义的异常
			BusinessException business = (BusinessException) ex;
			return CommonReturnType.error(business.getErrCode(),business.getErrMsg());
		}else if(ex instanceof HttpMessageNotReadableException){
			//参数缺失异常
			return CommonReturnType.error(EmBusinessError.REQUEST_PARAM_ERROR.getErrCode()
					,EmBusinessError.REQUEST_PARAM_ERROR.getErrMsg());

		}else if (ex instanceof BindException){
			//validation校验参数未通过
			code = EmBusinessError.REQUEST_PARAM_ERROR.getErrCode();
			message = ((BindException) ex).getAllErrors().get(0).getDefaultMessage();
			return CommonReturnType.error(code,message);

		}else if(ex instanceof HttpRequestMethodNotSupportedException){
			//请求方法类型错误
			return CommonReturnType.error(EmBusinessError.METHOD_NOT_SUPPORETD.getErrCode()
					,EmBusinessError.METHOD_NOT_SUPPORETD.getErrMsg());

		}else if(ex instanceof NullPointerException){
			//空指针异常
			return CommonReturnType.error(EmBusinessError.NULL_POINTER.getErrCode()
					,EmBusinessError.NULL_POINTER.getErrMsg());

		}else if(ex instanceof QiniuException){
			//操作图片失败
			log.error("操作文件发生异常 请求URL:{},错误信息:{}",request.getRequestURI(),ex.getMessage());
			ex.printStackTrace();
			return CommonReturnType.error(EmBusinessError.UPLOAD_FILE_ERROR.getErrCode()
					,EmBusinessError.UPLOAD_FILE_ERROR.getErrMsg());

		}else{
			//未知错误
			log.error("未知错误 请求URL:{},错误信息:{}",request.getRequestURI(),ex.getMessage());
			ex.printStackTrace();
			return CommonReturnType.error(EmBusinessError.UNKNOW_ERROR.getErrCode()
					,EmBusinessError.UNKNOW_ERROR.getErrMsg());
		}
	}
}
