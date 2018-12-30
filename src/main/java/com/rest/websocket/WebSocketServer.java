package com.rest.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {
    private static int onlineCount=0;//当前在线连接数
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet=new CopyOnWriteArraySet<WebSocketServer>();
    //线程安全set，用来存放每个用户端对应的websocket对象
    private Session session;//与某个客户端的连接对话，用于给客户端发送消息

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
        System.out.println(message);
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }


    //连接建立成功调用方法
    @OnOpen
    public void onOpen(Session session){
        this.session=session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("有新连接加入,当前连接人数为: "+getOnlineCount());
        try {
            sendMessage("连接成功");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为 "+getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("来自客户端的消息"+message);
        for (WebSocketServer webSocketServer:webSocketSet){
            try{
                webSocketServer.sendMessage(message);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }


}
