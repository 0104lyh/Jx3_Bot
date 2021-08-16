package com.lin.jx3_bot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.QueryData;
import com.lin.jx3_bot.service.RandomSaohua;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomSaohuaImpl implements RandomSaohua {
    private String url = "https://jx3api.com/app/random";
    private RestTemplate restTemplate = new RestTemplate();
    @Override
    public String getSaohua() {
        QueryData queryData = restTemplate.getForObject(url,QueryData.class);
        if(queryData.getCode()==200){
            JSONObject data = queryData.getData();
            String saoHua = data.getString("text");
            return saoHua;
        }else {
            return "骚不出来了";
        }
    }
}
