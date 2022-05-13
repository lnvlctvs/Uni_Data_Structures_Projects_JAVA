public class MTalk extends Service {
	private int sms;
	private double timeForStath, timeForMob;
	public MTalk(int currentID, String name, double pagio, double saleThisPeriod, double timeForStath, double timeForMob, int sms) {
		super(currentID, name, pagio, saleThisPeriod);
		this.timeForStath= timeForStath;
		this.timeForMob= timeForMob;
		this.sms = sms;
	}
	public int getSms() {
		return sms;
	}
	public void setSms(int sms) {
		this.sms = sms;
	}
	public double getTimeForStath() {
		return timeForStath;
	}
	public void setTimeForStath(double timeForStath) {
		this.timeForStath = timeForStath;
	}
	public double getTimeForMob() {
		return timeForMob;
	}
	public void setTimeForMob(double timeForMob) {
		this.timeForMob = timeForMob;
	}
	public double getUpoloipo(){return 0;};
	public void setUpoloipo(double x){};
	public double getMb(){return 0;};
	public void setMb(double x){};
}