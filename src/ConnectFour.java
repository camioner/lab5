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
    public String isGameOver(int fila, int col) {
        char symbol = grid[fila][col];
        if (symbol == ' ') return "";

        // Horizontal ← →
        String line = buildLine(fila, col, 0, -1) + symbol + buildLine(fila, col, 0, 1);
        if (line.contains(repeat(symbol, 4))) return String.valueOf(symbol);

        // Vertical ↓ (solo hacia abajo es suficiente)
        line = symbol + buildLine(fila, col, 1, 0);
        if (line.contains(repeat(symbol, 4))) return String.valueOf(symbol);

        // Diagonal ↘ (↖ y ↘)
        line = buildLine(fila, col, -1, -1) + symbol + buildLine(fila, col, 1, 1);
        if (line.contains(repeat(symbol, 4))) return String.valueOf(symbol);

        // Diagonal ↙ (↗ y ↙)
        line = buildLine(fila, col, -1, 1) + symbol + buildLine(fila, col, 1, -1);
        if (line.contains(repeat(symbol, 4))) return String.valueOf(symbol);

        for (int c = 0; c < 6; c++) {
            if (grid[0][c] == ' ') {
                return ""; // aún hay espacios: no hay empate
            }
        }
        return "DRAW"; // si terminó el for, entonces está lleno
    }
    private String buildLine(int fila, int col, int dFila, int dCol) {
        StringBuilder sb = new StringBuilder();
        int f = fila + dFila;
        int c = col + dCol;

        while (f >= 0 && f < 7 && c >= 0 && c < 6) {
            sb.append(grid[f][c]);
            f += dFila;
            c += dCol;
        }

        return sb.toString();
    }
    private String repeat(char c, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) sb.append(c);
        return sb.toString();
    }



}
