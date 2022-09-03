package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class editDAO {

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

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("시스템 오류 발생!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB 오류 발생!");
		}
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("자원 반납 오류!");
		}
	}

	public void edit() {
		try {
			getCon();
			System.out.print("ID : ");
			String id = sc.next();
			System.out.print("레벨 : ");
			int lv = sc.nextInt();
			System.out.print("hp : ");
			int hp = sc.nextInt();
			System.out.print("mp : ");
			int mp = sc.nextInt();
			System.out.print("str : ");
			int str = sc.nextInt();
			System.out.print("dex : ");
			int dex = sc.nextInt();
			System.out.print("int : ");
			int iq = sc.nextInt();
			System.out.print("luk : ");
			int luk = sc.nextInt();
			System.out.print("score : ");
			int score = sc.nextInt();

			String sql = "update character set lv = ?, hp = ?, mp = ?, str = ?, dex = ?, int = ?, luk = ?, score = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, lv);
			psmt.setInt(2, hp);
			psmt.setInt(3, mp);
			psmt.setInt(4, str);
			psmt.setInt(5, dex);
			psmt.setInt(6, iq);
			psmt.setInt(7, luk);
			psmt.setInt(8, score);
			psmt.setString(9, id);

			int row = psmt.executeUpdate();

			if (row > 0) {
				System.out.println("데이터 변경 성공");
			} else {
				System.out.println("데이터 변경 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

}
