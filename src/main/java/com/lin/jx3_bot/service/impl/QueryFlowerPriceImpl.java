package com.lin.jx3_bot.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.Flower;
import com.lin.jx3_bot.entily.QueryData;
import com.lin.jx3_bot.entily.QueryFlower;
import com.lin.jx3_bot.service.QueryFlowerPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
/**
 * @author linyanhao
 */
@Service
public class QueryFlowerPriceImpl implements QueryFlowerPrice {
    static private final String url = "https://jx3api.com/app/flower?server={server}&flower={flower}&map={map}";
    @Autowired
    private static RestTemplate restTemplate= new RestTemplate();
    @Override
    public String getFlowerPrice(String flower, String server){
        Map<String,String> map = new HashMap<>();
        map.put("server",server);
        map.put("flower",flower);
        map.put("map","广陵邑");
        QueryFlower queryFlower = restTemplate.getForObject(url,QueryFlower.class,map);
        if (queryFlower.getCode()==200){
            StringBuilder result= new StringBuilder();
            List<Flower> dataList = queryFlower.getData();
            for(int i=0;i<dataList.size();i++){
                Flower flowerData = dataList.get(i);
                String flowerName = "名称:"+flowerData.getName()+"\n";
                String flowerColor = "颜色:"+flowerData.getColor()+"\n";
                String flowerPrice = "倍率"+flowerData.getPrice()+"\n";
                String line = "线路:"+Arrays.toString(flowerData.getLine())+"\n";
                result.append(flowerName).append(flowerColor).append(flowerPrice).append(line).append("————————————————\n");
            }
            return result.toString();
        }else {
            return "无数据";
        }
    }
}
