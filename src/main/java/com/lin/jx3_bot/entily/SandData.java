package com.lin.jx3_bot.entily;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SandData {
    private Integer code;
    private String msg;
    private long time;
    private JSONObject data;
    private List<JSONObject> historyData;
}
