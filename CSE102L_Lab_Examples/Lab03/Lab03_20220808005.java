import java.util.ArrayList;
import java.util.Date;
/**---------------------------------------------------

 * Akdeniz University CSE102L Examples

 * @author: Yahya Efe Kuruçay

 * @since: 25.03.2024

 * Description: Lab03

 * Score: ?

 * Website: https://efekurucay.com

*---------------------------------------------------*/

/***
 *    ███████ ███████ ███████ 
 *    ██      ██      ██      
 *    █████   █████   █████   
 *    ██      ██      ██      
 *    ███████ ██      ███████ 
 *                            
 *                            
 */
class Vehicle {
    private String brand;
    private String model;
    private int year;
    private boolean isRented;

    public Vehicle(String brand, String model, int year, boolean isRented) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.isRented = isRented;
    }

    // Getters and setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}

class Car extends Vehicle {
    private int passengerCapacity;
    private boolean automaticTransmission;

    public Car(String brand, String model, int year, boolean isRented, int passengerCapacity, boolean automaticTransmission) {
        super(brand, model, year, isRented);
        this.passengerCapacity = passengerCapacity;
        this.automaticTransmission = automaticTransmission;
    }

    // Getters and setters
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public boolean hasAutomaticTransmission() {
        return automaticTransmission;
    }

    public void setAutomaticTransmission(boolean automaticTransmission) {
        this.automaticTransmission = automaticTransmission;
    }
}

class Truck extends Vehicle {
    private int loadCapacity;
    private boolean fourWheelDrive;

    public Truck(String brand, String model, int year, boolean isRented, int loadCapacity, boolean fourWheelDrive) {
        super(brand, model, year, isRented);
        this.loadCapacity = loadCapacity;
        this.fourWheelDrive = fourWheelDrive;
    }

    // Getters and setters
    public int getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public boolean hasFourWheelDrive() {
        return fourWheelDrive;
    }

    public void setFourWheelDrive(boolean fourWheelDrive) {
        this.fourWheelDrive = fourWheelDrive;
    }
}

class Motorcycle extends Vehicle {
    private int engineVolume;
    private boolean hasABS;

    public Motorcycle(String brand, String model, int year, boolean isRented, int engineVolume, boolean hasABS) {
        super(brand, model, year, isRented);
        this.engineVolume = engineVolume;
        this.hasABS = hasABS;
    }

    // Getters and setters
    public int getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(int engineVolume) {
        this.engineVolume = engineVolume;
    }

    public boolean hasABS() {
        return hasABS;
    }

    public void setHasABS(boolean hasABS) {
        this.hasABS = hasABS;
    }
}

class Customer {
    private String firstName;
    private String lastName;
    private String idNumber;
    private ArrayList<Vehicle> rentedVehicles;
    private ArrayList<RentalContract> rentalContracts;

    public Customer(String firstName, String lastName, String idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        rentedVehicles = new ArrayList<>();
        rentalContracts = new ArrayList<>();
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public ArrayList<Vehicle> getRentedVehicles() {
        return rentedVehicles;
    }

    public ArrayList<RentalContract> getRentalContracts() {
        return rentalContracts;
    }

    // Method to rent a vehicle
    public void rentVehicle(Vehicle vehicle) {
        rentedVehicles.add(vehicle);
        vehicle.setRented(true);
    }

    // Method to return a vehicle
    public void returnVehicle(Vehicle vehicle) {
        rentedVehicles.remove(vehicle);
        vehicle.setRented(false);
    }
}

class RentalContract {
    private Customer customer;
    private Vehicle rentedVehicle;
    private Date rentalStartDate;
    private Date rentalEndDate;

    public RentalContract(Customer customer, Vehicle rentedVehicle, Date rentalStartDate, Date rentalEndDate) {
        this.customer = customer;
        this.rentedVehicle = rentedVehicle;
        this.rentalStartDate = rentalStartDate;
        this.rentalEndDate = rentalEndDate;
    }

    // Getters and setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getRentedVehicle() {
        return rentedVehicle;
    }

    public void setRentedVehicle(Vehicle rentedVehicle) {
        this.rentedVehicle = rentedVehicle;
    }

    public Date getRentalStartDate() {
        return rentalStartDate;
    }

    public void setRentalStartDate(Date rentalStartDate) {
        this.rentalStartDate = rentalStartDate;
    }

    public Date getRentalEndDate() {
        return rentalEndDate;
    }

    public void setRentalEndDate(Date rentalEndDate) {
        this.rentalEndDate = rentalEndDate;
    }

    // Method to calculate rental period in days
    public long calculateRentalPeriod() {
        long diff = rentalEndDate.getTime() - rentalStartDate.getTime();
        return diff / (24 * 60 * 60 * 1000);
    }
}
