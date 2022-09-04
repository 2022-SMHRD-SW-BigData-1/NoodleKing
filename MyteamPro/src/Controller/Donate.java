package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Donate {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	static int count = 1;
	//DB서버 연결
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
	
	//Admin 로그인
	
	public void donate(String id) {

		Scanner sc = new Scanner(System.in);
		int an = 0;
		while(true) {
			System.out.print("[1]10만원 [2]50만원 [3]100만원 [4]돌아가기 >> ");
			int num = sc.nextInt();
			if(num == 4) {
				break;
			}
			System.out.println("\n==============================");
			System.out.println("스마트인재 은행 : 352-0702-0847-77");
			System.out.println("==============================");
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					if (count <= 1) {
						count++;
					} else {
						timer.cancel();
						System.out.print("입금완료하셨나요 >> [1]Y [2]N >> ");
					}
				}
			};
			timer.schedule(task, 1000, 1000);
			
			an = sc.nextInt();
			
			if(an==1) {
				try {
					getCon();
					String sql = "update user_info set donate = ? where id = ?";
					psmt = conn.prepareStatement(sql);
					psmt.setString(1, "Y");
					psmt.setString(2, id);			
					psmt.executeUpdate();
					
					System.out.println("후원 감사합니다! 캐릭 수정이 가능해졌습니다! ");
					
				} catch(SQLException e) {
					e.printStackTrace();
					System.out.println("오류!");
				} finally {
					close();
				}
				
				break;
			} else {
				System.out.println();
			}
		}
	
	}
}
