package com.lin.jx3_bot.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.Group;
import com.lin.jx3_bot.entily.JxApiData;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import org.apache.commons.lang.StringUtils;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author linyanhao on 2021/8/18.
 * @version 1.0
 */
@Slf4j
@Component
public class Jx3ApiWebSocket {
    /**
     * https://blog.csdn.net/lizp_sing/article/details/107223897
     */
    private static final Logger logger = LoggerFactory.getLogger(Jx3ApiWebSocket.class);

    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<Jx3ApiWebSocket> webSocketSet = new CopyOnWriteArraySet<Jx3ApiWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        //在线数加1
        addOnlineCount();
//        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        logger.info("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("w");
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

        //群发消息
        for (Jx3ApiWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     * @OnError
     */
     public void onError(Session session, Throwable error) {
         logger.error("发生错误");
         error.printStackTrace();
     }


     public void sendMessage(String message) throws IOException {
         this.session.getBasicRemote().sendText(message);
     //this.session.getAsyncRemote().sendText(message);
     }


     /**
      * 群发自定义消息
      *
      */
    public static void sendInfo(String message) throws IOException {
        for (Jx3ApiWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        Jx3ApiWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        Jx3ApiWebSocket.onlineCount--;
    }
}
