package com.jeff.service.service.dao;

import com.jeff.service.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Jeff on 2018/5/27.
 */
@Mapper
public interface UserDao {
    @Select("select * from user where id = #{uid}")
    User findById(Long uid);

    @Insert("insert into user (name, mobile) values (#{name}, #{mobile})")
    void add(User user);

    @Select("select * from user where id = #{uid}" )
    User find(@Param("uid") Long uid);
}
