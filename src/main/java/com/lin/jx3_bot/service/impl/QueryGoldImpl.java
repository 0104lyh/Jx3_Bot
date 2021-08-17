package com.lin.jx3_bot.service.impl;

import com.lin.jx3_bot.entily.QueryData;
import com.lin.jx3_bot.service.QueryGold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linyanhao
 */
@Service
public class QueryGoldImpl implements QueryGold {
    static final private String url = "https://jx3api.com/app/gold?server={server}";
    @Autowired
    private static final RestTemplate restTemplate=new RestTemplate();

    @Override
    public String getGold(String server){
        Map<String,String> map = new HashMap<>();
        map.put("server",server);
        QueryData queryData = restTemplate.getForObject(url,QueryData.class,map);
        if(queryData.getCode()==200){
            String wanBaoLou = "万宝楼:" +queryData.getData().getString("wanbaolou")+"\n";
            String uu898 = "uu898:" +queryData.getData().getString("uu898")+"\n";
            String dd373 = "dd373:" +queryData.getData().getString("dd373")+"\n";
            String price5173 = "5173:" +queryData.getData().getString("5173")+"\n";
            String price7881 = "7881:" +queryData.getData().getString("7881")+"\n";
            return wanBaoLou+uu898+dd373+price5173+price7881;
        }else{
            return "查询失败";
        }
    }
}
