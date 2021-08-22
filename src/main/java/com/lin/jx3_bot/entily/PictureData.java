package com.lin.jx3_bot.entily;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;
@Data
public class PictureData {
    /**
     * 这个是用于Cosersets网站返回对象的类
     */
    private String msg;
    private Integer code;
    private JSONObject data;

}
