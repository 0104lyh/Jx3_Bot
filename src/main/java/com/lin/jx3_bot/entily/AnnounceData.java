package com.lin.jx3_bot.entily;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;
@Data
public class AnnounceData {
    private Integer code;
    private String msg;
    private List<JSONObject> data;
    private long time;
}
