public  class MInternetContract extends Contract {
	private double mb;
	public MInternetContract(int ID, int serviceID, String tpay, String name, String phone, String date, double pagio, double statStath, double statKin, int statSMS, double statMB,double mb) {
		super(ID, serviceID, tpay, name, phone, date, pagio, statStath, statKin, statSMS, statMB);
		this.mb=mb;
		
	}
	public double getMb() {
		return mb;
	}
	public void setMb(double mb) {
		this.mb = mb;
	}
	public double getUpoloipo(){return 0;}
	public void setUpoloipo(double x){};
	public double getTimeForStath(){return 0;};
	public double getTimeForMob(){return 0;};
	public int getSms(){return 0;};
}