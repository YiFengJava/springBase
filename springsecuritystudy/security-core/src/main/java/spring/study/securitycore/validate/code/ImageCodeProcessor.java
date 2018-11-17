package spring.study.securitycore.validate.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import spring.study.securitycore.validate.image.ImageCode;
import javax.imageio.ImageIO;

@Component
public class ImageCodeProcessor extends  AbstractValidateCodeProcessor<ImageCode>{


    @Override
    protected void sendCode(ServletWebRequest request, ImageCode validateCode) throws  Exception{
        ImageIO.write(validateCode.getImge(),"JPG",request.getResponse().getOutputStream());
    }
}
