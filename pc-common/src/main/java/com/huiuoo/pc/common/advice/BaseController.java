package com.huiuoo.pc.common.advice;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.response.CommonReturnType;
import com.qiniu.common.QiniuException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class BaseController {

	//public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";
	
	// 定义ExceptionHandler解决未被Controller层吸收的Exception
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.OK)
	@ResponseBody
	public Object handlerException(Exception ex, HttpServletRequest request) {

		Map<String, Object> dataObject = new HashMap<String, Object>();
		int code;
		String message;

		if (ex instanceof BusinessException) {
			//自定义的异常
			BusinessException business = (BusinessException) ex;
			code = business.getErrCode();
			message =business.getErrMsg();
			log.warn("全局业务处理异常 >> 自定义异常 请求URL:{},错误信息:{}",request.getRequestURI(),message);
		}else if(ex instanceof HttpMessageNotReadableException){
			//参数缺失异常
			code = EmBusinessError.REQUEST_PARAM_ERROR.getErrCode();
			message =EmBusinessError.REQUEST_PARAM_ERROR.getErrMsg();

			log.warn("全局业务处理异常 >> 参数缺失 请求URL:{},错误信息:{}",request.getRequestURI(),message);
		}else if(ex instanceof HttpRequestMethodNotSupportedException){
			//请求方法类型错误
			code = EmBusinessError.METHOD_NOT_SUPPORETD.getErrCode();
			message = EmBusinessError.METHOD_NOT_SUPPORETD.getErrMsg();

			log.warn("全局业务处理异常 >> 请求方法类型错误 请求URL:{},错误信息:{}",request.getRequestURI(),message);
		}else if(ex instanceof NullPointerException){
			//空指针异常
			code = EmBusinessError.NULL_POINTER.getErrCode();
			message = EmBusinessError.NULL_POINTER.getErrMsg();

			log.warn("全局业务处理异常 >> 空指针异常 请求URL:{},错误信息:{}",request.getRequestURI(),message);
		}else if(ex instanceof QiniuException){
			//空指针异常
			code = EmBusinessError.UPLOAD_FILE_ERROR.getErrCode();
			message = EmBusinessError.UPLOAD_FILE_ERROR.getErrMsg();
			log.warn("全局业务处理异常 >> 上传文件发生异常 请求URL:{},错误信息:{}",request.getRequestURI(),message);
		}else{
			//未知错误
			code = EmBusinessError.UNKNOW_ERROR.getErrCode();
			message = EmBusinessError.UNKNOW_ERROR.getErrMsg();
			log.warn("全局业务处理异常 >> 未知错误 请求URL:{},错误信息:{}",request.getRequestURI(),message);
		}
		return CommonReturnType.create(code, message,null);
	}

}
