package com.company;

public class Cinema {


    public void init() {
        initRowsAndColumns();
    }

    public void initRowsAndColumns() {
        int column = 1;
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i=1; i<=8; i++) {
            System.out.print(column+" ");
            column++;
        }
        System.out.println();
        for (int i=1; i<=7; i++) {
            for (int j=0; j<9; j++) {
                if (j>0) {
                    System.out.print("S ");
                    continue;
                }
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

}
