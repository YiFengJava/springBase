package com.javaBeanBaseDome;

public class UserFunctionService {


    private FunctionService functionService;


    public FunctionService getFunctionService() {
        return functionService;
    }

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }

    public  String sayholle(){
       return this.functionService.sayHelloWorld();
    }
}
