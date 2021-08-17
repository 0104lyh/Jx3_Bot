package com.lin.jx3_bot.service.impl;

import catcode.CatCodeUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.service.RandomSetu;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RandomSetuImpl implements RandomSetu {
    final CatCodeUtil catUtil = CatCodeUtil.INSTANCE;
    private RestTemplate restTemplate = new RestTemplate();
    final private String url = "https://api.lolicon.app/setu/v2";
    @Override
    public String getSetu() {
//        String jsonString = restTemplate.getForObject(url,String.class);
//        JSONObject setuJson = JSONObject.parseObject(jsonString);
//        JSONArray data = setuJson.getJSONArray("data");
//        JSONObject urls = data.getObject(0,JSONObject.class);
//        JSONObject original = urls.getJSONObject("original");
//        String image = catUtil.toCat("image",true,original);
//        String image = catUtil.toCat("image",true);
        List<JSONObject> getJson = restTemplate.getForObject(url,List.class);
        JSONObject data = getJson.get(0);
        JSONObject urls = data.getJSONObject("urls");
        String original = urls.getString("original");
        return original;
    }
}
