package com.lin.jx3_bot.service;

import org.springframework.stereotype.Service;

/**
 * @author linyanhao on 2021/8/18.
 * @version 1.0
 */
@Service
public interface QueryHoles {
    /*
      查询奇穴
     */

    /**
     * @param  name:心法名字
     * @return holes：奇穴
     */
    String getHoles(String name);
}
