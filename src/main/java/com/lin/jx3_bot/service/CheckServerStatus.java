package com.lin.jx3_bot.service;

import org.springframework.stereotype.Service;

/**
 * @author linyanhao on 2021/8/19.
 * @version 1.0
 */
@Service
public interface CheckServerStatus {
    String getStatus(String server);
}
