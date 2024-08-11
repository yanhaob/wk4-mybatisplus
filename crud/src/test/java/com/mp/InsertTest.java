package com.mp;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.mp.dao.UserMapper;
import com.mp.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest 
public class InsertTest {
	
	@Autowired
    private UserMapper userMapper;
    
    @Test
    public void insert() {
    	User user = new User();
    	user.setName("qiandawang");
    	// user.setAge(26);
    	// user.setEmail("qdw@baomidou.com");
    	user.setManagerId(1088248166370832385L);
    	user.setCreateTime(LocalDateTime.now());
    	int rows = userMapper.insert(user);
    	System.out.println("Affected Rows: "+rows);
    	System.out.println("Key: "+user.getId());
    }
    	
}
