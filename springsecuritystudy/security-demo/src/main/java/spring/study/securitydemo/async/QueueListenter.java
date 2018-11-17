package spring.study.securitydemo.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class QueueListenter implements ApplicationListener<ContextRefreshedEvent>{
                          //初始化完毕事件
    @Autowired
    private  MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;
    private Logger logger= LoggerFactory.getLogger(QueueListenter.class);
//ExceptionTranslationFilter
//    FilterSecurityInterceptor
//UsernamePasswordAuthenticationFilter
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        new Thread(()->{
            while (true){
                if(StringUtils.isNotBlank(mockQueue.getPlaceOrder())){
                    String placeOrder = mockQueue.getPlaceOrder();
                    logger.info("返回订单处理结果："+placeOrder);
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
