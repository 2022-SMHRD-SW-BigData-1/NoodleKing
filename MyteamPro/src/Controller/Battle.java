package Controller;

import java.util.Random;

import Model.NoodleModel;

public class Battle {
	
	NoodleModel lvTotal = new NoodleModel();
	Random rd = new Random();

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
