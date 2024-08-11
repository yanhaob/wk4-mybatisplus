package com.mp;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.entity.User;
import com.mp.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest 
public class ServiceTest {
	
	@Autowired
    private UserService userService;
    
    @Test
    public void getOne() {
    	User one = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge,25),false);
    	System.out.println(one);
    }
    	
    @Test
    public void Batch() {
    	// User user1 = new User();
    	// user1.setName("xvli");
    	// user1.setAge(28);
    	// User user2 = new User();
    	// user2.setName("xvli");
    	// user2.setAge(29);
    	User user1 = new User();
    	user1.setName("xvlili");
    	user1.setAge(28);
    	User user2 = new User();
    	user2.setId("1811636271387373569");
    	user2.setName("xvlee");
    	user2.setAge(30);
    	List<User> userList = Arrays.asList(user1,user2);
    	boolean saveBatch = userService.saveOrUpdateBatch(userList);
    	System.out.println(userService);
    }
    
    @Test
    public void chain() {
    	// List<User> userList = userService.lambdaQuery().gt(User::getAge,25).like(User::getName,"yu").list();
    	// userList.forEach(System.out::println);
    	// boolean update = userService.lambdaUpdate().eq(User::getAge,25).set(User::getAge,26).update();
    	// System.out.println(update);
    	boolean delete = userService.lambdaUpdate().eq(User::getAge,24).remove();
    	System.out.println(delete);
    }
    
}
