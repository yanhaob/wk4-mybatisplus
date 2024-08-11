package com.mp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.mp.dao.UserMapper;
import com.mp.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest 
public class RetrieveTest {
	
	@Autowired
    private UserMapper userMapper;
    
    @Test
    public void selectById() {
    	User user = userMapper.selectById(1094590409767661570L);
    	System.out.println(user);
    }
    
    @Test
    public void selectIds() {
    	List<Long> idsList = Arrays.asList(1094592041087729666L, 1088248166370832385L, 1806944582865342466L);
        List<User> userList = userMapper.selectBatchIds(idsList);
        userList.forEach(System.out::println);
    }
    
    @Test
    public void selectByMap() {
    	Map<String,Object> columnMap = new HashMap<>();
    	columnMap.put("age",27);
    	List<User> userList = userMapper.selectByMap(columnMap);
    	userList.forEach(System.out::println);
    }
    
    @Test
    public void selectByWrapper() {
    	QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    	// queryWrapper.like("name","yu").lt("age",40);
    	// queryWrapper.like("name","yu").between("age",20,40).isNotNull("email");
    	// queryWrapper.likeRight("name","wang").or().ge("age",25).orderByDesc("age").orderByAsc("id");
    	// queryWrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}","2019-02-14").inSql("manager_id","select id from user where name like 'wang%'");
    	// queryWrapper.likeRight("name","wang").and(wq->wq.lt("age",40).or().isNotNull("email"));
    	// queryWrapper.likeRight("name","wang").or(wq->wq.lt("age",40).gt("age",20).isNotNull("email"));
    	// queryWrapper.nested(wq->wq.lt("age",40).or().isNotNull("email")).likeRight("name","wang");
    	// queryWrapper.in("age",Arrays.asList(30,31,34,35));
    	queryWrapper.in("age",Arrays.asList(30,31,34,35)).last("limit 1");
    	List<User> userList = userMapper.selectList(queryWrapper);
    	userList.forEach(System.out::println);
    }
    
    @Test
    public void selectByWrapperSupper() {
    	QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    	// queryWrapper.select("id","name").like("name","yu").lt("age",40);
    	queryWrapper.like("name","yu").lt("age",40).select(User.class, info->!info.getColumn().equals("create_time")&&!info.getColumn().equals("manager_id"));
    	List<User> userList = userMapper.selectList(queryWrapper);
    	userList.forEach(System.out::println);
    }
    
    @Test
    public void testCondition() {
    	String name = "";
    	String email = "x";
    	condition(name,email);
    }
    private void condition(String name,String email) {
    	QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    	// if(StringUtils.isNotEmpty(name)) {
    	//	 queryWrapper.like("name",name);
    	// }
    	// if(StringUtils.isNotEmpty(email)) {
    	// 	 queryWrapper.like("email",email);
    	// }
    	queryWrapper.like(StringUtils.isNotEmpty(name), "name", name).like(StringUtils.isNotEmpty(email), "email", email);
    	List<User> userList = userMapper.selectList(queryWrapper);
    	userList.forEach(System.out::println);
    }
    
    @Test
    public void selectByWrapperEntity() {
    	User whereUser = new User();
    	whereUser.setName("liuhongyu");
    	whereUser.setAge(32);
    	QueryWrapper<User> queryWrapper = new QueryWrapper<User>(whereUser);
    	// queryWrapper.like("name","yu").lt("age",40);
    	List<User> userList = userMapper.selectList(queryWrapper);
    	userList.forEach(System.out::println);
    }
    
    @Test
    public void selectByWrapperAllEq() {
    	QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("name","wangtianfeng");
    	// params.put("age",25);
    	// queryWrapper.allEq(params);
    	// params.put("age",null);
    	// queryWrapper.allEq(params,false);
    	params.put("age",null);
    	queryWrapper.allEq((k,v)->!k.equals("name"),params);
    	List<User> userList = userMapper.selectList(queryWrapper);
    	userList.forEach(System.out::println);
    }
    
    @Test
    public void selectByWrapperMaps() {
    	QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    	// queryWrapper.select("id","name").like("name","yu").lt("age",40);
    	queryWrapper.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age").groupBy("manager_id").having("sum(age)<{0}",500);
    	List<Map<String,Object>> userList = userMapper.selectMaps(queryWrapper);
    	userList.forEach(System.out::println);
    }
    
    @Test
    public void selectByWrapperObjs() {
    	QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    	queryWrapper.select("id","name").like("name","yu").lt("age",40);
    	List<Object> userList = userMapper.selectObjs(queryWrapper);
    	userList.forEach(System.out::println);
    }
    
    @Test
    public void selectByWrapperCount() {
    	QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    	queryWrapper.like("name","yu").lt("age",40);
    	Integer count = userMapper.selectCount(queryWrapper);
    	System.out.println("Total: "+count);
    }
    
    @Test
    public void selectByWrapperOne() {
    	QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    	queryWrapper.like("name","liuhongyu").lt("age",40);
    	User user = userMapper.selectOne(queryWrapper);
    	System.out.println("User: "+user);
    }
    
    @Test
    public void selectLambda() {
    	// LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
    	// LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
    	LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User> lambdaQuery();
    	// lambdaQuery.like(User::getName,"yu").lt(User::getAge,40);
    	lambdaQuery.likeRight(User::getName,"wang").and(lqw->lqw.lt(User::getAge,40).or().isNotNull(User::getEmail));
    	List<User> userList = userMapper.selectList(lambdaQuery);
    	userList.forEach(System.out::println);
    }
    
    @Test
    public void selectLambdaChain() {
    	List<User> userList = new LambdaQueryChainWrapper<User>(userMapper).like(User::getName,"yu").ge(User::getAge,20).list();
    	userList.forEach(System.out::println);
    }
    
    @Test
    public void selectMy() {
    	LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User> lambdaQuery();
    	lambdaQuery.likeRight(User::getName, "wang").and(lqw->lqw.lt(User::getAge,40).or().isNotNull(User::getEmail));
    	List<User> userList = userMapper.selectAll(lambdaQuery);
    	userList.forEach(System.out::println);
    }
    
    @Test
    public void selectPage() {
    	QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
    	queryWrapper.ge("age",26);
    	Page<User> page = new Page<User>(1,2);
    	// Page<User> page = new Page<User>(1,2,false);
    	/*
    	IPage<User> iPage = userMapper.selectPage(page,queryWrapper);
    	System.out.println("Total Pages: "+iPage.getPages());
    	System.out.println("Total Records: "+iPage.getTotal());
    	List<User> userList = iPage.getRecords();
    	*/
    	
    	IPage<Map<String, Object>> iPage = userMapper.selectMapsPage(page, queryWrapper);
    	System.out.println("Total Pages: "+iPage.getPages());
    	System.out.println("Total Records: "+iPage.getTotal());
    	List<Map<String, Object>> userList = iPage.getRecords();
    	
    	userList.forEach(System.out::println);
    }

}
