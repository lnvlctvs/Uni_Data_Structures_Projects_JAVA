package project2;

public class City implements CityInterface, Comparable<City> {
	
	
	private int ID;
	private String name;
	private int population;
	private int covidCases;
	
	@SuppressWarnings("unused")
	private float finalCovidCases;
	
	
	public City(int Id, String name, int population, int covidCases) {
		this.ID = Id;
		this.name = name;
		this.population = population;
		this.covidCases = covidCases;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPopulation() {
		return this.population;
	}
	
	public int getCovidCases() {
		return this.covidCases;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public void setCovidCases(int covidCases) {
		this.covidCases = covidCases;
	}
	
	public float calculateDensity() {
		if(getPopulation() > 50000) 
		return finalCovidCases = (50000*covidCases)/population;	
		else
			return getCovidCases();
	}

	@Override
	public int compareTo(City city) {
		
		
		if(calculateDensity() > city.calculateDensity()) {
			return 1;
		}else if(calculateDensity() < city.calculateDensity()) {
			return -1;
		}else {
			if(name.compareTo(city.getName()) > 0) {
				return -1;
			}else if(name.compareTo(city.getName()) < 0) {
				return 1;
			}else {
				if(ID > city.getID()) {
					return -1;
				}else {
					return 1;
				}
			}
		}
	}
	
	public int compareDynamic(City city) {
		
		if((float)getCovidCases()/getPopulation() > (float)city.getCovidCases()/city.getPopulation())
			return 1;
		else if((float)getCovidCases()/getPopulation() < (float)city.getCovidCases()/city.getPopulation())
			return -1;
		else {
			if(name.compareTo(city.getName()) > 0) {
				return -1;
			}else if(name.compareTo(city.getName()) < 0) {
				return 1;
			}else {
				if(ID > city.getID()) {
					return -1;
				}else {
					return 1;
				}
			}
		}
	}

}
