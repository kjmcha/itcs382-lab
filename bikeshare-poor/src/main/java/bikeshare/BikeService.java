package bikeshare;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BikeService {
    public static List<Bike> bikes = new ArrayList<>();

    static {
        bikes.add(new Bike("B1", 1));
        bikes.add(new Bike("B2", 2));
    }

    public void rentBike(String id, String user) {
        for (Bike bike : bikes) {
            if (bike.id.equals(id) && bike.status.equals("AVAILABLE")) {
                bike.status = "RENTED";
                System.out.println("Rented " + bike.id + " to " + user);
                saveToFile();
                return;
            }
        }
        System.out.println("Bike unavailable");
    }

    public double returnBike(String id, int hours) {
        for (Bike bike : bikes) {
            if (bike.id.equals(id)) {
                bike.status = "AVAILABLE";
                saveToFile();
                if (bike.type == 1) {
                    return hours * 10.0;
                } else {
                    return hours * 20.0;
                }
            }
        }
        System.out.println("Bike not found");
        return 0.0;
    }

    public void printBikes(boolean forStaff) {
        for (Bike bike : bikes) {
            if (forStaff) {
                System.out.println(bike.id + " " + bike.type + " " + bike.status);
            } else if (bike.status.equals("AVAILABLE")) {
                System.out.println(bike.id + " is available");
            }
        }
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter("rentals.csv")) {
            for (Bike bike : bikes) {
                writer.write(bike.id + "," + bike.status + "\n");
            }
        } catch (IOException e) {
            System.err.println("File save failed: " + e.getMessage());
        }
    }
}