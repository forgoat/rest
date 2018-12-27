package com.rest.websocket;

import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WebSocketController {
    @RequestMapping(value = "/pushVideoListToWeb",method =RequestMethod.POST,consumes = "application/json")
    public @ResponseBody Map<String,Object> pushVideoListToWeb(@RequestBody String param){
        Map<String,Object> result=new HashMap<String, Object>();
        try {
            WebSocketServer.sendInfo("有新客户呼入"+param);
            result.put("operateResults",true);
        }catch (IOException e){
            result.put("operateResults",true);
        }
        return result;
    }
}
