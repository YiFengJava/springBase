package springmvc.mvcinterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller //声明是一个控制类
@RequestMapping(value = "/anno")  //2 映射该类的访问路径
public class DemoController {

    @Autowired
    PushService pushService; // ①定时任务，定时更新DeferredResulto

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall() {
        return pushService.getAsyncUpdate(); // ②返回给客户端DeferredResulto
    }


    /**
     * ①注意，这里使用输出的媒体类型为text/event-stream ，这是服务器端SSE 的支持，
     * 本例演示每5 秒钟向浏览器推送随机消息。
     */
    @RequestMapping(value = "/push", produces = "text/event-stream;charset=UTF-8")
    public @ResponseBody
    String push() {
        Random r = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "data:Testing 1,2,3,...," + r.nextInt() + "\n\n";
    }


    @RequestMapping(value = "/a")
    public String jkjkfd(){
        return "converter";
    }
    @RequestMapping(value = "/b")
    public String jkjkfddfds(){
        return "async";
    }
    @RequestMapping(value = "/c")
    public String jkjkfddasda(){
        return "see";
    }


    @RequestMapping("/index")  //写一个Mapping映射
    public String hello(){
        return  "index";
    }

    @RequestMapping(produces = "text/plain;charset=UTF-8")//3 未标明路经则用类的路径，produces可定制返回的response的
//    媒体类型和字符集
    public @ResponseBody String index(HttpServletRequest request){ //4可接受HttpServletResquest作为参数也可以接受HttpServletRespeponse作为参数
//         此处的@ResponseBody用在返回值前面
        return  "url:"+ request.getRequestURL()+"can access";
    }

    @RequestMapping(value = "/pathvar/{str}",produces ="text/plain;charset=UTF-8" ) //5  接受路径参数
    public @ResponseBody String demoPathVar(@PathVariable String str ,HttpServletRequest request){
        return  "url:"+ request.getRequestURL()+"can access,str:"+str;
    }

    @RequestMapping(value = "/requestParam",
            produces ="text/plain;charset=UTF-8" ) //6  常规的参数获取 ？id=1
    public @ResponseBody String passRequestParam(Long id,HttpServletRequest request){
        return  "url:"+ request.getRequestURL()+"can access,id:"+id;
    }

    @RequestMapping(value = "/obj",
            produces ="application/json;charset=UTF-8" ) //7  对象参数
    @ResponseBody //8  也可以用在方法上
    public String passObj(DemoObj obj,HttpServletRequest request){
        return  "url:"+ request.getRequestURL()+"can access,obj id:"+obj.getId()+ ",obj name:"+ obj.getName();
    }

    @RequestMapping(value = {"/name1","/name2"},
            produces ="application/json;charset=UTF-8" ) //9  映射不同路径到相同的方法上
    public @ResponseBody String remove(HttpServletRequest request){
        return  "url:"+ request.getRequestURL()+"can access";
    }

    /**
     * 指定返回的媒体类型为我们自定义的媒体类型application/x惆wisely
     */
    @RequestMapping(method = RequestMethod.POST, value = "/convert",
            produces = "application/x-wisely")
    public @ResponseBody
    DemoObj converter(@RequestBody DemoObj obj) {
        return obj;
    }
}
