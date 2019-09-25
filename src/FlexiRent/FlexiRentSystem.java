package FlexiRent;

/**
 * Advanced programming assignment 1 FlexiRent
 * 
 * @version 1.0 2018-08-13
 * @author Miao Jiang (s3643562)
 *
 */
import java.util.Scanner;

public class FlexiRentSystem {
	private RentalProperty[] propertyStore;
	private int propertyNum;

	// constructor for FlexiRentSystem object
	public FlexiRentSystem() {
		this.propertyStore = new RentalProperty[50];
		this.propertyNum = 0;
	}

	// Menu method always show the menu
	public void menu() {

		Scanner sc = new Scanner(System.in);

		String[] list = { "**** FLEXIRENT SYSTEM MENU ****", "Add Property:			1", "Rent Property:			2",
				"Return Property:		3", "Property Maintenance:		4", "Complete Maintenance:		5",
				"Display All Properties:		6", "Exit Program:			7", "Enter your choice:" };

		do {
			for (int i = 0; i < list.length; i++)
				System.out.println(list[i]);
			if (!sc.hasNextInt()) {
				System.out.println("Choice must be integer");
				menu();
			}
			int choice = sc.nextInt();
			if (choice == 1) {
				this.addProperty();
			} else if (choice == 2) {
				this.rentProperty();
			} else if (choice == 3) {
				this.returnProperty();
			} else if (choice == 4) {
				this.propertyMaintenance();
			} else if (choice == 5) {
				this.completeMaintenance();
			} else if (choice == 6) {
				this.displayAllProperties();
			} else if (choice == 7) {
				System.out.println("Successfully exit the system.");
				sc.close();
				for (int i = 0; i < 3; i++) {
					System.out.println(propertyStore[i]);
				}
				break;
			} else {
				System.out.println("Invalid number!");
			}
		} while (true);

	}

	// displayAllProperties method can show all the properties
	private void displayAllProperties() {
		for (int i = 0; i < this.propertyNum; i++) {
			System.out.println(this.propertyStore[i].getDetails());
		}
	}

