package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import Model.NoodleModel;

public class Userinfo {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Random rd = new Random();
	Scanner sc = new Scanner(System.in);
	NoodleModel noo;
	
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
	
	public int createUser(String id, String pw, String name, String nick) {
		
		try {			
			getCon();
			
			String sql = "select count(*) from user_info where id = ? and nick = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,id);
			psmt.setString(2,nick);
			
			rs = psmt.executeQuery();
			
			
			sql = "insert into user_info values(?,?,?,?,'N')";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, name);
			psmt.setString(4, nick);

			row = psmt.executeUpdate();
			}
			
			if(row>0) {
				noo = new NoodleModel(id, pw, name, nick, "N");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("유저 데이터베이스 오류발생");
			
		} finally {
			close();
		} return row;
	}
	
	public int createCha(String id, String pw, String nick) {
		int row = 0;
		try {
			getCon();
			int sum = 0;
			int str = 0;
			int dex = 0;
			int iq = 0;
			int luk = 0;
			int[] arr = new int[4];
			
			while(true) {
				while (true) {
					for (int i = 0; i < 4; i++) {
						arr[i] = rd.nextInt(6) + 4;
						sum += arr[i];
					}
					if (sum <= 22) {
						break;
					}
					sum = 0;
				}			
				
				str = arr[0];
				dex = arr[1];
				iq = arr[2];
				luk = arr[3];
				System.out.println("힘: " + str + "\t" + "민첩: " + dex + "\t" + "지능: " + iq + "\t" + "운: " + luk);
				System.out.print("이대로 생성하시겠습니까?(Y/N) : ");
				String yorn = sc.next();
				if(yorn.equals("Y") || yorn.equals("y"))
					break;
			}
						
			String sql = "insert into character values(?, ?, ?, ?, ?, ?, ?, 1, 5, 200, 0, 0)";

			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, str);
			psmt.setInt(2, dex);
			psmt.setInt(3, iq);
			psmt.setInt(4, luk);
			psmt.setString(5, id);
			psmt.setString(6, pw);
			psmt.setString(7, nick);
			
			row = psmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("스탯 데이터베이스 오류!");
		} finally {
			close();
		} return row;
	}
	
	public int login(String id, String pw) {
		try {
		getCon();
	
		String sql = "select pw from user_info where id = ?";
		
		psmt = conn.prepareStatement(sql);
		
		psmt.setString(1, id);

		rs = psmt.executeQuery();
		
		if(rs.next()) {
			if(rs.getString(1).contentEquals(pw)) {		
				return 1; // 로그인 성공
			} else {
			return 0; // 비밀번호 실패
			}
		} return -1; // 없는 아이디
		
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스 오류");
		} finally {
			close();
		}
		return -2;
	}
	
	
}



//sql = "select * from character where id = ?";
//psmt = conn.prepareStatement(sql);
//psmt.setString(1, id);
//rs = psmt.executeQuery();
//if(rs.next()) {
//	noo = new NoodleModel(rs.getInt("str"), rs.getInt("dex"), rs.getInt("int"), rs.getInt("luk"), rs.getString("id"),
//			rs.getString("pw"), rs.getString("nick"), rs.getInt("lv"), rs.getInt("hp"), rs.getInt("mp"),
//			rs.getInt("exp"));
//}
