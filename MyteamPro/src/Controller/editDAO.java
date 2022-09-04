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

	public int edit(String id) {
		
		try {
			getCon();
			
			String sql = "select donate from user_info where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("donate").equals("Y")) {
					return 1;			
				} else {
					return 0;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("오류!!");
		} finally {
			close();
		} 
		return -2;
	}

	public void editLv(String id, int lv) {
		try {
			getCon();
			
			String sql = "update character set lv = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, lv);
			psmt.setString(2, id);
			int cnt = psmt.executeUpdate();
			if(cnt>0) {
				System.out.println("레벨이 변경되었습니다!");
			} else {
				System.out.println("레벨 변경이 실패하였습니다!");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("오류!!");
		} finally {
			close();
		} 
	}
	
	public void editHP(String id, int HP) {
		
		try {
			getCon();
			
			String sql = "update character set lv = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, HP);
			psmt.setString(2, id);
			int cnt = psmt.executeUpdate();
			if(cnt>0) {
				System.out.println("레벨이 변경되었습니다!");
			} else {
				System.out.println("레벨 변경이 실패하였습니다!");
			}

		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("오류!!");
		} finally {
			close();
		} 
		
	}

	public void editStat(String id, int menu, int stat) {
		
		try {
			getCon();
			if(menu == 1) {
				
				String sql = "update character set str = ? where id = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, stat);
				psmt.setString(2, id);
				int cnt = psmt.executeUpdate();
				if(cnt>0) {
					System.out.println("스텟(힘)이 변경되었습니다!");
				} else {
					System.out.println("스텟 변경에 실패하였습니다!");
				}
				
			} else if(menu == 2){

				String sql = "update character set int = ? where id = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, stat);
				psmt.setString(2, id);
				int cnt = psmt.executeUpdate();
				if(cnt>0) {
					System.out.println("스텟(지능)이 변경되었습니다!");
				} else {
					System.out.println("스텟 변경에 실패하였습니다!");
				}
				
			} else if(menu == 3) {

				String sql = "update character set dex = ? where id = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, stat);
				psmt.setString(2, id);
				int cnt = psmt.executeUpdate();
				if(cnt>0) {
					System.out.println("스텟(민첩)이 변경되었습니다!");
				} else {
					System.out.println("스텟 변경에 실패하였습니다!");
				}
				
			} else if(menu == 4) {

				String sql = "update character set luk = ? where id = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, stat);
				psmt.setString(2, id);
				int cnt = psmt.executeUpdate();
				if(cnt>0) {
					System.out.println("스텟(운)이 변경되었습니다!");
				} else {
					System.out.println("스텟 변경에 실패하였습니다!");
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("오류!!");
		} finally {
			close();
		} 	
	}	
}
