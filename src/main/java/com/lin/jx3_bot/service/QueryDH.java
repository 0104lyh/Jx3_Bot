package com.lin.jx3_bot.service;

import org.springframework.stereotype.Service;

@Service
public interface QueryDH {
    String getTieBa(String exterior,String baname);
    String getTieBaWithoutBaname(String exterior);
}
