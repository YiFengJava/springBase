package xyz.yudong520.manageadmin.system.controller.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import xyz.yudong520.manageadmin.common.util.R;
import xyz.yudong520.manageadmin.system.controller.exception.NotAuthorityHtmlException;
import xyz.yudong520.manageadmin.system.controller.exception.NotAuthorityJsonException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NotAuthorityExceptionHandler {

    @ExceptionHandler(value = NotAuthorityHtmlException.class)
    public ModelAndView notAuthorityHtmlException(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", e.getMessage());
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("not_authorized.html");
        return mav;
    }

    @ExceptionHandler(value = NotAuthorityJsonException.class)
    @ResponseBody
    public R notAuthorityJsonException(HttpServletRequest req, Exception e) throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("message",e.getMessage());
        map.put("url",req.getRequestURL());
        map.put("exception",e);
        return R.build(HttpStatus.UNAUTHORIZED.value(),"用户没有权限",map);
    }

}
