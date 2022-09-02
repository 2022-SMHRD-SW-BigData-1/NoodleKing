import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class s {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement psmt = null;

		Random rd = new Random();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:@Project-db-stu.ddns.net:1524:xe";
			String db_id = "campus_h_0830_5";
			String db_pw = "smhrd5";

			conn = DriverManager.getConnection(url, db_id, db_pw);

			int sum = 0;

			int[] arr = new int[4];
			
			while(true) {
				for(int i=0; i<4; i++) {
					arr[i] = rd.nextInt(6) + 4;
					sum += arr[i];	
				}
				if(sum<=22) {
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

}
