package com.mp.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.parsers.DynamicTableNameParser;
import com.baomidou.mybatisplus.extension.parsers.ITableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

@Configuration
public class MybatisPlusConfiguration {
    
	public static ThreadLocal<String> myTableName = new ThreadLocal<>();
	
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}
	
	@Bean
	@Profile({"dev","test"})
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		// performanceInterceptor.setFormat(true);
		// performanceInterceptor.setMaxTime(5L);
		return performanceInterceptor;
	}
	
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		ArrayList<ISqlParser> sqlParserList = new ArrayList<ISqlParser>();
		/*
		 * TenantSqlParser tenantSqlParser = new TenantSqlParser();
		 * tenantSqlParser.setTenantHandler(new TenantHandler() {
		 * 
		 * @Override public String getTenantIdColumn() { return "manager_id"; }
		 * 
		 * @Override public Expression getTenantId() { return new
		 * LongValue(1088248166370832385L); }
		 * 
		 * @Override public boolean doTableFilter(String tableName) {
		 * if("role".equals(tableName)) { return true; } return false; } });
		 * 
		 * sqlParserList.add(tenantSqlParser);
		 */
		DynamicTableNameParser dynamicTableNameParser = new DynamicTableNameParser();
		Map<String, ITableNameHandler> tableNameHandlerMap = new HashMap<>();
		tableNameHandlerMap.put("user", new ITableNameHandler() {
			@Override
			public String dynamicTableName(MetaObject metaObject, String sql, String tableName) {
				return myTableName.get();
			}
		});
		
		dynamicTableNameParser.setTableNameHandlerMap(tableNameHandlerMap );
		sqlParserList.add(dynamicTableNameParser);
		
		paginationInterceptor.setSqlParserList(sqlParserList);		
		paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
			@Override
			public boolean doFilter(MetaObject metaObject) {
				MappedStatement mappedStatement = SqlParserHelper.getMappedStatement(metaObject);
				if("com.mp.dao.UserMapper.selectById".equals(mappedStatement.getId())) {
					return true;
				}
				return false;
			}
		});
		
		return paginationInterceptor;
	}
	
}
