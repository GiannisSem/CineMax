import java.io.Serial;
import java.io.Serializable;

public class Sala implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final int lettere = 10;
    private static final int numeri = 20;

    private boolean[] posti;

    public Sala(){
        this.posti = new boolean[200];
    }

    public Sala(boolean[] posti) {
        this.posti = posti;
    }

    public void stampa(){
        // Riga dei numeri (Intestazione colonne)
        System.out.print("    "); // Spazio per allineare con le lettere
        for (int i = 1; i <= numeri; i++) {
            System.out.print(String.format("%2d ", i)); // Allinea i numeri a 2 cifre
        }
        System.out.println("\n   " + "---".repeat(numeri));

        // Stampa da J (in alto) a A (in basso, vicino allo schermo)
        for (int i = lettere - 1; i >= 0; i--) {
            char fila = (char) ('A' + i);
            System.out.print(fila + " | ");
            for (int j = 1; j <= numeri; j++) {
                // Stampa "." se libero, " X" se occupato (con spazio per allineamento)
                System.out.print(isPostoDisponibile(fila, j) ? " . " : " X ");
            }
            System.out.println();
        }
        System.out.println("\n                           [ SCHERMO ]");
    }

    // FUNZIONALITA'
    public boolean isPostoDisponibile(char lettera, int numero){
        int indice = (lettera - 'A') * numeri + numero-1; // [0, 199]
        return !posti[indice];
    }

    public boolean setPosto(char lettera, int numero, boolean SR) {
        // se si vuole occupare il posto ma non è disponibile.
        if (SR && !isPostoDisponibile(lettera, numero))
            return false;

        int indice = (lettera - 'A') * 20 + (numero - 1);
        posti[indice] = SR;
        return true;
    }

    public int postiOccupati(){
        int occupati = 0;
        for (boolean posto : posti) {
            occupati += (posto ? 1 : 0);
        }
        return occupati;
    }

    public int postiDisponibili(){
        return 200 - postiOccupati();
    }

    public void svuotaSala(){
        for (int i = 0; i < 200; i++) {
            posti[i] = false;
        }
    }
}
