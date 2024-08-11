package com.mp;

import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.dao.UserMapper;
import com.mp.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest 
public class DeleteTest {
	
	@Autowired
    private UserMapper userMapper;
    
    @Test
    public void deleteById() {
    	int rows = userMapper.deleteById(1806952145543544834L);
    	System.out.println("Rows Deleted: "+rows);
    }
    
    @Test
    public void deleteByMap() {
    	Map<String,Object> columnMap = new HashMap<>();
    	columnMap.put("name","xianghou");
    	columnMap.put("age",25);
    	int rows = userMapper.deleteByMap(columnMap);
    	System.out.println("Rows Deleted: "+rows);
    }
    
    @Test
    public void deleteBatchIds() {
        int rows = userMapper.deleteBatchIds(Arrays.asList(1806949568819056642L, 1806950282068819970L, 1806950995993907202L));
        System.out.println("Rows Deleted: "+rows);
    }
    
    @Test
    public void deleteByWrapper() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
        lambdaQuery.eq(User::getAge,27).or().gt(User::getAge,41);
        int rows = userMapper.delete(lambdaQuery);
        System.out.println("Rows Deleted: "+rows);
    }
    
}
