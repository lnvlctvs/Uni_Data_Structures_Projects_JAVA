public class MTalkContract extends Contract {
	//Ypoleipomena lepta/sms
	private double timeForStath,timeForMob;
	private int sms;
	
	public MTalkContract(int ID, int serviceID, String tpay, String name, String phone, String date, double pagio, double statStath, double statKin, int statSMS, double statMB,     double timeForStath, double timeForMob, int sms) {
		super(ID, serviceID, tpay, name, phone, date, pagio, statStath, statKin, statSMS, statMB);
		this.timeForStath=timeForStath;
		this.timeForMob=timeForMob;
		this.sms=sms;
	}
	public double getUpoloipo(){return 0;}
	public void setUpoloipo(double x){};
	public double getMb(){return 0;}
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

	public int getSms() {
		return sms;
	}

	public void setSms(int sms) {
		this.sms = sms;
	}
}