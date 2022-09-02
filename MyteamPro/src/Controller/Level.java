package Controller;

import Model.NoodleModel;

public class Level {

	NoodleModel lvTotal = new NoodleModel();

	public void lvUp(int exp, int lv, int score, int select) {
		if (lvTotal.getLv() == 1) {
			if (exp == 100) {
				lv += 1;
				score += 100000;
				if (lvTotal.getHp() < 5) {
					lvTotal.setHp(+1);
				}

			}
			exp = 0;
		}
		if (lvTotal.getLv() == 2) {
			if (exp == 200) {
				lv += 1;
				score += 200000;
				if (lvTotal.getHp() < 5) {
					lvTotal.setHp(+1);
				}
			}
			exp = 0;
		}
		if (lvTotal.getLv() == 3) {
			if (exp == 300) {
				lv += 1;
				score += 300000;
				if (lvTotal.getHp() < 5) {
					lvTotal.setHp(+1);
				}
			}
			exp = 0;
		}
		if (lvTotal.getLv() == 4) {
			if (exp == 400) {
				lv += 1;
				score += 400000;
				if (lvTotal.getHp() < 5) {
					lvTotal.setHp(+1);
				}
			}
			exp = 0;
		}
		if (lvTotal.getLv() == 5) {
			if (exp == 500) {
				lv += 1;
				score += 500000;
				if (lvTotal.getHp() < 5) {
					lvTotal.setHp(+1);
				}
			}
			exp = 0;
		}
		if (lvTotal.getLv() == 6) {
			score += 1000000;
			System.out.println("축하드립니다!!!! 게임을 클리어 하셨습니다!!!");
		}
	}

	public void statsUp(int select) {
		if (select == 1) {
			lvTotal.setStr(+1);
		} else if (select == 2) {
			lvTotal.setDex(+1);
		} else if (select == 3) {
			lvTotal.setIq(+1);
		} else if (select == 4) {
			lvTotal.setLuk(+1);
		}
	}
	
}
