import java.io.*;
import java.util.*;

public class BST {
    public Node root;

    public static class Node {
        int key; // cantidad de victorias
        ArrayList<String> jugadores; // nombres de jugadores con esas victorias
        Node izq, der;

        public Node(int key) {
            this.key = key;
            this.jugadores = new ArrayList<>(); // Aquí se crea la lista vacía
            this.izq = null;
            this.der = null;
        }


    }
    static Node getSuccessor(Node curr) {
        curr = curr.der;
        while (curr != null && curr.izq != null) {
            curr = curr.izq;
        }
        return curr;
    }
    public static Node successor(Node root, int wins) {
        Node succ = null;
        while (root != null) {
            if (wins < root.key) {
                succ = root;        // candidato a sucesor
                root = root.izq;    // busca más pequeño
            } else {
                root = root.der;    // salta a mayores
            }
        }
        return succ;
    }

    static Node delNode(Node root, int x) {
        if (root == null) {
            return root;
        }
        if (root.key > x) {
            root.izq = delNode(root.izq, x);
        } else if (root.key < x) {
            root.der = delNode(root.der, x);
        } else {
            if (root.izq == null) {
                return root.der;
            }
            if (root.der == null) {
                return root.izq;
            }
            Node succ = getSuccessor(root);
            root.key = succ.key;
            root.jugadores = new ArrayList<>(succ.jugadores); // Copiar lista jugadores
            root.der = delNode(root.der, succ.key);
        }
        return root;
    }

    public static Node removeJugador(Node root, int key, String nombre) {
        if (root == null) return null;

        if (key < root.key) {
            root.izq = removeJugador(root.izq, key, nombre);
        } else if (key > root.key) {
            root.der = removeJugador(root.der, key, nombre);
        } else {
            root.jugadores.remove(nombre);
            if (root.jugadores.isEmpty()) {
                return delNode(root, key); // elimina el nodo completo
            }
        }
        return root;
    }

    public static Node push(Node root, int key, String nombre) {
        if (root == null) {
            Node nuevo = new Node(key);
            nuevo.jugadores.add(nombre);
            return nuevo;
        } else if (key == root.key) {
            if (!root.jugadores.contains(nombre))
                root.jugadores.add(nombre);
        } else if (key < root.key) {
            root.izq = push(root.izq, key, nombre);
        } else {
            root.der = push(root.der, key, nombre);
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
    public static void winRange(Node root, int lo, int hi, ArrayList<String> resultado) {
        if (root == null) return;

        // Recorrer el subárbol izquierdo si puede haber claves dentro del rango
        if (root.key > lo) {
            winRange(root.izq, lo, hi, resultado);
        }

        // Si la clave está dentro del rango, agregar los jugadores
        if (root.key >= lo && root.key <= hi) {
            resultado.addAll(root.jugadores);
        }

        // Recorrer el subárbol derecho si puede haber claves dentro del rango
        if (root.key < hi) {
            winRange(root.der, lo, hi, resultado);
        }
    }

}