package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.NoodleModel;
import Model.lspVO;

public class lspDAO {

	private int batting;
	private int input;
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String id;
	NoodleModel lvTotal;
	
	lspVO lv2 = new lspVO();
	Scanner sc = new Scanner(System.in);
	int exp, score, hp, mp, lv, str, iq, dex, luk;
	String nick;
	
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
	
	public void playGame(String id) {
		
		try {
			insertModel(id);
			getCon();			

			int winCount = 0;
			int loseCount = 0;
			int round = 1;
			System.out.println("exp = " + exp);
			while (true) {
				System.out.print("batting exp(나가기 0) = ");
				batting = sc.nextInt();
				if(batting == 0) {
					break;
				} else {
					System.out.println();
					if (exp - batting >= 0 && batting != 0) {
						while (true) {
							System.out.println("["+round+" 라운드]");
							System.out.print("[1]가위 [2]바위 [3]보 >> ");
							input = sc.nextInt();
							lv.setNumber();
							if (lv.getNumber() == 1) {
								System.out.println("MMMMMMMMMMMMMMMXOO0OOOXMMMMMMMMWX0000NMM\r\n"
										+ "MMMMMMMMMMMMMMXxkNWNXkxXMMMMMMWOx0KKOx0W\r\n"
										+ "MMMMMMMMMMMMMMOxXMMMMXxOMMMMMWkxXMWWMkxW\r\n"
										+ "MMMMMMMMMMMMMMOxXMMMMWkkWMMMWOxXMMMW0xKM\r\n"
										+ "MMMMMMMMMMMMMMXx0MMMMMOxNMMMKdKMMMM0dKMM\r\n"
										+ "MMMMMMMMMMMMMMWOxXMMMMXx0MMXxOWMMMXxOWMM\r\n"
										+ "MMMMMMMMMMMMMMMNxOMMMMWkkWNxkWMMMMkxNMMM\r\n"
										+ "MMMMMMMMMMMWKOO0odNWWWWKxkxkNMMMMNxOMMMM\r\n"
										+ "MMMMMWN0O0OdkKXXklo00000xodk00XWMKx0MMWW\r\n"
										+ "MMMMMKxOXXkoKWWWKx0WWMMMMWNXK0OO0Ox0WWMM\r\n"
										+ "MMMMMkxWMWWkkWMMKkkKNWMMMMMMMMMN0dcOMMMM\r\n"
										+ "MMMMM0xKMMMKd0WWMNOlcx00O0NMMMWMMNkdKMMM\r\n"
										+ "MMMMMWOdKMMM0xKWWMWkckNNOoOWWWMMMMWkxNMM\r\n"
										+ "MMMMMMNoc0WMMKdkKXKxlOWKxOWWMMMMMMMOxNMM\r\n"
										+ "MMMMMMWkdkk00OxkO0OKNWW00WWMMMMMMMXxOWMM\r\n"
										+ "MMMMMMMKx0NXXXWMMMMMMMMMMMMMMMMMMXxOWMMM\r\n"
										+ "MMMMMMMMKxONWMMMMMMMMMMMMMWMMMMNOx0WMMMM\r\n"
										+ "MMMMMMMMMXOkOXWMMMMMMMMMMMWWWKOkONWMMMMM\r\n"
										+ "MMMMMMMMMMWXOOO00KXXXXXXK0O0OOKNMMMMMMMW\r\n"
										+ "MMMMMMMMMMMWWMNOkkxxxxxxxkOXWWWMMMMMMMMW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WK00000XWWWWWWWWX0KXXX0KWWWWNXXXXXNNWWWW\r\n"
										+ "X:.....,OWWWWWWO:',;;,'xW0dc:::;;;;lKWWW\r\n"
										+ "No......;KWWWWk;.''',.:Ox,.'''',lkO0NWWW\r\n"
										+ "WK;......lNWWO,.''''''xx,''''''dNWWWWWWW\r\n"
										+ "WWk'......xW0;.''''..lKo.''''.;0WWWWWWWW\r\n"
										+ "WWNo......;kc....'':xKWx,.'''''lKWWWWWWW\r\n"
										+ "WWWK;.............;0WWWNOc,''''',lkXWWWW\r\n"
										+ "WWWWx............'kWWWWWWN0d;'''''':kNWW\r\n"
										+ "WWWWNo...........dNWWWWWWWWWXo'',',''dNW\r\n"
										+ "WWWWWK;.........lXWWWWWWWWWWWK:.''''.cXW\r\n"
										+ "WWWWWWd........;0WWWWWNNNNWWXd,''''''xWW\r\n"
										+ "WWWWWWXo;;;;;,:OWKdlcc;,,;::,....',:xNWW\r\n"
										+ "WWWWWWWNNNNNNNNWWWX0OOkxxxddxxxxxk0NWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
								if (input == 1) {
									System.out.println("MMMMMMMMMMMMMMMXOO0OOOXMMMMMMMMWX0000NMM\r\n"
											+ "MMMMMMMMMMMMMMXxkNWNXkxXMMMMMMWOx0KKOx0W\r\n"
											+ "MMMMMMMMMMMMMMOxXMMMMXxOMMMMMWkxXMWWMkxW\r\n"
											+ "MMMMMMMMMMMMMMOxXMMMMWkkWMMMWOxXMMMW0xKM\r\n"
											+ "MMMMMMMMMMMMMMXx0MMMMMOxNMMMKdKMMMM0dKMM\r\n"
											+ "MMMMMMMMMMMMMMWOxXMMMMXx0MMXxOWMMMXxOWMM\r\n"
											+ "MMMMMMMMMMMMMMMNxOMMMMWkkWNxkWMMMMkxNMMM\r\n"
											+ "MMMMMMMMMMMWKOO0odNWWWWKxkxkNMMMMNxOMMMM\r\n"
											+ "MMMMMWN0O0OdkKXXklo00000xodk00XWMKx0MMWW\r\n"
											+ "MMMMMKxOXXkoKWWWKx0WWMMMMWNXK0OO0Ox0WWMM\r\n"
											+ "MMMMMkxWMWWkkWMMKkkKNWMMMMMMMMMN0dcOMMMM\r\n"
											+ "MMMMM0xKMMMKd0WWMNOlcx00O0NMMMWMMNkdKMMM\r\n"
											+ "MMMMMWOdKMMM0xKWWMWkckNNOoOWWWMMMMWkxNMM\r\n"
											+ "MMMMMMNoc0WMMKdkKXKxlOWKxOWWMMMMMMMOxNMM\r\n"
											+ "MMMMMMWkdkk00OxkO0OKNWW00WWMMMMMMMXxOWMM\r\n"
											+ "MMMMMMMKx0NXXXWMMMMMMMMMMMMMMMMMMXxOWMMM\r\n"
											+ "MMMMMMMMKxONWMMMMMMMMMMMMMWMMMMNOx0WMMMM\r\n"
											+ "MMMMMMMMMXOkOXWMMMMMMMMMMMWWWKOkONWMMMMM\r\n"
											+ "MMMMMMMMMMWXOOO00KXXXXXXK0O0OOKNMMMMMMMW\r\n"
											+ "MMMMMMMMMMMWWMNOkkxxxxxxxkOXWWWMMMMMMMMW\r\n"
											+ "");
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
									System.out.println("MMMMMMMMMMMMXOkOOOOOkOKXXXWMMMMWMMMMMMMM\r\n"
											+ "MMMMMMMMMMWKdkNWWMWKOlckkkxxKMMMMMMMMMMM\r\n"
											+ "MMMMMMMMMM0o0MWWMMWKkOXMMMNkl0MWMMMMMMMM\r\n"
											+ "MMMMMMMMMKdOWMWWMW00XNMMMMMWdlOkx0WMMMMM\r\n"
											+ "MMMMMMMWXodWMMMMW0ONWWMMMMMWkxXXOooKMWMM\r\n"
											+ "MMMMNOxkdlkXNNWWKONMWWMMMMMXOXMMMNlcXMMM\r\n"
											+ "MMW0od0NWWNNXXXKxkNWMMMMMMN0KWMMMMx;xXWM\r\n"
											+ "WWOlOWMMMMMMMMMWXK0KXWWWMWKKWMMMMWkx0kON\r\n"
											+ "MKoOMMMMMMMMMMMMMMW0xKMMWKKWMMMMW0ONMKoO\r\n"
											+ "WxxWMMMWNNWMMMMMMMMKxKMWK0NMMMMWKOXWWOl0\r\n"
											+ "NdkMMMMWNXXXKXXNWWKKXNN00NMWMMWK0XWMKldW\r\n"
											+ "NoxMMMMMMMMWNXKKK0kk0xckWWMMWX0KNMMNooXM\r\n"
											+ "MklKMMMMMMMMMMMMMMMXOxodOKKkk0XWMMXxdXMM\r\n"
											+ "MNdoXMMMMMMMMMMMMMMX0XNK0KKkx00XXkokNWMM\r\n"
											+ "WWNxoKMMMMMMMMMMMMMWXNMMMMMMNKKXxcOWMMMM\r\n"
											+ "MMMWOoxKWMMMMMMMMMMWWWMMMMWMMMW0lkWMWWMM\r\n"
											+ "MMMMMXkodx0NMMMMMWWWWWMMMMWWNOdoOWMMMMMM\r\n"
											+ "MMMMMMMNKkxxxxxkkOkkkxxxxxddddONMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMNXKKXWMWNKOxddk0NMMWWMMMMMMM");
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
									System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMMMMMMMMMMW0xdxxxx0WMMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMMMMMMMWKkookXNWXdc0MMMMMMMMM\r\n"
											+ "MMWNNNWMMMMMMMMMMNkllxNMMMWKloXMMMMMMMMM\r\n"
											+ "XxdxxxdxKWMMMMMW0ooKWWMMMNklxNNKOkkOXWMM\r\n"
											+ "OckWWWXxlkWMMWXdlkNMMMMMXdlxkxddxkkxodKM\r\n"
											+ "XldNMMMWxc0MXxlxXMMMMMW0;'cdx0NWMMMMKcdW\r\n"
											+ "M0cxWMMMKclxoxXMMMMMMNk:cxKWMMMMMWXkoo0M\r\n"
											+ "MWkcOMMMXockXMMMMMMNKkdKWMMMMMWKkddx0WMM\r\n"
											+ "MM0ckMMMMWWMMMMMMMWKOKWMMMMMXkoodk00O0NM\r\n"
											+ "MMxcKMMMMMMMMMMMMMMWMMMMMWKd:,:dxxxxxdok\r\n"
											+ "MNolXMMMWNWMMMMMMMMMMMMMNkdk0KXNWMMWN0co\r\n"
											+ "0o,lNMNkokNWKKNMMMMMMMMWKKNMMMMWXOxdddOX\r\n"
											+ "coxc0Kdd0KkdoOWMMMMMMMMMMMMWKkxddxOXWMMM\r\n"
											+ "ooKdlkXKxdxO0kKWMMMMMMMMN0xddx0NWMMMMMMM\r\n"
											+ "KloKklx0KOxddkKWMMMMMWKkodOXWMMMMMMMMMMM\r\n"
											+ "MXdlO0doxO0NWMWWNKOxxddxKWMMMMMMMMMMMMMM\r\n"
											+ "MMW0dddo;;oxxxxxxxxO0XWMMMMMMMMMMMMMMMMM\r\n"
											+ "MMMMWXOkk0NWNNNWWMMMMMMMMMMMMMMMMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
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
								System.out.println("MMMMMMMMMMMMXOkOOOOOkOKXXXWMMMMWMMMMMMMM\r\n"
										+ "MMMMMMMMMMWKdkNWWMWKOlckkkxxKMMMMMMMMMMM\r\n"
										+ "MMMMMMMMMM0o0MWWMMWKkOXMMMNkl0MWMMMMMMMM\r\n"
										+ "MMMMMMMMMKdOWMWWMW00XNMMMMMWdlOkx0WMMMMM\r\n"
										+ "MMMMMMMWXodWMMMMW0ONWWMMMMMWkxXXOooKMWMM\r\n"
										+ "MMMMNOxkdlkXNNWWKONMWWMMMMMXOXMMMNlcXMMM\r\n"
										+ "MMW0od0NWWNNXXXKxkNWMMMMMMN0KWMMMMx;xXWM\r\n"
										+ "WWOlOWMMMMMMMMMWXK0KXWWWMWKKWMMMMWkx0kON\r\n"
										+ "MKoOMMMMMMMMMMMMMMW0xKMMWKKWMMMMW0ONMKoO\r\n"
										+ "WxxWMMMWNNWMMMMMMMMKxKMWK0NMMMMWKOXWWOl0\r\n"
										+ "NdkMMMMWNXXXKXXNWWKKXNN00NMWMMWK0XWMKldW\r\n"
										+ "NoxMMMMMMMMWNXKKK0kk0xckWWMMWX0KNMMNooXM\r\n"
										+ "MklKMMMMMMMMMMMMMMMXOxodOKKkk0XWMMXxdXMM\r\n"
										+ "MNdoXMMMMMMMMMMMMMMX0XNK0KKkx00XXkokNWMM\r\n"
										+ "WWNxoKMMMMMMMMMMMMMWXNMMMMMMNKKXxcOWMMMM\r\n"
										+ "MMMWOoxKWMMMMMMMMMMWWWMMMMWMMMW0lkWMWWMM\r\n"
										+ "MMMMMXkodx0NMMMMMWWWWWMMMMWWNOdoOWMMMMMM\r\n"
										+ "MMMMMMMNKkxxxxxkkOkkkxxxxxddddONMMMMMMMM\r\n"
										+ "MMMMMMMMMMMMNXKKXWMWNKOxddk0NMMWWMMMMMMM\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WK00000XWWWWWWWWX0KXXX0KWWWWNXXXXXNNWWWW\r\n"
										+ "X:.....,OWWWWWWO:',;;,'xW0dc:::;;;;lKWWW\r\n"
										+ "No......;KWWWWk;.''',.:Ox,.'''',lkO0NWWW\r\n"
										+ "WK;......lNWWO,.''''''xx,''''''dNWWWWWWW\r\n"
										+ "WWk'......xW0;.''''..lKo.''''.;0WWWWWWWW\r\n"
										+ "WWNo......;kc....'':xKWx,.'''''lKWWWWWWW\r\n"
										+ "WWWK;.............;0WWWNOc,''''',lkXWWWW\r\n"
										+ "WWWWx............'kWWWWWWN0d;'''''':kNWW\r\n"
										+ "WWWWNo...........dNWWWWWWWWWXo'',',''dNW\r\n"
										+ "WWWWWK;.........lXWWWWWWWWWWWK:.''''.cXW\r\n"
										+ "WWWWWWd........;0WWWWWNNNNWWXd,''''''xWW\r\n"
										+ "WWWWWWXo;;;;;,:OWKdlcc;,,;::,....',:xNWW\r\n"
										+ "WWWWWWWNNNNNNNNWWWX0OOkxxxddxxxxxk0NWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
								if (input == 1) {
									System.out.println("MMMMMMMMMMMMMMMXOO0OOOXMMMMMMMMWX0000NMM\r\n"
											+ "MMMMMMMMMMMMMMXxkNWNXkxXMMMMMMWOx0KKOx0W\r\n"
											+ "MMMMMMMMMMMMMMOxXMMMMXxOMMMMMWkxXMWWMkxW\r\n"
											+ "MMMMMMMMMMMMMMOxXMMMMWkkWMMMWOxXMMMW0xKM\r\n"
											+ "MMMMMMMMMMMMMMXx0MMMMMOxNMMMKdKMMMM0dKMM\r\n"
											+ "MMMMMMMMMMMMMMWOxXMMMMXx0MMXxOWMMMXxOWMM\r\n"
											+ "MMMMMMMMMMMMMMMNxOMMMMWkkWNxkWMMMMkxNMMM\r\n"
											+ "MMMMMMMMMMMWKOO0odNWWWWKxkxkNMMMMNxOMMMM\r\n"
											+ "MMMMMWN0O0OdkKXXklo00000xodk00XWMKx0MMWW\r\n"
											+ "MMMMMKxOXXkoKWWWKx0WWMMMMWNXK0OO0Ox0WWMM\r\n"
											+ "MMMMMkxWMWWkkWMMKkkKNWMMMMMMMMMN0dcOMMMM\r\n"
											+ "MMMMM0xKMMMKd0WWMNOlcx00O0NMMMWMMNkdKMMM\r\n"
											+ "MMMMMWOdKMMM0xKWWMWkckNNOoOWWWMMMMWkxNMM\r\n"
											+ "MMMMMMNoc0WMMKdkKXKxlOWKxOWWMMMMMMMOxNMM\r\n"
											+ "MMMMMMWkdkk00OxkO0OKNWW00WWMMMMMMMXxOWMM\r\n"
											+ "MMMMMMMKx0NXXXWMMMMMMMMMMMMMMMMMMXxOWMMM\r\n"
											+ "MMMMMMMMKxONWMMMMMMMMMMMMMWMMMMNOx0WMMMM\r\n"
											+ "MMMMMMMMMXOkOXWMMMMMMMMMMMWWWKOkONWMMMMM\r\n"
											+ "MMMMMMMMMMWXOOO00KXXXXXXK0O0OOKNMMMMMMMW\r\n"
											+ "MMMMMMMMMMMWWMNOkkxxxxxxxkOXWWWMMMMMMMMW\r\n"
											+ "");
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
									System.out.println("MMMMMMMMMMMMXOkOOOOOkOKXXXWMMMMWMMMMMMMM\r\n"
											+ "MMMMMMMMMMWKdkNWWMWKOlckkkxxKMMMMMMMMMMM\r\n"
											+ "MMMMMMMMMM0o0MWWMMWKkOXMMMNkl0MWMMMMMMMM\r\n"
											+ "MMMMMMMMMKdOWMWWMW00XNMMMMMWdlOkx0WMMMMM\r\n"
											+ "MMMMMMMWXodWMMMMW0ONWWMMMMMWkxXXOooKMWMM\r\n"
											+ "MMMMNOxkdlkXNNWWKONMWWMMMMMXOXMMMNlcXMMM\r\n"
											+ "MMW0od0NWWNNXXXKxkNWMMMMMMN0KWMMMMx;xXWM\r\n"
											+ "WWOlOWMMMMMMMMMWXK0KXWWWMWKKWMMMMWkx0kON\r\n"
											+ "MKoOMMMMMMMMMMMMMMW0xKMMWKKWMMMMW0ONMKoO\r\n"
											+ "WxxWMMMWNNWMMMMMMMMKxKMWK0NMMMMWKOXWWOl0\r\n"
											+ "NdkMMMMWNXXXKXXNWWKKXNN00NMWMMWK0XWMKldW\r\n"
											+ "NoxMMMMMMMMWNXKKK0kk0xckWWMMWX0KNMMNooXM\r\n"
											+ "MklKMMMMMMMMMMMMMMMXOxodOKKkk0XWMMXxdXMM\r\n"
											+ "MNdoXMMMMMMMMMMMMMMX0XNK0KKkx00XXkokNWMM\r\n"
											+ "WWNxoKMMMMMMMMMMMMMWXNMMMMMMNKKXxcOWMMMM\r\n"
											+ "MMMWOoxKWMMMMMMMMMMWWWMMMMWMMMW0lkWMWWMM\r\n"
											+ "MMMMMXkodx0NMMMMMWWWWWMMMMWWNOdoOWMMMMMM\r\n"
											+ "MMMMMMMNKkxxxxxkkOkkkxxxxxddddONMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMNXKKXWMWNKOxddk0NMMWWMMMMMMM");
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
									System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMMMMMMMMMMW0xdxxxx0WMMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMMMMMMMWKkookXNWXdc0MMMMMMMMM\r\n"
											+ "MMWNNNWMMMMMMMMMMNkllxNMMMWKloXMMMMMMMMM\r\n"
											+ "XxdxxxdxKWMMMMMW0ooKWWMMMNklxNNKOkkOXWMM\r\n"
											+ "OckWWWXxlkWMMWXdlkNMMMMMXdlxkxddxkkxodKM\r\n"
											+ "XldNMMMWxc0MXxlxXMMMMMW0;'cdx0NWMMMMKcdW\r\n"
											+ "M0cxWMMMKclxoxXMMMMMMNk:cxKWMMMMMWXkoo0M\r\n"
											+ "MWkcOMMMXockXMMMMMMNKkdKWMMMMMWKkddx0WMM\r\n"
											+ "MM0ckMMMMWWMMMMMMMWKOKWMMMMMXkoodk00O0NM\r\n"
											+ "MMxcKMMMMMMMMMMMMMMWMMMMMWKd:,:dxxxxxdok\r\n"
											+ "MNolXMMMWNWMMMMMMMMMMMMMNkdk0KXNWMMWN0co\r\n"
											+ "0o,lNMNkokNWKKNMMMMMMMMWKKNMMMMWXOxdddOX\r\n"
											+ "coxc0Kdd0KkdoOWMMMMMMMMMMMMWKkxddxOXWMMM\r\n"
											+ "ooKdlkXKxdxO0kKWMMMMMMMMN0xddx0NWMMMMMMM\r\n"
											+ "KloKklx0KOxddkKWMMMMMWKkodOXWMMMMMMMMMMM\r\n"
											+ "MXdlO0doxO0NWMWWNKOxxddxKWMMMMMMMMMMMMMM\r\n"
											+ "MMW0dddo;;oxxxxxxxxO0XWMMMMMMMMMMMMMMMMM\r\n"
											+ "MMMMWXOkk0NWNNNWWMMMMMMMMMMMMMMMMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
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
								System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMMMMMM\r\n"
										+ "MMMMMMMMMMMMMMMMMMMMMW0xdxxxx0WMMMMMMMMM\r\n"
										+ "MMMMMMMMMMMMMMMMMMWKkookXNWXdc0MMMMMMMMM\r\n"
										+ "MMWNNNWMMMMMMMMMMNkllxNMMMWKloXMMMMMMMMM\r\n"
										+ "XxdxxxdxKWMMMMMW0ooKWWMMMNklxNNKOkkOXWMM\r\n"
										+ "OckWWWXxlkWMMWXdlkNMMMMMXdlxkxddxkkxodKM\r\n"
										+ "XldNMMMWxc0MXxlxXMMMMMW0;'cdx0NWMMMMKcdW\r\n"
										+ "M0cxWMMMKclxoxXMMMMMMNk:cxKWMMMMMWXkoo0M\r\n"
										+ "MWkcOMMMXockXMMMMMMNKkdKWMMMMMWKkddx0WMM\r\n"
										+ "MM0ckMMMMWWMMMMMMMWKOKWMMMMMXkoodk00O0NM\r\n"
										+ "MMxcKMMMMMMMMMMMMMMWMMMMMWKd:,:dxxxxxdok\r\n"
										+ "MNolXMMMWNWMMMMMMMMMMMMMNkdk0KXNWMMWN0co\r\n"
										+ "0o,lNMNkokNWKKNMMMMMMMMWKKNMMMMWXOxdddOX\r\n"
										+ "coxc0Kdd0KkdoOWMMMMMMMMMMMMWKkxddxOXWMMM\r\n"
										+ "ooKdlkXKxdxO0kKWMMMMMMMMN0xddx0NWMMMMMMM\r\n"
										+ "KloKklx0KOxddkKWMMMMMWKkodOXWMMMMMMMMMMM\r\n"
										+ "MXdlO0doxO0NWMWWNKOxxddxKWMMMMMMMMMMMMMM\r\n"
										+ "MMW0dddo;;oxxxxxxxxO0XWMMMMMMMMMMMMMMMMM\r\n"
										+ "MMMMWXOkk0NWNNNWWMMMMMMMMMMMMMMMMMMMMMMM\r\n"
										+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WK00000XWWWWWWWWX0KXXX0KWWWWNXXXXXNNWWWW\r\n"
										+ "X:.....,OWWWWWWO:',;;,'xW0dc:::;;;;lKWWW\r\n"
										+ "No......;KWWWWk;.''',.:Ox,.'''',lkO0NWWW\r\n"
										+ "WK;......lNWWO,.''''''xx,''''''dNWWWWWWW\r\n"
										+ "WWk'......xW0;.''''..lKo.''''.;0WWWWWWWW\r\n"
										+ "WWNo......;kc....'':xKWx,.'''''lKWWWWWWW\r\n"
										+ "WWWK;.............;0WWWNOc,''''',lkXWWWW\r\n"
										+ "WWWWx............'kWWWWWWN0d;'''''':kNWW\r\n"
										+ "WWWWNo...........dNWWWWWWWWWXo'',',''dNW\r\n"
										+ "WWWWWK;.........lXWWWWWWWWWWWK:.''''.cXW\r\n"
										+ "WWWWWWd........;0WWWWWNNNNWWXd,''''''xWW\r\n"
										+ "WWWWWWXo;;;;;,:OWKdlcc;,,;::,....',:xNWW\r\n"
										+ "WWWWWWWNNNNNNNNWWWX0OOkxxxddxxxxxk0NWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\r\n"
										+ "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
								if (input == 1) {
									System.out.println("MMMMMMMMMMMMMMMXOO0OOOXMMMMMMMMWX0000NMM\r\n"
											+ "MMMMMMMMMMMMMMXxkNWNXkxXMMMMMMWOx0KKOx0W\r\n"
											+ "MMMMMMMMMMMMMMOxXMMMMXxOMMMMMWkxXMWWMkxW\r\n"
											+ "MMMMMMMMMMMMMMOxXMMMMWkkWMMMWOxXMMMW0xKM\r\n"
											+ "MMMMMMMMMMMMMMXx0MMMMMOxNMMMKdKMMMM0dKMM\r\n"
											+ "MMMMMMMMMMMMMMWOxXMMMMXx0MMXxOWMMMXxOWMM\r\n"
											+ "MMMMMMMMMMMMMMMNxOMMMMWkkWNxkWMMMMkxNMMM\r\n"
											+ "MMMMMMMMMMMWKOO0odNWWWWKxkxkNMMMMNxOMMMM\r\n"
											+ "MMMMMWN0O0OdkKXXklo00000xodk00XWMKx0MMWW\r\n"
											+ "MMMMMKxOXXkoKWWWKx0WWMMMMWNXK0OO0Ox0WWMM\r\n"
											+ "MMMMMkxWMWWkkWMMKkkKNWMMMMMMMMMN0dcOMMMM\r\n"
											+ "MMMMM0xKMMMKd0WWMNOlcx00O0NMMMWMMNkdKMMM\r\n"
											+ "MMMMMWOdKMMM0xKWWMWkckNNOoOWWWMMMMWkxNMM\r\n"
											+ "MMMMMMNoc0WMMKdkKXKxlOWKxOWWMMMMMMMOxNMM\r\n"
											+ "MMMMMMWkdkk00OxkO0OKNWW00WWMMMMMMMXxOWMM\r\n"
											+ "MMMMMMMKx0NXXXWMMMMMMMMMMMMMMMMMMXxOWMMM\r\n"
											+ "MMMMMMMMKxONWMMMMMMMMMMMMMWMMMMNOx0WMMMM\r\n"
											+ "MMMMMMMMMXOkOXWMMMMMMMMMMMWWWKOkONWMMMMM\r\n"
											+ "MMMMMMMMMMWXOOO00KXXXXXXK0O0OOKNMMMMMMMW\r\n"
											+ "MMMMMMMMMMMWWMNOkkxxxxxxxkOXWWWMMMMMMMMW\r\n"
											+ "");
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
									System.out.println("MMMMMMMMMMMMXOkOOOOOkOKXXXWMMMMWMMMMMMMM\r\n"
											+ "MMMMMMMMMMWKdkNWWMWKOlckkkxxKMMMMMMMMMMM\r\n"
											+ "MMMMMMMMMM0o0MWWMMWKkOXMMMNkl0MWMMMMMMMM\r\n"
											+ "MMMMMMMMMKdOWMWWMW00XNMMMMMWdlOkx0WMMMMM\r\n"
											+ "MMMMMMMWXodWMMMMW0ONWWMMMMMWkxXXOooKMWMM\r\n"
											+ "MMMMNOxkdlkXNNWWKONMWWMMMMMXOXMMMNlcXMMM\r\n"
											+ "MMW0od0NWWNNXXXKxkNWMMMMMMN0KWMMMMx;xXWM\r\n"
											+ "WWOlOWMMMMMMMMMWXK0KXWWWMWKKWMMMMWkx0kON\r\n"
											+ "MKoOMMMMMMMMMMMMMMW0xKMMWKKWMMMMW0ONMKoO\r\n"
											+ "WxxWMMMWNNWMMMMMMMMKxKMWK0NMMMMWKOXWWOl0\r\n"
											+ "NdkMMMMWNXXXKXXNWWKKXNN00NMWMMWK0XWMKldW\r\n"
											+ "NoxMMMMMMMMWNXKKK0kk0xckWWMMWX0KNMMNooXM\r\n"
											+ "MklKMMMMMMMMMMMMMMMXOxodOKKkk0XWMMXxdXMM\r\n"
											+ "MNdoXMMMMMMMMMMMMMMX0XNK0KKkx00XXkokNWMM\r\n"
											+ "WWNxoKMMMMMMMMMMMMMWXNMMMMMMNKKXxcOWMMMM\r\n"
											+ "MMMWOoxKWMMMMMMMMMMWWWMMMMWMMMW0lkWMWWMM\r\n"
											+ "MMMMMXkodx0NMMMMMWWWWWMMMMWWNOdoOWMMMMMM\r\n"
											+ "MMMMMMMNKkxxxxxkkOkkkxxxxxddddONMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMNXKKXWMWNKOxddk0NMMWWMMMMMMM");
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
									System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMMMMMMMMMMW0xdxxxx0WMMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMMMMMMMWKkookXNWXdc0MMMMMMMMM\r\n"
											+ "MMWNNNWMMMMMMMMMMNkllxNMMMWKloXMMMMMMMMM\r\n"
											+ "XxdxxxdxKWMMMMMW0ooKWWMMMNklxNNKOkkOXWMM\r\n"
											+ "OckWWWXxlkWMMWXdlkNMMMMMXdlxkxddxkkxodKM\r\n"
											+ "XldNMMMWxc0MXxlxXMMMMMW0;'cdx0NWMMMMKcdW\r\n"
											+ "M0cxWMMMKclxoxXMMMMMMNk:cxKWMMMMMWXkoo0M\r\n"
											+ "MWkcOMMMXockXMMMMMMNKkdKWMMMMMWKkddx0WMM\r\n"
											+ "MM0ckMMMMWWMMMMMMMWKOKWMMMMMXkoodk00O0NM\r\n"
											+ "MMxcKMMMMMMMMMMMMMMWMMMMMWKd:,:dxxxxxdok\r\n"
											+ "MNolXMMMWNWMMMMMMMMMMMMMNkdk0KXNWMMWN0co\r\n"
											+ "0o,lNMNkokNWKKNMMMMMMMMWKKNMMMMWXOxdddOX\r\n"
											+ "coxc0Kdd0KkdoOWMMMMMMMMMMMMWKkxddxOXWMMM\r\n"
											+ "ooKdlkXKxdxO0kKWMMMMMMMMN0xddx0NWMMMMMMM\r\n"
											+ "KloKklx0KOxddkKWMMMMMWKkodOXWMMMMMMMMMMM\r\n"
											+ "MXdlO0doxO0NWMWWNKOxxddxKWMMMMMMMMMMMMMM\r\n"
											+ "MMW0dddo;;oxxxxxxxxO0XWMMMMMMMMMMMMMMMMM\r\n"
											+ "MMMMWXOkk0NWNNNWWMMMMMMMMMMMMMMMMMMMMMMM\r\n"
											+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
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
						break;
					} else {
						System.out.println("exp가 부족합니다.\t");
						break;
					}
				}
				}		
			
			String sql = "update character set exp = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, exp);
			psmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("게임 오류");
		} finally {
			close();
		}
	}
}
