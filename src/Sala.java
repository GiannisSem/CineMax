import java.io.Serial;
import java.io.Serializable;

/**
 * Questa classe crea e gestisce lo stato di una Sala
 */
public class Sala implements Serializable {
    /**
     * <code>serialVersionUID</code> serve per la creazione automatica dei file binari.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * Le <code>lettere</code> disponibili: A-J.<br>
     * Rappresenta le file.
     */
    private static final int lettere = 10;

    /**
     * I <code>numeri</code> dei sedili per ogni fila: 1-20.
     */
    private static final int numeri = 20;

    /**
     * Vettore che tiene traccia dei <code>posti</code> liberi e occupati.
     */
    private boolean[] posti;

    /**
     * Questo metodo costruisce un nuovo oggetto <strong>Sala</strong>.
     */
    public Sala(){
        this.posti = new boolean[200];
    }

    /**
     * Stampa a video la griglia dei posti.
     */
    public void stampa(){
        System.out.print("    "); // Spazio per allineare con le lettere
        for (int i = 1; i <= numeri; i++) {
            System.out.print(String.format("%2d ", i));
        }
        System.out.println("\n   " + "---".repeat(numeri));

        // Stampa da J (in alto) a A (in basso, vicino allo schermo)
        for (int i = lettere - 1; i >= 0; i--) {
            char fila = (char) ('A' + i);
            System.out.print(fila + " | ");
            for (int j = 1; j <= numeri; j++) {
                System.out.print(isPostoDisponibile(fila, j) ? " . " : " X ");
            }
            System.out.println();
        }
        System.out.println("\n                           [ SCHERMO ]");
    }

    // FUNZIONALITA'

    /**
     * Verifica se un posto in Sala è libero o occupato.
     * @param lettera lettera del posto.
     * @param numero numero del posto.
     * @return true se il posto è libero, altrimenti false.
     */
    public boolean isPostoDisponibile(char lettera, int numero){
        int indice = (lettera - 'A') * numeri + numero-1; // [0, 199]
        return !posti[indice];
    }

    /**
     * Imposta un posto a libero/occupato.
     * @param posto posto è formato dalla combinazione di numero e lettera.
     * @param setReset true per provare ad occupare il posto, se disponibile. Invece false per liberarlo.
     * @return Restituisce true se il posto è stato occupato/liberato correttamente, altrimenti false.
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

    /**
     * Occupa un posto nella Sala.
     * @param posto posto è formato dalla combinazione di numero e lettera.
     * @return true se il posto è stato occupato, altrimenti false.
     */
    public boolean occupaPosto(String posto){
        return setPosto(posto, true);
    }

    /**
     * Libera un posto nella Sala.
     * @param posto posto è formato dalla combinazione di numero e lettera.
     */
    public void liberaPosto(String posto){
        setPosto(posto, false);
    }

    /**
     * Conta il numero dei posti occupati nella Sala.
     * @return numero di posti occupati nella Sala.
     */
    public int postiOccupati(){
        int occupati = 0;
        for (boolean posto : posti) {
            occupati += (posto ? 1 : 0);
        }
        return occupati;
    }

    /**
     * Indica il numero di posti liberi rimanenti nella Sala.
     * @return numero di posti liberi rimanenti nella Sala.
     */
    public int postiDisponibili(){
        return 200 - postiOccupati();
    }

    /**
     * Metodo usato per svuotare la Sala, ovvero liberando tutti i posti.
     */
    public void svuotaSala(){
        for (int i = 0; i < 200; i++) {
            posti[i] = false;
        }
    }
}
