package View;

import java.util.Scanner;

public class v {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		
		System.out.println("========================");
		System.out.println("      누 들 킹 RPG");
		System.out.println();
		System.out.print("[1]로그인 [2]회원가입 [3]종료");
		System.out.println("\n========================");
		menu = sc.nextInt();
		
		while(true) {
			if(menu==1) {
				
			} else if(menu==2) {
				
			} else if(menu==3) {
				System.out.println("게임을 종료하겠습니다");
				break;
			}
		}
	}

}
