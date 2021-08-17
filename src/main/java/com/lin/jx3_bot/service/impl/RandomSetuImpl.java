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
        JSONObject getJson = restTemplate.getForObject(url,JSONObject.class);
        JSONArray data = getJson.getJSONArray("data");
        JSONObject urls = data.getJSONObject(0).getJSONObject("urls");
        String original = urls.getString("original");
        return original;
    }
}
