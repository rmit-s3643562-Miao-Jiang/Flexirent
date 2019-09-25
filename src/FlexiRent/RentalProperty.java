package FlexiRent;

/**
 * Advanced programming assignment 1 FlexiRent
 * 
 * @version 1.0 2018-08-13
 * @author Miao Jiang (s3643562)
 *
 */

public abstract class RentalProperty {
	private String propertyID;
	private int streetNum;
	private String streetName;
	private String suburb;
	private int bedroomNum;
	private String properType;
	private String propertyStatus;
	private RentalRecord rentalRecord[];
	private int rentedNum = 0;

	/**
	 * constructor for RentalProperty object
	 * 
	 * @param propertyID
	 *            the property's ID
	 * @param streetNum
	 *            the street's number
	 * @param streetName
	 *            the street's name
	 * @param suburb
	 *            the suburb
	 * @param bedroomNum
	 *            the bedroom's number
	 */
	public RentalProperty(String propertyID, int streetNum, String streetName, String suburb, int bedroomNum,
			String properType) {
		this.propertyID = propertyID;
		this.streetNum = streetNum;
		this.streetName = streetName;
		this.suburb = suburb;
		this.bedroomNum = bedroomNum;
		this.properType = properType;
		propertyStatus = "Available";
		rentalRecord = new RentalRecord[10];

	}

	// getters
	public abstract double getRentalRate();

	public abstract double getLateFeePerDay();

	public RentalRecord getRentalRecord(int num) {
		return rentalRecord[num];
	}

	public String getProperType() {
		return properType;
	}

	public String getSuburb() {
		return suburb;
	}

	public int getStreetNum() {
		return streetNum;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getPropertyID() {
		return propertyID;
	}

	public int getBedroomNum() {
		return bedroomNum;
	}

	public String getPropertyStatus() {
		return propertyStatus;
	}

	/**
	 * rent method decides whether the property can be rented
	 * 
	 * @param customerId
	 * @param rentDate
	 *            the rent date
	 * @param numOfRentDay
	 *            the number of rent days
	 * @return false represents the property cannot be rented
	 */
	public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
		boolean rentStatus = false;
		DateTime ERD = new DateTime(rentDate, numOfRentDay); // ERD represents estimated return date	
			if (this.properType.compareTo("Apartment") == 0) {
				DateTime compareDay = new DateTime(06, 01, 2018);
				int weekNum = (DateTime.diffDays(rentDate, compareDay)) % 7;
				if (((weekNum >= 0 && weekNum <= 4) && (numOfRentDay <= 28 && numOfRentDay >= 2))
						|| ((weekNum >= 5 && weekNum <= 6) && (numOfRentDay <= 28 && numOfRentDay >= 3))) {
					RentalRecord RR = new RentalRecord(this, customerId, rentDate, ERD);
					for (int i = 9; i > 0; i--) {
						this.rentalRecord[i] = this.rentalRecord[i - 1];
					}
					this.rentalRecord[0] = RR;
					this.propertyStatus = "Rented";
					rentStatus = true;
				}
			} else if (this.properType.compareTo("Premium Suite") == 0) {
				PremiumSuite PS = (PremiumSuite) this;
				DateTime a = new DateTime(rentDate, numOfRentDay);
				if (DateTime.diffDays(a, PS.getLastMaintenanceDate()) + 10 > 0) {
					RentalRecord RR = new RentalRecord(this, customerId, rentDate, ERD);
					for (int i = 9; i > 0; i--) {
						this.rentalRecord[i] = this.rentalRecord[i - 1];
					}
					this.rentalRecord[0] = RR;
					this.propertyStatus = "Rented";
					rentStatus = true;
				}
			}
		if (rentStatus == true) {
			this.rentedNum++;
		}
		return rentStatus;
	}

