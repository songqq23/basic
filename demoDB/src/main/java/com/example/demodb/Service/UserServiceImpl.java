package com.example.demodb.Service;

import com.example.demodb.DTO.*;
import com.example.demodb.Mapper.UserMapper;
import com.example.demodb.Result.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    public void save(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setId("2");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);

    }

    @Transactional
    public PageResult pagequery(PageQueryDTO pageQueryDTO){
        pageRequest pq = pageQueryDTO.getP();
        PageHelper.startPage(pq.getCurrent(),pq.getSize());
        Page<PageQueryVO> page = userMapper.pagequery(pageQueryDTO);
        long total = page.size();
        List<PageQueryVO> records = page.getResult();

        return new PageResult(total,records);
    }

    public void delete(DelUserDTO delUserDTO){
        String userid = delUserDTO.getLoginId();
        userMapper.delete(userid);
    }

    @Autowired
    private RedisTemplate redisTemplate;
    @Transactional
    public User login(User user){

        User gotuser = userMapper.getByUsernameAndPassword(user);
        if(gotuser.getPassword().equals(user.getPassword()))
            return gotuser;
        redisTemplate.opsForValue().set(gotuser.getName(),gotuser.getPassword());
        return null;
    }

}
