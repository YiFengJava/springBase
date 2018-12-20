package xyz.yudong520.manageadmin.core.security.validate.code;

import xyz.yudong520.manageadmin.core.security.properties.Commom;

public enum ValidateType {
    
    SMS{
        @Override
        public String getParamNameOnvalidete() {
            return Commom.LOGIN_VALIDATE_CODE_SMS;
        }
    },
    IMAGE{
        @Override
        public String getParamNameOnvalidete() {
            return Commom.LOGIN_VALIDATE_CODE_IMAGE;
        }
    };
    public abstract String getParamNameOnvalidete();
}
