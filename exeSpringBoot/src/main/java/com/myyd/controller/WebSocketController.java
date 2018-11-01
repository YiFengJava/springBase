package com.myyd.controller;

import com.myyd.activemq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    @Autowired
    private Producer producer;
    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav=new ModelAndView("/socket.html");
        mav.addObject("cid", cid);
        return mav;
    }


//    //推送数据接口
//    @ResponseBody
//    @RequestMapping("/socket/push/{cid}")
//    public ApiReturnObject pushToWeb(@PathVariable String cid,String message) {
//        try {
//            WebSocketServer.sendInfo(message,cid);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ApiReturnUtil.error(cid+"#"+e.getMessage());
//        }
//        return ApiReturnUtil.success(cid);
//    }



    @GetMapping("/activeMq")
    @ResponseBody
    public void activeMq() throws  Exception{
        this.producer.send("Test message");
        return;
    }

    public static void main(String[] args) throws IOException{
        String path="cmd /c CScript C:\\Users\\Administrator\\Desktop\\ss.vbs";
        java.lang.Runtime.getRuntime().exec(path);
//        String[] cpCmd  = new String[]{"wscript", "path"};
//        Runtime.getRuntime().exec(cpCmd);
    }


}
