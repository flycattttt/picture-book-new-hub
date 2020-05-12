package com.huiuoo.pc.common.response;

import com.huiuoo.pc.common.error.EmBusinessError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public CommonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonResponse(EmBusinessError error, T data) {
        this.code = error.getErrCode();
        this.message = error.getErrMsg();
        this.data = data;
    }
}
