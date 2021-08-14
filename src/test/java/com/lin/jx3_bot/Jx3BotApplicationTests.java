package com.lin.jx3_bot;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class Jx3BotApplicationTests {
    String url = "https://jx3api.com/app/daily?server={server}";
    String url2 = "https://jx3api.com/app/daily";
    RestTemplate restTemplate=new RestTemplate();

    @Test
    void contextLoads() {
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
}
