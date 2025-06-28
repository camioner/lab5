import java.io.*;
import java.util.*;

public class ConnectFour {
    public char[][] grid;
    public char currentSymbol;

    public ConnectFour() {
        grid = new char[7][6];
        for (int fila = 0; fila < 7; fila++) {
            for (int columna = 0; columna < 6; columna++) {
                grid[fila][columna] = ' ';
            }
        }
        currentSymbol = 'X';
    }
    public boolean makeamove(int move){
        int moves = move-1;
        if (moves < 0 || moves > 7 || grid[moves][0] != ' ' ) {return false;}
        //hacer el movimiento

        if (currentSymbol == 'X'){currentSymbol = 'O';}
        else{currentSymbol = 'X';}
        return true;
    }


}
