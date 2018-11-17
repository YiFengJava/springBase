package spring.study.securitycore.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import spring.study.securitycore.properties.SecurityProperties;
import spring.study.securitycore.validate.image.ImageCode;
import spring.study.securitycore.validate.sms.SmsCodeSender;
import spring.study.securitycore.validate.sms.ValidateCode;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 验证码接口
 */
@RestController
public class ValidateController {

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    public static final  String SESSION_KEY="SESSION_KEY_IMAGE_CODE";

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessors;

//    @GetMapping(value = "/code/image")
//    public void  createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        ImageCode imageCode=(ImageCode)imageCodeGenerator.createValidateCode(request);
//        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
//        ImageIO.write(imageCode.getImge(),"JPG",response.getOutputStream());
//    }

//    @GetMapping(value = "/code/sms")
//    public void  createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
//        ValidateCode validateCode = smsCodeGenerator.createValidateCode(request);
//        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,validateCode);
////        ImageIO.write(imageCode.getImge(),"JPG",response.getOutputStream());
//        String mobile = ServletRequestUtils.getStringParameter(request, "mobile");
//        smsCodeSender.send(mobile,validateCode.getCode());
//    }

    //验证码接口
    @GetMapping(value = "/code/{type}")
    public void  ctreateCode(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws Exception {
        validateCodeProcessors.get(type+"CodeProcessor").createCode(new ServletWebRequest(request,response));
    }


}
