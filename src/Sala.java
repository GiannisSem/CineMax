import java.io.Serial;
import java.io.Serializable;

public class Sala implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final int lettere = 10;
    private static final int numeri = 20;

    private byte[] posti;

    public Sala(){
        this.posti = new byte[25];
    }

    public Sala(byte[] posti) {
        this.posti = posti;
    }

    public void stampa(){
        for (int i = 0; i < numeri; i++) {
            System.out.print(i+1 + " ");
        }
        System.out.println();
        for (int i = 0; i < lettere; i++) {
            char fila = (char) ('A' + i);
            System.out.print(fila + " | ");
            for (int j = 1; j <= numeri; j++) {
                System.out.print(isPostoDisponibile(fila, j) ? "X" : ".");
            }
            System.out.println();
        }
    }

    // FUNZIONALITA'
    public boolean isPostoDisponibile(char lettera, int numero){
        int indice = (lettera - 'A') * numeri + numero-1; // [0, 199]
        int indiceByte = indice / 8;
        int posizioneBit = indice % 8;

        return (posti[indiceByte] & (1 << posizioneBit)) != 0;
    }

    public boolean setPosto(char lettera, int numero, boolean SR) {
        if (!isPostoDisponibile(lettera, numero))
            return false;

        int indice = (lettera - 'A') * 20 + (numero - 1);
        int indiceByte = indice / 8;
        int posizioneBit = indice % 8;

        if (SR){
            // Set: Forza il bit a 1 (Operazione OR)
            posti[indiceByte] |= (byte) (1 << posizioneBit);
        } else {
            // Reset: Forza il bit a 0 (Operazione AND con maschera invertita)
            posti[indiceByte] &= (byte) ~(1 << posizioneBit);
        }
        return true;
    }

    public int postiOccupati(){
        int occupati = 0;
        for (byte b : posti) {
            // Integer.bitCount conta quanti bit sono a '1' in un intero.
            // b & 0xFF serve per evitare problemi con i numeri negativi (signed byte).
            occupati += Integer.bitCount(b & 0xFF);
        }
        return occupati;
    }

    public int postiDisponibili(){
        return 200 - postiOccupati();
    }
}
