package xyz.yudong520.manageadmin.core.security.validate.generate.image;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.yudong520.manageadmin.core.security.properties.SecurityCommon;
import xyz.yudong520.manageadmin.core.security.validate.code.image.ImageValidateCode;
import xyz.yudong520.manageadmin.core.security.validate.generate.ValidateCodeGenerater;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
@Component
@Data
public class ImageValidateCodeGenerater implements ValidateCodeGenerater {

    @Autowired
    private SecurityCommon securityCommon;

    @Override
    public ImageValidateCode generateValidateCode(HttpServletRequest request) {
        //
        int width = securityCommon.getValidateCode().getImageCode().getWidth();
        int height = securityCommon.getValidateCode().getImageCode().getHeight();
        int length = securityCommon.getValidateCode().getImageCode().getLength();
        int expireIn = securityCommon.getValidateCode().getImageCode().getExpireIn();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageValidateCode(sRand, expireIn,image);
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

}
