package train;

public class Train {
    private static int idCount=0;
    private int id;
    private String tcode;
    private String name;
    private int seat;
    private int booked;
    private double departTime;
    private String departPlace;

    public Train() {
        this.id = ++idCount;
    }

    public Train(String tcode, String name, int seat, int booked, double departTime, String departPlace) {
        this.id = ++idCount;
        this.tcode = tcode;
        this.name = name;
        this.seat = seat;
        this.booked = booked;
        this.departTime = departTime;
        this.departPlace = departPlace;
    }

    public String getTcode() {
        return tcode;
    }

    public void setTcode(String tcode) {
        this.tcode = tcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public double getDepartTime() {
        return departTime;
    }

    public void setDepartTime(double departTime) {
        this.departTime = departTime;
    }

    public String getDepartPlace() {
        return departPlace;
    }

    public void setDepartPlace(String departPlace) {
        this.departPlace = departPlace;
    }

    @Override
    public String toString() {
        return new String().format("%-10s%-11s%-9d%-8d%-16.1f%-14s%d", tcode, name, seat, booked, departTime, departPlace, (seat-booked));
    }
}
