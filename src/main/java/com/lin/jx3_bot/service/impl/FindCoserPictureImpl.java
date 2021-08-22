package com.lin.jx3_bot.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lin.jx3_bot.entily.PictureData;
import com.lin.jx3_bot.service.FindCoserPicture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

@Service
public class FindCoserPictureImpl implements FindCoserPicture {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String getCoser() {
        Logger log = LoggerFactory.getLogger(FindCoserPictureImpl.class);
        String url = "https://zfile.cosersets.com/api/list/1?path=";
        String coserName = getName(url);
        log.info("[cosername]:"+coserName);
        coserName.replaceAll(" ","%20");
        url+=coserName;
        log.info("[url]:"+url);
        String fileName = getName(url);
//        fileName = URLEncoder.encode(fileName,"utf-8");
        fileName.replaceAll(" ","%20");
        url+=("/"+fileName);
        log.info(url);
        String finalURL = getPictureURL(url);
        if(finalURL!=null){
            return finalURL;
        }else {
            return "获取失败";
        }

    }
    private String getName(String url){
        //            URI uri = new URI(url);
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<PictureData> query = restTemplate.exchange(url, HttpMethod.GET,entity,PictureData.class);
        PictureData pictureData = query.getBody();
        JSONObject data = pictureData.getData();
        JSONArray files=  data.getJSONArray("files");
        int size = files.size();
        Random random = new Random();
        int index = random.nextInt(size);
        JSONObject coser = files.getJSONObject(index);
        String name = coser.getString("name");
        return name;
    }
    private String getPictureURL(String url){
        //            URI uri = new URI(url);
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<PictureData> query = restTemplate.exchange(url, HttpMethod.GET,entity,PictureData.class);
        PictureData pictureData = query.getBody();
        JSONObject data = pictureData.getData();
        JSONArray files=  data.getJSONArray("files");
        int size = files.size();
        Random random = new Random();
        int index = random.nextInt(size);
        JSONObject coser = files.getJSONObject(index);
        String picture = coser.getString("url");
        return picture;
    }
}
