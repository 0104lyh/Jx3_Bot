package com.lin.jx3_bot.listener.group;

import com.lin.jx3_bot.service.*;
import love.forte.common.ioc.annotation.Depend;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.FilterValue;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.filter.MatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupListener {
    /**
     * qq群消息监听
     */
    @Autowired
    private QueryDaily queryDaily;
    @Autowired
    private QueryGold queryGold;
    @Autowired
    private QueryFlowerPrice queryFlowerPrice;
    @Autowired
    private QuerySand querySand;
    @Autowired
    private RandomSetu randomSetu;
    @Autowired
    private Announce announce;
    @Autowired
    private RandomSaohua randomSaohua;
    @Depend
    private MessageContentBuilderFactory builderFactory;
    @Autowired
    public GroupListener(MessageContentBuilderFactory builderFactory) {
        this.builderFactory = builderFactory;
    }

    @OnGroup
    @Filter(value = "日常 {{server}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void GroupDailyQueryOnServer(GroupMsg groupMsg, Sender sender, @FilterValue("server") String server){
        sender.sendGroupMsg(groupMsg, queryDaily.getDaily(server));
    }
    @OnGroup
    @Filter(value = "日常",trim = true,matchType = MatchType.EQUALS)
    public void GroupDailyQuery(GroupMsg groupMsg, Sender sender){
        String server = "梦江南";
        sender.sendGroupMsg(groupMsg, queryDaily.getDaily(server));
    }

    @OnGroup
    @Filter(value = "金价 {{server}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void GroupGoldQuery(GroupMsg groupMsg, Sender sender, @FilterValue("server") String server){
        sender.sendGroupMsg(groupMsg, queryGold.getGold(server));
    }

    @OnGroup
    @Filter(value = "花价 {{flower}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void GroupFlowerQuery(GroupMsg groupMsg, Sender sender, @FilterValue("flower") String flower){
        //默认双梦
        String server="梦江南";
        sender.sendGroupMsg(groupMsg, queryFlowerPrice.getFlowerPrice(flower,server));
    }
    @OnGroup
    @Filter(value = "{{server}} 花价 {{flower}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void GroupFlowerQueryOnserver(GroupMsg groupMsg, Sender sender, @FilterValue("flower") String flower,@FilterValue("server") String server){
        sender.sendGroupMsg(groupMsg, queryFlowerPrice.getFlowerPrice(flower,server));
    }
    @OnGroup
    @Filter(value = "沙盘 {{server}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void SandQueryOnGroup(GroupMsg groupMsg, Sender sender, @FilterValue("server") String server){
        MessageContentBuilder builder = builderFactory.getMessageContentBuilder();
        builder.image(querySand.getSandImage(server));
        MessageContent msg = builder.build();
        sender.sendGroupMsg(groupMsg,msg);
//        sender.sendGroupMsg(groupMsg, querySand.getSandImage(server));
    }
    @OnGroup
    @Filter(value = "公告",trim = true,matchType = MatchType.EQUALS)
    public void GroupQueryAnnounce(GroupMsg groupMsg, Sender sender){
        sender.sendGroupMsg(groupMsg, announce.getAnnounce());
    }
    @OnGroup
    @Filter(value = "骚话",trim = true,matchType = MatchType.EQUALS)
    public void GroupGetRandomSaoHua(GroupMsg groupMsg, Sender sender){
        sender.sendGroupMsg(groupMsg, randomSaohua.getSaohua());
    }
    @OnGroup
    @Filter(value = "涩图",trim = true,matchType = MatchType.EQUALS)
    public void RandomSetuGet(GroupMsg groupMsg, Sender sender){
//        sender.sendGroupMsg(groupMsg, randomSetu.getSetu());
        MessageContentBuilder builder = builderFactory.getMessageContentBuilder();
        builder.image(randomSetu.getSetu());
        MessageContent msg = builder.build();
        sender.sendGroupMsg(groupMsg,msg);
    }
}