	/**
	 * returnProperty method decides whether the property can be returned
	 * 
	 * @param returnDate
	 * @return false represent the property cannot be returnned
	 */
	public boolean returnProperty(DateTime returnDate) {
		boolean returnStatus = false;
		if (this.propertyStatus.compareTo("Rented") == 0) {
			if (DateTime.diffDays(returnDate, this.rentalRecord[0].getRentDate()) >= 0) {
				returnStatus = true;
				this.rentalRecord[0].setActualReturnDate(returnDate);
				this.propertyStatus = "Available";
				if (DateTime.diffDays(returnDate, this.rentalRecord[0].getEstimatedReturnDate()) > 0) {
					this.rentalRecord[0]
							.setLateFee(DateTime.diffDays(returnDate, this.rentalRecord[0].getEstimatedReturnDate())
									* this.getLateFeePerDay());
				} else {
					this.rentalRecord[0].setRentalFee(DateTime.diffDays(returnDate,this.rentalRecord[0].getRentDate())*this.getRentalRate());
					this.rentalRecord[0].setLateFee(0.0);
				}
			} else {
				returnStatus = false;
			}
		}
		return returnStatus;
	}

	// perforMaintenance method decides the status of property maintenance
	public boolean performMaintenance() {
		boolean MaintenanceStatus = false;
		if (this.propertyStatus.equals("Available")) {
			this.propertyStatus = "Maintenance";
			MaintenanceStatus = true;
		}
		return MaintenanceStatus;
	}

	/**
	 * completeMaintenance method decides the status of property maintenance
	 * 
	 * @param completionDate
	 * @return false represents the property does not finish maintenance
	 */

	public boolean completeMaintenance(DateTime completionDate) {
		boolean completeMaintenanceStatus = false;
		if (this.properType.compareTo("Apartment") == 0) {
			if (this.propertyStatus.compareTo("Maintenance") == 0) {
				this.propertyStatus = "Available";
				completeMaintenanceStatus = true;
			}
		} else if (this.properType.compareTo("Premium Suite") == 0) {
			if (this.propertyStatus.compareTo("Maintenance") == 0) {
				this.propertyStatus = "Available";
				completeMaintenanceStatus = true;
				PremiumSuite PS = (PremiumSuite) this;
				PS.setLastMaintenanceDate(completionDate);
			}
		}
		return completeMaintenanceStatus;
	}

	// override the method toString
	public String toString() {
		String information;
		if (this.properType.compareTo("Apartment") == 0) {
			information = this.propertyID + ":" + this.streetName + ":" + this.streetNum + ":" + this.suburb + ":"
					+ this.properType + ":" + this.getBedroomNum() + ":" + this.propertyStatus;
		} else if (this.properType.compareTo("Premium Suite") == 0) {
			PremiumSuite PS = (PremiumSuite) this;
			information = this.propertyID + ":" + this.streetName + ":" + this.streetNum + ":" + this.suburb + ":"
					+ this.getProperType() + ":" + this.getBedroomNum() + ":" + this.propertyStatus + ":"
					+ PS.getLastMaintenanceDate().getFormattedDate();
		} else {
			information = null;
		}
		return information;
	}

	// getDetails method can get all the details of the rent property
	public String getDetails() {
		String detail = "Property ID:" + "\t\t" + this.propertyID + "\n" + "Address:" + "\t\t\t" + this.getStreetNum()
				+ " " + this.getStreetName() + " " + this.getSuburb() + "\n" + "Type:" + "\t\t\t" + this.getProperType()
				+ "\n" + "Bedroom:" + "\t\t\t" + this.getBedroomNum() + "\n" + "Status:" + "\t\t\t"
				+ this.getPropertyStatus();
		if (this.properType.compareTo("Premium Suite") == 0) {
			PremiumSuite PS = (PremiumSuite) this;
			detail += "\n" + "Last maintenance:" + "\t" + PS.getLastMaintenanceDate();
		}
		if (this.rentalRecord[0] != null) {
			detail += "\n" + "RENTAL RECORD";
			detail += this.rentalRecord[0].getDetails();
		} else {
			detail += "\n" + "RENTAL RECORD:" + "\t\t" + "empty";
		}
		if (this.rentedNum > 1 && this.rentedNum < 10) {
			for (int i = 1; i < rentedNum; i++) {
				detail += this.rentalRecord[i].getDetails();
			}
		} else if (this.rentedNum >= 10)
			for (int i = 1; i < 9; i++) {
				detail += this.rentalRecord[i].getDetails();
			}
		return detail;
	}
}
