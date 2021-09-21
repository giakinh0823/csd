package booking;

public class Booking {
    private static int idCount;
    private int id;
    private String tcode;
    private String ccode;
    private int seat;

    public Booking() {
        this.id = ++idCount;
    }

    public Booking(String tcode, String ccode, int seat) {
        this.id = ++idCount;
        this.tcode = tcode;
        this.ccode = ccode;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public String getTcode() {
        return tcode;
    }

    public void setTcode(String tcode) {
        this.tcode = tcode;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
