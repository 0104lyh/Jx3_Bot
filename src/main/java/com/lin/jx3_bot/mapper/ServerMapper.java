package com.lin.jx3_bot.mapper;

import com.lin.jx3_bot.model.GroupServerInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author linyanhao on 2021/8/20.
 * @version 1.0
 */
@Mapper
public interface ServerMapper {
    /**
     * @param code 群号
     * @param server 服务器
     * @param region 区服
     */
    @Insert("insert into GROUP_SERVER(group_code,server,region) values(#{group_code},#{server},#{region})")
    void setServer(@Param("group_code") Integer code,@Param("server") String server,@Param("region") String region);

    @Select("select * from GROUP_SERVER where group_code=#{group_code}")
    GroupServerInfo getServerInfo(@Param("group_code") Integer code);
}
