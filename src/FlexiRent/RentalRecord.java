package FlexiRent;

/**
 * Advanced programming assignment 1 FlexiRent
 * 
 * @version 1.0 2018-08-13
 * @author Miao Jiang (s3643562)
 *
 */

public class RentalRecord {
	private RentalProperty property;
	private String recordID;
	private DateTime rentDate;
	private DateTime estimatedReturnDate;
	private DateTime actualReturnDate;
	private double rentalFee;
	private double lateFee;
	private String customerID;

	/**
	 * constructor for the rental record
	 * 
	 * @param property
	 * @param customerID
	 * @param rentDate
	 * @param estimatedReturnDate
	 * @param actualReturnDate
	 */

	public RentalRecord(RentalProperty property, String customerID, DateTime rentDate, DateTime estimatedReturnDate) {
		this.property = property;
		this.customerID = customerID;
		this.recordID = this.property.getPropertyID() + '_' + this.customerID + '_' + rentDate.getEightDigitDate();
		this.rentDate = rentDate;
		this.estimatedReturnDate = estimatedReturnDate;
		this.rentalFee = property.getRentalRate() * DateTime.diffDays(estimatedReturnDate, rentDate);
	}

	// getters
	public String getCustomerID() {
		return customerID;
	}

	public String getRecordID() {
		return this.recordID;
	}

	public DateTime getEstimatedReturnDate() {
		return estimatedReturnDate;
	}

	public double getRentalFee() {
		return this.rentalFee;
	}

	public double getLateFee() {
		return lateFee;
	}

	public DateTime getRentDate() {
		return rentDate;
	}

	public DateTime getActualReturnDate() {
		return actualReturnDate;
	}

	// setters
	public void setActualReturnDate(DateTime actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}

	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}
	public void setRentalFee(double rentalFee) {
		this.rentalFee =  rentalFee;
	}

	// override the method toString
	public String toString() {
		if (this.getActualReturnDate() != null) {
			String rentalRecord = this.getRecordID() + ": " + this.rentDate.getFormattedDate() + ": "
					+ this.getEstimatedReturnDate().getFormattedDate() + ": "
					+ this.getActualReturnDate().getFormattedDate() + ": " + this.getRentalFee() + ": "
					+ this.getLateFee();
			return rentalRecord;
		} else {
			String rentalRecord = this.getRecordID() + ": " + this.getRentDate().getFormattedDate() + ": "
					+ this.getEstimatedReturnDate().getFormattedDate() + ": none: none: none";
			return rentalRecord;
		}
	}

	// getDetails method get the information of rent record
	public String getDetails() {
		String details;
		if (this.getActualReturnDate() != null) {
			details = "\n" + "Record ID:" + "\t\t" + this.getRecordID() + "\n" + "Rent Date:" + "\t\t"
					+ this.getRentDate().getFormattedDate() + "\n" + "Estimated Return Date:" + "  "
					+ this.getEstimatedReturnDate().getFormattedDate() + "\n" + "Actual Return Date:" + "\t"
					+ this.getActualReturnDate().getFormattedDate() + "\n" + "Rental Fee:" + "\t\t"
					+ this.getRentalFee() + "\n" + "Late Fee:" + "\t\t" + this.getLateFee() + "\n"
					+ "--------------------------------------------------";
		} else {
			details = "\n" + "Record ID:" + "\t\t" + this.getRecordID() + "\n" + "Rent Date:" + "\t\t"
					+ this.getRentDate().getFormattedDate() + "\n" + "Estimated Return Date:" + "  "
					+ this.getEstimatedReturnDate().getFormattedDate() + "\n"
					+ "--------------------------------------------------";
		}
		return details;
	}
}
