package com.lin.jx3_bot.service;

import org.springframework.stereotype.Service;

/**
 * @author linyanhao
 */
@Service
public interface QueryDaily {
    String getDaily(String server);
}
