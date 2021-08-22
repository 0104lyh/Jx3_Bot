package com.lin.jx3_bot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.J3DHData;
import com.lin.jx3_bot.entily.PictureData;
import com.lin.jx3_bot.service.QueryDH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service
public class QueryDHImpl implements QueryDH {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String getTieBa(String exterior,String baname) {
        String url = "https://www.j3dh.com/v1/wg/data/exterior?baname={baname}&exterior={exterior}&ignorePriceFlag=false&page=0";
        Map<String,String> map = new HashMap<>();
        map.put("exterior",exterior);
        map.put("baname",baname);
//        J3DHData dhData = restTemplate.getForObject(url,J3DHData.class,map);
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String > query = restTemplate.exchange(url, HttpMethod.GET,entity,String.class,map);
        JSONObject object = JSON.parseObject(query.getBody());
        if(object.getInteger("Code")==0){
            String finalResult = "";
            JSONObject Result = object.getJSONObject("Result");
            JSONArray Exteriors = Result.getJSONArray("Exteriors");
            if(Exteriors!=null){
                int size = Exteriors.size()/2;
                for (int i=0;i<size;i++){
                    String detail = Exteriors.getJSONObject(i).getString("Details");
                    String BigPostId = Exteriors.getJSONObject(i).getString("BigPostId");
                    String PostId = Exteriors.getJSONObject(i).getString("PostId");
                    String time = Exteriors.getJSONObject(i).getString("Time");
                    String tieURL = "http://c.tieba.baidu.com/p/"+BigPostId+"?pid="+PostId+"&cid=0#"+PostId;
                    finalResult += "详情: "+detail+"\n"+"链接: "+tieURL+"\n"+"时间: "+time+"\n"+"=========================\n";
                }
                return finalResult;
            }else {
                return "获取失败，请检查命令";
            }
        }
        else {
            return "获取失败";
        }
    }

    @Override
    public String getTieBaWithoutBaname(String exterior) {
        String url = "https://www.j3dh.com/v1/wg/data/exterior?baname={baname}&exterior={exterior}&ignorePriceFlag=false&page=0";
        Map<String,String> map = new HashMap<>();
        map.put("exterior",exterior);
        map.put("baname","");
//        J3DHData dhData = restTemplate.getForObject(url,J3DHData.class,map);
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String > query = restTemplate.exchange(url, HttpMethod.GET,entity,String.class,map);
        JSONObject object = JSON.parseObject(query.getBody());
        if(object.getInteger("Code")==0){
            String finalResult = "";
            JSONObject Result = object.getJSONObject("Result");
            JSONArray Exteriors = Result.getJSONArray("Exteriors");
            if(Exteriors!=null){
                int size = Exteriors.size()/2;
                for (int i=0;i<size;i++){
                    String detail = Exteriors.getJSONObject(i).getString("Details");
                    String BigPostId = Exteriors.getJSONObject(i).getString("BigPostId");
                    String PostId = Exteriors.getJSONObject(i).getString("PostId");
                    String time = Exteriors.getJSONObject(i).getString("Time");
                    String tieURL = "http://c.tieba.baidu.com/p/"+BigPostId+"?pid="+PostId+"&cid=0#"+PostId;
                    finalResult += "详情: "+detail+"\n"+"链接: "+tieURL+"\n"+"时间: "+time+"\n"+"=========================\n";
                }
                return finalResult;
            }else {
                return "获取失败，请检查命令";
            }
        }
        else {
            return "获取失败";
        }
    }
}
