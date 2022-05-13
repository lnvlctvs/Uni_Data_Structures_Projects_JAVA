public abstract class Contract {
	public static int ID=0;
	private int serviceID, statSMS, currID;
	private String tpay, name, phone, date;
	private double pagio, statStath, statKin, statMB;
	public Contract(int ID, int serviceID, String tpay, String name, String phone, String date, double pagio, double statStath, double statKin, int statSMS, double statMB){
		//koina
		this.currID=ID;
		this.serviceID=serviceID;
		this.tpay=tpay;
		this.name=name;
		this.phone=phone;
		this.date=date;
		this.pagio=pagio;
		this.statKin=statKin;
		this.statMB=statMB;
		this.statSMS=statSMS;
		this.statStath=statStath;
	}
	
	public abstract double getUpoloipo();
	public abstract void setUpoloipo(double x);
	public abstract double getTimeForStath();
	public abstract double getTimeForMob();
	public abstract double getMb();
	public abstract int getSms();
	
	public int getStatSMS() {
		return statSMS;
	} public void setStatSms(int sms) {
		statSMS=sms;
	}
	public double getStatStath() {
		return statStath;
	} public void setStatStath(double Stath) {
		statStath=Stath;
	}
	
	public double getStatKin() {
		return statKin;
	} public void setStatKin(double kin) {
		statKin=kin;
	}
	
	public double getStatMB() {
		return statMB;
	} public void setStatMB(double mb) {
		statMB=mb;
	}
	
	
	
	public int getCurrID() {
		return currID;
	}
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	public String getTpay() {
		return tpay;
	}
	public void setTpay(String tpay) {
		this.tpay = tpay;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getPagio() {
		return pagio;
	}
	public void setPagio(double pagio) {
		this.pagio = pagio;
	}
}