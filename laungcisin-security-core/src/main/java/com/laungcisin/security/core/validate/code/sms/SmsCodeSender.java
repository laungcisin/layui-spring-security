/**
 *
 */
package com.laungcisin.security.core.validate.code.sms;

/**
 * @author laungcisin
 */
public interface SmsCodeSender {

    void send(String mobile, String code);

}
