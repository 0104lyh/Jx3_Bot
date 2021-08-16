package com.lin.jx3_bot.service;

import catcode.Neko;
import org.springframework.stereotype.Service;

@Service
public interface QuerySand {
    String getSandImage(String server);

    @Deprecated
    public String getSandAPI(String server);
}
