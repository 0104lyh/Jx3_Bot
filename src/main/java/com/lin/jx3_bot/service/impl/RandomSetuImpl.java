package com.lin.jx3_bot.service.impl;

import catcode.CatCodeUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.service.RandomSetu;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author linyanhao
 */
@Service
public class RandomSetuImpl implements RandomSetu {
    final CatCodeUtil catUtil = CatCodeUtil.INSTANCE;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public String getSetu() {
        String url = "https://api.lolicon.app/setu/v2";
        JSONObject getJson = restTemplate.getForObject(url,JSONObject.class);
        assert getJson != null;
        JSONArray data = getJson.getJSONArray("data");
        JSONObject urls = data.getJSONObject(0).getJSONObject("urls");
        return urls.getString("original");
    }

    @Override
    public String getTargetSetu(String name) {
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        String url = "https://api.lolicon.app/setu/v2?tag={name}";
        JSONObject getJson = restTemplate.getForObject(url,JSONObject.class,map);
        assert getJson != null;
        JSONArray data = getJson.getJSONArray("data");
        if(data.size()!=0){
            JSONObject urls = data.getJSONObject(0).getJSONObject("urls");
            return urls.getString("original");
        }else{
            return "找不到你要的涩图";
        }

    }
}