package com.lin.jx3_bot.entily;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * @author linyanhao
 */
@Data
public class QueryFlower {
    private Integer code;
    private String msg;
    private List<Flower> data;
    private long time;
}
