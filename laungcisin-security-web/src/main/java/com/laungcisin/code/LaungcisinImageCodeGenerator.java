/**
 *
 */
package com.laungcisin.code;

import com.google.code.kaptcha.Producer;
import com.laungcisin.security.core.properties.SecurityProperties;
import com.laungcisin.security.core.validate.code.generator.ValidateCodeGenerator;
import com.laungcisin.security.core.validate.code.image.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

/**
 * @author imooc
 */
@Component("imageValidateCodeGenerator")
public class LaungcisinImageCodeGenerator implements ValidateCodeGenerator {
    /**
     * 系统配置
     */
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private Producer producer;

    @Override
    public ImageCode generate(ServletWebRequest request) {
        // 图片宽度
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
        // 图片高度
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);

        // 图像image
//        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        return new ImageCode(image, text, securityProperties.getCode().getImage().getExpireIn());
    }

}
