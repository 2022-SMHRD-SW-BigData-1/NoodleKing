package View;

import java.util.Scanner;

import Controller.Battle;
import Controller.Donate;
import Controller.Level;
import Controller.BGM;
import Controller.Ranking;
import Controller.Userinfo;
import Controller.diceDAO;
import Controller.editDAO;
import Controller.lspDAO;
import Controller.statusDAO;
import Model.BGMModel;
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
		statusDAO stat = new statusDAO();
		editDAO ed = new editDAO();
		
		Scanner sc = new Scanner(System.in);
		BGM bgm = new BGM();
		
		int menu = 0;
		String id = "";
		String pw = "";
		String nick = "";
		String name = "";			
		
		while (true) {
			bgm.play(1);
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
						System.out.println("[1]사냥하기 [2]미니게임 [3]랭킹 [4]후원하기 [5]캐릭상태보기 [6]로그아웃");
						System.out.println("===========================================================");
						menu = sc.nextInt();
						if(menu==1) {
							bgm.play(2);
							//몬스터정보랑 선택지
							bat.chaInfo(id);
							bat.monInfo(id);
							menu = sc.nextInt();
							bat.fight(id, menu);
							bat.exp(id);
							lev.lvUp(id);
							bat.chaInfo(id);
									
						} else if(menu==2) {
							bgm.play(3);
							// 미니게임
							System.out.println("플레이할 미니게임을 고르세요");
							System.out.print("[1]가위바위보 [2]홀짝 [3]돌아가기");
							menu = sc.nextInt();
							while(true) {
								if(menu == 1) {
									lsp.playGame(id);
									bgm.play(1);
									break;
								} else if(menu == 2) {
									dice.playGame(id);
									bgm.play(1);
									break;
								} else if(menu == 3) {
									bgm.play(1);
									break;
								}
							}

						} else if(menu==3) {
							bgm.play(6);
							rank.rank(id);;
						} else if(menu==4) {
							bgm.play(4);
							don.donate(id);
							bgm.play(1);
						} else if(menu==5) {
							bgm.play(5);
							while(true) {
								stat.status(id);
								System.out.print("[1]캐릭터 수정하기 [2]돌아가기");
								menu = sc.nextInt();
								if(menu == 1) {
									if(ed.edit(id) == 1) {
										System.out.println("변경하실 캐릭터의 정보를 고르세요");
										System.out.print("[1]레벨 [2]라이프 [3]스텟 [4]돌아가기");
										menu = sc.nextInt();
										
										if(menu == 1) {
											System.out.print("변경하실 캐릭터의 레벨을 적으세요(최대 5) : ");
											menu = sc.nextInt();											
											ed.editLv(id, menu);
											
										} else if(menu == 2) {
											System.out.print("변경하실 캐릭터의 HP를 적으세요(최대 5) : ");
											menu = sc.nextInt();
											ed.editHP(id, menu);
											
										} else if(menu == 3) {
											System.out.println("변경하실 캐릭터의 스텟을 고르세요");
											System.out.println("[1]힘 [2]지능 [3]민첩 [4]운");
											menu = sc.nextInt();
											System.out.print("변경하실 수치를 고르세요(최대 10) : ");
											int st = sc.nextInt();
											ed.editStat(id, menu, st);
										} else {
											break;
										}
									} else if(ed.edit(id) == 0) {
										System.out.println("후원을 하신 분만 이용하실 수 있는 메뉴입니다.");
										break;
									}
								} else if(menu == 2) {
									bgm.play(1);
									break;
								}
							}

								
							
						} else if(menu==6) {
							bgm.play(1);
							break;
						}
					}
				} else if(log == 0) {
					System.out.println("비밀번호 오류!");
				} else if(log == -1) {
					System.out.println("없는 아이디 입니다!");
				}

			} else if (menu == 2) {
				System.out.print("아이디 입력 : ");
				id = sc.next();
				System.out.print("비번 입력 : ");
				pw = sc.next();
				System.out.print("이름 입력 : ");
				name = sc.next();
				System.out.print("닉네임 입력 : ");
				nick = sc.next();
				
				if(user.createOverlapID(id)==1) {
					
					System.out.println("아이디가 중복입니다.");
					System.out.println("다른 아이디를 적어주세요");
					
				} else if(user.createOverlapNick(nick)==1) {
					
					System.out.println("닉네임이 중복입니다.");
					System.out.println("다른 닉네임을 적어주세요");
					
				} else {
					
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
				}

			} else if (menu == 3) {
				System.out.println("게임을 종료하겠습니다");
				bgm.stop();
				break;
			}
		}
	}
	
}
