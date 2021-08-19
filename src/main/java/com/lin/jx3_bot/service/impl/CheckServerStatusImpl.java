package com.lin.jx3_bot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.QueryData;
import com.lin.jx3_bot.service.CheckServerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linyanhao on 2021/8/19.
 * @version 1.0
 */
@Service
public class CheckServerStatusImpl implements CheckServerStatus {
    @Autowired
    private RestTemplate restTemplate;
    final private String OPEN="1";
    final private String CLOSE="0";
    @Override
    public String getStatus(String server) {
        Map<String,String> map = new HashMap<>();
        map.put("server",server);
        String url = "https://jx3api.com/app/check?server={server}";
        QueryData queryData = restTemplate.getForObject(url,QueryData.class,map);
        if (queryData.getCode()==200){
            JSONObject data = queryData.getData();
            String status = data.getString("status");
            String result = "服务器 "+server+"\n";
            if(status.equals(OPEN)){
                return result+"状态 开服啦";
            }else if (status.equals(CLOSE)){
                return result+"状态 维护中";
            }else{
                return "获取失败";
            }
        }else{
            return "查询失败";
        }
    }
}
