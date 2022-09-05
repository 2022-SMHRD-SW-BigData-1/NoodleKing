package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.diceVO;

public class diceDAO {
	private int exp;
	private int batting;
	private int input;
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String id;
	Scanner sc = new Scanner(System.in);	
	diceVO d1 = new diceVO();
	diceVO d2 = new diceVO();
	
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
			String sql = "select * from character where id = ?";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next())
				exp = rs.getInt("exp");
			
			int winCount = 0;
			int loseCount = 0;
			int round = 1;
			int output = 0;
			
			System.out.println("exp = " + exp);
			
			while (true) {
				System.out.print("batting exp(나가기 0) = ");
				batting = sc.nextInt();
				if(batting == 0) {
					break;
				}
				System.out.println();
				if (exp - batting >= 0 && batting != 0) {
					while (true) {
						System.out.println("["+round+" 라운드]");
						System.out.print("[1]홀 [2]짝 >> ");
						input = sc.nextInt();
						d1.setNumber();
						d2.setNumber();
						int dsum = d1.getNumber() + d2.getNumber();

						if(d1.getNumber()==1) {
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⠉⠉⠉⠉⠉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠀⠀⣀⠀⠀⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠀⠀⠉⠀⠀⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣀⣀⣀⣀⣀⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						}else if(d1.getNumber()==2) {
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⣉⡉⠉⠉⠉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠙⠁⠀⠀⠀⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠀⠀⠀⢀⣄⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣀⣀⣀⣈⣉⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						} else if(d1.getNumber()==3) {
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⣉⡉⠉⠉⠉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠙⠁⣀⠀⠀⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠀⠀⠉⢀⣄⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣀⣀⣀⣈⣉⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						} else if(d1.getNumber()==4) {
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⣉⡉⠉⢉⣉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠙⠁⠀⠈⠋⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⣠⡀⠀⢀⣄⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣉⣁⣀⣈⣉⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						} else if(d1.getNumber()==5) {
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⣉⡉⠉⢉⣉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠙⠁⣀⠈⠋⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⣠⡀⠉⢀⣄⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣉⣁⣀⣈⣉⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						} else if(d1.getNumber()==6){
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⣉⡉⠉⢉⣉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⣙⡁⠀⢈⣋⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⣩⡁⠀⢈⣍⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣉⣁⣀⣈⣉⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						}
						
						if(d2.getNumber()==1) {
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⠉⠉⠉⠉⠉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠀⠀⣀⠀⠀⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠀⠀⠉⠀⠀⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣀⣀⣀⣀⣀⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						}else if(d2.getNumber()==2) {
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⣉⡉⠉⠉⠉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠙⠁⠀⠀⠀⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠀⠀⠀⢀⣄⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣀⣀⣀⣈⣉⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						} else if(d2.getNumber()==3) {
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⣉⡉⠉⠉⠉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠙⠁⣀⠀⠀⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠀⠀⠉⢀⣄⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣀⣀⣀⣈⣉⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						} else if(d2.getNumber()==4) {
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⣉⡉⠉⢉⣉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠙⠁⠀⠈⠋⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⣠⡀⠀⢀⣄⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣉⣁⣀⣈⣉⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						} else if(d2.getNumber()==5) {
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⣉⡉⠉⢉⣉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⠙⠁⣀⠈⠋⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⣠⡀⠉⢀⣄⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣉⣁⣀⣈⣉⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						} else if(d2.getNumber()==6){
							System.out.print("\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢰⠉⣉⡉⠉⢉⣉⠉⡆⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⣙⡁⠀⢈⣋⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⢸⠀⣩⡁⠀⢈⣍⠀⡇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠸⣀⣉⣁⣀⣈⣉⣀⠇⠀⠀⠀\r\n"
									+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n"
									+ "");
						}

						if (dsum % 2 == 0) {
							output = 2;
						} else {
							output = 1;
						}

						if (output==input) {
							winCount++;
							System.out.println("[winCount] : "+winCount+"\t[loseCount] : "+loseCount+"\n");
							if (winCount == 2) {
								System.out.println("\r\n"
										+ " _____  _   _  _____  _____  _____  _____  _____ \r\n"
										+ "/  ___|| | | |/  __ \\/  __ \\|  ___|/  ___|/  ___|\r\n"
										+ "\\ `--. | | | || /  \\/| /  \\/| |__  \\ `--. \\ `--. \r\n"
										+ " `--. \\| | | || |    | |    |  __|  `--. \\ `--. \\\r\n"
										+ "/\\__/ /| |_| || \\__/\\| \\__/\\| |___ /\\__/ //\\__/ /\r\n"
										+ "\\____/  \\___/  \\____/ \\____/\\____/ \\____/ \\____/ \r\n"
										+ "                                                 \r\n"
										+ "                                                 \r\n"
										+ "");
								exp = batting * 3;
								break;
							}
						} else {
							loseCount++;
							System.out.println("[winCount] : "+winCount+"\t[loseCount] : "+loseCount+"\n");
							if (loseCount == 2) {
								System.out.println("\r\n"
										+ "______   ___   _____  _      _   _ ______  _____ \r\n"
										+ "|  ___| / _ \\ |_   _|| |    | | | || ___ \\|  ___|\r\n"
										+ "| |_   / /_\\ \\  | |  | |    | | | || |_/ /| |__  \r\n"
										+ "|  _|  |  _  |  | |  | |    | | | ||    / |  __| \r\n"
										+ "| |    | | | | _| |_ | |____| |_| || |\\ \\ | |___ \r\n"
										+ "\\_|    \\_| |_/ \\___/ \\_____/ \\___/ \\_| \\_|\\____/ \r\n"
										+ "                                                 \r\n"
										+ "                                                 \r\n"
										+ "");
								exp -= batting;
								break;
							}
						}
						round++;
					}
					
				} else {
					System.out.println("exp가 부족합니다.\t");
					break;
				}
				
			}
			
			sql = "update character set exp = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, exp);
			psmt.executeUpdate();
		
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("미니게임 오류!");
		} finally {
			close();
		}
	}
}
