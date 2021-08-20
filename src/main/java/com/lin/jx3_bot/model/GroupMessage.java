package com.lin.jx3_bot.model;

import lombok.Data;

/**
 * @author linyanhao on 2021/8/20.
 * @version 1.0
 */
@Data
public class GroupMessage {
    private Long id;
    private Integer group_code;
    private Integer group_member;
    private String message;
}
