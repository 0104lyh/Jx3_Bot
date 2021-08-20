package com.lin.jx3_bot.service;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author linyanhao on 2021/8/20.
 * @version 1.0
 */
@Service
public interface GroupSetServer {
    int setServer(String name,Integer code);
}
