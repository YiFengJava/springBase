package xyz.yudong520.manageadmin.system.controller.advice;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.ControllerAdviceBean;
import org.springframework.web.servlet.ModelAndView;
import xyz.yudong520.manageadmin.common.util.R;
import xyz.yudong520.manageadmin.system.controller.exception.NotAuthorityHtmlException;
import xyz.yudong520.manageadmin.system.controller.exception.NotAuthorityJsonException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

    /**
     * 捕捉全局的Exception异常，返回一个异常页面
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", e.getMessage());
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("exception.html");
        return mav;
    }
}
