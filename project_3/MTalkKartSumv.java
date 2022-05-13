public class MTalkKartSumv extends MTalk {
	private double upoloipo;
	public MTalkKartSumv(int currentID, String name, double pagio, double saleThisPeriod, double timeForStath, double timeForMob, int sms, double upoloipo) {
		super(currentID, name, pagio, saleThisPeriod, timeForStath, timeForMob, sms);
		this.upoloipo=upoloipo;
	}
	public double getUpoloipo() {
		return upoloipo;
	}
	public void setUpoloipo(double upoloipo) {
		this.upoloipo = upoloipo;
	}
}