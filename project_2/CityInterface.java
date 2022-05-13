package project2;

public interface CityInterface {
	
	
	public int getID();
	
	public String getName();
	
	public int getPopulation();
	
	public int getCovidCases();
	
	
	public void setID(int ID);
	
	public void setName(String name);
	
	public void setPopulation(int population);
	
	public void setCovidCases(int covidCases);
	
	public float calculateDensity();
	
	public int compareTo(City array);
	
	public int compareDynamic(City city);

}
