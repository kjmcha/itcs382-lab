package bikeshare;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileRentalStorage implements RentalStorage {
    private final String filename;

    public FileRentalStorage(String filename) {
        this.filename = filename;
    }

    @Override
    public void saveAll(List<Bike> bikes) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Bike bike : bikes) {
                writer.write(bike.getId() + "," + (bike.isRented() ? "RENTED" : "AVAILABLE") + "\n");
            }
        } catch (IOException e) {
            System.err.println("Failed to save rentals: " + e.getMessage());
        }
    }
}