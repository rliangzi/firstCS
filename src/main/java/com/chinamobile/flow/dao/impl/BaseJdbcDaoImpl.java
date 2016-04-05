package com.chinamobile.flow.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.SqlTypeValue;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.support.JdbcUtils;

import com.chinamobile.flow.dao.IBaseJdbcDao;

/**
 * @Description: 用户Dao基类
 * @ClassName BaseDaoImpl
 * @author: shosho
 * @Created 2015 2015年7月6日 下午8:37:12
 */
public class BaseJdbcDaoImpl extends JdbcTemplate implements IBaseJdbcDao {

	/**
	 * 关闭资源
	 * 
	 * @param rs
	 * @param stmt
	 * @param con
	 * @throws DataAccessException
	 */
	public void close(ResultSet rs, Statement stmt, Connection con) throws DataAccessException {
		JdbcUtils.closeResultSet(rs);
		JdbcUtils.closeStatement(stmt);
		JdbcUtils.closeConnection(con);
	}

	/**
	 * 
	 * jdbc执行更新操作。
	 * 
	 * @param sql
	 * @param params
	 * @throws DataAccessException
	 */
	public boolean executeByPara(String sql, final Object[] params) throws DataAccessException {
		Boolean bool = (Boolean) execute(sql, new PreparedStatementCallback() {
			public Object doInPreparedStatement(PreparedStatement pstmt) throws SQLException, DataAccessException {
				setPreparedStatementParams(pstmt, params);
				try {
					pstmt.execute();
					return Boolean.valueOf(true);
				} catch (Exception e) {
					e.printStackTrace();
					return Boolean.valueOf(false);
				}
			}
		});
		return bool.booleanValue();
	}

	/**
	 * 
	 * jdbc执行更新操作。
	 * 
	 * @param sql
	 * @param params
	 * @throws DataAccessException
	 */
	public boolean executeBySql(String sql) throws DataAccessException {
		Boolean bool = (Boolean) execute(sql, new PreparedStatementCallback() {
			public Object doInPreparedStatement(PreparedStatement pstmt) throws SQLException, DataAccessException {
				try {
					pstmt.execute();
					return Boolean.valueOf(true);
				} catch (Exception e) {
					return Boolean.valueOf(false);
				}
			}
		});
		return bool.booleanValue();
	}

	/**
	 * @Description: 获取新的链接
	 * @return
	 * @throws SQLException
	 * @ReturnType Connection
	 * @author: idas
	 * @Created 2011 2011-12-2下午05:06:24
	 */
	public Connection getNewConnection() throws SQLException {
		return this.getDataSource().getConnection();
	}

	/**
	 * 向PreparedStatement里设置参数
	 * 
	 * @param pstmt
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static PreparedStatement setPreparedStatementParams(PreparedStatement pstmt, Object[] params) throws SQLException {
		if (pstmt == null || params == null || params.length == 0) {
			return pstmt;
		}
		for (int i = 0; i < params.length; i++) {
			int paramIndex = i + 1;
			StatementCreatorUtils.setParameterValue(pstmt, paramIndex, SqlTypeValue.TYPE_UNKNOWN, params[i]);
		}
		return pstmt;
	}

}