package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import Model.NoodleModel;

public class Battle {

	int exp;
	int score;
	NoodleModel lvTotal = new NoodleModel();
	Random rd = new Random();
	Userinfo user = new Userinfo();

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String m_name;
	int m_lv;
	int m_exp;
	int menu;
	int m_score;
	int hp;
	int mp;
	int str;
	int iq;
	int dex;
	int luk;
	int length = 0;
	int succ = 1;
	int fail = 0;
	int[] arr = new int[100];

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

	public void monster(String id) {
		try {
			getCon();
			String sql = "select * from character where id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				lvTotal.setLv(rs.getInt("lv"));
			}

			// 몬스터 정보
			sql = "select * from monster where m_lv = ?";
			// db에서 랜덤뽑기
			// 자바에서 뽑기

			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, lvTotal.getLv());
			rs = psmt.executeQuery();

			if (rs.next()) {
				m_name = rs.getString("m_name");
				m_lv = rs.getInt("m_lv");
				m_exp = rs.getInt("m_exp");
				m_score = rs.getInt("m_score");
				System.out.println("몬스터 이름 : " + m_name + "\t Lv : " + m_lv + "\t 몬스터_exp : " + m_exp
						+ "\t 몬스터_score : " + m_score);
			}

			// 선택지
			sql = "select * from character where id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				System.out.println("[1]평타치기(" + rs.getInt("str") * 10 + ")%");
				System.out.println("[2]스킬쓰기(" + rs.getInt("int") * 10 + ")%");
				System.out.println("[3]도망가기(" + rs.getInt("dex") * 10 + ")%");
				System.out.println("[4]협상하기(" + rs.getInt("luk") * 10 + ")%");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void fight(String id, int choice) {

		try {
			getCon();
			String sql = "select * from character where id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next()) {
				exp = rs.getInt("exp");
				score = rs.getInt("score");
				hp = rs.getInt("hp");
				mp = rs.getInt("mp");
				str = rs.getInt("str");
				iq = rs.getInt("int");
				dex = rs.getInt("dex");
				luk = rs.getInt("luk");
			}

				if (choice == 1) {
					length = str * 10;
					for (int i = 0; i < length; i++) {
						arr[i] = succ;
					}
					for (int j = length; j < 100; j++) {
						arr[j] = fail;
					}
					int match = arr[rd.nextInt(100)];
					if (match == succ) {
						System.out.println("전투에서 승리하였습니다.");
						exp += m_exp; // 경험치 추가
						score += 1000 + m_score;
					} else {
						hp -= 1;
						System.out.println("전투에서 패배하셨습니다.");
					}
				} else if (choice == 2) {
					length = iq * 10;
					int[] arr = new int[100];
					for (int i = 0; i < length; i++) {
						arr[i] = succ;
					}
					for (int j = length; j < 100; j++) {
						arr[j] = fail;
					}
					int match = arr[rd.nextInt(100)];
					if (match == succ) {
						System.out.println("전투에서 승리하였습니다.");
						mp -= 10;
						exp += m_exp; // 경험치 추가
						score += 1000 + m_score;
					} else {
						hp -= 1;
						System.out.println("전투에서 패배하셨습니다.");
					}
				} else if (choice == 3) {
					length = dex * 10;
					int[] arr = new int[100];
					for (int i = 0; i < length; i++) {
						arr[i] = succ;
					}
					for (int j = length; j < 100; j++) {
						arr[j] = fail;
					}
					int match = arr[rd.nextInt(100)];
					if (match == succ) {
						System.out.println("전투에서 승리하였습니다.");
						exp += m_exp; // 경험치 추가
						score += 1000 + m_score;
					} else {
						hp -= 1;
						System.out.println("전투에서 패배하셨습니다.");
					}
				} else if (choice == 4) {
					length = luk * 10;
					int[] arr = new int[100];
					for (int i = 0; i < length; i++) {
						arr[i] = succ;
					}
					for (int j = length; j < 100; j++) {
						arr[j] = fail;
					}
					int match = arr[rd.nextInt(100)];
					if (match == succ) {
						System.out.println("전투에서 승리하였습니다.");
						exp += m_exp; // 경험치 추가
						score += 1000 + m_score;
					} else {
						hp -= 1;
						System.out.println("전투에서 패배하셨습니다.");
					}
				}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("싸움 오류!");
		} finally {
			close();
		}
	}

	public void exp(String id) {
		
		try {
			getCon();
			
			System.out.println("현재 경험치 : " + exp);
			System.out.println("현재 점수 : " + score);
			System.out.println(hp);
			System.out.println(mp);
			
			String sql = "update character set exp = ?, hp = ?, mp = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, exp);
			psmt.setInt(2, hp);
			psmt.setInt(3, mp);
			psmt.setString(4, id);
			psmt.executeUpdate();
			
			System.out.println(score);
			
			sql = "update character set score = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, 1100);
			psmt.setString(2, id);
			psmt.executeUpdate();
			
			System.out.println(score);
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("경험치 오류!");
		} finally {
			close();
		}
		
	}
}
