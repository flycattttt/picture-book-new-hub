package com.huiuoo.pc.db.vo;

import com.huiuoo.pc.common.constant.LoginType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @项目名称：picture-book-app-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/8
 * @version：V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequest {

    private String smsCode;
    private Integer loginType;
    private String identity;
    private String headImg;
    private String nickName;

    public boolean phoneValidate() {
        return StringUtils.isNotBlank(identity)
                && StringUtils.isNotBlank(smsCode);
    }

    public boolean oauthValidate() {
        return StringUtils.isNotBlank(String.valueOf(loginType))
                && StringUtils.isNotBlank(identity)
                && StringUtils.isNotBlank(headImg)
                && StringUtils.isNotBlank(nickName)
                && (loginType == LoginType.QQ.getType() || loginType == LoginType.WE_CHAT.getType());
    }
}
