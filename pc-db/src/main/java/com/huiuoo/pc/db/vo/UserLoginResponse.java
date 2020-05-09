package com.huiuoo.pc.db.vo;

import com.huiuoo.pc.db.dataobject.UserDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class UserLoginResponse {

    private UserDO userDO;
    private String token;
}
