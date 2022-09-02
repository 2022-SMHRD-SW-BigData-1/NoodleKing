package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Donate {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	static int count = 1;
	//DB서버 연결

	
	//Admin 로그인
	
	public void donate() {
		Scanner sc = new Scanner(System.in);
		int an = 0;
		while(true) {
			System.out.print("[1]10만원 [2]50만원 [3]100만원 >> ");
			int num = sc.nextInt();
			System.out.println("\n==============================");
			System.out.println("스마트인재 은행 : 352-0702-0847-77");
			System.out.println("==============================");
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					if (count <= 5) {
						count++;
					} else {
						timer.cancel();
						System.out.print("입금완료하셨나요 >> [1]Y [2]N >> ");
					}
				}
			};
			timer.schedule(task, 1000, 1000);
			
			an = sc.nextInt();
			
			if(an==1) {
				System.out.println("\nadmin_ID : ");
				System.out.println("admin_PW : ");
				break;
			} else {
				System.out.println();
			}
		}
	}
	
	
}
