package xyz.yudong520.manageadmin.core.security.validate.abvalidate.image;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import xyz.yudong520.manageadmin.core.security.validate.abvalidate.AbstractValidateCodeProcessor;
import xyz.yudong520.manageadmin.core.security.validate.code.image.ImageValidateCode;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageValidateCode> {
    @Override
    protected void sendValidateCode(ServletWebRequest request, ImageValidateCode validateCode) throws IOException {
        BufferedImage image = validateCode.getImage();
        HttpServletResponse response = request.getResponse();
        ImageIO.write(image,"JPG",response.getOutputStream());
    }


}
