package jp.co.wisdom_technology;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcAccess {

	public static void main(String[] args) throws Exception {
		//DB接続文字列
		//DB名、user、passwordは各自の環境に合わせて変更してください。
		String connectString = "jdbc:mysql://localhost:3306/wt?user=dev&password=38akdAdks&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF8";
		
		//DB接続
		Connection conn = null;
		//JDBCのDriverをロードする
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(connectString);
		conn.setAutoCommit(false);
		
		String sql = "select * from staff";
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery(sql);
		System.out.println("id\tname\tdepartment\ttimeunit");
		while(resultSet.next()) {
			StringBuilder sb = new StringBuilder();
			sb.append(resultSet.getInt("id") + "\t");
			sb.append(resultSet.getString("name") + "\t");
			sb.append(resultSet.getInt("department") + "\t\t");
			sb.append(resultSet.getInt("time_unit"));
			System.out.println(sb.toString());
		}
		
		try {
			
			sql = "update staff set time_unit = 15 where id = 2 ";
			int resultCount = stmt.executeUpdate(sql);
			System.out.println("更新した件数：" + resultCount);
			
			sql = "delete from staff where ids = 1 ";
			resultCount = stmt.executeUpdate(sql);
			System.out.println("削除した件数：" + resultCount);
			
			conn.commit();
			
		} catch (Exception ex) {
			conn.rollback();
		}
		
		
		
		sql = "select time_unit from staff where id = 2";
		resultSet = stmt.executeQuery(sql);
		if (resultSet.next()) {
			System.out.println("更新したTIMEUNIT：" + resultSet.getInt("time_unit"));
		}
		
		//DB接続を終了する。
		conn.close();
	}

}
