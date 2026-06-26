import java.io.Serial;
import java.io.Serializable;

/**
 * Questa classe crea e gestisce un'Ora con ore, minuti e secondi.
 * @see DataOra
 *
 * @author Samuele Baragiola
 */
public class Ora implements Serializable, Comparable<Ora> {
    /**
     * <code>serialVersionUID</code> serve per la creazione automatica dei file binari.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Le <code>ore</code> dell'ora.
     */
    private int ore;

    /**
     * I <code>minuti</code> dell'ora.
     */
    private int minuti;

    /**
     * I <code>secondi</code> dell'ora.
     */
    private int secondi;

    /**
     * Questo metodo costruisce un oggetto <strong>Ora</strong>.
     * @param ore ore dell'ora.
     * @param minuti minuti dell'ora.
     * @param secondi secondi dell'ora.
     */
    public Ora(int ore, int minuti, int secondi){
        this.ore = ore;
        this.minuti = minuti;
        this.secondi = secondi;
    }

    /**
     * Questo metodo costruisce un oggetto <strong>Ora</strong>.
     * @param ora sotto forma di stringa: hh:mm:ss.
     */
    public Ora(String ora){
        String[] attributi = ora.split(":");
        this.ore = Integer.parseInt(attributi[0]);
        this.minuti = Integer.parseInt(attributi[1]);
        this.secondi = Integer.parseInt(attributi[2]);
    }

    /**
     * Questo metodo crea la stringa associata all'istanza.
     * @return stringa associata all'istanza.
     */
    @Override
    public String toString(){
        return String.format("%02d:%02d:%02d", ore, minuti, secondi);
    }

    /**
     * Questo metodo compara due oggetti di tipo <strong>Ora</strong>.
     * @param other the object to be compared.
     * @return 1 se <em>this ora</em> è maggiore della <em>other ora</em>, 0 se sono uguali, altrimenti -1.
     */
    @Override
    public int compareTo(Ora other) {
        // hh:mm:ss
        if (ore == other.ore){
            if(minuti == other.minuti){
                return Integer.compare(secondi, other.secondi);
            }
            return Integer.compare(minuti, other.minuti);
        }
        return Integer.compare(ore, other.ore);
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>ore</code>.
     * @return <code>ore</code>
     */
    public int getOre() {
        return ore;
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>minuti</code>.
     * @return <code>minuti</code>
     */
    public int getMinuti() {
        return minuti;
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>secondi</code>.
     * @return <code>secondi</code>
     */
    public int getSecondi() {
        return secondi;
    }

    /**
     * Metodo <em>getter</em> dell'<strong>ora</strong> espressa in <code>minuti</code>.
     * @return ora in <code>minuti</code>
     */
    public int getOraInMinutiTot(){
        return ore * 60 + minuti;
    }
}
