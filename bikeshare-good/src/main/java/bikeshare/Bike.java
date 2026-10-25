package bikeshare;

public class Bike {
    private final String id;
    private final BikeType type;
    private boolean rented;

    public Bike(String id, BikeType type) {
        this.id = id;
        this.type = type;
        this.rented = false;
    }

    public String getId() {
        return id;
    }

    public BikeType getType() {
        return type;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}