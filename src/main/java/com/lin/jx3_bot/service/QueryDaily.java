package com.lin.jx3_bot.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.QueryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryDaily {
    /**
     * 日常查询，格式:日常 {server} server为服务器名
     */
    static final private String url = "https://jx3api.com/app/daily?server={server}";
    @Autowired
    private static RestTemplate restTemplate=new RestTemplate();

    public static JSONObject getDailyJson(){
        Map<String,String> map = new HashMap<>();
        map.put("server","梦江南");
        String dailyString = restTemplate.getForObject(url,String.class,map);
        JSONObject dailyJson = JSONObject.parseObject(dailyString);
        return dailyJson;
    }

    public static String getDailyString(){
        JSONObject dailyJson = QueryDaily.getDailyJson();
//        JSONObject data = JSONObject.parseObject(dailyJson.getString("data"));
        Integer code = dailyJson.getInteger("code");
        if (code==200){
            String data = dailyJson.getString("data");
            JSONObject dataJson = JSONObject.parseObject(data);

            return data;
        }else{
            return "查询失败";
        }
    }
    public static String getDaily(String server){
        Map<String,String> map = new HashMap<>();
        map.put("server",server);
        QueryData queryData = restTemplate.getForObject(url,QueryData.class,map);
        if(queryData.getCode()==200){
            String dayWar = "【大战】:"+queryData.getData().getString("DayWar")+"\n";
            String dayBattle = "【战场】:" + queryData.getData().getString("DayBattle") + "\n";
            String dayCamp = "【矿车】:" + queryData.getData().getString("DayCamp") + "\n";
            String dayCommon = "【公共日常】:" + queryData.getData().getString("DayCommon") + "\n";
            String weekCommon = "【公共周常】:" + queryData.getData().getString("WeekCommon") + "\n";
            String weekTeam = "【周常十人本】:" + queryData.getData().getString("WeekTeam") + "\n";
            String weekFive = "【周常五人本】:" + queryData.getData().getString("WeekFive") + "\n";
            String dayDraw = "【美人图】:" + queryData.getData().getString("DayDraw") + "\n";
            String dailyString = dayWar+dayBattle+dayCamp+dayCommon+weekCommon+weekTeam+weekFive;
            if(queryData.getData().getString("DayDraw")!=null){
                dailyString+=dayDraw;
            }
            return dailyString;
        }else{
            return "查询失败";
        }
    }
}
