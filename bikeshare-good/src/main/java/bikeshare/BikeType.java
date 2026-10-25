package bikeshare;

public enum BikeType {
    STANDARD(10.0),
    ELECTRIC(20.0);

    private final double hourlyRate;

    BikeType(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}