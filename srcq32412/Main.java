public class Scoreboard {
    BST wintree = new BST();//llave de int que es el numero de victorioas y valos es strin con nombre del jugador
    hash players = new hash<String,player>;//asocia el nombre con con su instancia de objeto jugador ,
    // de cierta manera lleva registro de jugadores
    int playedgames;//numero de partidas

    public void addgameresult(String Winname, String losername, boolean draw) {
        /*
 Registrael resultado de una partida, actualizando las estadísticas de
los jugadores involucrados y el árbol binario de búsqueda (winTree).
        */
    }

    public void registPlayer(String playername) {
        if (!players.contains(playername)) {
            players.add(playername);
        }

    }


}