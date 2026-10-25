package bikeshare;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RentalStorage storage = new FileRentalStorage("rentals.csv");
        BikeService service = new BikeService(storage);

        System.out.println("Commands: rent <id> <user>, return <id> <hours>, list, staff, quit");
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    continue;
                }

                String[] words = input.split(" ");
                String command = words[0];

                if (command.equals("rent") && words.length >= 3) {
                    boolean success = service.rentBike(words[1], words[2]);
                    System.out.println(success ? "Rented " + words[1] + " to " + words[2] : "Bike unavailable");
                } else if (command.equals("return") && words.length >= 3) {
                    double fee = service.returnBike(words[1], Integer.parseInt(words[2]));
                    System.out.println("Fee: " + fee);
                } else if (command.equals("list")) {
                    service.getBikes().stream()
                            .filter(b -> !b.isRented())
                            .forEach(b -> System.out.println(b.getId() + " is available"));
                } else if (command.equals("staff")) {
                    service.getBikes().forEach(b ->
                            System.out.println(b.getId() + " " + b.getType() + " " + (b.isRented() ? "RENTED" : "AVAILABLE")));
                } else if (command.equals("quit")) {
                    break;
                }
            }
        }
    }
}