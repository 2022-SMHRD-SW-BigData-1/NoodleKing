package Controller;

import java.util.Scanner;

import model.diceVO;

public class diceDAO {
	private int exp;
	private int batting;
	private int input;
	
	diceVO d1 = new diceVO();
	diceVO d2 = new diceVO();

	public void playGame() {
		Scanner sc = new Scanner(System.in);
		
		exp = 10;
		int winCount = 0;
		int loseCount = 0;
		int round = 1;
		int output = 0;
		
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
			System.out.print("[1]홀 [2]짝 >> ");
			input = sc.nextInt();
			d1.setNumber();
			d2.setNumber();
			int dsum = d1.getNumber() + d2.getNumber();

			System.out.println("🎲 : " + d1.getNumber());
			System.out.println("🎲 : " + d2.getNumber());

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
					exp = exp + batting * 3;
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
		System.out.println("현재exp : " + exp);
	}
}
