package xyz.yudong520.manageadmin.core.valid;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.yudong520.manageadmin.system.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyValidator implements ConstraintValidator<MyValid,Object> {
    //实现这个借口 可以直接在类里注入 spring的bean   不用在类上加@Componet
    @Autowired
    private UserService userService;
    @Override
    public void initialize(MyValid constraintAnnotation) {
        System.out.println("my validator init");
    }
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("isValid");
        return true;
    }
}
