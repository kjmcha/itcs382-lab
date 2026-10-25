package bikeshare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BikeService {
    private final List<Bike> bikes = new ArrayList<>();
    private final RentalStorage storage;

    public BikeService(RentalStorage storage) {
        this.storage = storage;
        bikes.add(new Bike("B1", BikeType.STANDARD));
        bikes.add(new Bike("B2", BikeType.ELECTRIC));
    }

    public List<Bike> getBikes() {
        return Collections.unmodifiableList(bikes);
    }

    public boolean rentBike(String id, String user) {
        Optional<Bike> bikeOpt = findBike(id);
        if (bikeOpt.isPresent() && !bikeOpt.get().isRented()) {
            bikeOpt.get().setRented(true);
            storage.saveAll(bikes);
            return true;
        }
        return false;
    }

    public double returnBike(String id, int hours) {
        Optional<Bike> bikeOpt = findBike(id);
        if (bikeOpt.isPresent() && bikeOpt.get().isRented()) {
            Bike bike = bikeOpt.get();
            bike.setRented(false);
            storage.saveAll(bikes);
            return calculateFee(bike.getType(), hours);
        }
        return 0.0;
    }

    public double calculateFee(BikeType type, int hours) {
        return hours * type.getHourlyRate();
    }

    private Optional<Bike> findBike(String id) {
        return bikes.stream().filter(b -> b.getId().equals(id)).findFirst();
    }
}