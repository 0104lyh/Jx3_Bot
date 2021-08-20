package com.lin.jx3_bot.listener.group;

import catcode.CatCodeUtil;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.QueryData;
import com.lin.jx3_bot.mapper.ServerMapper;
import com.lin.jx3_bot.model.GroupServerInfo;
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
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

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
    @Autowired
    private QueryMacro queryMacro;
    @Autowired
    private QueryHoles queryHoles;
    @Autowired
    private QueryNews queryNews;
    @Autowired
    private CheckServerStatus  checkServerStatus;
    @Autowired
    private GroupSetServer groupSetServer;
    @Autowired
    private ServerMapper serverMapper;
    private final CatCodeUtil catUtil = CatCodeUtil.INSTANCE;
    @Depend
    private MessageContentBuilderFactory builderFactory;
    @Autowired
    public GroupListener(MessageContentBuilderFactory builderFactory) {
        this.builderFactory = builderFactory;
    }

    @OnGroup
    @Filter(value = "日常 {{server}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void groupDailyQueryOnServer(GroupMsg groupMsg, Sender sender, @FilterValue("server") String server){
        sender.sendGroupMsg(groupMsg, queryDaily.getDaily(server));
    }
    @OnGroup
    @Filter(value = "设置服务器 {{name}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void setServer(GroupMsg groupMsg,Sender sender,@FilterValue("name") String name){
//        List<String> idList = Arrays.asList(groupMsg.getId().split("\\."));
        Integer code = Integer.valueOf(groupMsg.getGroupInfo().getGroupCode());
        int flag = groupSetServer.setServer(name,code);
        if(flag==1){
            sender.sendGroupMsg(groupMsg, "设置成功");
        }else{
            sender.sendGroupMsg(groupMsg, "设置失败");
        }
    }
    @OnGroup
    @Filter(value = "日常",trim = true,matchType = MatchType.EQUALS)
    public void groupDailyQuery(GroupMsg groupMsg, Sender sender){
        GroupServerInfo info = serverMapper.getServerInfo(Integer.valueOf(groupMsg.getGroupInfo().getGroupCode()));
        if(info==null){
            String server = "梦江南";
            sender.sendGroupMsg(groupMsg, queryDaily.getDaily(server)+"\n"+"-----------------------------\n"
                    +"该群没有绑定服务器，默认返回梦江南\n"+"请使用命令：设置服务器 {}绑定服务器");
        }else{
            String server = info.getServer();
            sender.sendGroupMsg(groupMsg, queryDaily.getDaily(server));
        }
    }
    @OnGroup
    @Filter(value = "开服 {{server}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void groupGetServerStatus(GroupMsg groupMsg, Sender sender, @FilterValue("server") String server){
        sender.sendGroupMsg(groupMsg, checkServerStatus.getStatus(server));
    }
    @OnGroup
    @Filter(value = "金价 {{server}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void groupGoldQuery(GroupMsg groupMsg, Sender sender, @FilterValue("server") String server){
        sender.sendGroupMsg(groupMsg, queryGold.getGold(server));
    }

    @OnGroup
    @Filter(value = "花价 {{flower}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void groupFlowerQuery(GroupMsg groupMsg, Sender sender, @FilterValue("flower") String flower){
        //默认双梦
        String server="梦江南";
        sender.sendGroupMsg(groupMsg, queryFlowerPrice.getFlowerPrice(flower,server));
    }
    @OnGroup
    @Filter(value = "{{server}} 花价 {{flower}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void groupFlowerQueryOnserver(GroupMsg groupMsg, Sender sender, @FilterValue("flower") String flower, @FilterValue("server") String server){
        sender.sendGroupMsg(groupMsg, queryFlowerPrice.getFlowerPrice(flower,server));
    }
    @OnGroup
    @Filter(value = "沙盘 {{server}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void sandQueryOnGroup(GroupMsg groupMsg, Sender sender, @FilterValue("server") String server){
        String image = catUtil.toCat("image",true,"url="+querySand.getSandImage(server));
        sender.sendGroupMsg(groupMsg, image);
    }
    @OnGroup
    @Filter(value = "公告",trim = true,matchType = MatchType.EQUALS)
    public void groupQueryAnnounce(GroupMsg groupMsg, Sender sender){
        sender.sendGroupMsg(groupMsg, announce.getAnnounce());
    }

    @OnGroup
    @Filter(value = "资讯",trim = true,matchType = MatchType.EQUALS)
    public void groupQueryNes(GroupMsg groupMsg, Sender sender){
        sender.sendGroupMsg(groupMsg, queryNews.getNews());
    }

    @OnGroup
    @Filter(value = "骚话",trim = true,matchType = MatchType.EQUALS)
    public void groupGetRandomSaoHua(GroupMsg groupMsg, Sender sender){
        sender.sendGroupMsg(groupMsg, randomSaohua.getSaohua());
    }
    @OnGroup
    @Filter(value = "宏 {{name}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    @Filter(value = "{{name}} 宏",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void groupDailyQuery(GroupMsg groupMsg, Sender sender,@FilterValue("name") String name){
        sender.sendGroupMsg(groupMsg, queryMacro.getMacro(name));
    }
    @OnGroup
    @Filter(value = "奇穴 {{name}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void groupGetHoles(GroupMsg groupMsg, Sender sender, @FilterValue("name") String name){
        sender.sendGroupMsg(groupMsg, queryHoles.getHoles(name));
    }
    /**
     * 发送瑟图及其衍生功能，通过随机色图api
     * 从消息构造器改为使用catCode工具来构造图片消息。降低复杂度
     */
    @OnGroup
    @Filter(value = "涩图",trim = true,matchType = MatchType.EQUALS)
    public void randomSetuGet(GroupMsg groupMsg, Sender sender){
        String image = catUtil.toCat("image",true,"url="+randomSetu.getSetu());
        sender.sendGroupMsg(groupMsg, image);
    }
    @OnGroup
    @Filter(value = "来{{num,\\d+}}份涩图",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void randomSetuGetFireOpen(GroupMsg groupMsg, Sender sender,@FilterValue("num") int number){
        if(number<=10){
            for(int i=0;i<number;i++){
                String image = catUtil.toCat("image",true,"url="+randomSetu.getSetu());
                sender.sendGroupMsg(groupMsg,image);
            }
        }else {
            sender.sendGroupMsg(groupMsg,"请节制");
        }
    }
    @OnGroup
    @Filter(value = "涩图 {{name}}",trim = true,matchType = MatchType.REGEX_MATCHES)
    public void targetSetuGet(GroupMsg groupMsg, Sender sender,@FilterValue("name") String name){
        String image = randomSetu.getTargetSetu(name);
        if(image.equals("找不到你要的涩图")){
            sender.sendGroupMsg(groupMsg,image);
        }else{
            String targetImage = catUtil.toCat("image",true,"url="+image);
            sender.sendGroupMsg(groupMsg,targetImage);
        }
    }
}
