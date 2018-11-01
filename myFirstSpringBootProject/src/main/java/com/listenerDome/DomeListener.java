package com.listenerDome;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义监听器
 */
@Component
//实现ApplicationListener接口并指定监听事件
public class DomeListener implements ApplicationListener<DomeEvent>{

    @Override   //使用onApplicationEvent方法进行接受处理
    public void onApplicationEvent(DomeEvent domeEvent) {
        String msg = domeEvent.getMsg();
        System.out.println("我的DomeListener接收信息:"+msg);
    }
//    ApplicationStartingEvent
}
