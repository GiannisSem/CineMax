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

    /**
     *
     * @param posto Posto (lettera + numero)
     * @param setReset True per provare ad occupare il posto, se disponibile. Invece false per liberarlo.
     * @return Restituisce true se il posto è stato occupato/liberato correttamente. Altrimenti false.
     */
    private boolean setPosto(String posto, boolean setReset) {
        char lettera = posto.charAt(posto.length() - 1);
        int numero = Integer.parseInt(posto.substring(0, posto.length() - 1));

        if (setReset && !isPostoDisponibile(lettera, numero))
            return false;

        int indice = (lettera - 'A') * 20 + (numero - 1);
        posti[indice] = setReset;
        return true;
    }

    public boolean occupaPosto(String posto){
        return setPosto(posto, true);
    }

    public void liberaPosto(String posto){
        setPosto(posto, false);
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
