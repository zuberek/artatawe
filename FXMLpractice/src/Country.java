/**
 * A model of a country. Each country has a name, a capital city and a population.
 * @author Liam O'Reilly
 */
public class Country {
	private String name;
	private String capital;
	private double population;
	
	/**
	 * Create a new country.
	 * @param name The country's name.
	 * @param capital The capital city of the country.
	 * @param population The population of the country in millions.
	 */
	public Country(String name, String capital, double population) {
		this.name = name;
		this.capital = capital;
		this.population = population;
	}
	
	/**
	 * Get the name of the country.
	 * @return The name of the country.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the country.
	 * @param name The new name of the country.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the country's capital city.
	 * @return The country's continent.
	 */
	public String getCapital() {
		return capital;
	}
	
	/**
	 * Set the capital of the country.
	 * @param capital The new capital of the country.
	 */
	public void setCapital(String capital) {
		this.capital = capital;
	}
	
	/**
	 * Get the population of the country.
	 * @return The population of the country (in millions).
	 */
	public double getPopulation() {
		return population;
	}
	
	/**
	 * Set the population of the country.
	 * @param population The new population (in millions) of the country.
	 */
	public void setPopulation(double population) {
		this.population = population;
	}
	
	/**
	 * Get a short description of the country that is suitable for use in a ListView.
	 * @return A short description of the country.
	 */
	public String getDescriptionForList() {
		return name + " - " + capital + " - " + population + " million";
	}
}