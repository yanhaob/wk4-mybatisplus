package com.mp.component;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		boolean hasSetter = metaObject.hasSetter("createTime");
		if(hasSetter) {
			System.out.println("insertFill~~");
			setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		Object val = getFieldValByName("updateTime", metaObject);
		if(val==null) {
			System.out.println("updateFill~~");
			setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
		}
	}

}
