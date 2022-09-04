
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class idsearchDAO {

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
	
	public void idSearch() {
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("닉네임 : ");
		String nick = sc.next();
		String id = null;
		try {
			getCon();
			String sql = "select id from user_info where name= ? and nick = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, nick);
			rs = psmt.executeQuery();

			if (rs.next()) {
				id = rs.getString(1);
				System.out.println("ID : "+id);
			} else {
				System.out.println("id를 찾지 못했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
}