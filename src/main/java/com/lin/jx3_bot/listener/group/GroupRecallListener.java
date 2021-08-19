package com.lin.jx3_bot.listener.group;

import love.forte.common.ioc.annotation.Beans;
import love.forte.simbot.annotation.OnGroup;
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
    @OnGroup
    public void getInfo(GroupMsg groupMsg,Sender sender){
        if(groupMsg.getId().contains("REC-")){
            groupMsg.getId().replace("REC-", "");
            sender.sendGroupMsg(groupMsg, groupMsg.getMsgContent().getMsg());
        }
//        String info = groupMsg.getId();
    }
//    @OnGroupMsgRecall
//    public void getRecallMessage(GroupMsgRecall groupMsgRecall,Sender sender){
//        String info = groupMsgRecall.getId().replace("REC-", "");
//        sender.sendGroupMsg(groupMsgRecall, info);
//    }

}
