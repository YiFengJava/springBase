package xyz.yudong520.manageadmin.core.security.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping(value = "/code")
public class ValidateController {

    //
    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessor;

    //生成，保存，发送验证码接口
    @GetMapping(value = "/{codeType}")
    @ResponseBody
    public void getCode(@PathVariable(value = "codeType") String codeType, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        String key = codeType + ValidateCodeProcessor.class.getSimpleName();
        validateCodeProcessor.get(key).createValidateCode(new ServletWebRequest(request,response));
    }

}
