package com.example.demo.controller;

import org.springframework.stereotype.Component;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {
    private static int onlineCount=0;
    //set线程安全，用来存放每个用户端对应的websocket对象
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet=new CopyOnWriteArraySet<>();
    //与某个客户端的连接对话，用于给客户端发送信息
    private Session session;

    public void sendMessage(String message)throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }

    public static synchronized void addOnlineCount(){
        WebSocketServer.onlineCount++;
    }

    public static synchronized  void subOnlineCount(){
        WebSocketServer.onlineCount--;
    }

    public static void sendInfo(String message) throws IOException{
        System.out.println(message);
        for(WebSocketServer item:webSocketSet){
            try{
                item.sendMessage(message);
            }catch (IOException e){
                continue;
            }
        }
    }

    @OnOpen
    public void onOpen(Session session)throws IOException{
        this.session=session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("有新连接加入,当前连接人数为: "+getOnlineCount());
        try{
            sendMessage("连接成功");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
