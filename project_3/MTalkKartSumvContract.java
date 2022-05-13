public class MTalkKartSumvContract extends MTalkContract {
	//Ypoleipomena
	private double timeForStath,timeForMob, upoloipo;
	private int sms;
	
	public MTalkKartSumvContract(int ID, int serviceID, String tpay, String name, String phone, String date, double pagio, double statStath, double statKin, int statSMS, double statMB,     double timeForStath, double timeForMob, int sms, double upoloipo) {
		super(ID, serviceID, tpay, name, phone, date, pagio, statStath, statKin, statSMS, statMB, timeForStath, timeForMob, sms);
		this.upoloipo=upoloipo;
	}


	public void setTimeForStath(double timeForStath) {
		this.timeForStath = timeForStath;
	}

	public void setTimeForMob(double timeForMob) {
		this.timeForMob = timeForMob;
	}

	public double getUpoloipo() {
		return this.upoloipo;
	}

	public void setUpoloipo(double upoloipo) {
		this.upoloipo = upoloipo;
	}

	public void setSms(int sms) {
		this.sms = sms;
	}
}