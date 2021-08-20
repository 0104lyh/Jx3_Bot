package com.lin.jx3_bot.listener.group;

import catcode.CatCodeUtil;
import com.lin.jx3_bot.mapper.MessageMapper;
import com.lin.jx3_bot.model.GroupMessage;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author linyanhao
 */
@Service
public class GroupRecallListener {
    @Autowired
    private MessageMapper mapper;
    Logger log = LoggerFactory.getLogger(GroupRecallListener.class);
    private CatCodeUtil cat = CatCodeUtil.INSTANCE;
    @OnGroup
    public void saveMessage(GroupMsg groupMsg,Sender sender){
        Enum<GroupMsg.Type> type = groupMsg.getGroupMsgType();
        if(type==GroupMsg.Type.NORMAL){
            List<String> idList = Arrays.asList(groupMsg.getId().split("\\."));
            Integer member = Integer.parseInt(idList.get(0));
            Long id = Long.valueOf(idList.get(1));
            Integer group = Integer.parseInt(groupMsg.getGroupInfo().getGroupCode());
            String message = groupMsg.getMsg();
            mapper.insertMessage(id, group,member,message);
//            log.info("insert success");
        }
    }
    @OnGroupMsgRecall
    public void getRecallMessage(GroupMsgRecall groupMsgRecall,Sender sender){
        String info = groupMsgRecall.getId().replace("REC-", "");
        List<String> idList = Arrays.asList(info.split("\\."));
        int member = Integer.parseInt(idList.get(0));
        Long id = Long.valueOf(idList.get(1));
        GroupMessage message = mapper.selectMessagebyMember(member,id);
        String text = message.getMessage();
        String at = cat.toCat("at","code="+message.getGroup_member());
        sender.sendGroupMsg(groupMsgRecall,at+"刚刚撤回的消息是："+text);
    }

}
