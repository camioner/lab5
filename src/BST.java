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
    static Node getSuccessor(Node curr) {
        curr = curr.der;
        while (curr != null && curr.izq != null) {
            curr = curr.izq;
        }
        return curr;
    }
    static Node delNode(Node root, int x) {

        // Base case
        if (root == null) {
            return root;
        }

        // If key to be searched is in a subtree
        if (root.key > x) {
            root.izq = delNode(root.izq, x);
        } else if (root.key < x) {
            root.der = delNode(root.der, x);
        } else {
            // If root matches with the given key

            // Cases when root has 0 children or
            // only right child
            if (root.izq == null) {
                return root.der;
            }

            // When root has only left child
            if (root.der == null) {
                return root.izq;
            }

            // When both children are present
            Node succ = getSuccessor(root);
            root.key = succ.key;
            root.der = delNode(root.der, succ.key);
        }
        return root;
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