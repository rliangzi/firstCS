package com.chinamobile.flow.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;

/**
 * @Description: Dao接口基类
 * @ClassName IBaseDao
 * @author: shosho
 * @Created 2015 2015年7月6日 下午8:37:37
 */
public interface IBaseJdbcDao extends JdbcOperations {
	/**
	 * 关闭资源
	 * 
	 * @param rs
	 * @param stmt
	 * @param con
	 * @throws DataAccessException
	 */
	public void close(ResultSet rs, Statement stmt, Connection con) throws DataAccessException;

	/**
	 * 
	 * jdbc执行更新操作。
	 * 
	 * @param sql
	 * @param params
	 * @throws DataAccessException
	 */
	public boolean executeByPara(String sql, Object[] params) throws DataAccessException;

	/**
	 * 
	 * jdbc执行更新操作。
	 * 
	 * @param sql
	 * @param params
	 * @throws DataAccessException
	 */
	public boolean executeBySql(String sql) throws DataAccessException;

	/**
	 * @Description: 获取新的链接
	 * @return
	 * @throws SQLException
	 * @ReturnType Connection
	 * @author: idas
	 * @Created 2011 2011-12-2下午05:06:24
	 */
	public Connection getNewConnection() throws SQLException;
}
