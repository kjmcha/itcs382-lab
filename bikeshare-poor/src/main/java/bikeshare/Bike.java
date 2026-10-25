package bikeshare;

public class Bike {
    public String id;
    public int type; // 1 = Standard, 2 = Electric
    public String status;

    public Bike(String id, int type) {
        this.id = id;
        this.type = type;
        this.status = "AVAILABLE";
    }
}