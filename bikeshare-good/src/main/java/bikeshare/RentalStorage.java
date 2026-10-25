package bikeshare;

import java.util.List;

public interface RentalStorage {
    void saveAll(List<Bike> bikes);
}