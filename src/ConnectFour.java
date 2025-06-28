import java.io.*;
import java.util.*;

public class ConnectFour {
    public char[][] grid;
    public char currentSymbol;
    public int lastfil , lastCol;

    public ConnectFour() {
        grid = new char[7][6];
        for (int fila = 0; fila < 7; fila++) {
            for (int columna = 0; columna < 6; columna++) {
                grid[fila][columna] = ' ';
            }
        }
        currentSymbol = 'X';
    }
    public boolean makeamove(int move) {
        if (move < 0 || move > 6 || grid[0][move] != ' ') {
            return false;
        }
        //hacer el movimiento
        for (int fila = 6; fila >= 0; fila--) {
            if (grid[fila][move] == ' ') {
                grid[fila][move] = currentSymbol;
                lastfil = fila;
                lastCol = move;
                if (currentSymbol == 'X') {
                    currentSymbol = 'O';
                } else {
                    currentSymbol = 'X';
                }
                return true;

            }
        }

        return false;
    }
}
