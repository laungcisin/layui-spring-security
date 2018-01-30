package com.laungcisin.code;

import com.laungcisin.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.stereotype.Component;

/**
 * 短信发送器
 *
 * @author laungcisin
 */
@Component("smsCodeSender")
public class LaungcisinSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        //TODO:根据实际情况修改
        System.out.println("自定义短信发送器向手机" + mobile + "发送短信验证码:" + code);
    }

}
