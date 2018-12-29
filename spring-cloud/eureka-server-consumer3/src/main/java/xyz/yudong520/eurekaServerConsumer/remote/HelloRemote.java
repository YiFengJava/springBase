package xyz.yudong520.eurekaServerConsumer.remote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import xyz.yudong520.eurekaServerConsumer.remote.hystrix.HelloRemoteHystrix;

@FeignClient(name= "server-provider1",fallback=HelloRemoteHystrix.class)
public interface HelloRemote {
    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);
}