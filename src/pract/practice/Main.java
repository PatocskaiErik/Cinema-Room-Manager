package pract.practice;

import java.util.Scanner;

class Cinema {

    private static int rows;
    private static int seats;
    private static char[][] theater;
    private static int soldTickets = 0;
    private static int currentIncome = 0;
    private static int totalIncome;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();

        initializeTheater();

        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    return;
                case 1:
                    showSeats();
                    break;
                case 2:
                    buyTicket(scanner);
                    break;
                case 3:
                    showStatistics();
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }

    private static void initializeTheater() {
        theater = new char[rows][seats];
        calculateTotalIncome();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                theater[i][j] = 'S';
            }
        }
    }

    private static void showSeats() {
        System.out.println("Cinema:");
        System.out.print("  ");

        for (int i = 1; i <= seats; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");

            for (int j = 0; j < seats; j++) {
                System.out.print(theater[i][j] + " ");
            }

            System.out.println();
        }
    }

    private static void buyTicket(Scanner scanner) {
        while (true) {

            System.out.println("Enter a row number:");
            int row = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            int seat = scanner.nextInt();

            if (row < 1 || row > rows || seat < 1 || seat > seats) {
                System.out.println("Wrong input!");
            } else if (theater[row - 1][seat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                int price = calculatePrice(row);

                System.out.println("Ticket price: $" + price);

                theater[row - 1][seat - 1] = 'B';

                int totalSeats = rows * seats;
                int availableSeats = totalSeats - soldTickets;

                soldTickets++;
                currentIncome += price;


                break;
            }
        }
    }

    private static int calculatePrice(int row) {
        int totalSeats = rows * seats;
        int frontHalf = rows / 2;

        if (totalSeats <= 60 || row <= frontHalf) {
            return 10;
        } else {
            return 8;
        }
    }

    private static void calculateTotalIncome() {
        int totalSeats = rows * seats;
        int frontHalf = rows / 2;
        int backHalf = rows - frontHalf;

        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            totalIncome = (frontHalf * seats * 10) + (backHalf * seats * 8);
        }
    }

    private static void showStatistics() {
        int totalSeats = rows * seats;
        double percentage = (double) soldTickets / totalSeats * 100;

        System.out.println("Number of purchased tickets: " + soldTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}
