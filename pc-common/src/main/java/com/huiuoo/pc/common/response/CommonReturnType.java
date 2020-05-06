package com.huiuoo.pc.common.response;

import lombok.Data;

@Data
public class CommonReturnType {

    private Integer code;
    private String message;
    private Object data;

    public static CommonReturnType create(Object data) {
        CommonReturnType com = new CommonReturnType();
        com.setCode(200);
        com.setMessage("success");
        com.setData(data);
        return com;
    }

    public static CommonReturnType.Error error(int code,String errMsg) {
        CommonReturnType.Error err = new CommonReturnType.Error();
        err.setCode(code);
        err.setMessage(errMsg);
        return err;
    }

    @Data
    private static class Error{
        private Integer code;
        private String message;
    }

}
