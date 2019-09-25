package FlexiRent;

/**
 * Advanced programming assignment 1 FlexiRent
 * 
 * @version 1.0 2018-08-13
 * @author Miao Jiang (s3643562)
 *
 */

public class Apartment extends RentalProperty {

	private double rentalRate;
	private double lateFeePerDay;

	/**
	 * constructor for Apartment object
	 * 
	 * Apartment is a type of apartment
	 * 
	 */
	public Apartment(String propertyID, int streetNum, String streetName, String suburb, int bedroomNum,
			String properType) {
		super(propertyID, streetNum, streetName, suburb, bedroomNum, properType);
		if (super.getBedroomNum() == 3) {
			rentalRate = 319.0;
		} else if (super.getBedroomNum() == 2) {
			rentalRate = 210.0;
		} else {
			rentalRate = 143.0;
		}
		this.lateFeePerDay = this.rentalRate * 1.15;
	}

	// getters
	public double getRentalRate() {
		return rentalRate;
	}

	public double getLateFeePerDay() {
		return lateFeePerDay;
	}
}
