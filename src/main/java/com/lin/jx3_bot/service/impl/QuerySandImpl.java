package com.lin.jx3_bot.service.impl;

import catcode.CatCodeUtil;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.QueryData;
import com.lin.jx3_bot.entily.SandData;
import com.lin.jx3_bot.service.QuerySand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
@Service
public class QuerySandImpl implements QuerySand {

    private static String account;
    @Value("${jx3sp.account}")
    public void setAccount(String account){
        QuerySandImpl.account=account;
    }
    private static String password;
    @Value("${jx3sp.password}")
    public void setPassword(String password){
        QuerySandImpl.password=password;
    }

    RestTemplate restTemplate = new RestTemplate();
    final CatCodeUtil catUtil = CatCodeUtil.INSTANCE;

    @Override
    public String getSandImage(String server){
        Map<String,String> map = new HashMap<>();
        map.put("server",server);
        String loginUrl = "https://www.j3sp.com/api/user/login/?account="+account+"&password="+password;
        String login = restTemplate.getForObject(loginUrl,String.class);
        JSONObject loginJSON = JSONObject.parseObject(login);
        JSONObject data = loginJSON.getJSONObject("data");
        JSONObject userinfo = data.getJSONObject("userinfo");
        String token = userinfo.getString("token");
        map.put("token",token);
        String url = "https://www.j3sp.com/api/sand/?serverName={server}&token={token}";
        SandData sandData = restTemplate.getForObject(url,SandData.class,map);
        if(sandData.getCode()==1){
            JSONObject sand_data = sandData.getData().getJSONObject("sand_data");

            return sand_data.getString("sandImage");
        }else{
            return "获取失败";
        }
    }

    @Override
    public String getSandAPI(String server) {
        Map<String,String> map = new HashMap<>();
        map.put("server",server);
        String jx3APIurl = "https://jx3api.com/app/sand?server={server}";
        QueryData queryData = restTemplate.getForObject(jx3APIurl,QueryData.class,map);
        if(queryData.getCode()==200){
            return queryData.getData().getString("url");
        }else {
            return "失败";
        }
    }


}
