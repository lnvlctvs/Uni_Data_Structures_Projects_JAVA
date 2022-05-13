public class MInternet extends Service {
	//Ypoleipomena mb
	private double mb;
	public MInternet(int currentID, String name, double pagio, double saleThisPeriod, int mb) {
		super(currentID, name, pagio, saleThisPeriod);
		this.mb = mb;
	}
	public double getMb() {
		return mb;
	}
	public void setMb(double mb) {
		this.mb = mb;
	}
	public void setTimeForStath(double x){} 
	public double getTimeForStath(){return 0;} 
	public void setTimeForMob(double x){}
	public double getTimeForMob(){return 0;}
	public int getSms(){return 0;}
	public void setSms(int x){}
	public double getUpoloipo(){return 0;};
	public void setUpoloipo(double x){};
}