package com.example.demodb.Mapper;

import com.example.demodb.DTO.PageQueryDTO;
import com.example.demodb.DTO.PageQueryVO;
import com.example.demodb.DTO.User;
import com.example.demodb.DTO.UserDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

@Mapper
@CacheNamespace
public interface UserMapper {

    Page<PageQueryVO> pagequery(PageQueryDTO pageQueryDTO);

    @Insert("insert into miyuuser (id, loginId, name, password, type, create_time, update_time) " +
            "values " +
    "(#{id}, #{loginId}, #{name}, #{password}, #{type}, #{createTime}, #{updateTime})")

    void insert(User user);

    @Delete("delete from miyuuser where loginId = #{userid}")
    void delete(String userid);

    @Select("select * from miyuuser where name=#{name}")
    User getByUsernameAndPassword(User user);

    @Select("select * from miyuuser where name=#{openid}")
    User getByName(String openid);
}
