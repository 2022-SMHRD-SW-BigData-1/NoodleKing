
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class pwsearchDAO {

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
	
	public void pwSearch() {
		System.out.print("ID : ");
		String id = sc.next();
		String pw = null;
		try {
			getCon();
			String sql = "select pw from user_info where id= ? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				pw = rs.getString(1);
				System.out.println("PW : "+pw);
			} else {
				System.out.println("비밀번호를 찾이 못했습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
}
