package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.lspVO;

public class lspDAO {

	private int exp;
	private int batting;
	private int input;
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String id;
	lspVO lv = new lspVO();
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

	public void playGame(String id) {
		
		try {
			getCon();
	
			String sql = "select id, exp from character where id = ?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next())
				id = rs.getString("id");
				exp = rs.getInt("exp");
			
			int winCount = 0;
			int loseCount = 0;
			int round = 1;
			
			System.out.println("exp = " + exp);
			while (true) {
				System.out.print("batting exp = ");
				batting = sc.nextInt();
				System.out.println();
				if (exp - batting >= 0) {
					break;
				} else {
					System.out.println("exp가 부족합니다.\t");
					break;
				}
			}

			while (true) {
				System.out.println("["+round+" 라운드]");
				System.out.print("[1]가위 [2]바위 [3]보 >> ");
				input = sc.nextInt();
				lv.setNumber();
				if (lv.getNumber() == 1) {
					System.out.print("✌\t🆚\t");
					if (input == 1) {
						System.out.println("✌");
						System.out.println("\r\n"
								+ "______                        \r\n"
								+ "|  _  \\                       \r\n"
								+ "| | | | _ __   __ _ __      __\r\n"
								+ "| | | || '__| / _` |\\ \\ /\\ / /\r\n"
								+ "| |/ / | |   | (_| | \\ V  V / \r\n"
								+ "|___/  |_|    \\__,_|  \\_/\\_/  \r\n"
								+ "                              \r\n"
								+ "                              \r\n"
								+ "");
					} else if (input == 2) {
						System.out.println("👊");
						winCount++;
						if(winCount==2) {
							System.out.println("\r\n"
									+ " _    _  _                           \r\n"
									+ "| |  | |(_)                          \r\n"
									+ "| |  | | _  _ __   _ __    ___  _ __ \r\n"
									+ "| |/\\| || || '_ \\ | '_ \\  / _ \\| '__|\r\n"
									+ "\\  /\\  /| || | | || | | ||  __/| |   \r\n"
									+ " \\/  \\/ |_||_| |_||_| |_| \\___||_|   \r\n"
									+ "                                     \r\n"
									+ "                                     \r\n"
									+ "");
							exp = batting * 3 + exp;
							break;
						}
					} else {
						System.out.println("✋");
						loseCount++;
						if(loseCount==2) {
							System.out.println("\r\n"
									+ " _                             \r\n"
									+ "| |                            \r\n"
									+ "| |      ___   ___   ___  _ __ \r\n"
									+ "| |     / _ \\ / __| / _ \\| '__|\r\n"
									+ "| |____| (_) |\\__ \\|  __/| |   \r\n"
									+ "\\_____/ \\___/ |___/ \\___||_|   \r\n"
									+ "                               \r\n"
									+ "                               \r\n"
									+ "");
							exp -= batting;
							break;
						}
					}
					System.out.println("[winCount] : "+winCount+"\t[loseCount] : "+loseCount+"\n");
				} else if (lv.getNumber() == 2) {
					System.out.print("👊\t🆚\t");
					if (input == 1) {
						System.out.println("✌");
						loseCount++;
						if(loseCount==2) {
							System.out.println("\r\n"
									+ " _                             \r\n"
									+ "| |                            \r\n"
									+ "| |      ___   ___   ___  _ __ \r\n"
									+ "| |     / _ \\ / __| / _ \\| '__|\r\n"
									+ "| |____| (_) |\\__ \\|  __/| |   \r\n"
									+ "\\_____/ \\___/ |___/ \\___||_|   \r\n"
									+ "                               \r\n"
									+ "                               \r\n"
									+ "");
							exp -= batting;
							break;
						}
					} else if (input == 2) {
						System.out.println("👊");
						System.out.println("\r\n"
								+ "______                        \r\n"
								+ "|  _  \\                       \r\n"
								+ "| | | | _ __   __ _ __      __\r\n"
								+ "| | | || '__| / _` |\\ \\ /\\ / /\r\n"
								+ "| |/ / | |   | (_| | \\ V  V / \r\n"
								+ "|___/  |_|    \\__,_|  \\_/\\_/  \r\n"
								+ "                              \r\n"
								+ "                              \r\n"
								+ "");
					} else {
						System.out.println("✋");
						winCount++;
						if(winCount==2) {
							System.out.println("\r\n"
									+ " _    _  _                           \r\n"
									+ "| |  | |(_)                          \r\n"
									+ "| |  | | _  _ __   _ __    ___  _ __ \r\n"
									+ "| |/\\| || || '_ \\ | '_ \\  / _ \\| '__|\r\n"
									+ "\\  /\\  /| || | | || | | ||  __/| |   \r\n"
									+ " \\/  \\/ |_||_| |_||_| |_| \\___||_|   \r\n"
									+ "                                     \r\n"
									+ "                                     \r\n"
									+ "");
							exp = batting * 3 + exp;
							break;
						}
					}
					System.out.println("[winCount] : "+winCount+"\t[loseCount] : "+loseCount+"\n");
				} else {
					System.out.print("✋\t🆚\t");
					if (input == 1) {
						System.out.println("✌");
						winCount++;
						if(winCount==2) {
							System.out.println("\r\n"
									+ " _    _  _                           \r\n"
									+ "| |  | |(_)                          \r\n"
									+ "| |  | | _  _ __   _ __    ___  _ __ \r\n"
									+ "| |/\\| || || '_ \\ | '_ \\  / _ \\| '__|\r\n"
									+ "\\  /\\  /| || | | || | | ||  __/| |   \r\n"
									+ " \\/  \\/ |_||_| |_||_| |_| \\___||_|   \r\n"
									+ "                                     \r\n"
									+ "                                     \r\n"
									+ "");
							exp = batting * 3 + exp;
							break;
						}
					} else if (input == 2) {
						System.out.println("👊");
						loseCount++;
						if(loseCount==2) {
							System.out.println("\r\n"
									+ " _                             \r\n"
									+ "| |                            \r\n"
									+ "| |      ___   ___   ___  _ __ \r\n"
									+ "| |     / _ \\ / __| / _ \\| '__|\r\n"
									+ "| |____| (_) |\\__ \\|  __/| |   \r\n"
									+ "\\_____/ \\___/ |___/ \\___||_|   \r\n"
									+ "                               \r\n"
									+ "                               \r\n"
									+ "");
							exp -= batting;
							break;
						}
					} else {
						System.out.println("✋");
						System.out.println("\r\n"
								+ "______                        \r\n"
								+ "|  _  \\                       \r\n"
								+ "| | | | _ __   __ _ __      __\r\n"
								+ "| | | || '__| / _` |\\ \\ /\\ / /\r\n"
								+ "| |/ / | |   | (_| | \\ V  V / \r\n"
								+ "|___/  |_|    \\__,_|  \\_/\\_/  \r\n"
								+ "                              \r\n"
								+ "                              \r\n"
								+ "");
					}
					System.out.println("[winCount] : "+winCount+"\t[loseCount] : "+loseCount+"\n");
				}
				round++;
		}
			sql = "update character set exp = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, exp);
			rs = psmt.executeQuery();
			if(rs.next()) {
				System.out.println("현재exp : " + exp);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("게임 오류");
		} finally {
			close();
		}
	}
}
