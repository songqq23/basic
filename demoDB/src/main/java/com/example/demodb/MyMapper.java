package com.example.demodb;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyMapper {
    @Delete("delete from category where id=#{id}")
    public void delete(Integer id);

}
