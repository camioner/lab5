import java.io.*;
import java.util.*;

public class BST {
    public static class Node {
        int key;
        Node izq;
        Node der;
        String value;

        public Node(int key,String value) {
            this.izq = null;
            this.der = null;
            this.key = key;
        }
    }

    public static Node push(Node root, int key, String value) {
        if (root == null) {
            root = new Node(key,value);
        } else if (key <= root.key) {
            root.izq = push(root.izq, key, value);
        } else {
            root.der = push(root.der, key, value);
        }
        return root;
    }

    public static void preOrder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preOrder(root.izq);
            preOrder(root.der);
        }
    }

    public static void inOrder(Node root) {
        if (root != null) {
            inOrder(root.izq);
            System.out.print(root.key + " ");
            inOrder(root.der);
        }
    }

    public static void postOrder(Node root) {
        if (root != null) {
            postOrder(root.izq);
            postOrder(root.der);
            System.out.print(root.key + " ");
        }
    }
}