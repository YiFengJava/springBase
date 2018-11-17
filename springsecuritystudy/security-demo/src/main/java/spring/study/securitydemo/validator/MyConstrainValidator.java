package spring.study.securitydemo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import spring.study.securitydemo.service.HelloService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstrainValidator implements ConstraintValidator<MyConstraint,Object> {
//实现这个借口 可以直接在类里注入 spring的bean   不用在类上加@Componet
    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String greeting = helloService.greeting("测试");
        System.out.println(greeting);
        System.out.println(value);
        return false;
    }
}
