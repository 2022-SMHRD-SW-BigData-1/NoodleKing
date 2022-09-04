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
	private int m_score;
	private String m_pic;
	
	
	
	public NoodleModel(String m_name, String m_pic) {
		super();
		this.m_name = m_name;
		this.m_pic = m_pic;
	}

	public NoodleModel(String m_name, int m_lv, int m_exp, int m_score) {
		super();
		this.m_name = m_name;
		this.m_lv = m_lv;
		this.m_exp = m_exp;
		this.m_score = m_score;
	}

	public NoodleModel(int exp, int score, int hp, int mp, int lv, int str, int iq, int dex, int luk, String nick) {
		super();
		this.exp = exp;
		this.score = score;
		this.hp = hp;
		this.mp = mp;
		this.lv = lv;
		this.str = str;
		this.iq = iq;
		this.dex = dex;
		this.luk = luk;
		this.nick = nick;
	}

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

	public int getScore() {
		return score;
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

	public int getM_score() {
		return m_score;
	}	
	
}
