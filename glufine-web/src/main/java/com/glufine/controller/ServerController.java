package com.glufine.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.glufine.connect.server.MainServer;
import com.glufine.entity.message.Message;
import com.glufine.enums.Code;
/**
 * 控制启动类
 * @author syj
 *
 */
@Controller
@RequestMapping("/sever")
public class ServerController {
    @Autowired
    private MainServer mainServer;
    
    @RequestMapping("/start")
    public @ResponseBody Message doServer(int port,String password){
        try {
            mainServer.doServer(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Message.newCodeWithMessage(Code.START_ALADY);
    }
}
