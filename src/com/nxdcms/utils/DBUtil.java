package com.nxdcms.utils;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class DBUtil {
	private static DataSource ds;

	// 创建数据源
	public static void createDataSource() throws Exception {
		try {
			Context context = new InitialContext();
			if (context == null) {
				throw new Exception("create context failed!");
			}
			ds = (DataSource) context.lookup("java:comp/env/jdbc/db2");
			if (ds == null) {
				Thread.sleep(2000);
				ds = (DataSource) context.lookup("java:comp/env/jdbc/db2");
				if (ds == null) {
					throw new Exception("get datasource failed!");
				}
			}
		} catch (NamingException ne) {
			throw ne;
		} catch (Exception e) {
			throw e;
		}
	}
	

	// 获取数据源
	public static DataSource getDataSource() {
		if (ds == null) {
			try {
				createDataSource();
				return ds;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ds;
	}

	// 创建链接
	public static Connection getConnectionFromDataSource() {
		Connection conn = null;
		try {
			if (ds == null) {
				createDataSource();
			}
			conn = ds.getConnection();
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		getDataSource();
	}
}
