package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import Model.Monster;
import Model.NoodleModel;

public class Battle {
	NoodleModel lvTotal;
	Random rd = new Random();
	Userinfo user = new Userinfo();
	Sound sou = new Sound();
	Monster mon = new Monster();

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String m_name;
	String nick;
	int index;
	int exp, score, hp, mp, lv, str, iq, dex, luk;
	int m_exp, m_score, m_lv;
	int match;
	int ran = rd.nextInt(100);
	ArrayList<String> mon2;

	int menu;
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

	public void insertModel(String id) {
		try {
			getCon();

			String sql = "select * from character where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			if (rs.next()) {
				lvTotal = new NoodleModel(rs.getInt("exp"), rs.getInt("score"), rs.getInt("HP"), rs.getInt("MP"),
						rs.getInt("lv"), rs.getInt("str"), rs.getInt("int"), rs.getInt("dex"), rs.getInt("luk"),
						rs.getString("nick"));
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

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public void insertMonModel(String id) {
		try {
			getCon();
			// 몬스터 정보
			String sql = "select * from (select * from monster order by DBMS_RANDOM.RANDOM) where rownum = 1 and m_lv = ?";
			// db에서 랜덤뽑기
			// 자바에서 뽑기

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, lv);
			rs = psmt.executeQuery();

			if (rs.next()) {
				m_name = rs.getString("m_name");
				m_lv = rs.getInt("m_lv");
				m_exp = rs.getInt("m_exp");
				m_score = rs.getInt("m_score");
			}
			int index = getMon(m_name);
			mon2 = mon.getMon();

			System.out.println(mon2.get(index));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public int getMon(String m_name) {
		String[] mon = { "속찬라면", "콩국수", "봉골라면", "메밀소바", "스파게티", "라면볶이", "쌀라면", "독도와함께라면", "목이라면", "손큰라면", "간짬뽕", "국민라면",
				"남자라면", "사리면", "속풀라면", "우리밀라면", "짜짜로니", "카레라면", "컵누들", "황태라면", "나가사끼짬뽕", "새우탕", "손칼국수", "쇠고기면", "수타면",
				"열라면", "진짬뽕", "참깨라면", "튀김우동", "파워라면", "꼬꼬면", "너구리", "무파마", "비빔면", "삼양라면", "스낵면", "안성탕면", "오모리김치찌개",
				"오징어짬뽕", "틈새라면", "감자면", "공화춘", "마왕라면", "부대찌개라면", "불닭볶음면", "사리곰탕", "순후추라면", "육개장", "진라면", "짜파게티" };

		while (true) {
			if (index > 49) {
				index = 0;
			}
			if (m_name.equals(mon[index])) {
				return index;
			} else {
				index++;
			}
		}
	}

	public void chaInfo(String id) {
		insertModel(id);
		System.out.println("==============================캐릭터정보==============================");
		System.out.println("닉네임\t : " + nick);
		System.out.print("HP\t : " + hp + "\t");
		System.out.println("MP\t : " + mp + "\t");
		System.out.print("레벨\t : " + lv + "\t");
		System.out.print("현재 경험치\t : " + exp + "\t");
		System.out.print("현재 점수\t : " + score + "\t");
		System.out.println();
		System.out.print("힘\t : " + str + "\t");
		System.out.print("지능\t : " + iq + "\t");
		System.out.print("민첩\t : " + dex + "\t");
		System.out.println("운\t : " + luk + "\t");
		System.out.println("===================================================================");
		System.out.println();
	}

	public void monInfo(String id) {
		insertMonModel(id);
		System.out.println();
		System.out.println("==============================몬스터정보==============================");
		System.out.println("몬스터 이름\t: " + m_name);
		System.out.print("레벨\t: " + m_lv);
		System.out.print("\t몬스터 처치 시 경험치\t: " + m_exp);
		System.out.println("\t몬스터 처치 시 점수\t: " + m_score);
		System.out.println("===================================================================");
		System.out.println();
		chaInfo(id);
		// 선택지
		System.out.println("====================전투옵션====================");
		System.out.println("[1]평타치기(" + str * 10 + "% 승리)");
		System.out.println("[2]스킬쓰기(" + iq * 10 + "% 승리)");
		System.out.println("[3]도망가기(" + dex * 10 + "% 승리)");
		System.out.println("[4]협상하기(" + luk * 10 + "% 승리)");
		System.out.println("==============================================");

	}

	public void fight(String id, int choice) {

		if (choice == 1) {
			sou.play(1);
			length = str * 10;
			for (int i = 0; i < length; i++) {
				arr[i] = succ;
			}

			for (int j = length; j < 100; j++) {
				arr[j] = fail;
			}

			match = arr[ran];
			if (match == succ) {
				exp += m_exp;
				score += 1000 + m_score;
				System.out.println("전투에서 승리하였습니다.");
			} else {
				hp -= 1;
				System.out.println("전투에서 패배하셨습니다.");
			}
		} else if (choice == 2) {
			sou.play(2);
			length = iq * 10;
			for (int i = 0; i < length; i++) {
				arr[i] = succ;
			}
			for (int j = length; j < 100; j++) {
				arr[j] = fail;
			}

			match = arr[ran];
			if (match == succ) {
				mp -= 10;
				exp += m_exp;
				score += 1000 + m_score;
				System.out.println("전투에서 승리하였습니다.");
			} else {
				mp -= 10;
				hp -= 1;
				System.out.println("전투에서 패배하셨습니다.");
			}
		} else if (choice == 3) {
			sou.play(3);
			length = dex * 10;
			for (int i = 0; i < length; i++) {
				arr[i] = succ;
			}
			for (int j = length; j < 100; j++) {
				arr[j] = fail;
			}
			match = arr[ran];
			if (match == succ) {
				exp += m_exp;
				score += 1000 + m_score;
				System.out.println("전투에서 승리하였습니다.");
			} else {
				hp -= 1;
				System.out.println("전투에서 패배하셨습니다.");
			}
		} else if (choice == 4) {
			sou.play(4);
			length = luk * 10;
			for (int i = 0; i < length; i++) {
				arr[i] = succ;
			}
			for (int j = length; j < 100; j++) {
				arr[j] = fail;
			}
			match = arr[ran];
			if (match == succ) {
				exp += m_exp;
				score += 1000 + m_score;
				System.out.println("전투에서 승리하였습니다.");
			} else {
				hp -= 1;
				System.out.println("전투에서 패배하셨습니다.");
			}
		}
	}

	public void exp(String id) {

		try {
			getCon();

			String sql = "update character set hp = ?, mp = ?, exp = ?, score = ? where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, hp);
			psmt.setInt(2, mp);
			psmt.setInt(3, exp);
			psmt.setInt(4, score);
			psmt.setString(5, id);
			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("오류!");
		} finally {
			close();
		}

	}

	public int die(String id) {
		if (hp <= 0) {

			try {
				getCon();

				String sql = "delete from character where id = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.executeUpdate();

				sql = "delete from user_info where id = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return 1;
		}
		return 0;
	}
}
