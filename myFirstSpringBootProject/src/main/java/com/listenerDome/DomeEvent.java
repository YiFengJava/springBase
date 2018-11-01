package com.listenerDome;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 */
public class DomeEvent  extends ApplicationEvent{

    private static final  long serialVersionUID=1L;

    private String msg;

    public DomeEvent(Object source,String msg) {
        super(source);
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
