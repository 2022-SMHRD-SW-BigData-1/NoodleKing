package Model;

public class NoodleModel {

	private int str;
	private int dex;
	private int iq;
	private int luk;
	private String id;
	private String pw;
	private String nick;
	private int lv;
	private int hp;
	private int mp;
	private int exp;
	private int score;
	
	private String name;
	private String donate;
	private String m_name;
	private int m_lv;
	private int m_exp;
	
	
	public NoodleModel(String id, String nick, int lv, int score) {
		super();
		this.id = id;
		this.nick = nick;
		this.lv = lv;
		this.score = score;
	}
	
	public NoodleModel(String id, String pw, String nick, String name, String donate) {
		super();
		this.id = id;
		this.pw = pw;
		this.nick = nick;
		this.name = name;
		this.donate = donate;
	}
	public NoodleModel() {
		
	}

	public NoodleModel(int str, int dex, int iq, int luk, int lv, int hp, int mp, int exp, int score) {
		super();
		this.str = str;
		this.dex = dex;
		this.iq = iq;
		this.luk = luk;
		this.lv = lv;
		this.hp = hp;
		this.mp = mp;
		this.exp = exp;
		this.score = score;
	}



	public int getStr() {
		return str;
	}

	public int getDex() {
		return dex;
	}

	public int getIq() {
		return iq;
	}

	public int getLuk() {
		return luk;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getNick() {
		return nick;
	}

	public int getLv() {
		return lv;
	}

	public int getHp() {
		return hp;
	}

	public int getMp() {
		return mp;
	}

	public int getExp() {
		return exp;
	}

	public String getName() {
		return name;
	}

	public String getDonate() {
		return donate;
	}

	public String getM_name() {
		return m_name;
	}

	public int getM_lv() {
		return m_lv;
	}

	public int getM_exp() {
		return m_exp;
	}
	public void setStr(int str) {
		this.str = str;
	}
	public void setDex(int dex) {
		this.dex = dex;
	}
	public void setIq(int iq) {
		this.iq = iq;
	}
	public void setLuk(int luk) {
		this.luk = luk;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDonate(String donate) {
		this.donate = donate;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public void setM_lv(int m_lv) {
		this.m_lv = m_lv;
	}
	public void setM_exp(int m_exp) {
		this.m_exp = m_exp;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}	
	
	
}
