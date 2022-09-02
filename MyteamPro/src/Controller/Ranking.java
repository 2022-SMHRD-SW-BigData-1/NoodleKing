package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

import Model.m;

public class Ranking {

	ArrayList<Integer> list = new ArrayList<Integer>();
	m lv = new m();
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	public void rang() {

//		try {
			list.add(1000);
			list.add(2000);
			list.add(3000);
			list.add(14000);
			list.add(50300);
			list.add(6300);
//			lc.connect();
//			String sql = "select * from user_info";
//			psmt = conn.prepareStatement(sql);
//			rs = psmt.executeQuery();
//			while(rs.next()) {
//				String id = rs.getString("id");
//				String name = rs.getString("name");
//				String nick = rs.getString("nick");
//			}

			Collections.sort(list, Collections.reverseOrder());
			System.out.println("=================랭킹순위=================");
			for(int i = 0; i < list.size(); i++) {
				System.out.println("이름 : " + lv.getName() + " 닉네임 : " + lv.getNick() + " 스코어 : "+list.get(i));
			}
			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		lc.close();
	}
}
