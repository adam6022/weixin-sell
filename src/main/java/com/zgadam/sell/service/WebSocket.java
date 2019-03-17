package com.zgadam.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 描述:
 *
 * @author yd
 * @create 2019-03-17 20:49
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSets = new CopyOnWriteArraySet<>();

    @OnOpen
    public void opOpen(Session session){
        this.session = session;
        webSocketSets.add(this);
        log.info("【websocket消息】有新的连接，总数：{}", webSocketSets.size());
    }

    @OnClose
    public void onClose(){
        webSocketSets.remove(this);
        log.info("【websocket消息】连接断开，总数：{}", webSocketSets.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【websocket消息】收到客户端发来的消息：{}", message);
    }

    public void sendMessage(String message){
        for (WebSocket webSocket : webSocketSets){
            log.info("【websocket消息】广播消息，message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
