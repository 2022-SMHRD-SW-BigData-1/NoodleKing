package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class statusDAO {
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Scanner sc = new Scanner(System.in);
	
	public void getCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String db_id = "campus_h_0830_5";
			String db_pw = "smhrd5";
			
			conn = DriverManager.getConnection(url, db_id, db_pw);
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("시스템 오류 발생!");
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("DB 오류 발생!");
		}
	}
	
	public void close() {
		try {
			if(rs != null) {
				rs.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("자원 반납 오류!");
		}	
	}
	
	public void status(String id) {
		String nick = null;
		int lv = 0;
		int hp = 0;
		int mp = 0;
		int exp = 0;
		int str = 0;
		int dex = 0;
		int iq = 0;
		int luk = 0;
		int score = 0;
		
		try {
			getCon();
			String sql = "select * from character where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				id = rs.getString(5);
				nick = rs.getString(7);
				lv = rs.getInt(8);
				hp = rs.getInt(9);
				mp = rs.getInt(10);
				exp = rs.getInt(11);
				str = rs.getInt(1);
				dex = rs.getInt(2);
				iq = rs.getInt(3);
				luk = rs.getInt(4);
				score = rs.getInt("score");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		System.out.println("┌────────┬──────────┐");
		System.out.println("    id       "+id);
		System.out.println("├────────┼──────────┤");
		System.out.println("   nick      "+nick);
		System.out.println("├────────┼──────────┤");
		System.out.println("    lv       "+lv);
		System.out.println("├────────┼──────────┤");
		System.out.println("    hp       "+hp);
		System.out.println("├────────┼──────────┤");
		System.out.println("    mp       "+mp);
		System.out.println("├────────┼──────────┤");
		System.out.println("    exp      "+exp);
		System.out.println("├────────┼──────────┤");
		System.out.println("    str      "+str);
		System.out.println("├────────┼──────────┤");
		System.out.println("    dex      "+dex);
		System.out.println("├────────┼──────────┤");
		System.out.println("    iq       "+iq);
		System.out.println("├────────┼──────────┤");
		System.out.println("    luk      "+luk);
		System.out.println("└────────┴──────────┘");
		System.out.println("   score     "+score);
		System.out.println("└────────┴──────────┘");
	}
}

