/**
 *
 */
package com.laungcisin.security.core.validate.code.sms;

/**
 * 短信发送器
 * @author laungcisin
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    /* (non-Javadoc)
     * @see com.imooc.security.core.validate.code.sms.SmsCodeSender#send(java.lang.String, java.lang.String)
     */
    @Override
    public void send(String mobile, String code) {
        //TODO:根据实际情况修改
        System.out.println("向手机" + mobile + "发送短信验证码" + code);
    }

}
