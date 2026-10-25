package bikeshare;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BikeService service = new BikeService();
        System.out.println("Commands: rent <id> <user>, return <id> <hours>, list, staff, quit");
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNextLine()) {
                String input = in.nextLine().trim();
                if (input.isEmpty()) {
                    continue;
                }
                String[] words = input.split(" ");
                if (words[0].equals("rent") && words.length >= 3) {
                    service.rentBike(words[1], words[2]);
                } else if (words[0].equals("return") && words.length >= 3) {
                    double fee = service.returnBike(words[1], Integer.parseInt(words[2]));
                    System.out.println("Fee: " + fee);
                } else if (words[0].equals("list")) {
                    service.printBikes(false);
                } else if (words[0].equals("staff")) {
                    service.printBikes(true);
                } else if (words[0].equals("quit")) {
                    break;
                }
            }
        }
    }
}