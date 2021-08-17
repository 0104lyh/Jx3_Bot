package com.lin.jx3_bot;

import catcode.CatCodeUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.SandData;
import com.lin.jx3_bot.service.QuerySand;
import com.lin.jx3_bot.service.RandomSetu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest
public class Jx3BotApplicationTests {
    String url = "https://jx3api.com/app/daily?server={server}";
    String url2 = "https://jx3api.com/app/daily";
    RestTemplate restTemplate=new RestTemplate();

    @Test
    public void contextLoads() {
        Map<String,String> map = new HashMap();
        map.put("server","双梦");

        String dailyJson = restTemplate.getForObject(url,String.class,map);
//        JSON dailyJson = (JSON) JSON.toJSON(dailyString);
        System.out.println(dailyJson);
    }

//    @Test
//    void awdw(){
//        String test = restTemplate.getForObject(url2,String.class);
//        System.out.println(test);
//    }
    @Test
    public void Sand(){
        final CatCodeUtil catUtil = CatCodeUtil.INSTANCE;
        String login = restTemplate.getForObject("https://www.j3sp.com/api/user/login/?account=1062589920@qq.com&password=lyh750802",String.class);
        System.out.println(login);
        String url = "https://www.j3sp.com/api/sand/?serverName={server}";
        Map<String,String> map = new HashMap<>();
        map.put("server","梦江南");
        SandData sandData = restTemplate.getForObject(url,SandData.class,map);
        String sand_dataString = sandData.getData().getString("sand_data");
        JSONObject sand_data = JSONObject.parseObject(sand_dataString);
        String image = catUtil.toCat("image",true,sand_data.getString("sandImage"));
        System.out.println(image);
    }
    RandomSetu randomSetu;
    QuerySand querySand;
    @Test
    public void setu(){
        final CatCodeUtil catUtil = CatCodeUtil.INSTANCE;
        String urlsetu = "https://api.lolicon.app/setu/v2";
        JSONObject getJson = restTemplate.getForObject(urlsetu,JSONObject.class);
        JSONArray data = getJson.getJSONArray("data");
        JSONObject urls = data.getJSONObject(0).getJSONObject("urls");
        String original = urls.getString("original");
        System.out.println(original);
    }
//    @Autowired
//    Jx3spConfig jx3spConfig;
//    @Test
//    public void testConfig(){
//        System.out.println(jx3spConfig.getPassword());
//        System.out.println(jx3spConfig.getAccount());
//    }
}
