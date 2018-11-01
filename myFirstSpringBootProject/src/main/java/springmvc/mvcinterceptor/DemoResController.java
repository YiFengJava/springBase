package springmvc.mvcinterceptor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//1  不用@ResponseBody
@RequestMapping(value = "/rest")
public class DemoResController {

    @RequestMapping(value = "/getJson",produces = {"application/json;charset=UTF-8"}) //2 返回数据的媒体为json
    public DemoObj getJson(DemoObj obj){
        return  new DemoObj(obj.getId()+1,obj.getName()+"yy"); //3 直接返回对象
    }

    @RequestMapping(value = "/getXml",produces = {"application/xml;charset=UTF-8"}) //4  返回数据的媒体为xml
    public DemoObj getXml(DemoObj obj){
        return  new DemoObj(obj.getId()+1,obj.getName()+"yy"); //5
    }
}
