/**
 *
 */
package com.imooc.security.core.validate.code.sms;

/**
 * @author imooc
 */
public interface SmsCodeSender {

    void send(String mobile, String code);

}
