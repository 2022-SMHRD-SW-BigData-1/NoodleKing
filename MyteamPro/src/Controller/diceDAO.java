package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.NoodleModel;
import Model.diceVO;

public class diceDAO {
	
	private int batting;
	private int input;
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String id;
	NoodleModel lvTotal;
	Scanner sc = new Scanner(System.in);	
	diceVO d1 = new diceVO();
	diceVO d2 = new diceVO();
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
			int output = 0;
			
			System.out.println("exp = " + exp);
			
			while (true) {
				System.out.print("batting exp(나가기 0) = ");
				batting = sc.nextInt();
				if(batting == 0) {
					break;
				}
				else {
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
								System.out.print("MMMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMM\r\n"
										+ "MMMWKdllllllllllllllllllllllllloollllllllllld0WMMM\r\n"
										+ "MMKl;lk0KKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXK0kl;c0MM\r\n"
										+ "MK:;0WMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMK:,0M\r\n"
										+ "Mx.xMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMO'lW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md.kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'lW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0,lW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMN0xddxONMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMM0:.     ,OWMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMNc        ;XMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMWo.       cNMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMNk:'...;xNMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMWNXXNWMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md.xMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Mk'oWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMx.dM\r\n"
										+ "MWd,lXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMXd,lXM\r\n"
										+ "MMWOc:clodddooddddddddddddddddddddddddddoooc:ckNMW\r\n"
										+ "MMMMWXOxddddddddddddddddddddddddddddddddddxOXWWWWW\r\n"
										+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWWMM");
							}else if(d1.getNumber()==2) {
								System.out.print("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\r\n"
										+ "MMMMMMMNKOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO0NMMMMMM\r\n"
										+ "MMMMW0lcllooooooooooooooooooooooooooooooollcl0WMMM\r\n"
										+ "MMMWd,oXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWXd,dNMM\r\n"
										+ "MMMk'oWMMMWXOkOKWMMMMMMMMMMMMMMMMMMMMMMMMMMMWd'kMM\r\n"
										+ "MMMd'kMMMXo.   .cKMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMx.      oWMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMO'     .kWMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMWKo:;;l0WMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMx,kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMO,dMM\r\n"
										+ "MMMd,kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMMO,dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMWOc,,;l0WMMMO'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMx.     'OMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMWo      .xMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMXo'...'dNMMMk'dMM\r\n"
										+ "MMMO,oWMMMMMMMMMMMMMMMMMMMMMMMMMMMWN0O0NMMMMWo'kMM\r\n"
										+ "MWWWx;lKWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWKl;xWMM\r\n"
										+ "WWWMW0olllllloooooooooooolloooloooolllooolllo0WWMM\r\n"
										+ "WWMMWWMWX0OOO00000000000OOO000O0000OOO0000XWMMMMMM\r\n"
										+ "MMMWWWMMMMMMMWMWWWWWWWMMWMWMMWWMWWMWWMWWMMWMWWMWWM");
							} else if(d1.getNumber()==3) {
								System.out.print("MMMWXOkxxxxxxxxxxkxxxxxxxxxxxxxxxxxxxxxxxxxxOXWMMM\r\n"
										+ "MNkc:cclllllllllooollllllllllllllllllllllllcc:cxXM\r\n"
										+ "K:,dXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMXx;;O\r\n"
										+ ":'0MMMMMMWNNWWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMK;,\r\n"
										+ ".cWMMMMXd:,'':kNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMK;       lNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMk.       ,KMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMXc      .dWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMNOl:;co0WMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ "'lWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMWKkxxk0NMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMWO;.    .;kWMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMK,        '0MMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMK,        'OMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMWO;.    .;OWMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMWKkxxk0NMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWKdc;:lkNMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWx.      :KMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMX;        dMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWd.      '0MMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWOc,'';oKMMMMMo.\r\n"
										+ ":'0MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWNNWMMMMMMK,,\r\n"
										+ "K:,dXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWXx;;0\r\n"
										+ "MNkc:cclllllllllllllllllllllllllllllllllllllc:cxXM\r\n"
										+ "WWMWKxlcccccccccccccccccccccccccccccccccccclx0WMMM");
							} else if(d1.getNumber()==4) {
								System.out.print("MMMMMMMMWNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNWWMWWMMMW\r\n"
										+ "MMMMNOolloooooooooooooooooooooooooooooooolox0NMMMW\r\n"
										+ "MMWOc:d0XNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNX0d:cKMWW\r\n"
										+ "MM0,cXMMMMMWWWMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMK;:XMM\r\n"
										+ "MMd'kMMMWKo;,,ckNMMMMMMMMMMMMMMMMNx:,,:dXMMMMd'kMM\r\n"
										+ "MMd'OMMM0'      oNMMMMMMMMMMMMMMXc      ;KMMMd'xMM\r\n"
										+ "MMd'OMMMx.      :NMMMMMMMMMMMMMMK,      .OMMMd'xMM\r\n"
										+ "MMd'OMMMNo.   .:0WMMMMMMMMMMMMMMWk,    'xNMMMd'xMM\r\n"
										+ "MMd'OMMMMWXOxk0WMMMMMMMMMMMMMMMMMMN0kxONMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMx'kMM\r\n"
										+ "MMd'OMMMMMWX0KNMMMMMMMMMMMMMMMMMMMWXK0XWMMMMMx,kMM\r\n"
										+ "MMd'OMMMWk;...'oXMMMMMMMMMMMMMMMM0c....:OWMMMd'xMM\r\n"
										+ "MMd'OMMMk.      cNMMMMMMMMMMMMMMX;      '0MMMd'xMM\r\n"
										+ "MMd'OMMMk.      cNMMMMMMMMMMMMMMX;      ,0MMMd'xMM\r\n"
										+ "MMd'kMMMWk:...,oXMMMMMMMMMMMMMMMMKl'..,c0WMMMd'xMM\r\n"
										+ "MMO,oWMMMMWXKXNMMMMMMMMMMMMMMMMMMMMNXXNWMMMMNc,0MM\r\n"
										+ "MMWk;cONMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWXk::OMMM\r\n"
										+ "MMMMXdclllllllllllllllllllllllooolllllllllllxXMMMM\r\n"
										+ "MMMMMMWX0kkkkkkkkkkkkkkkkkkkkkOOOkkkkkkkO0NMMMMMMM");
							} else if(d1.getNumber()==5) {
								System.out.print("MWMWWMMMMMMMMMWWMMMMMMMWMMMMMMMMWWWMMWMMMMMMWMWMMM\r\n"
										+ "WWMWWN0kxxxkOOkxxxxxxxxxkOOOxxkOkxxxkOkxk0NMWWWMMW\r\n"
										+ "MWW0lcloooddxxdooooooooddxxxdddxdddddxxdllcoKWMMMM\r\n"
										+ "MWx,lXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMW0c;OMMMM\r\n"
										+ "MK,cWMMMMXOxk0NMMMMMMMMMMMMMMMMMMX0kxONMMMMX;:XMMM\r\n"
										+ "MO'oMMMNx'   .,kWMMMMMMMMMMMMMMWx'.  .,kWMMWc,KMMM\r\n"
										+ "MO'oMMMO'      ;KMMMMMMMMMMMMMM0'      ;KMMWc,KMMM\r\n"
										+ "MO'oMMMXc      lNMMMMMMMMMMMMMMX:     .lNMMWc,KMMM\r\n"
										+ "MO'oMMMMXxc;;ckNMMMMMMMMMMMMMMMMNxc;;ckNMMMWc,KMMM\r\n"
										+ "MO,dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWl;KMMM\r\n"
										+ "MO,dMMMMMMMMMMMMMMMMXkolodkNMMMMMMMMMMMMMMMWl;KMMM\r\n"
										+ "MO'oMMMMMMMMMMMMMMMO,      ;KMMMMMMMMMMMMMMWc,KMMM\r\n"
										+ "MO'oMMMMMMMMMMMMMMWl        dMMMMMMMMMMMMMMWc,KMMM\r\n"
										+ "MO'oMMMMMMMMMMMMMMWO'      ;0MMMMMMMMMMMMMMWc,KMMM\r\n"
										+ "MO'oMMMMMMMMMMMMMMMMXxllodkNMMMMMMMMMMMMMMMWc,KMMM\r\n"
										+ "MO'oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWc,KMMM\r\n"
										+ "MO'oMMMMNxc;:lONMMMMMMMMMMMMMMMMNkc::lkNMMMWc,KMMM\r\n"
										+ "MO'oMMMX:      lNMMMMMMMMMMMMMMXc      lNMMWc,KMMM\r\n"
										+ "MO'oMMMO.      ,KMMMMMMMMMMMMMMO'      ,KMMWc,KMMM\r\n"
										+ "MO'oMMMNx.    ,kWMMMMMMMMMMMMMMNx'    'kWMMWc,KMMM\r\n"
										+ "M0,cWMMMMXkdxONMMMMMMMMMMMMMMMMMMXkdxONMMMMX::XMMM\r\n"
										+ "MWx,oXMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWKc;OMMMM\r\n"
										+ "MMW0lcloddddddddddddddddddddddddddddddddolcoKMMMMM\r\n"
										+ "MMMMMXOxxddddddddddddddddddddddddddddddxk0NMMMMMMM\r\n"
										+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
							} else if(d1.getNumber()==6){
								System.out.print("MMWWWKkollllllllllllllllllllllllllllllllllox0WMMMM\r\n"
										+ "MMWkccldxkkxkkkkkkkkkkkkkkkkkkkkkkkkkkkkkxdl:ckNMM\r\n"
										+ "MNo,dNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNx,lNM\r\n"
										+ "MO'lWMMMMNOddx0WMMMMMMMMMMMMMMMMMMWKxddOXMMMMMd.xM\r\n"
										+ "Mx.dMMMWk,    .:KMMMMMMMMMMMMMMMMXc.    'xWMMMx'dM\r\n"
										+ "Mx.dMMMK;       lWMMMMMMMMMMMMMMMo       '0MMMx.dM\r\n"
										+ "Mx.dMMMNl      .xWMMMMMMMMMMMMMMMO.      :XMMMx.dM\r\n"
										+ "Mx.dMMMMNkc,';lOWMMMMMMMMMMMMMMMMW0o;',:xXMMMMk'dM\r\n"
										+ "Mx.dMMMMMMMWWWMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMk'dM\r\n"
										+ "Mx.dMMMMMN0xdkKWMMMMMMMMMMMMMMMMMMWKkdxONMMMMMk'dM\r\n"
										+ "Mx.dMMMWO,    .cKMMMMMMMMMMMMMMMMXl.    ,kWMMMk'dM\r\n"
										+ "Mx.dMMMX;       lWMMMMMMMMMMMMMMMo       '0MMMk'dM\r\n"
										+ "Mx.dMMMNl      .xWMMMMMMMMMMMMMMMk.      :XMMMx.dM\r\n"
										+ "Mx.dMMMMNx:'',cOWMMMMMMMMMMMMMMMMW0l,'':dXMMMMx'dM\r\n"
										+ "Mk'dMMMMMMMWNWMMMMMMMMMMMMMMMMMMMMMMWNWWMMMMMMk'dM\r\n"
										+ "Mx'dMMMMMN0kxkKWMMMMMMMMMMMMMMMMMMWXOxx0NMMMMMk.dM\r\n"
										+ "Mx.dMMMWO;.   .cKMMMMMMMMMMMMMMMMXo.    ,kWMMMx.dM\r\n"
										+ "Mx.dMMMX;       lWMMMMMMMMMMMMMMMd       '0MMMx.dM\r\n"
										+ "Mx.dMMMNl      .dWMMMMMMMMMMMMMMMk.      :XMMMk.dM\r\n"
										+ "Mx.dMMMMXx:,''cOWMMMMMMMMMMMMMMMMWOc,.';dXMMMMx.dM\r\n"
										+ "MK;;XMMMMMWNNWWMMMMMMMMMMMMMMMMMMMMMWNNWMMMMMNc,0M\r\n"
										+ "MM0::xKWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWXx:;OWM\r\n"
										+ "MMMNklclllllllllloollllllllllllllllllllllllclxNMMM\r\n"
										+ "MMMMMMNKOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOKNMMMMMM");
							}
							
							if(d2.getNumber()==1) {
								System.out.print("MMMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMM\r\n"
										+ "MMMWKdllllllllllllllllllllllllloollllllllllld0WMMM\r\n"
										+ "MMKl;lk0KKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXK0kl;c0MM\r\n"
										+ "MK:;0WMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMK:,0M\r\n"
										+ "Mx.xMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMO'lW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md.kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'lW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0,lW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMN0xddxONMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMM0:.     ,OWMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMNc        ;XMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMWo.       cNMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMNk:'...;xNMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMWNXXNWMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Md.xMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM0'cW\r\n"
										+ "Mk'oWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMx.dM\r\n"
										+ "MWd,lXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMXd,lXM\r\n"
										+ "MMWOc:clodddooddddddddddddddddddddddddddoooc:ckNMW\r\n"
										+ "MMMMWXOxddddddddddddddddddddddddddddddddddxOXWWWWW\r\n"
										+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWWMM");
							}else if(d2.getNumber()==2) {
								System.out.print("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM\r\n"
										+ "MMMMMMMNKOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO0NMMMMMM\r\n"
										+ "MMMMW0lcllooooooooooooooooooooooooooooooollcl0WMMM\r\n"
										+ "MMMWd,oXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWXd,dNMM\r\n"
										+ "MMMk'oWMMMWXOkOKWMMMMMMMMMMMMMMMMMMMMMMMMMMMWd'kMM\r\n"
										+ "MMMd'kMMMXo.   .cKMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMx.      oWMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMO'     .kWMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMWKo:;;l0WMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMk'dMM\r\n"
										+ "MMMx,kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMO,dMM\r\n"
										+ "MMMd,kMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMMO,dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMWOc,,;l0WMMMO'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMx.     'OMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMWo      .xMMMk'dMM\r\n"
										+ "MMMd'kMMMMMMMMMMMMMMMMMMMMMMMMMMMXo'...'dNMMMk'dMM\r\n"
										+ "MMMO,oWMMMMMMMMMMMMMMMMMMMMMMMMMMMWN0O0NMMMMWo'kMM\r\n"
										+ "MWWWx;lKWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWKl;xWMM\r\n"
										+ "WWWMW0olllllloooooooooooolloooloooolllooolllo0WWMM\r\n"
										+ "WWMMWWMWX0OOO00000000000OOO000O0000OOO0000XWMMMMMM\r\n"
										+ "MMMWWWMMMMMMMWMWWWWWWWMMWMWMMWWMWWMWWMWWMMWMWWMWWM");
							} else if(d2.getNumber()==3) {
								System.out.print("MMMWXOkxxxxxxxxxxkxxxxxxxxxxxxxxxxxxxxxxxxxxOXWMMM\r\n"
										+ "MNkc:cclllllllllooollllllllllllllllllllllllcc:cxXM\r\n"
										+ "K:,dXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMXx;;O\r\n"
										+ ":'0MMMMMMWNNWWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMK;,\r\n"
										+ ".cWMMMMXd:,'':kNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMK;       lNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMk.       ,KMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMXc      .dWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMNOl:;co0WMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ "'lWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMWKkxxk0NMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMWO;.    .;kWMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMK,        '0MMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMK,        'OMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMWO;.    .;OWMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMWKkxxk0NMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWKdc;:lkNMMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWx.      :KMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMX;        dMMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWd.      '0MMMMo.\r\n"
										+ ".cWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWOc,'';oKMMMMMo.\r\n"
										+ ":'0MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWNNWMMMMMMK,,\r\n"
										+ "K:,dXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWXx;;0\r\n"
										+ "MNkc:cclllllllllllllllllllllllllllllllllllllc:cxXM\r\n"
										+ "WWMWKxlcccccccccccccccccccccccccccccccccccclx0WMMM");
							} else if(d2.getNumber()==4) {
								System.out.print("MMMMMMMMWNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNWWMWWMMMW\r\n"
										+ "MMMMNOolloooooooooooooooooooooooooooooooolox0NMMMW\r\n"
										+ "MMWOc:d0XNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNX0d:cKMWW\r\n"
										+ "MM0,cXMMMMMWWWMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMK;:XMM\r\n"
										+ "MMd'kMMMWKo;,,ckNMMMMMMMMMMMMMMMMNx:,,:dXMMMMd'kMM\r\n"
										+ "MMd'OMMM0'      oNMMMMMMMMMMMMMMXc      ;KMMMd'xMM\r\n"
										+ "MMd'OMMMx.      :NMMMMMMMMMMMMMMK,      .OMMMd'xMM\r\n"
										+ "MMd'OMMMNo.   .:0WMMMMMMMMMMMMMMWk,    'xNMMMd'xMM\r\n"
										+ "MMd'OMMMMWXOxk0WMMMMMMMMMMMMMMMMMMN0kxONMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMd'xMM\r\n"
										+ "MMd'OMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMx'kMM\r\n"
										+ "MMd'OMMMMMWX0KNMMMMMMMMMMMMMMMMMMMWXK0XWMMMMMx,kMM\r\n"
										+ "MMd'OMMMWk;...'oXMMMMMMMMMMMMMMMM0c....:OWMMMd'xMM\r\n"
										+ "MMd'OMMMk.      cNMMMMMMMMMMMMMMX;      '0MMMd'xMM\r\n"
										+ "MMd'OMMMk.      cNMMMMMMMMMMMMMMX;      ,0MMMd'xMM\r\n"
										+ "MMd'kMMMWk:...,oXMMMMMMMMMMMMMMMMKl'..,c0WMMMd'xMM\r\n"
										+ "MMO,oWMMMMWXKXNMMMMMMMMMMMMMMMMMMMMNXXNWMMMMNc,0MM\r\n"
										+ "MMWk;cONMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWXk::OMMM\r\n"
										+ "MMMMXdclllllllllllllllllllllllooolllllllllllxXMMMM\r\n"
										+ "MMMMMMWX0kkkkkkkkkkkkkkkkkkkkkOOOkkkkkkkO0NMMMMMMM");
							} else if(d2.getNumber()==5) {
								System.out.print("MWMWWMMMMMMMMMWWMMMMMMMWMMMMMMMMWWWMMWMMMMMMWMWMMM\r\n"
										+ "WWMWWN0kxxxkOOkxxxxxxxxxkOOOxxkOkxxxkOkxk0NMWWWMMW\r\n"
										+ "MWW0lcloooddxxdooooooooddxxxdddxdddddxxdllcoKWMMMM\r\n"
										+ "MWx,lXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMW0c;OMMMM\r\n"
										+ "MK,cWMMMMXOxk0NMMMMMMMMMMMMMMMMMMX0kxONMMMMX;:XMMM\r\n"
										+ "MO'oMMMNx'   .,kWMMMMMMMMMMMMMMWx'.  .,kWMMWc,KMMM\r\n"
										+ "MO'oMMMO'      ;KMMMMMMMMMMMMMM0'      ;KMMWc,KMMM\r\n"
										+ "MO'oMMMXc      lNMMMMMMMMMMMMMMX:     .lNMMWc,KMMM\r\n"
										+ "MO'oMMMMXxc;;ckNMMMMMMMMMMMMMMMMNxc;;ckNMMMWc,KMMM\r\n"
										+ "MO,dMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWl;KMMM\r\n"
										+ "MO,dMMMMMMMMMMMMMMMMXkolodkNMMMMMMMMMMMMMMMWl;KMMM\r\n"
										+ "MO'oMMMMMMMMMMMMMMMO,      ;KMMMMMMMMMMMMMMWc,KMMM\r\n"
										+ "MO'oMMMMMMMMMMMMMMWl        dMMMMMMMMMMMMMMWc,KMMM\r\n"
										+ "MO'oMMMMMMMMMMMMMMWO'      ;0MMMMMMMMMMMMMMWc,KMMM\r\n"
										+ "MO'oMMMMMMMMMMMMMMMMXxllodkNMMMMMMMMMMMMMMMWc,KMMM\r\n"
										+ "MO'oMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWc,KMMM\r\n"
										+ "MO'oMMMMNxc;:lONMMMMMMMMMMMMMMMMNkc::lkNMMMWc,KMMM\r\n"
										+ "MO'oMMMX:      lNMMMMMMMMMMMMMMXc      lNMMWc,KMMM\r\n"
										+ "MO'oMMMO.      ,KMMMMMMMMMMMMMMO'      ,KMMWc,KMMM\r\n"
										+ "MO'oMMMNx.    ,kWMMMMMMMMMMMMMMNx'    'kWMMWc,KMMM\r\n"
										+ "M0,cWMMMMXkdxONMMMMMMMMMMMMMMMMMMXkdxONMMMMX::XMMM\r\n"
										+ "MWx,oXMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWKc;OMMMM\r\n"
										+ "MMW0lcloddddddddddddddddddddddddddddddddolcoKMMMMM\r\n"
										+ "MMMMMXOxxddddddddddddddddddddddddddddddxk0NMMMMMMM\r\n"
										+ "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
							} else if(d2.getNumber()==6){
								System.out.print("MMWWWKkollllllllllllllllllllllllllllllllllox0WMMMM\r\n"
										+ "MMWkccldxkkxkkkkkkkkkkkkkkkkkkkkkkkkkkkkkxdl:ckNMM\r\n"
										+ "MNo,dNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNx,lNM\r\n"
										+ "MO'lWMMMMNOddx0WMMMMMMMMMMMMMMMMMMWKxddOXMMMMMd.xM\r\n"
										+ "Mx.dMMMWk,    .:KMMMMMMMMMMMMMMMMXc.    'xWMMMx'dM\r\n"
										+ "Mx.dMMMK;       lWMMMMMMMMMMMMMMMo       '0MMMx.dM\r\n"
										+ "Mx.dMMMNl      .xWMMMMMMMMMMMMMMMO.      :XMMMx.dM\r\n"
										+ "Mx.dMMMMNkc,';lOWMMMMMMMMMMMMMMMMW0o;',:xXMMMMk'dM\r\n"
										+ "Mx.dMMMMMMMWWWMMMMMMMMMMMMMMMMMMMMMMWWWMMMMMMMk'dM\r\n"
										+ "Mx.dMMMMMN0xdkKWMMMMMMMMMMMMMMMMMMWKkdxONMMMMMk'dM\r\n"
										+ "Mx.dMMMWO,    .cKMMMMMMMMMMMMMMMMXl.    ,kWMMMk'dM\r\n"
										+ "Mx.dMMMX;       lWMMMMMMMMMMMMMMMo       '0MMMk'dM\r\n"
										+ "Mx.dMMMNl      .xWMMMMMMMMMMMMMMMk.      :XMMMx.dM\r\n"
										+ "Mx.dMMMMNx:'',cOWMMMMMMMMMMMMMMMMW0l,'':dXMMMMx'dM\r\n"
										+ "Mk'dMMMMMMMWNWMMMMMMMMMMMMMMMMMMMMMMWNWWMMMMMMk'dM\r\n"
										+ "Mx'dMMMMMN0kxkKWMMMMMMMMMMMMMMMMMMWXOxx0NMMMMMk.dM\r\n"
										+ "Mx.dMMMWO;.   .cKMMMMMMMMMMMMMMMMXo.    ,kWMMMx.dM\r\n"
										+ "Mx.dMMMX;       lWMMMMMMMMMMMMMMMd       '0MMMx.dM\r\n"
										+ "Mx.dMMMNl      .dWMMMMMMMMMMMMMMMk.      :XMMMk.dM\r\n"
										+ "Mx.dMMMMXx:,''cOWMMMMMMMMMMMMMMMMWOc,.';dXMMMMx.dM\r\n"
										+ "MK;;XMMMMMWNNWWMMMMMMMMMMMMMMMMMMMMMWNNWMMMMMNc,0M\r\n"
										+ "MM0::xKWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMWXx:;OWM\r\n"
										+ "MMMNklclllllllllloollllllllllllllllllllllllclxNMMM\r\n"
										+ "MMMMMMNKOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOKNMMMMMM");
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
									exp = batting * 3 + exp;
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
		
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("미니게임 오류!");
		} finally {
			close();
		}
	}
}
