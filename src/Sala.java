import java.io.Serial;
import java.io.Serializable;

public class Sala implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    private Posto[][] posti;

    /**
     * Crea una sala vuota.
     * @param righe Rappresenta il numero di lettere presenti. (es. A-J -> r = 10)
     * @param colonne Rappresenta il numero di posti per ogni riga. (es. 1-25 -> c = 25)
     */
    public Sala(int righe, int colonne){
        this.posti = new Posto[righe][colonne];

        for (int i = 0; i < righe; i++) {
            char lettera = (char) (65 + i);
            for (int j = 0; j < colonne; j++) {
                this.posti[i][j] = new Posto(j+1, lettera, false);
            }
        }
    }

    public Sala(Posto[][] posti) {
        this.posti = posti;
    }

    @Override
    public String toString() {
        String s = rigaToString(0);
        for (int i = 1; i < posti.length; i++) {
            s += ";" + rigaToString(i);
        }
        return s;
    }

    private String rigaToString(int i){
        String riga = posti[i][0].toString();
        for (int j = 0; j < posti[0].length; j++) {
            riga += "," + posti[i][j];
        }
        return riga;
    }
    
    public String stampa(){
        /*
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            for (byte colonna : posti){
                colonna
                // bit_i = colonna & 2^(7-i)
                sb.append()
            }
        }*/
        return "";
    }

    // FUNZIONALITA'
    public int postiDisponibili(){
        int liberi = 0;
        for (int i = 0; i < posti.length; i++) {
            for(Posto p : posti[i]){
                if (!p.isOccupato())
                    liberi++;
            }
        }
        return liberi;
    }

    public int postiOccupati(){
        return 200 - postiDisponibili();
    }
}
