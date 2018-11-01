package com.aopDome;

import org.springframework.stereotype.Service;

@Service
public class AopAnnotationService {

   @MyAction(name = "注解拦截add方法")
   public void add(){}
}
