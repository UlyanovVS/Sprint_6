package data;

public class OrderData {
    public final String name;
    public final String surname;
    public final String address;
    public final String metro;
    public final String phone;
    public final String date;
    public final String duration;
    public final boolean fromLowerButton;

    public OrderData(String name, String surname, String address, String metro,
                     String phone, String date, String duration, boolean fromLowerButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.fromLowerButton = fromLowerButton;
    }
}
