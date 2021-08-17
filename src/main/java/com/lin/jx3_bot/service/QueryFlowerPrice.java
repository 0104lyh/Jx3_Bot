package com.lin.jx3_bot.service;

import org.springframework.stereotype.Service;

/**
 * @author linyanhao
 */
@Service
public interface QueryFlowerPrice {
    String getFlowerPrice(String flower,String server);
}
