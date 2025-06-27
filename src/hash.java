public class hash {

    private int M = 1024;
    private HashNode[] st = new HashNode[M];

    // Clase interna para representar cada nodo de la tabla
    private static class HashNode {
        private String nombre;   // la clave
        private Object jugador;  // el valor asociado
        private HashNode next;

        public HashNode(String nombre, Object jugador, HashNode next) {
            this.nombre = nombre;
            this.jugador = jugador;
            this.next = next;
        }
    }

    // Función hash para claves tipo String
    private int hash(String nombre) {
        return (nombre.hashCode() & 0x7fffffff) % M;
    }

    // Agrega o actualiza un jugador con ese nombre
    public void add(String nombre, Object jugador) {
        int i = hash(nombre);
        for (HashNode x = st[i]; x != null; x = x.next) {
            if (nombre.equals(x.nombre)) {
                x.jugador = jugador;
                return;
            }
        }
        st[i] = new HashNode(nombre, jugador, st[i]);
    }

    // Retorna el jugador asociado a ese nombre (o null si no existe)
    public Object get(String nombre) {
        int i = hash(nombre);
        for (HashNode x = st[i]; x != null; x = x.next) {
            if (nombre.equals(x.nombre)) {
                return x.jugador;
            }
        }
        return null;
    }

    // Verifica si existe un jugador con ese nombre
    public boolean contains(String nombre) {
        return get(nombre) != null;
    }

    // Elimina al jugador asociado a ese nombre
    public void delete(String nombre) {
        int i = hash(nombre);
        HashNode prev = null;
        for (HashNode x = st[i]; x != null; x = x.next) {
            if (nombre.equals(x.nombre)) {
                if (prev == null) {
                    st[i] = x.next;
                } else {
                    prev.next = x.next;
                }
                return;
            }
            prev = x;
        }
    }
}
