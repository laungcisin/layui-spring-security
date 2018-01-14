/**
 *
 */
package com.imooc.security.core.validate.code.image;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图形验证码生产器
 * @author laungcisin
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    /**
     * 系统配置
     */
    @Autowired
    private SecurityProperties securityProperties;

    /*
     * (non-Javadoc)
     * 生成图形校验码
     * @see
     * com.imooc.security.core.validate.code.ValidateCodeGenerator#generate(org.
     * springframework.web.context.request.ServletWebRequest)
     */
    @Override
    public ImageCode generate(ServletWebRequest request) {
        int x = 0, fontHeight = 0, codeY = 0;

        //请求级配置-->应用级配置-->默认配置
        // 图片宽度
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());
        // 图片高度
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());

        x = width / (securityProperties.getCode().getImage().getLength() + 2);//每个字符的宽度(左右各空出一个字符)
        fontHeight = height - 5;//字体的高度
        codeY = height - 7;

        // 图像image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();
        // 生成随机数
        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 创建字体
        g.setFont(new Font("Times New Roman", Font.ITALIC, fontHeight));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x0 = random.nextInt(width);
            int y0 = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x0, y0, x0 + xl, y0 + yl);
        }

        //记录产生的验证码
        StringBuffer sRand = new StringBuffer();

        // 随机产生length个字符的验证码
        for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, (i + 1) * x, codeY);
        }

        g.dispose();

        return new ImageCode(image, sRand.toString(), securityProperties.getCode().getImage().getExpireIn());
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }


}
