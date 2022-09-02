package Model;

import java.util.Random;

public class lspVO {

	private int number;
	Random rd = new Random();
	
	public void setNumber() {
		number = rd.nextInt(3)+1;
	}
	
	public int getNumber() {
		return number;
	}
	
}
