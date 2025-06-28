import java.io.*;
import java.util.*;


public class Scoreboard {
    BST wintree = new BST();//llave de int que es el numero de victorioas y valos es string con nombre del jugador
    hash players = new hash();    //asocia el nombre con con su instancia de objeto jugador ,
    // de cierta manera lleva registro de jugadores
    int playedgames;  //numero de partidas

    public void addgameresult(String Winname, String losername, boolean draw) {
        // Verificar si los jugadores existen
        if (!players.contains(Winname) || !players.contains(losername)) return;

        Jugador ganador = (Jugador) players.get(Winname);
        Jugador perdedor = (Jugador) players.get(losername);

        if (draw) {
            // Empate: ambos suman empate, no se modifica el árbol BST
            ganador.addDraw();
            perdedor.addDraw();
        } else {
            // 1. Eliminar al ganador de su nodo actual del árbol (si tiene al menos 1 victoria)
            if (ganador.victoria > 0) {
                wintree.root = BST.removeJugador(wintree.root, ganador.victoria, ganador.nombre);
            }

            // 2. Eliminar al perdedor de su nodo actual del árbol (si tiene al menos 1 victoria)
            if (perdedor.victoria > 0) {
                wintree.root = BST.removeJugador(wintree.root, perdedor.victoria, perdedor.nombre);
            }

            // 3. Actualizar estadísticas
            ganador.addWin();
            perdedor.addLoss();

            // 4. Reinsertar con nuevas victorias
            wintree.root = BST.push(wintree.root, ganador.victoria, ganador.nombre);
            wintree.root = BST.push(wintree.root, perdedor.victoria, perdedor.nombre);
        }

        // 5. Sumar al contador de partidas
        playedgames++;
    }


    public void registPlayer(String playername) {
        if (!players.contains(playername)) {
            Jugador j = new Jugador(playername);
            players.add(playername, j);

        }
    }
    public Jugador[] winSuccessor(int wins) {
        BST.Node nodo = BST.successor(wintree.root, wins);
        if (nodo == null) return new Jugador[0];

        Jugador[] jugadores = new Jugador[nodo.jugadores.size()];
        for (int i = 0; i < nodo.jugadores.size(); i++) {
            jugadores[i] = (Jugador) players.get(nodo.jugadores.get(i));
        }
        return jugadores;
    }


    public boolean check4player(String playername) {
        return players.contains(playername);
    }

    public void guardarDatos() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("jugadores.txt"))) {
            writer.println("playedGames=" + playedgames);

            ArrayList<Jugador> lista = players.getJugadores();
            for (Jugador j : lista) {
                writer.println(j.nombre + "," + j.victoria + "," + j.empates + "," + j.perdidas);
            }

        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }


    public void cargarDatos() {
        try (BufferedReader reader = new BufferedReader(new FileReader("jugadores.txt"))) {
            String linea = reader.readLine();
            if (linea.startsWith("playedGames=")) {
                playedgames = Integer.parseInt(linea.split("=")[1]);
            }

            String datos;
            while ((datos = reader.readLine()) != null) {
                String[] partes = datos.split(",");
                String nombre = partes[0];
                int wins = Integer.parseInt(partes[1]);
                int draws = Integer.parseInt(partes[2]);
                int losses = Integer.parseInt(partes[3]);

                Jugador j = new Jugador(nombre);
                j.victoria = wins;
                j.empates = draws;
                j.perdidas = losses;

                players.add(nombre, j);

                // Insertar al jugador en el BST usando su cantidad de victorias
                wintree.root = BST.push(wintree.root, wins, nombre);
            }

        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        }
    }

    public Jugador[] winRange(int lo, int hi) {
        ArrayList<String> nombres = new ArrayList<>();
        BST.winRange(wintree.root, lo, hi, nombres);

        Jugador[] jugadores = new Jugador[nombres.size()];
        for (int i = 0; i < nombres.size(); i++) {
            jugadores[i] = (Jugador) players.get(nombres.get(i));
        }
        return jugadores;
    }


}
