package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static int rows;
    private static int seatsInRow;



    public static void main(String[] args) {
        var cinema = new Cinema();
        cinema.initCinema();
    }


    public void initCinema() {
        var scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        seatsInRow = scanner.nextInt();
        int totalNumberOfSeats = rows*seatsInRow;
        int totalIncome;
        if (totalNumberOfSeats <= 60) {
            totalIncome = 10*totalNumberOfSeats;
        } else {
            int frontRowsIncome = 10*(rows/2) * seatsInRow;
            int backRowsIncome = (int)(8*(Math.ceil(rows/2.0)) * seatsInRow);
            totalIncome = frontRowsIncome + backRowsIncome;
        }
        System.out.println("Total income: $" + totalIncome);
    }

    public void displayCinemaRoom(char[][] cinemaRoom) {
        var rowNumber = 1;
        for (var i=0; i<cinemaRoom.length; i++) {
            System.out.print(rowNumber +" ");
            rowNumber++;
            for (var j=0; j<cinemaRoom[i].length; j++) {
                System.out.print(cinemaRoom[i][j] + " ");;
            }
            System.out.println();
        }
    }

    public char[][] createCinemaRoom() {
        char[][] cinemaRoom = new char[rows][seatsInRow];
        for (var i=0; i<cinemaRoom.length; i++) {
            for (var j=0; j<cinemaRoom[i].length; j++) {
                Arrays.fill(cinemaRoom[i], 'S');
            }
        }
        return cinemaRoom;
    }
    

}
