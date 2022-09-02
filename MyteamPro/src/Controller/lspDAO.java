package Controller;

import java.util.Scanner;

import model.lspVO;

public class lspDAO {

	private int exp;
	private int batting;
	private int input;

	lspVO lv = new lspVO();

	public void playGame() {
		Scanner sc = new Scanner(System.in);
		
		exp = 10;
		int winCount = 0;
		int loseCount = 0;
		int round = 1;
		
		System.out.println("exp = " + exp);
		while (true) {
			System.out.print("batting exp = ");
			batting = sc.nextInt();
			System.out.println();
			if (exp - batting >= 0) {
				break;
			} else {
				System.out.println("exp가 부족합니다.\t");
			}
		}

		while (true) {
			System.out.println("["+round+" 라운드]");
			System.out.print("[1]가위 [2]바위 [3]보 >> ");
			input = sc.nextInt();
			lv.setNumber();
			if (lv.getNumber() == 1) {
				System.out.print("✌\t🆚\t");
				if (input == 1) {
					System.out.println("✌");
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
					System.out.println("👊");
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
					System.out.println("✋");
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
				System.out.print("👊\t🆚\t");
				if (input == 1) {
					System.out.println("✌");
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
					System.out.println("👊");
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
					System.out.println("✋");
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
				System.out.print("✋\t🆚\t");
				if (input == 1) {
					System.out.println("✌");
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
					System.out.println("👊");
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
					System.out.println("✋");
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
		System.out.println("현재exp : " + exp);
	}
}
