public abstract class Service  {
	public static int ID=0;
	private int currentID;
	private String name;
	private double pagio, saleThisPeriod;
	public Service(int currentID, String name, double pagio, double saleThisPeriod){
		//koina
		this.currentID=currentID;
		this.name = name;
		this.pagio = pagio;
		this.saleThisPeriod=saleThisPeriod;
	}
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
	public int getCurrentID() {
		return currentID;
	}
	public void setCurrentID(int currentID) {
		this.currentID = currentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPagio() {
		return pagio;
	}
	public void setPagio(double pagio) {
		this.pagio = pagio;
	}
	public double getSaleThisPeriod() {
		return saleThisPeriod;
	}
	public void setSaleThisPeriod(double saleThisPeriod) {
		this.saleThisPeriod = saleThisPeriod;
	}
	public abstract double getTimeForStath();
	public abstract void setTimeForStath(double timeForStath);
	public abstract double getTimeForMob();
	public abstract void setTimeForMob(double z);
	public abstract int getSms();
	public abstract void setSms(int sms);
	public abstract double getUpoloipo();
	public abstract void setUpoloipo(double x);
	public abstract double getMb();
	public abstract void setMb(double x);
}                                           