package com.cqhg.ensure.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
	static String driverClass = "oracle.jdbc.driver.OracleDriver"; // oracle的驱动
	//static String url = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST=(ADDRESS = (PROTOCOL = TCP)(HOST =23.29.2.2)(PORT = 1521)))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME =cams)))";// "jdbc:oracle:thin:@192.168.21.110:1521/mzdev";
	static String url = "jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST=(ADDRESS = (PROTOCOL = TCP)(HOST =192.168.21.110)(PORT = 1521)))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME =mzdev)))";// "jdbc:oracle:thin:@192.168.21.110:1521/mzdev";
	static String user = "frm_ws"; // user是数据库的用户名
	static String password = "frm_ws_dev";// "frm_ws_mzxm2018"; // 用户登录密码
	//static String password = "frm_ws_mzxm2018";


	public static Connection getconn() { // 为了方便下面的讲解，这里专门建立了一个用于数据库连接的一个方法
		Connection conn = null;
		try {

			// 首先建立驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 驱动成功后进行连接
			conn = DriverManager.getConnection(url, user, password);

			System.out.println("连接成功");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn; // 返回一个连接
	}
}