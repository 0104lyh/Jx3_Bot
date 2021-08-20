package com.lin.jx3_bot.mapper;

import com.lin.jx3_bot.model.GroupMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author linyanhao on 2021/8/20.
 * @version 1.0
 */
@Mapper
public interface MessageMapper {
    @Select("select * from GROUP_MESSAGE where group_member=#{group_member} and id=#{id}")
    GroupMessage selectMessagebyMember(@Param("group_member") Integer member,@Param("id") Long id);
    @Insert("insert into GROUP_MESSAGE(id,group_code,group_member,message) values(${id},${group_code},${group_member},#{message})")
    void insertMessage(@Param("id") Long id, @Param("group_code") Integer groupCode, @Param("group_member") Integer groupMember,@Param("message") String message);
}
