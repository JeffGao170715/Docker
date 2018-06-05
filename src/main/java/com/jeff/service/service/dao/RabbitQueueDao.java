package com.jeff.service.service.dao;

import com.jeff.service.model.RabbitmqQueue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Jeff on 2018/6/1.
 */
@Mapper
public interface RabbitQueueDao {

    @Select("select * from rabbitmq_queue where id = #{id}")
    RabbitmqQueue findById(Integer id);
}
