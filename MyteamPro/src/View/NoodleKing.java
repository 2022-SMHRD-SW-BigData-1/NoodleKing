package View;

import java.util.Scanner;

import Controller.Battle;
import Controller.Donate;
import Controller.Level;
import Controller.Ranking;
import Controller.Userinfo;
import Controller.diceDAO;
import Controller.lspDAO;
import Model.NoodleModel;

public class NoodleKing {
	static int count = 1;
	
	public static void main(String[] args) {
		Battle bat = new Battle();
		Level lv = new Level();
		Userinfo user = new Userinfo();
		Donate don = new Donate();
		lspDAO lsp = new lspDAO();
		diceDAO dice = new diceDAO();
		Ranking rank = new Ranking();
		Level lev = new Level();

		Scanner sc = new Scanner(System.in);

		int menu = 0;
		String id = "";
		String pw = "";
		String nick = "";
		String name = "";
		NoodleModel noo = new NoodleModel();
		
				

		while (true) {
			
			System.out.println("========================");
			System.out.println("      누 들 킹 RPG");
			System.out.println();
			System.out.println("[1]로그인 [2]회원가입 [3]종료");
			System.out.println("========================");
			menu = sc.nextInt();

			if (menu == 1) {
				System.out.print("ID : ");
				id = sc.next();
				System.out.print("PW : ");
				pw = sc.next();
				
				int log = user.login(id, pw);
				
				if(log == 1) {
					System.out.println("로그인 성공!");
					
					//로그인 후 화면
					while(true) {
						System.out.println("===========================================================");
						System.out.println("      누 들 킹 RPG");
						System.out.println();
						System.out.println("[1]사냥하기 [2]미니게임 [3] 랭킹 [4]후원하기 [5]캐릭상태보기 [6]로그아웃");
						System.out.println("===========================================================");
						menu = sc.nextInt();
						
						if(menu==1) {
							//몬스터정보랑 선택지
							bat.monster(id);
							menu = sc.nextInt();
							bat.fight(id, menu);
							lev.lvUp(id);
														
						} else if(menu==2) {
							// 미니게임
							System.out.println("플레이할 미니게임을 고르세요");
							System.out.print("[1]가위바위보 [2]홀짝");
							menu = sc.nextInt();
							if(menu == 1) {
								lsp.playGame(id);
							} else if(menu == 2) {
								dice.playGame(id);
							}
							
							
						} else if(menu==3) {
							rank.rank(id);;
							
						} else if(menu==4) {
							
						} else if(menu==5) {
							
						} else if(menu==6) {
							break;
						}
					}
					
					
					
					
					
					
					
				} else if(log == 0) {
					System.out.println("비밀번호 오류!");
				} else if(log == -1) {
					System.out.println("없는 아이디 입니다!");
				}

			} else if (menu == 2) {
				System.out.print("아이디입력");
				id = sc.next();
				System.out.print("비번입력");
				pw = sc.next();
				System.out.print("이름입력");
				name = sc.next();
				System.out.print("닉입력");
				nick = sc.next();

				int us = user.createUser(id, pw, name, nick);
				if (us > 0) {
					System.out.println("계정 생성 성공!");
				} else {
					System.out.println("계정 생성 실패!");
				}

				int ch = user.createCha(id, pw, nick);
				if (ch > 0) {
					System.out.println("캐릭터 생성 성공!");
				} else {
					System.out.println("캐릭터 생성 실패!");
				}

			} else if (menu == 3) {
				System.out.println("게임을 종료하겠습니다");
				break;
			}
		}
	}
	
}
