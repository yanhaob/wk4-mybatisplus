package com.mp.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.entity.User;

public interface UserMapper extends BaseMapper<User> {
	
	@Select("select * from user ${ew.customSqlSegment}")
	List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
