package com.lin.jx3_bot;

import catcode.CatCodeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.J3DHData;
import com.lin.jx3_bot.entily.PictureData;
import com.lin.jx3_bot.entily.SandData;
import com.lin.jx3_bot.service.QuerySand;
import com.lin.jx3_bot.service.RandomSetu;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @Test
    public void testCOSER(){
        String url = "https://zfile.cosersets.com/api/list/1?path=";
        String coserName = getName(url);
        url+=coserName;
        String fileName = getName(url);
        System.out.println(fileName);
        fileName=URLEncoder.encode(fileName);
        url+=("/"+fileName);
        String pictureUrl = getName(url);
        String finalURL = url+=("/"+pictureUrl);
        System.out.println(finalURL);
    }
    private String getName(String url){
        try {
//            URI uri = new URI(url);
            String uri = URLEncoder.encode(url,"utf-8");
            System.out.println(uri);
            HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//        String url1 = "https://zfile.cosersets.com/api/list/1?path=";
            ResponseEntity<PictureData> query = restTemplate.exchange(url, HttpMethod.GET,entity,PictureData.class);
            PictureData pictureData = query.getBody();
//        List<JSONObject> data = pictureData.getData();
            JSONObject data = pictureData.getData();
            JSONArray files=  data.getJSONArray("files");
            JSONObject coser = files.getJSONObject(0);
            String name = coser.getString("name");
            return name;
        } catch ( UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void testDH(){
        String url = "https://www.j3dh.com/v1/wg/data/exterior?baname={baname}&exterior={exterior}&ignorePriceFlag=false&page=0";
        Map<String,String> map = new HashMap<>();
        map.put("exterior","王红");
        map.put("baname","双梦镇");
//        J3DHData dhData = restTemplate.getForObject(url,J3DHData.class,map);
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<J3DHData> query = restTemplate.exchange(url, HttpMethod.GET,entity,J3DHData.class,map);
        ResponseEntity<String> query1 = restTemplate.exchange(url, HttpMethod.GET,entity,String.class,map);
        J3DHData dhData = query.getBody();
        JSONObject object = JSON.parseObject(query1.getBody().toString());
        System.out.println(query1.getBody().toString());
        System.out.println(object);
    }
}
