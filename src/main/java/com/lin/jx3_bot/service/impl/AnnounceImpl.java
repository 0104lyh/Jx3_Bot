package com.lin.jx3_bot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.AnnounceData;
import com.lin.jx3_bot.entily.QueryData;
import com.lin.jx3_bot.service.Announce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author linyanhao
 */
@Service
public class AnnounceImpl implements Announce {

    private final RestTemplate restTemplate = new RestTemplate();
    @Override
    public String getAnnounce() {
        String url = "https://jx3api.com/app/announce";
        AnnounceData announceData = restTemplate.getForObject(url,AnnounceData.class);
        if(announceData.getCode()==200){
            List<JSONObject> dataList = announceData.getData();
            JSONObject data = dataList.get(0);
            String title = "【标题】"+ data.getString("title")+"\n";
            String announceUrl = "【连接】"+data.getString("url")+"\n";
            return title+announceUrl;
        }else {
            return "连接失败";
        }
    }
}
