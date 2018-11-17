package spring.study.securitydemo.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@RestController
public class AsyncController {

    private Logger logger=LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private MockQueue mockQueue;

@Autowired
private DeferredResultHolder deferredResultHolder;

    @RequestMapping(value = "/order")
    public String order() throws  Exception{
        logger.info("主线程开始");
        Thread.sleep(1000);
        logger.info("主线程返回");
        return  "SUCCESS";
    }


    @RequestMapping(value = "/order1")
    public Callable<String> order1() throws  Exception{
        logger.info("主线程开始");
        Callable<String> stringCallable=new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("副线程开始");
                Thread.sleep(1000);
                logger.info("副线程返回");
                return  "SUCCESS";
            }
        } ;

        logger.info("主线程返回");
       return stringCallable;
    }

    @RequestMapping(value = "/order2")
    public DeferredResult<String> order2() throws  Exception{
        logger.info("主线程开始");
        String random = RandomStringUtils.random(8);
        mockQueue.setPlaceOrder(random);
        DeferredResult<String> result = new DeferredResult<>();
        Map<String,DeferredResult<String>> map=new HashMap<>();
        map.put(random,result);
        deferredResultHolder.setMap(map);
        Thread.sleep(1000);
        logger.info("主线程返回");
        return  result;
    }

}
