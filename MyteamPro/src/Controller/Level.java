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
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Random rd = new Random();
	Scanner sc = new Scanner(System.in);
	NoodleModel lvTotal;
	int exp, score, hp, mp, lv, str, iq, dex, luk;
	String nick;
	Battle bat = new Battle();
	Sound sou = new Sound();
	
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
		
	public void insertModel(String id) {
		try {
			getCon();
			String sql = "select * from character where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,  id);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				lvTotal = new NoodleModel(rs.getInt("exp"), rs.getInt("score"), 
						rs.getInt("HP"), rs.getInt("MP"), rs.getInt("lv"),
						rs.getInt("str"), rs.getInt("int"), rs.getInt("dex"), 
						rs.getInt("luk"), rs.getString("nick"));
			}
			exp = lvTotal.getExp();
			score = lvTotal.getScore();
			hp = lvTotal.getHp();
			mp = lvTotal.getMp();
			lv = lvTotal.getLv();
			str = lvTotal.getStr();
			iq = lvTotal.getIq();
			dex = lvTotal.getDex();
			luk = lvTotal.getLuk();
			nick = lvTotal.getNick();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}	
	}
	
	public void lvUp(String id) {
		
		insertModel(id);
		
		if (lv == 1) {
			if (exp >= 100) {
				statsUp(id);
				if (hp < 5) {
					hp++;
				}
				lv++;
				mp = 200;
				score += 100000;
				exp -= 100;
			}
		} else if (lv == 2) {
			if (exp >= 200) {
				statsUp(id);
				if (hp < 5) {
					hp++;
				}
				lv++;
				mp = 200;
				score += 200000;
				exp -= 200;
				
			}

		} else if (lv == 3) {
			if (exp >= 300) {
				statsUp(id);
				if (hp < 5) {
					hp++;
				}
				lv++;
				mp = 200;
				score += 300000;
				exp -= 300;
			}
		} else if (lv == 4) {
			if (exp >= 400) {
				statsUp(id);
				if (hp < 5) {
					hp++;
				}
				lv++;
				mp = 200;
				score += 400000;
				exp -= 400;
			}
		} else if (lv == 5) {
			if (exp >= 500) {
				statsUp(id);
				if (hp < 5) {
					hp++;
				}
				lv++;
				mp = 200;
				score += 500000;
				exp -= 500;
			}
		} else if (lv == 6) {
			statsUp(id);
			mp = 200;
			score += 1000000;
			System.out.println("축하드립니다!!! 게임을 클리어 하셨습니다!!!");
		} else if (lv > 6){
			System.out.println("게임을 클리어하셨습니다!!!");
		}
		try {
			getCon();
			
			String sql = "update character set lv = ?, exp = ?, score = ?, hp = ?, mp = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, lv);
			psmt.setInt(2, exp);
			psmt.setInt(3, score);
			psmt.setInt(4, hp);
			psmt.setInt(5, mp);
			psmt.setString(6, id);
			psmt.executeUpdate();		
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("레벨업 오류!!");
		} finally {
			close();
		}
		
	}
	
	public void statsUp(String id) {
		
		insertModel(id);
		bat.chaInfo(id);
		sou.play(5);
		System.out.println("====================스텟====================");
		System.out.println("레벨업!!");
		System.out.println("증가할 스탯을 고르세요");
		System.out.print("[1]STR [2]INT [3]DEX [4]LUK");
		System.out.println("===========================================");
		int select = sc.nextInt();

		if (select == 1) {
			str++;
		} else if (select == 2) {
			iq++;
		} else if (select == 3) {
			dex++;
		} else if (select == 4) {
			luk++;
		}

		try {
			getCon();

			String sql = "update character set str = ?, int = ?, dex = ?, luk = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, str);
			psmt.setInt(2, iq);
			psmt.setInt(3, dex);
			psmt.setInt(4, luk);
			psmt.setString(5, id);
			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("스텟업 오류!");
		}
	}

}

