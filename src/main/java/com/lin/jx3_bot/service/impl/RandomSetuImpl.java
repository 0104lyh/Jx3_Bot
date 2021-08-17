package com.lin.jx3_bot.service.impl;

import catcode.CatCodeUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.service.RandomSetu;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        String original = urls.getString("original");
        return original;
    }
}
