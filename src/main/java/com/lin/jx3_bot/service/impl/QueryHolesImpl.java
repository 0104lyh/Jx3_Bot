package com.lin.jx3_bot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.QueryData;
import com.lin.jx3_bot.service.QueryHoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author linyanhao on 2021/8/18.
 * @version 1.0
 */
@Service
public class QueryHolesImpl implements QueryHoles {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String getHoles(String name) {
        HashMap<String,String> map = new HashMap<>();
        map.put("name", name);
        String url = "https://jx3api.com/app/holes?name={name}";
        QueryData queryData = restTemplate.getForObject(url, QueryData.class,name);
        if(queryData.getCode() == 200){
            JSONObject data = queryData.getData();
            String longMen = "【龙门】 "+data.getString("longmen")+"\n";
            String battle = "【战场】 "+data.getString("battle");
            return "【心法】 "+name+"\n"+longMen+battle;
        }else{
            return "获取失败";
        }
    }
}
