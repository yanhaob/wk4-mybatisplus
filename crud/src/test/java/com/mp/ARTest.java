package com.mp;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mp.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest 
public class ARTest {
    
    @Test
    public void insert() {
    	User user = new User();
    	user.setName("zhangcao");
    	user.setAge(24);
    	user.setEmail("zc@baomidou.com");
    	user.setManagerId(1088248166370832385L);
    	user.setCreateTime(LocalDateTime.now());
    	user.insert();
    	boolean insert = user.insert();
    	System.out.println(insert);
    }
    
    @Test
    public void selectById() {
    	User user = new User();
    	// User userSelect = user.selectById(1811613703656255490L);
    	user.setId(1811613703656255490L);
    	User userSelect = user.selectById();
    	System.out.println(userSelect==user);
    	System.out.println(userSelect);
    }
    
    @Test
    public void updateById() {
    	User user = new User();
    	user.setId(1811613703656255490L);
    	user.setName("zhangcaocao");
    	boolean updateById = user.updateById();
    	System.out.println(updateById);
    }
    
    @Test
    public void deleteById() {
    	User user = new User();
    	user.setId(1811613703656255490L);
    	boolean deleteById = user.deleteById();
    	System.out.println(deleteById);
    }
    
    @Test
    public void insertOrUpdate() {
    	User user = new User();
    	user.setName("zhangqiang");
    	user.setAge(24);
    	user.setEmail("zq@baomidou.com");
    	user.setManagerId(1088248166370832385L);
    	user.setCreateTime(LocalDateTime.now());
    	boolean insertOrUpdate = user.insertOrUpdate(); 
    	System.out.println(insertOrUpdate);
    }
}
