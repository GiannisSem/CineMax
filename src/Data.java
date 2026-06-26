import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Questa classe crea e gestisce una Data con giorno, mese e anno.
 * @see DataOra
 *
 * @author Baragiola Samuele
 */
public class Data implements Serializable, Comparable<Data> {
    /**
     * <code>serialVersionUID</code> serve per la creazione automatica dei file binari.
     */
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * Il <code>formatter</code> serve per dare una formattazione personalizzata alla data <code>LocalDateTime.now()</code>.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * Contiene le informazioni riguardo la data di <code>oggi</code> nel formato imposto dalla classe.
     */
    public static final Data oggi = new Data(LocalDateTime.now().format(formatter));

    /**
     * Il <code>giorno</code> della data.
     */
    private int giorno;
    /**
     * Il <code>mese</code> della data.
     */
    private int mese;
    /**
     * L'<code>anno</code> della data.
     */
    private int anno;

    /**
     * Questo metodo costruisce una <strong>Data</strong>.
     * @param anno anno della data.
     * @param mese mese della data.
     * @param giorno giorno della data.
     */
    public Data(int anno, int mese, int giorno){
        this.anno = anno;
        this.mese = mese;
        this.giorno = giorno;
    }

    /**
     * Questo metodo costruisce una <strong>Data</strong>.
     * @param data sotto forma di stringa: yyyy/mm/dd.
     */
    public Data(String data){
        String[] attributi = data.split("-");
        this.anno = Integer.parseInt(attributi[0]);
        this.mese = Integer.parseInt(attributi[1]);
        this.giorno = Integer.parseInt(attributi[2]);
    }

    /**
     * Questo metodo crea la stringa associata all'istanza.
     * @return stringa associata all'istanza.
     */
    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", anno, mese, giorno);
    }

    /**
     * Questo metodo compara due oggetti di tipo <strong>Data</strong>.
     * @param other the object to be compared.
     * @return 1 se <em>this data</em> è maggiore della <em>other data</em>, 0 se sono uguali, altrimenti -1.
     */
    @Override
    public int compareTo(Data other) {
        // anno, mese, giorno
        if (anno == other.anno){
            if(mese == other.mese){
                return Integer.compare(giorno, other.giorno);
            }
            return Integer.compare(mese, other.mese);
        }
        return Integer.compare(anno, other.anno);
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>anno</code>.
     * @return <code>anno</code>
     */
    public int getAnno() {
        return anno;
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>mese</code>.
     * @return <code>mese</code>
     */
    public int getMese() {
        return mese;
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>giorno</code>.
     * @return <code>giorno</code>
     */
    public int getGiorno() {
        return giorno;
    }
}
