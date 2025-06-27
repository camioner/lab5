
public class Jugador {

    String nombre;
    int victoria = 0;
    int empates = 0;
    int perdidas = 0;
    double WinRate;

    Jugador(String nombre) {
        this.nombre = nombre;
    }

    public void addWin() {
        victoria += 1;
    }

    public void addDraw() {
        empates += 1;
    }

    public void addLoss() {
        perdidas += 1;
    }

    public double WinRate() {
        int total = victoria + perdidas + empates;
        WinRate = ((double) victoria / total);
        return WinRate;
    }


}
