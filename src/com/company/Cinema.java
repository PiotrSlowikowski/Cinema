package com.company;

import java.util.List;
import java.util.Scanner;

public class Cinema {
    private static int numberOfPurchasedTickets = 0;
    private static int currentIncome = 0;


    public static void main(String[] args) {
        // Write your code here
        appInit();
    }

    public static void appInit() {
        char[][] cinemaRoom = roomInit();
        displayMenu(cinemaRoom);
    }

    public static void displayMenu(char[][] cinemaRoom) {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        boolean shouldDisplayMenu = true;
        while (shouldDisplayMenu) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();
            if (choice == 1) {
                displayCinemaRoom(cinemaRoom);
            } else if (choice == 2) {
                buyTicket(cinemaRoom);
            } else if (choice == 3) {
                displayStatistics(cinemaRoom);
            } else if (choice == 0) {
                shouldDisplayMenu = false;
            }
        }
    }

    public static void displayStatistics(char[][] cinemaRoom) {
        System.out.println();
        int totalNumberOfSeats = cinemaRoom.length * (cinemaRoom[0].length - 1);
        double percentageOfTicketsSold = ((double) numberOfPurchasedTickets / (double) totalNumberOfSeats) * 100;

        System.out.println("Number of purchased tickets: " + numberOfPurchasedTickets);
        System.out.printf("Percentage: %.2f", percentageOfTicketsSold);
        System.out.print("%");
        System.out.println();
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + getPossibleIncome(cinemaRoom));
        System.out.println();
    }

    public static void buyTicket(char[][] cinemaRoom) {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter a row number: ");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int seatNumberInRow = scanner.nextInt();

        while (!checkIfCoordinateIsInRange(cinemaRoom, rowNumber, seatNumberInRow)) {
            System.out.println();
            System.out.println("Wrong input!");
            System.out.println();
            System.out.println("Enter a row number: ");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row: ");
            seatNumberInRow = scanner.nextInt();
            System.out.println();
        }


        if (checkSeatAvailability(cinemaRoom, rowNumber, seatNumberInRow)) {
            markSeat(cinemaRoom, rowNumber, seatNumberInRow);
            System.out.println("Ticket price: $" + getTicketPrice(cinemaRoom, rowNumber));
            currentIncome += getTicketPrice(cinemaRoom, rowNumber);
            numberOfPurchasedTickets++;
            System.out.println();
        } else {
            System.out.println("That ticket has already been purchased!");
            buyTicket(cinemaRoom);
        }


    }

    public static boolean checkIfCoordinateIsInRange(char[][] cinemaRoom, int rowNumber, int seatNumberInRow) {
        if (rowNumber < 0 || seatNumberInRow < 0 || rowNumber > cinemaRoom.length || seatNumberInRow > cinemaRoom[rowNumber-1].length - 1) {
            return false;
        }
        return true;
    }

    public static boolean checkSeatAvailability(char[][] cinemaRoom, int rowNumber, int seatNumberInRow) {
        if (cinemaRoom[rowNumber - 1][seatNumberInRow] == 'B') {
            return false;
        }
        return true;
    }

    public static char[][] markSeat(char[][] cinemaRoom, int rowNumber, int seatNumberInRow) {
        cinemaRoom[rowNumber - 1][seatNumberInRow] = 'B';
        return cinemaRoom;
    }


    public static int getPossibleIncome(char[][] cinemaRoom) {
        int totalNumberOfSeats = cinemaRoom.length * (cinemaRoom[0].length - 1);
        int totalPossibleIncome = 0;

        if (totalNumberOfSeats <= 60) {
            totalPossibleIncome = 10 * totalNumberOfSeats;
        } else if (totalNumberOfSeats > 60 && cinemaRoom.length % 2 == 0) {
            totalPossibleIncome = 9 * totalNumberOfSeats;
        } else if (totalNumberOfSeats > 60 && cinemaRoom.length % 2 != 0) {
            totalPossibleIncome = (10*(cinemaRoom.length/2)*cinemaRoom[0].length) + (8*((int)Math.ceil(cinemaRoom.length/2)*cinemaRoom[0].length));
        }
        return totalPossibleIncome;
    }

    public static int getTicketPrice(char[][] cinemaRoom, int rowNumber) {
        int ticketPrice = 0;
        int totalNumberOfSeats = cinemaRoom.length * (cinemaRoom[0].length - 1);

        if (totalNumberOfSeats > 60 && rowNumber <= (cinemaRoom.length / 2)) {
            ticketPrice = 10;
        } else if (totalNumberOfSeats > 60 && rowNumber > (cinemaRoom.length / 2)) {
            ticketPrice = 8;
        } else if (totalNumberOfSeats <= 60) {
            ticketPrice = 10;
        }
        return ticketPrice;
    }

    public static char[][] roomInit() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int seatsInRow = scanner.nextInt();
        char[][] seatingArrangement = new char[rows][seatsInRow + 1];

        List<Character> list = List.of('1', '2', '3', '4', '5', '6', '7', '8', '9');
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= seatsInRow; j++) {
                if (j == 0) {
                    seatingArrangement[i][j] = list.get(i);
                } else {
                    seatingArrangement[i][j] = 'S';
                }
            }
        }
        return seatingArrangement;
    }

    public static void displayCinemaRoom(char[][] cinemaRoom) {
        System.out.println();
        System.out.println("Cinema: ");
        System.out.printf("  ");
        for (int i = 1; i < cinemaRoom[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < cinemaRoom.length; i++) {
            for (int j = 0; j < cinemaRoom[i].length; j++) {
                System.out.print(cinemaRoom[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
