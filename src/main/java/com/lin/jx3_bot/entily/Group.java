package com.lin.jx3_bot.entily;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linyanhao on 2021/8/18.
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private Integer id;
    private String groupId;
    private String server;
}
