package lab5;
//crear un mapa con el que guarde el indice de hash el cual poner en un mapa de arreglo
public class hash {

    private int M = 1024;
    private HashNode[] st = new HashNode[M];

    private static class HashNode {
        private int key;
        private String val;
        private HashNode next;

        public HashNode(int key, String val, HashNode node) {
            this.key = key;
            this.val = val;

        }
    }

    public String get(int key) {

        int i = hash(key);
        for (HashNode x = st[i]; x != null; x = x.next)
            if (key==x.key) return x.val;
        return null;

    }
    public int hashCode()
    {
        int hash = 0;
        for (int i = 0; i < length(); i++)
            hash = s[i] + (31 * hash);
        return hash;
    }
    private int hash(int key)
    { return (key.hashCode() & 0x7fffffff) % M; }

    public void put(int key, String val) {

        int i = hash(key);
        for (HashNode x = st[i]; x != null; x = x.next)
            if (key==x.key) { x.val = val; return; }
        st[i] = new HashNode(key, val, st[i]);
    }

}