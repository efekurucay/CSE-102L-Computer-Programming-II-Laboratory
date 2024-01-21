import java.util.Calendar;
public class Ex2_cse_102L_Spring{
    public static void main(String[] args) {

    }

}
class City{
    private String postalCode;
    private String name;

    City(String postalCode, String name){
        this.postalCode = postalCode;
        this.name = name;
    }

    public String getPostalCode(){
        return postalCode;
    }
    public String getName(){
        return name;
    }
}

class Ticket{
    private City from;
    private City to;
    private java.util.Date date;
    private int seat;

    Ticket(City from, City to, java.util.Date date, int seat){
        this.from = from;
        this.to = to;
        this.date = date;
        this.seat = seat;
    }
    Ticket(City from, City to, int seat){
        this.from = from;
        this.to = to;
        this.seat = seat;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        date = cal.getTime();
        System.out.println(date);
    }
    public City getFrom(){
        return from;
    }

    public City getTo(){
        return to;
    }

    public java.util.Date getDate(){
        return date;
    }

    public void setDate(java.util.Date date){
        this.date = date;
    }

    public int getSeat(){
        return seat;
    }

    public void setSeat(int seat){
        this.seat = seat;
    }

}

class Person{
    private String name;
    private String surname;
    private String phoneNumber;

    Person(String name, String surname, String phoneNumber){
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}