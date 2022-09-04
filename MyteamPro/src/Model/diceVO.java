package Model;

import java.util.Random;

public class diceVO {

	private int number;
	Random rd = new Random();
	
	public void setNumber() {
		number = rd.nextInt(6)+1;
	}
	
	public int getNumber() {
		return number;
	}
	
}
