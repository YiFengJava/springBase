package xyz.yudong520.manageadmin.system.controller.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueListenter implements ApplicationListener<ContextRefreshedEvent>{
                          //初始化完毕事件
    @Autowired
    private  MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("执行监听事件===================");
        new Thread(()->{
            while (true){
                if(StringUtils.isNotBlank(mockQueue.getPlaceOrder())){
                    String placeOrder = mockQueue.getPlaceOrder();
                    log.info("返回订单处理结果："+placeOrder);
                    deferredResultHolder.getMap().get(placeOrder).setResult("palec order success");
                    try {
                        mockQueue.setPlaceOrder(null);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
