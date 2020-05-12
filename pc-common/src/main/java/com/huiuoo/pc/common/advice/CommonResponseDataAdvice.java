package com.huiuoo.pc.common.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huiuoo.pc.common.error.EmBusinessError;
import com.huiuoo.pc.common.response.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = {"com.huiuoo.pc"}) // 注意哦，这里要加上需要扫描的包
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是CommonResponse那就没有必要进行额外的操作，返回false
        return !returnType.getGenericParameterType().equals(CommonResponse.class);
    }

    @SuppressWarnings("all")
    @Override
    public Object beforeBodyWrite(Object data,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        CommonResponse<Object> response = new CommonResponse<>(EmBusinessError.SUCCESS.getErrCode()
                ,EmBusinessError.SUCCESS.getErrMsg());

        // String类型不能直接包装，所以要进行些特别的处理
        if (methodParameter.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在CommonResponse里后，再转换为json字符串响应给前端
                response.setData(data);
                return objectMapper.writeValueAsString(response);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("返回String类型错误");
            }
        }

        if (null == data) {
            return response;
        } else if (data instanceof CommonResponse) {
            response = (CommonResponse<Object>) data;
        } else {
            response.setData(data);
        }

        return response;
    }
}
