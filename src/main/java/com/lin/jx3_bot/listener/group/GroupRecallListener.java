package com.lin.jx3_bot.listener.group;

import love.forte.common.ioc.annotation.Beans;
import love.forte.simbot.annotation.OnGroupMsgRecall;
import love.forte.simbot.api.message.assists.Flag;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.GroupMsgRecall;
import love.forte.simbot.api.message.events.MessageRecallEventGet;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.component.mirai.message.MiraiMessageCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author linyanhao
 */
@Service
public class GroupRecallListener {
    @OnGroupMsgRecall
    public void getRecallMessage(GroupMsgRecall groupMsgRecall,Sender sender,Flag flag){

        String info = "id:"+groupMsgRecall.getId()+"\n"+"text:"+groupMsgRecall.getText()+"\n"+"msg:"+groupMsgRecall.getMsg()
                +"type:"+groupMsgRecall.getGroupRecallType()+"\n"+"OriginalData："+groupMsgRecall.getOriginalData();
//        sender.sendGroupMsg(groupMsgRecall, info);
    }
}
