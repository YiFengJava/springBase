package xyz.yudong520.eurekaServerConsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.yudong520.eurekaServerConsumer.remote.HelloRemote;

@RestController
public class ConsumerController {

    @Autowired
    private HelloRemote helloRemote;

    @Value("${yd}")
    private String yd;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.hello(name);
    }

    @GetMapping("/abc")
    public String jij(){
        return yd;
    }

}