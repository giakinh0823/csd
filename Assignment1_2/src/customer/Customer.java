package customer;

public class Customer {
    private static int idCount =0;
    private int id;
    private String ccode;
    private String name;
    private String phone;

    public Customer() {
        this.id = ++idCount;
    }

    public Customer(String ccode, String name, String phone) {
        this.ccode = ccode;
        this.name = name;
        this.phone = phone;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return new String().format("%-8s%-10s%-10s", ccode, name, phone);
    }
}
