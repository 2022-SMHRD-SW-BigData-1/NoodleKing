package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.NoodleModel;

public class Ranking {

	ArrayList<NoodleModel> list = new ArrayList<NoodleModel>();
	NoodleModel lv;
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	
	public void getCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String db_id = "campus_h_0830_5";
			String db_pw = "smhrd5";
			
			conn = DriverManager.getConnection(url, db_id, db_pw);
			if(conn!=null)
				System.out.println("접속완료");
			
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

	public void rank(String id) {

		try {
			getCon();
			
//			String sql = "select * from character";
//			psmt = conn.prepareStatement(sql);
//			rs = psmt.executeQuery();
//			
//			
//			while(rs.next()) {
//				id = rs.getString("id");
//				String nick = rs.getString("nick");
//				int lev = rs.getInt("lev");
//				int score = rs.getInt("score");
//				lv = new NoodleModel(id, nick, lev, score);
//				list.add(lv);	
//			}
			
			String sql = "select * from character order by score desc";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			int i = 1;
			System.out.println("=================랭킹순위=================");
			System.out.println("등수\tID\t닉네임\t레벨\t점수");
			while(rs.next()) {
				id = rs.getString("id");
				String nick = rs.getString("nick");
				int lev = rs.getInt("lv");
				int score = rs.getInt("score");
				System.out.print(i + "등\t");
				System.out.print(id);
				System.out.print("\t" + nick);
				System.out.print("\t" + lev + "\t");
				System.out.println(score);
				i++;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

		
	}
}
