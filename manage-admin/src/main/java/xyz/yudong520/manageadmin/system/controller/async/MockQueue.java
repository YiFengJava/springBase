package xyz.yudong520.manageadmin.system.controller.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockQueue {

    private String placeOrder;

    private String completeOrder;
    private Logger logger= LoggerFactory.getLogger(MockQueue.class);

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(()->{
            logger.info("接到下单请求，"+placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.placeOrder = placeOrder;
            logger.info("下单请求处理完成，"+placeOrder);
        }).start();


    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
