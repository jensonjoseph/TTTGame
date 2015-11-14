package com.example.t9422jk.ttt_sms;

/**
 * Created by T9422JK on 11/12/2015.
 */
public class PlayerInfo {
    // public static String myNumber="";
    public static String oponentNumber = "";
    public static String mySymbol = "";
    public static String opponentSymbol = "";

    public static Cell[][] myCell = new Cell[4][4];
    public static Cell[][] opponentCell = new Cell[4][4];

    public static void initialize() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                myCell[i][j] = new Cell();
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                opponentCell[i][j] = new Cell();
            }
        }
    }
public static void reset(){
    initialize();

}
    public static boolean amIWinner() {
        int x = 0, y = 0, xy = 0, yz = 0, x1 = 0, x2 = 0, x3 = 0, x0 = 0, y1 = 0, y2 = 0, y3 = 0, y0 = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {


                if (myCell[i][j].symbol.equals(mySymbol)) {
                    if (i == j) {
                        xy++;
                    }
                    if (i + j == 3) {
                        yz++;
                    }
                    if (i == 0) {
                        x0++;
                    } else if (i == 1) {
                        x1++;
                    } else if (i == 2) {
                        x2++;
                    } else if (i == 3) {
                        x3++;
                    }

                    if (j == 0) {
                        y0++;
                    } else if (j == 1) {
                        y1++;
                    } else if (j == 2) {
                        y2++;
                    } else if (j == 3) {
                        y3++;
                    }

                }


            }
        }

        if (xy == 4 || yz == 4 || x0 == 4 || x1 == 4 || x2 == 4 || x3 == 4 || y0 == 4 || y1 == 4 || y2 == 4 || y3 == 4) {
            return true;
        } else {
            return false;

        }
    }

}
