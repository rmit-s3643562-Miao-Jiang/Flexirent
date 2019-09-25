package FlexiRent;

/**
 * Advanced programming assignment 1 FlexiRent
 * 
 * @version 1.0 2018-08-13
 * @author Miao Jiang (s3643562)
 *
 */

public class PremiumSuite extends RentalProperty {

	private int rentalDay;
	private double rentalRate;
	private double lateFeePerDay;
	private DateTime lastMaintenanceDate;

	/**
	 * constructor for PremiumSuite object
	 * 
	 * PremiumSuite is a type of apartment
	 * 
	 * @param lastMaintanceDate
	 *            the last date of maintains date
	 */
	public PremiumSuite(String propertyID, int streetNum, String streetName, String suburb, int bedroomNum,
			String properType, DateTime lastMaintenanceDate) {
		super(propertyID, streetNum, streetName, suburb, 3, properType);
		this.rentalRate = 554.0;
		this.lateFeePerDay = 662.0;
		this.lastMaintenanceDate = lastMaintenanceDate;
	}

	// getters
	public int getRentalDay() {
		return rentalDay;
	}

	public double getRentalRate() {
		return this.rentalRate;
	}

	public double getLateFeePerDay() {
		return this.lateFeePerDay;
	}

	public DateTime getLastMaintenanceDate() {
		return lastMaintenanceDate;
	}

	/**
	 * set the last maintenance
	 * 
	 * @param lastMaintanceDate
	 */
	public void setLastMaintenanceDate(DateTime lastMaintenanceDate) {
		this.lastMaintenanceDate = lastMaintenanceDate;
	}
}
