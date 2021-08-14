package com.lin.jx3_bot.entily;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class QueryData {
    private Integer code;
    private String msg;
    private JSONObject data;
    private long time;
}
