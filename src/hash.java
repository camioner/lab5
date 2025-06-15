import java.io.*;
import java.util.*;

public class hash {
    public static int hash(String w, int size) {
        int result = w.length() % size;
        System.out.println("RESULTADO HASH: " + result);
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // largo maximo de mi tabla hash
        String[] hashTable = new String[N];
        int words = in.nextInt(); // X palabras
        for (int i = 0; i < N; i++) {
            hashTable[i] = "X";
        }
        int index;
        for (int i = 0; i < words; i++) {
            String w = in.next();
            index = hash(w, N);
            System.out.println("index devuelto: " + index);
            hashTable[index] = w;
        }
        for (int i = 0; i < N; i++) {
            System.out.print(hashTable[i] + " ");
        }
    }
}