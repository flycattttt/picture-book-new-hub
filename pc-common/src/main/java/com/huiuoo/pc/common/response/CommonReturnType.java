package com.huiuoo.pc.common.response;

import lombok.Data;

@Data
public class CommonReturnType {

    private Integer code;
    private String message;
    private Object data;

    public static CommonReturnType create(Object data) {
        return CommonReturnType.create(200, "success", data);
    }

    public static CommonReturnType create(Integer code, String message, Object data) {
        CommonReturnType com = new CommonReturnType();
        com.setCode(code);
        com.setMessage(message);
        com.setData(data);
        return com;
    }
}
