import java.util.Date;
/**-----------------------------------------------------
* Akdeniz University CSE102L Lab Assignments
* Name: Yahya Efe Kurucay
* Date: 16.04.2024
* Description: Homework02
* Score: 100
* Website: https://efekurucay.com
*----------------------------------------------------- */
/*
 * Bu bir otomatik maildir!!
 -------------START----------------
20220808005 - HW02->100
Test Result
Successfull!
--------------END-----------------

 */
public class HW02_20220808005 {
    public static void main(String[] args) {
        // Creating cities
        City city1 = new City("12345", "City A");
        City city2 = new City("67890", "City B");

        // Creating a person
        Person person = new Person("John", "Doe", "123-456-7890");

        // Creating a ticket
        Ticket ticket1 = new Ticket(city1, city2, new Date(), 10);
        System.out.println("Ticket 1:");
        System.out.println("From: " + ticket1.getFrom().getName());
        System.out.println("To: " + ticket1.getTo().getName());
        System.out.println("Date: " + ticket1.getDate());
        System.out.println("Seat: " + ticket1.getSeat());

        Ticket ticket2 = new Ticket(city2, city1, 20);
        System.out.println("\nTicket 2:");
        System.out.println("From: " + ticket2.getFrom().getName());
        System.out.println("To: " + ticket2.getTo().getName());
        System.out.println("Date: " + ticket2.getDate());
        System.out.println("Seat: " + ticket2.getSeat());

        // Updating phone number of the person
        person.setPhoneNumber("987-654-3210");
        System.out.println("\nUpdated Phone Number: " + person.getPhoneNumber());
    }
}

// Ticket class
class Ticket {
    private City from;
    private City to;
    private Date date;
    private int seat;

    // Constructor with from, to, date, and seat
    public Ticket(City from, City to, Date date, int seat) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.seat = seat;
    }

    // Constructor with from, to, and seat (date set to 1 day later)
    public Ticket(City from, City to, int seat) {
        this.from = from;
        this.to = to;
        this.seat = seat;
        this.date = new Date(System.currentTimeMillis() + 86400000); // Adding 1 day in milliseconds
    }

    // Getter and setter methods
    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}

// City class
class City {
    private String postalCode;
    private String name;

    // Constructor
    public City(String postalCode, String name) {
        this.postalCode = postalCode;
        this.name = name;
    }

    // Getter methods
    public String getPostalCode() {
        return postalCode;
    }

    public String getName() {
        return name;
    }
}

// Person class
class Person {
    private String name;
    private String surname;
    private String phoneNumber;

    // Constructor
    public Person(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
