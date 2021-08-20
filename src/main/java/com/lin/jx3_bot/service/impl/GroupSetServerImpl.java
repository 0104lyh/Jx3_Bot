package com.lin.jx3_bot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.QueryData;
import com.lin.jx3_bot.mapper.ServerMapper;
import com.lin.jx3_bot.service.GroupSetServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author linyanhao on 2021/8/20.
 * @version 1.0
 */
@Service
public class GroupSetServerImpl implements GroupSetServer {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ServerMapper serverMapper;
    @Override
    public int setServer(String name,Integer code) {
        String url = "https://jx3api.com/app/server?name="+name;
        QueryData queryData = restTemplate.getForObject(url,QueryData.class);
        if (queryData.getCode()==200){
            JSONObject data = queryData.getData();
            String server = data.getString("server");
            String region = data.getString("region");
            serverMapper.setServer(code,server,region);
            return 1;
        }else {
            return 0;
        }
    }
}
