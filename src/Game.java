import java.util.Scanner;

public class Game {
    public String status;  // IN_PROGRESS, VICTORY, DRAW
    public String winnerPlayerName;
    public String playerNameA;
    public String playerNameB;
    public ConnectFour connectFour;
    public Scoreboard scoreboard;

    public Game(String playerNameA, String playerNameB, Scoreboard scoreboard) {
        this.playerNameA = playerNameA;
        this.playerNameB = playerNameB;
        this.status = "IN_PROGRESS";
        this.winnerPlayerName = "";
        this.connectFour = new ConnectFour();
        this.scoreboard = scoreboard;

        // Registrar jugadores en scoreboard si no existen
        if (!scoreboard.check4player(playerNameA)) {
            scoreboard.registPlayer(playerNameA);
        }
        if (!scoreboard.check4player(playerNameB)) {
            scoreboard.registPlayer(playerNameB);
        }
    }

    public String play() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard();

            String currentPlayer = (connectFour.currentSymbol == 'X') ? playerNameA : playerNameB;
            System.out.print(currentPlayer + " (" + connectFour.currentSymbol + "), elige columna (0 a 5): ");

            int columna;
            try {
                columna = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intenta de nuevo.");
                scanner.nextLine(); // Limpiar buffer
                continue;
            }

            if (!connectFour.makeamove(columna)) {
                System.out.println("Movimiento inválido. Intenta otra columna.");
                continue;
            }

            String resultado = connectFour.isGameOver(connectFour.lastfil, connectFour.lastCol);

            if (resultado.equals("X") || resultado.equals("O")) {
                printBoard();
                status = "VICTORY";
                winnerPlayerName = resultado.equals("X") ? playerNameA : playerNameB;
                System.out.println("¡Victoria de " + winnerPlayerName + "!");

                // Actualizar scoreboard
                String loser = (winnerPlayerName.equals(playerNameA)) ? playerNameB : playerNameA;
                scoreboard.addgameresult(winnerPlayerName, loser, false);

                return winnerPlayerName;
            } else if (resultado.equals("DRAW")) {
                printBoard();
                status = "DRAW";
                System.out.println("¡Empate!");

                // Actualizar scoreboard para empate
                scoreboard.addgameresult(playerNameA, playerNameB, true);

                return "";
            }
        }
    }

    private void printBoard() {
        System.out.println("\nTablero:");
        for (int fila = 0; fila < 7; fila++) {
            for (int col = 0; col < 6; col++) {
                System.out.print("| " + connectFour.grid[fila][col] + " ");
            }
            System.out.println("|");
        }
        System.out.println("  0   1   2   3   4   5\n");
    }

    // main para probar el juego
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.cargarDatos(); // carga datos guardados al iniciar

        System.out.print("Nombre jugador A (X): ");
        String nameA = scanner.nextLine();

        System.out.print("Nombre jugador B (O): ");
        String nameB = scanner.nextLine();

        Game partida = new Game(nameA, nameB, scoreboard);
        partida.play();

        // Guardar datos al terminar
        scoreboard.guardarDatos();
    }
}
