package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import Model.NoodleModel;


public class Level {
	int score =0;
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Random rd = new Random();
	Scanner sc = new Scanner(System.in);
	NoodleModel lvTotal = new NoodleModel();
	int exp = 0;
	int str, iq, dex, luk;

	public void lvUp(String id) {
		try {
			getCon();
			String sql = "select * from character where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				lvTotal.setLv(rs.getInt("lv"));
				lvTotal.setExp(rs.getInt("exp"));
			}
			
			
			if (lvTotal.getLv() == 1) {
				if (lvTotal.getExp() >= 100) {
					statsUp(id);
					lvTotal.setLv(2);
					
					score += 100000;
					if (lvTotal.getHp() < 5) {
						lvTotal.setHp(+1);
						lvTotal.setScore(score);
					}
				}
				exp = lvTotal.getExp()-100;
			}
			if (lvTotal.getLv() == 2) {
				if (lvTotal.getExp() >= 200) {
					statsUp(id);
					lvTotal.setLv(3);
					score += 200000;
					if (lvTotal.getHp() < 5) {
						lvTotal.setHp(+1);
						lvTotal.setScore(score);
					}
				}
				exp = lvTotal.getExp()-200;
			}
			if (lvTotal.getLv() == 3) {
				if (lvTotal.getExp() >= 300) {
					statsUp(id);
					lvTotal.setLv(4);
					score += 300000;
					if (lvTotal.getHp() < 5) {
						lvTotal.setHp(+1);
						lvTotal.setScore(score);
					}
				}
				exp = lvTotal.getExp()-300;
			}
			if (lvTotal.getLv() == 4) {
				if (lvTotal.getExp() >= 400) {
					statsUp(id);
					lvTotal.setLv(5);
					score += 400000;
					if (lvTotal.getHp() < 5) {
						lvTotal.setHp(+1);
						lvTotal.setScore(score);
					}
				}
				exp = lvTotal.getExp()-400;
			}
			if (lvTotal.getLv() == 5) {
				if (lvTotal.getExp() >= 500) {
					statsUp(id);
					lvTotal.setLv(6);
					score += 500000;
					if (lvTotal.getHp() < 5) {
						lvTotal.setHp(+1);
						lvTotal.setScore(score);
					}
				}
				exp = lvTotal.getExp();
			}
			if (lvTotal.getLv() == 6) {
				statsUp(id);
				score += 1000000;
				lvTotal.setScore(score);
				System.out.println("축하드립니다!!!! 게임을 클리어 하셨습니다!!!");
			}
			
			sql = "update character set lv = ?, exp = ?, score = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, lvTotal.getLv());
			psmt.setInt(2, lvTotal.getExp());
			psmt.setInt(3, lvTotal.getScore());
			psmt.setString(4, id);
			rs = psmt.executeQuery();			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("유저 데이터베이스 오류발생");
			
		} finally {
			close();
		}
	}

	public void statsUp(String id) {
		try {
			getCon();
			String sql = "select * from character where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				lvTotal.setStr(rs.getInt("str"));
				lvTotal.setIq(rs.getInt("int"));
				lvTotal.setDex(rs.getInt("dex"));
				lvTotal.setLuk(rs.getInt("luk"));
			}
			System.out.println("레벨업!!");
			System.out.println("증가할 스탯을 고르세요");
			System.out.print("[1]STR [2]INT [3]DEX [4]LUK");
			int select = sc.nextInt();
			
			if (select == 1) {
				str = 1;
			} else if (select == 2) {
				iq = 1;
			} else if (select == 3) {
				dex = 1;
			} else if (select == 4) {
				luk = 1;
			}
			sql = "update character set str = ?, int = ?, dex = ?, luk = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, lvTotal.getStr()+str);
			psmt.setInt(2, lvTotal.getIq()+iq);
			psmt.setInt(3, lvTotal.getDex()+dex);
			psmt.setInt(4, lvTotal.getLuk()+luk);
			psmt.setString(5, id);
			rs = psmt.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("유저 데이터베이스 오류발생");	
		}
	}
	
	
	
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
	
	public void getscore(String id) {

	}
}

