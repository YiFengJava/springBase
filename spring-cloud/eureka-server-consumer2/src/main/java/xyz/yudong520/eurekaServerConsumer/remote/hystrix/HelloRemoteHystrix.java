package xyz.yudong520.eurekaServerConsumer.remote.hystrix;

import org.springframework.stereotype.Component;
import xyz.yudong520.eurekaServerConsumer.remote.HelloRemote;

@Component
public class HelloRemoteHystrix implements HelloRemote {
    @Override
    public String hello(String name) {
        return "服务提供者断开了";
    }
}
