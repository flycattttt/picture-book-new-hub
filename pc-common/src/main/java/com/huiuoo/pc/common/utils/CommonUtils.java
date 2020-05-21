package com.huiuoo.pc.common.utils;

import com.huiuoo.pc.common.error.BusinessException;
import com.huiuoo.pc.common.error.EmBusinessError;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/18
 * @version：V1.0
 */
public class CommonUtils {

    public static String generateImageUrl(String fileName, Integer type) {

        StringBuilder buffer = new StringBuilder();
        buffer.append(imgNamePrefix(type));
        //命名格式 bg_202003111739548_951.webp
        buffer.append(DateUtil.getMsTime());
        buffer.append("_");

        Random ran = new Random();
        int num = ran.nextInt(999);
        buffer.append(String.format("%03d", num));

        // 追加文件名后缀
        buffer.append(fileName.substring(fileName.lastIndexOf(".")));
        return buffer.toString();
    }

    private static String imgNamePrefix(Integer eventType) {
        switch (eventType) {
            case 1:
                return "bg_";
            case 2:
                return "element_";
            case 3:
                return "model_";
            case 4:
                return "cover_";
            case 5:
                return "font_";
            default:
                return "other_";
        }
    }

    /**
     * 描述：获取 0~(num-1) 之间的int随机数
     */
    public static int getRandom(int num) throws BusinessException {
        if (num < 0) {
            return 0;
        }
        SecureRandom random = null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessError.RESPONSE_ERROR,"获取随机数失败");
        }
        return random.nextInt(num);
    }

    /**
     * 描述：随机返回字符串数组中的字符串
     */
    public static String randomName() {
        String[] names = {"灰灰", "小灰", "绘绘", "我是小画家", "故事少年", "绘太狼", "天才小作家", "灰灰s1vf3", "灰灰兔", "兔灰灰"};
        int random_index = (int) (Math.random() * names.length);
        return names[random_index];
    }

    /**
     * 描述：生成4位数短信验证码
     */
    public static String getSmsCode() {
        Random random = new Random();
        int i = random.nextInt(999);
        i += 1000;
        return String.valueOf(i);
    }

    /**
     * 验证手机号格式
     */
    public static void validatorPhone(String phone) throws BusinessException {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (StringUtils.isBlank(phone)) {
            throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR, "手机号不能为空");
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                throw new BusinessException(EmBusinessError.REQUEST_PARAM_ERROR, "请填入正确的手机号");
            }
        }
    }

    /**
     * 对字符串进行MD5加密
     */
    public static String md5(String value) {
        return DigestUtils.md5Hex(value).toUpperCase();
    }
}
