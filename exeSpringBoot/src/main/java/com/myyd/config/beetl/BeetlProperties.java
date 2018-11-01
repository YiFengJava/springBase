package com.myyd.config.beetl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Data
@Component
@ConfigurationProperties(prefix = BeetlProperties.PREFIX)
public class BeetlProperties {
    public static final  String PREFIX="beetl";

    private String delimiterStatementStart;

    private String delimiterStatementEnd;

    private String resourceTagroot;

    private String resourceTagsuffix;

    private String resourceAutoCheck;

    private String resourceFunctionSuffix;

    public Properties getProperties(){
        Properties properties = new Properties();
        if(!isNullEmpety(delimiterStatementStart)){
            properties.setProperty("DELIMITER_STATEMENT_START",delimiterStatementStart);
        }
        if(!isNullEmpety(delimiterStatementEnd)){
            properties.setProperty("DELIMITER_STATEMENT_END",delimiterStatementEnd);
        }else{
            properties.setProperty("DELIMITER_STATEMENT_END","null");
        }
        if(!isNullEmpety(resourceTagroot)){
            properties.setProperty("RESOURCE.tagRoot",resourceTagroot);
        }
        if(!isNullEmpety(resourceTagsuffix)){
            properties.setProperty("RESOURCE.tagSuffix",resourceTagsuffix);
        }
        if(!isNullEmpety(resourceAutoCheck)){
            properties.setProperty("RESOURCE.autoCheck",resourceAutoCheck);
        }
        if(!isNullEmpety(resourceFunctionSuffix)){
            properties.setProperty("RESOURCE.functionSuffix",resourceFunctionSuffix);
        }
        return properties;
    }


    private boolean isNullEmpety(Object ob){
        if(ob==null){
            return  true;
        } else if(ob.toString().trim().equals("")){
            return true;
        }else if(ob.toString().trim().equals("null")){
            return  true;
        }else {
            return  false;
        }
    }
}