	// completeMaintenance method performs the operations required
	// when the maintenance of that property is finished
	private void completeMaintenance() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the property ID (A/S_+streetNumNameSurburb) that you want to complete maintenance:");
		String propertyID = sc.nextLine();
		int rentNumOfProperty;
		for (rentNumOfProperty = 0; rentNumOfProperty < this.propertyNum; rentNumOfProperty++) {
			if (this.propertyStore[rentNumOfProperty].getPropertyID().equals(propertyID)) {
				if (this.propertyStore[rentNumOfProperty].getPropertyStatus().equals("Maintenance")) {
					System.out.println("complete maintenance date (dd/mm/yyyy):");
					String CMD = sc.nextLine();
					if (checkDateFormat(CMD)) {
						int day = Integer.parseInt(CMD.substring(0, 2));
						int month = Integer.parseInt(CMD.substring(3, 5));
						int year = Integer.parseInt(CMD.substring(6, 10));
						DateTime completeMainentanceDate = new DateTime(day, month, year);
						if (this.propertyStore[rentNumOfProperty]
								.completeMaintenance(completeMainentanceDate) == true) {
							System.out.println(this.propertyStore[rentNumOfProperty].getProperType() + " "
									+ this.propertyStore[rentNumOfProperty].getPropertyID()
									+ " has all maintenance completed and ready for rent.");
						}
					} else {
						System.out.println(this.propertyStore[rentNumOfProperty].getProperType() + " "
								+ this.propertyStore[rentNumOfProperty].getPropertyID()
								+ "The rental property is not under maintenanced.");
					}
				} else {
					System.out.println("Complete maintenance date is not correct");
				}
				break;
			} else if (rentNumOfProperty == this.propertyNum - 1) {
				System.out.println("Property ID is not correct");
			}
		}
	}

	// propertyMaintenance method performs the operations required to perform the
	// maintenance of that property
	private void propertyMaintenance() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the property ID (A/S_+streetNumNameSurburb) that you want to maintenance:");
		String propertyID = sc.nextLine();
		int rentNumOfProperty;
		for (rentNumOfProperty = 0; rentNumOfProperty < this.propertyNum; rentNumOfProperty++) {
			if (this.propertyStore[rentNumOfProperty].getPropertyID().equals(propertyID)) {
				if (this.propertyStore[rentNumOfProperty].performMaintenance() == true) {
					System.out.println(this.propertyStore[rentNumOfProperty].getProperType() + " "
							+ this.propertyStore[rentNumOfProperty].getPropertyID() + " is now under maintenance.");
				} else {
					System.out.println(this.propertyStore[rentNumOfProperty].getProperType() + " "
							+ this.propertyStore[rentNumOfProperty].getPropertyID()
							+ " cannot be under maintenance now.");
				}
				break;
			} else if (rentNumOfProperty == this.propertyNum - 1) {
				System.out.println("Property ID is not correct");
			}
		}
	}

	// returnProperty method performs the operations required to return this
	// property
	private void returnProperty() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the property ID (A/S_+streetNumNameSurburb) that you want to return:");
		String propertyID = sc.nextLine();
		for (int rentNumOfProperty = 0; rentNumOfProperty < this.propertyNum; rentNumOfProperty++) {
			if (this.propertyStore[rentNumOfProperty].getPropertyID().equals(propertyID)) {
				System.out.println("return date (dd/mm/yyyy):");
				String ARD = sc.nextLine();
				if (checkDateFormat(ARD)) {
					int day = Integer.parseInt(ARD.substring(0, 2));
					int month = Integer.parseInt(ARD.substring(3, 5));
					int year = Integer.parseInt(ARD.substring(6, 10));
					DateTime returnDate = new DateTime(day, month, year);
					if (this.propertyStore[rentNumOfProperty].returnProperty(returnDate) == true) {
						System.out.println(this.propertyStore[rentNumOfProperty].getPropertyID()
								+ " is returned by customer "
								+ this.propertyStore[rentNumOfProperty].getRentalRecord(0).getCustomerID() + ".");
						System.out.println(this.propertyStore[rentNumOfProperty].getDetails());
					} else {
						System.out.println("It cannot be returned");
					}
				} else {
					System.out.println("Actual return date cannot be empty");
				}
				break;
			} else if (rentNumOfProperty == this.propertyNum - 1) {
				System.out.println("Property ID is not correct");
			}
		}
	}

	// rentProperty method performs the operations required to rent this property
	private void rentProperty() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the property ID (A/S_+streetNumNameSurburb) that you want to rent:");
		String propertyID = sc.nextLine();
		for (int rentNumOfProperty = 0; rentNumOfProperty < this.propertyNum; rentNumOfProperty++) {
			if (this.propertyStore[rentNumOfProperty].getPropertyID().equals(propertyID)) {
				if (this.propertyStore[rentNumOfProperty].getPropertyStatus().compareTo("Available") == 0) {
					System.out.println("Customer ID:");
					String customerId = sc.nextLine();
					System.out.println("Rent date (dd/mm/yyyy):");
					String RD = sc.nextLine();
					if (checkDateFormat(RD)) {
						int day = Integer.parseInt(RD.substring(0, 2));
						int month = Integer.parseInt(RD.substring(3, 5));
						int year = Integer.parseInt(RD.substring(6, 10));
						DateTime rentDate = new DateTime(day, month, year);
						System.out.println("How many days?:");
						if (!sc.hasNextInt()) {
							System.out.println("Street number must be integer");
							return;
						}
						int numOfRentDay = sc.nextInt();
						sc.nextLine();
						if (this.propertyStore[rentNumOfProperty].rent(customerId, rentDate, numOfRentDay) == true) {
							System.out.println(this.propertyStore[rentNumOfProperty].getProperType() + " "
									+ this.propertyStore[rentNumOfProperty].getPropertyID()
									+ " is now rented by customer " + customerId + ".");
						} else {
							System.out.println(this.propertyStore[rentNumOfProperty].getProperType() + " "
									+ this.propertyStore[rentNumOfProperty].getPropertyID() + "could not be rented.");
						}
					} else {
						System.out.println("Rent date is not correct");
					}
				} else {
					System.out.println(this.propertyStore[rentNumOfProperty].getProperType() + " "
							+ this.propertyStore[rentNumOfProperty].getPropertyID() + "could not be rented.");
				}
				break;
			} else if (rentNumOfProperty == this.propertyNum - 1) {
				System.out.println("Property ID is not correct");
			}
		}
	}

	// addProperty methods performs to add a new property to the database
	private void addProperty() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the property type(Apartment or Premium Suite):");
		String properType = sc.nextLine();
		if (this.propertyNum <= 50) {
			if (this.checkPropertyFormat(properType) == false)
				return;
			System.out.println("Enter the street name:");
			String streetName = sc.nextLine();
			if (streetName.compareTo("") == 0) {
				System.out.println("Street name cannot be empty");
				return;
			}
			System.out.println("Enter the street number:");
			if (!sc.hasNextInt()) {
				System.out.println("Street number must be integer");
				return;
			}
			int streetNum = sc.nextInt();
			sc.nextLine();
			System.out.println("Enter the suburb:");
			String suburb = sc.nextLine();
			if (suburb.compareTo("") == 0) {
				System.out.println("suburb cannot be empty");
				return;
			}
			System.out.println("Enter the number of bedroom (1-3):");
			if (!sc.hasNextInt()) {
				System.out.println("bedroom number must be integer");
				return;
			}
			int bedroomNum = sc.nextInt();
			if (checkPropertyBedroom(properType, bedroomNum) == false) {
				return;
			}
			formPropertyID(properType, streetNum, streetName, suburb, bedroomNum); // call the method formPropertyID
		} else {
			System.out.println("The number of property is more than 50");
		}
	}

	/**
	 * CheckPropertyFormat method decides the property is correct
	 * 
	 * @param properType
	 *            is the property type
	 * @return true represents the property type is correct
	 */

	private boolean checkPropertyFormat(String properType) {
		if (properType.compareTo("Apartment") == 0 || properType.compareTo("Premium Suite") == 0) {
			return true;
		} else {
			System.out.println("The format must be the same with Apartment or Premium Suite");
			return false;
		}
	}

	/**
	 * CheckPropertyBedroom is a method to check the bedroom number
	 * 
	 * @param properType
	 *            is the type of property
	 * @param bedroomNum
	 *            is the number of bedroom
	 * @return true represents the input is correct
	 */
	private boolean checkPropertyBedroom(String properType, int bedroomNum) {
		if (properType.compareTo("Apartment") == 0 && (bedroomNum < 1 || bedroomNum > 3)) {
			return false;
		} else if (properType.compareTo("Premium Suite") == 0 && bedroomNum != 3) {
			System.out.println("The number of debroom is not correct.");
			System.out.println("Enter the number of bedroom (1-3):");
			return false;
		}
		return true;
	}

	/**
	 * formPropertyID method is to form the property ID
	 * 
	 * @param properType
	 * @param streetNum
	 * @param streetName
	 * @param suburb
	 * @param bedroomNum
	 */
	private void formPropertyID(String properType, int streetNum, String streetName, String suburb, int bedroomNum) {
		String propertyID;
		Scanner sc = new Scanner(System.in);
		if (properType.compareTo("Apartment") == 0) {
			propertyID = "A_" + streetNum + streetName + suburb;
		} else {
			propertyID = "S_" + streetNum + streetName + suburb;
		}
		boolean inexistence = true;
		for (int i = 0; i < this.propertyNum; i++) {
			if (propertyID.equals(this.propertyStore[i].getPropertyID())) {
				inexistence = false;
				System.out.println("The property has already existed");
			}
		}
		if (inexistence == true) {
			if (properType.compareTo("Apartment") == 0) {
				Apartment A = new Apartment(propertyID, streetNum, streetName, suburb, bedroomNum, properType);
				this.propertyStore[this.propertyNum] = A;
				System.out.println("The " + properType + " " + propertyID + " has been added to the system.");
				this.propertyNum++;
			} else if (properType.compareTo("Premium Suite") == 0) {
				System.out.println("Enter the last maintenance date  (dd/mm/yyyy):");
				String LMD = sc.nextLine();
				if (checkDateFormat(LMD)) {
					int day = Integer.parseInt(LMD.substring(0, 2));
					int month = Integer.parseInt(LMD.substring(3, 5));
					int year = Integer.parseInt(LMD.substring(6, 10));
					DateTime lastMaimtanceDate = new DateTime(day, month, year);
					PremiumSuite PS = new PremiumSuite(propertyID, streetNum, streetName, suburb, 3, properType,
							lastMaimtanceDate);
					this.propertyStore[this.propertyNum] = PS;
					System.out.println("The " + properType + " " + propertyID + " has been added to the system.");
					this.propertyNum++;
				} else {
					System.out.println("Last maintenance date is not correct");
				}
			}
		}
	}

	/**
	 * checkDateFormat method check the input date is correct
	 * 
	 * @param date
	 * @return true represents the date is correct
	 */
	private boolean checkDateFormat(String date) {
		boolean correct = false;
		for (int i = 1; i >= 0; i--) {
			if (Character.isDigit(date.charAt(i))) {
				correct = true;
			}
		}
		for (int i = 4; i >= 3; i--) {
			if (Character.isDigit(date.charAt(i))) {
				correct = true;
			}
		}
		for (int i = 9; i >= 6; i--) {
			if (Character.isDigit(date.charAt(i))) {
				correct = true;
			}
		}
		return correct;
	}
}
