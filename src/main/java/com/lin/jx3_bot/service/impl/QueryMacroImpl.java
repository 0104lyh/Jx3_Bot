package com.lin.jx3_bot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.QueryData;
import com.lin.jx3_bot.service.QueryMacro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linyanhao on 2021/8/17.
 * @version 1.0
 */
@Service
public class QueryMacroImpl implements QueryMacro {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final int OK=200;
    @Override
    public String getMacro(String name) {
        String url = "https://jx3api.com/app/macro?name={name}";
        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        QueryData queryData = restTemplate.getForObject(url, QueryData.class,map);
        if(queryData.getCode()==OK){
            JSONObject data = queryData.getData();
            String plan = "奇穴:"+data.getString("plan")+"\n";
            String command = "宏:"+data.getString("command")+"\n";
            return "心法:"+name+"\n"+plan+command;
        }else{
            return "获取失败";
        }
    }
}
