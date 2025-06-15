/******************************************************************************

 Welcome to GDB Online.
 GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
 C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
 Code, Compile, Run and Debug online from anywhere in world.
 *******************************************************************************/
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
        WinRate = (victoria / total) * 100;
        return WinRate;
    }


}
