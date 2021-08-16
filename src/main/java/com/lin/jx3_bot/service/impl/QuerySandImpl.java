package com.lin.jx3_bot.service.impl;

import catcode.CatCodeUtil;
import catcode.CodeBuilder;
import catcode.Neko;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.QueryData;
import com.lin.jx3_bot.entily.SandData;
import com.lin.jx3_bot.service.QuerySand;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service
public class QuerySandImpl implements QuerySand {
    static final private String url = "https://www.j3sp.com/api/sand/?serverName={server}&token={token}";
    RestTemplate restTemplate = new RestTemplate();
    final CatCodeUtil catUtil = CatCodeUtil.INSTANCE;
    public String getSandImage(String server){
        Map<String,String> map = new HashMap<>();
        map.put("server",server);
        String login = restTemplate.getForObject("https://www.j3sp.com/api/user/login/?account=1062589920@qq.com&password=lyh750802",String.class);
        JSONObject loginJSON = JSONObject.parseObject(login);
        JSONObject data = loginJSON.getJSONObject("data");
        JSONObject userinfo = data.getJSONObject("userinfo");
        String token = userinfo.getString("token");
        map.put("token",token);
//        System.out.println(token);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", token);
//        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.135 Safari/537.36");
//        ResponseEntity<SandData> exchange = restTemplate.exchange(
//                url,
//                HttpMethod.GET,
//                new HttpEntity<>(headers),
//                SandData.class,
//                map
//        );
//        SandData sandData = exchange.getBody();
        SandData sandData = restTemplate.getForObject(url,SandData.class,map);
        if(sandData.getCode()==1){
//            String sand_dataString = sandData.getData().getString("sand_data");
            JSONObject sand_data = sandData.getData().getJSONObject("sand_data");
//            String image = catUtil.toCat("image",true,sand_data.getString("sandImage"));
            String image = sand_data.getString("sandImage");

            return image;
        }else{
            return "获取失败";
        }
    }

    @Override
    public String getSandUrl(String server) {
        Map<String,String> map = new HashMap<>();
        map.put("server",server);
        QueryData queryData = restTemplate.getForObject(url,QueryData.class,map);
        if(queryData.getCode()==200){
            String imager = queryData.getData().getString("url");
            return imager;
        }else {
            return "失败";
        }
    }


}
