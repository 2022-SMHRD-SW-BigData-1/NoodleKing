package Model;

public class NoodleModel {

	private int str;
	private int dex;
	private int iq;
	private int luk;
	private int hp;
	private int mp;
	private int exp;
	private int lv;
	private String nick;
	private String id;
	private String pw;
	private String name;
	private String donate;

	private String m_name;
	private int m_lv;
	private int m_exp;
	public NoodleModel() {
		
	}

	public NoodleModel(int str, int dex, int iq, int luk, int hp, int mp, int exp, int lv, String nick, String id, String pw,
			String name, String donate, String m_name, int m_lv, int m_exp) {
		super();
		this.str = str;
		this.dex = dex;
		this.iq = iq;
		this.luk = luk;
		this.hp = hp;
		this.mp = mp;
		this.exp = exp;
		this.lv = lv;
		this.nick = nick;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.donate = donate;
		this.m_name = m_name;
		this.m_lv = m_lv;
		this.m_exp = m_exp;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getDex() {
		return dex;
	}

	public void setDex(int dex) {
		this.dex = dex;
	}

	public int getIq() {
		return iq;
	}

	public void setIq(int iq) {
		this.iq = iq;
	}

	public int getLuk() {
		return luk;
	}

	public void setLuk(int luk) {
		this.luk = luk;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDonate() {
		return donate;
	}

	public void setDonate(String donate) {
		this.donate = donate;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public int getM_lv() {
		return m_lv;
	}

	public void setM_lv(int m_lv) {
		this.m_lv = m_lv;
	}

	public int getM_exp() {
		return m_exp;
	}

	public void setM_exp(int m_exp) {
		this.m_exp = m_exp;
	}

}
