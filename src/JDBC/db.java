package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db {
	private static final String url = "jdbc:mysql://localhost:3306/domitory";
	private static final String user = "root";
	private static final String password = "123456";

	public static Connection conn = null;

	static {

		try {
			// ��������
			Class.forName("com.mysql.jdbc.Driver");
			// �õ����ݿ�����
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return conn;
	}
}
