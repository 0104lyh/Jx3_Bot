package com.lin.jx3_bot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.AnnounceData;
import com.lin.jx3_bot.service.QueryNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author linyanhao on 2021/8/20.
 * @version 1.0
 */
@Service
public class QueryNewsImpl implements QueryNews {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String getNews() {
        String url = "https://jx3api.com/app/news";
        AnnounceData announceData = restTemplate.getForObject(url, AnnounceData.class);
        if(announceData.getCode()==200){
            List<JSONObject> dataList = announceData.getData();
            JSONObject data = dataList.get(0);
            String title = data.getString("title")+"\n";
            String type = data.getString("type")+"\n";
            String newsUrl = data.getString("url")+"\n";
            return title+type+newsUrl;
        }else {
            return "获取失败";
        }
    }
}
