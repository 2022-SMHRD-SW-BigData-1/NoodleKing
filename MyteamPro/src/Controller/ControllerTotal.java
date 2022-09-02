package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import Model.m;

public class ControllerTotal {

	m lvTotal = new m();
	Random rd = new Random();
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public void Create() {
		Random rd = new Random();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@Project-db-stu.ddns.net:1524:xe";
			String db_id = "campus_h_0830_5";
			String db_pw = "smhrd5";

			conn = DriverManager.getConnection(url, db_id, db_pw);

			int sum = 0;

			int[] arr = new int[4];

			while (true) {
				for (int i = 0; i < 4; i++) {
					arr[i] = rd.nextInt(6) + 4;
					sum += arr[i];
				}
				if (sum <= 22) {
					break;
				}
				sum = 0;
			}

			int str = arr[0];
			int dex = arr[1];
			int iq = arr[2];
			int luk = arr[3];

			String sql = "insert into char1 values(?, ?, ?, ?)";

			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, str);
			psmt.setInt(2, dex);
			psmt.setInt(3, iq);
			psmt.setInt(4, luk);

			int row = psmt.executeUpdate();
			if (row > 0) {
				System.out.println("캐릭터 생성 성공!!");
			} else {
				System.out.println("캐릭터 생성 실패!!");
			}

			System.out.print("힘: " + str + "\t" + "민첩: " + dex + "\t" + "지능: " + iq + "\t" + "운: " + luk);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("스탯-오류발생");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("스탯데이터베이스 오류발생");
		}

		finally {
			try {
				if (conn != null) {
					conn.close();
					if (psmt != null) {
						psmt.close();
					}
				}
			} catch (SQLException e) {

				e.printStackTrace();
				System.out.println("자원 반납시 오류");
			}
		}
	}

	public void connect() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String db_id = "campus_h_0830_5";
			String db_pw = "smhrd5";

			conn = DriverManager.getConnection(url, db_id, db_pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("접속 오류");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("로그인 접속오류");
		}
	}

	private void close() {
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

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("자원 반납시 오류");
		}

	}

	public void lvUp(int exp, int lv, int score, int select) {
		if (lvTotal.getLv() == 1) {
			if (exp == 100) {
				lv += 1;
				score += 100000;
				if (lvTotal.getHp() < 5) {
					lvTotal.setHp(+1);
				}

			}
			exp = 0;
		}
		if (lvTotal.getLv() == 2) {
			if (exp == 200) {
				lv += 1;
				score += 200000;
				if (lvTotal.getHp() < 5) {
					lvTotal.setHp(+1);
				}
			}
			exp = 0;
		}
		if (lvTotal.getLv() == 3) {
			if (exp == 300) {
				lv += 1;
				score += 300000;
				if (lvTotal.getHp() < 5) {
					lvTotal.setHp(+1);
				}
			}
			exp = 0;
		}
		if (lvTotal.getLv() == 4) {
			if (exp == 400) {
				lv += 1;
				score += 400000;
				if (lvTotal.getHp() < 5) {
					lvTotal.setHp(+1);
				}
			}
			exp = 0;
		}
		if (lvTotal.getLv() == 5) {
			if (exp == 500) {
				lv += 1;
				score += 500000;
				if (lvTotal.getHp() < 5) {
					lvTotal.setHp(+1);
				}
			}
			exp = 0;
		}
		if (lvTotal.getLv() == 6) {
			score += 1000000;
			System.out.println("축하드립니다!!!! 게임을 클리어 하셨습니다!!!");
		}
	}

	public void stats(int select) {
		if (select == 1) {
			lvTotal.setStr(+1);
		} else if (select == 2) {
			lvTotal.setDex(+1);
		} else if (select == 3) {
			lvTotal.setIq(+1);
		} else if (select == 4) {
			lvTotal.setLuk(+1);
		}
	}

	public void fight(int choice) {
		System.out.println("[1]평타치기(" + (lvTotal.getStr() * 5) + "%");
		System.out.println("[2]스킬쓰기(" + (lvTotal.getIq() * 5) + "%");
		System.out.println("[3]도망가기(" + (lvTotal.getDex() * 5) + "%");
		System.out.println("[4]협상하기(" + (lvTotal.getLuk() * 5) + "%");
		fightChoice(choice);

	}

	private void fightChoice(int choice) {
		int length = 0;
		int succ = 1;
		int fail = 0;
		if (choice == 1) {
			length = 100 / lvTotal.getStr();
			int[] arr = new int[length];
			for (int i = 1; i < arr.length; i++) {
				arr[0] = succ;
				arr[i] = fail;
			}
			int luck = rd.nextInt(length - 1);
			if (luck == arr[0]) {
				System.out.println("전투에서 승리하였습니다.");
			} else {
				lvTotal.setHp(-1);
				System.out.println("전투에서 패배하셨습니다.");
			}
		} else if (choice == 2) {
			length = 100 / lvTotal.getIq();
			int[] arr = new int[length];
			for (int i = 1; i < arr.length; i++) {
				arr[0] = succ;
				arr[i] = fail;
			}
			int luck = rd.nextInt(length - 1);
			if (luck == arr[0]) {
				System.out.println("전투에서 승리하였습니다.");
			} else {
				lvTotal.setHp(-1);
				System.out.println("전투에서 패배하셨습니다.");
			}
		} else if (choice == 3) {
			length = 100 / lvTotal.getDex();
			int[] arr = new int[length];
			for (int i = 1; i < arr.length; i++) {
				arr[0] = succ;
				arr[i] = fail;
			}
			int luck = rd.nextInt(length - 1);
			if (luck == arr[0]) {
				System.out.println("전투에서 승리하였습니다.");
			} else {
				lvTotal.setHp(-1);
				System.out.println("전투에서 패배하셨습니다.");
			}
		} else if (choice == 4) {
			length = 100 / lvTotal.getLuk();
			int[] arr = new int[length];
			for (int i = 1; i < arr.length; i++) {
				arr[0] = succ;
				arr[i] = fail;
			}
			int luck = rd.nextInt(length - 1);
			if (luck == arr[0]) {
				System.out.println("전투에서 승리하였습니다.");
			} else {
				lvTotal.setHp(-1);
				System.out.println("전투에서 패배하셨습니다.");
			}
		}
	}

}
