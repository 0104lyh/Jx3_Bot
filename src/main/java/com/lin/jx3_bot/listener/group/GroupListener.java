package com.lin.jx3_bot.listener.group;

import com.lin.jx3_bot.service.QueryDaily;
import com.lin.jx3_bot.service.QueryGold;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.FilterValue;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.filter.MatchType;
import org.springframework.stereotype.Service;

@Service
public class GroupListener {
    /**
     * qq群消息监听
     */
    @OnGroup
    @Filter(value = "日常 {{server}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void GroupDailyQuery(GroupMsg groupMsg, Sender sender, @FilterValue("server") String server){
        sender.sendGroupMsg(groupMsg, QueryDaily.getDaily(server));
    }

    @OnGroup
    @Filter(value = "金价 {{server}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void GroupGoldQuery(GroupMsg groupMsg, Sender sender, @FilterValue("server") String server){
        sender.sendGroupMsg(groupMsg, QueryGold.getGold(server));
    }
}
