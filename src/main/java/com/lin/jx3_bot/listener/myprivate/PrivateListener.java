package com.lin.jx3_bot.listener.myprivate;

import com.lin.jx3_bot.service.QueryDaily;
import com.lin.jx3_bot.service.QuerySand;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.FilterValue;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.annotation.OnPrivate;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.filter.MatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivateListener {
    private MessageContentBuilderFactory builderFactory;
    private QueryDaily queryDaily;
    @Autowired
    public void MyPrivateListen(MessageContentBuilderFactory builderFactory) {
        this.builderFactory = builderFactory;
    }

    /**
     * 此监听函数监听一个私聊消息，并会复读这个消息，然后再发送一个表情。
     * 此方法上使用的是一个模板注解{@link OnPrivate}，
     * 其代表监听私聊。
     * 由于你监听的是私聊消息，因此参数中要有个 {@link PrivateMsg} 来接收这个消息实体。
     *
     * 其次，由于你要“复读”这句话，因此你需要发送消息，
     * 因此参数中你需要一个 "消息发送器" {@link Sender}。
     *
     * 当然，你也可以使用 {@link love.forte.simbot.api.sender.MsgSender}，
     * 然后 {@code msgSender.SENDER}.
     */
    @Autowired
    QuerySand querySand;
    @OnPrivate
    @Filter(value = "日常 {{server}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void privateDailyQuery(PrivateMsg privateMsg, Sender sender,@FilterValue("server") String server){
        sender.sendPrivateMsg(privateMsg, queryDaily.getDaily(server));
    }
    @OnPrivate
    @Filter(value = "沙盘 {{server}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void SandQueryOnGroup(PrivateMsg privateMsg, Sender sender, @FilterValue("server") String server){
        MessageContentBuilder builder = builderFactory.getMessageContentBuilder();
        builder.image(querySand.getSandAPI(server));
        MessageContent msg = builder.build();
        sender.sendPrivateMsg(privateMsg,msg);
//        sender.sendGroupMsg(groupMsg, querySand.getSandImage(server));
    }
}
