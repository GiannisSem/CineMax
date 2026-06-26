import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Questa classe crea e gestisce un orario con Data e Ora.
 * @see Data
 * @see Ora
 *
 * @author Samuele Baragiola
 */
public class DataOra implements Serializable, Comparable<DataOra> {
    /**
     * <code>serialVersionUID</code> serve per la creazione automatica dei file binari.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Il <code>formatter</code> serve per dare una formattazione personalizzata alla data e ora <code>LocalDateTime.now()</code>.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh HH:mm:ss");

    /**
     * Contiene le informazioni riguardo la data e l'ora di <code>oggi</code> (in questo momento) nel formato imposto dalla classe.
     */
    public static final DataOra oggi = new DataOra(LocalDateTime.now().format(formatter));

    /**
     * Parte dell'orario che contiene la <code>Data</code>.
     */
    private final Data data;

    /**
     * Parte dell'orario che contiene l'<code>Ora</code> (hh:mm:ss).
     */
    private final Ora ora;

    /**
     * Questo medoto costruisce un oggetto <strong>DataOra</strong>.
     * @param data parte della data.
     * @param ora parte dell'ora.
     */
    public DataOra(Data data, Ora ora){
        this.data = data;
        this.ora = ora;
    }

    /**
     * Questo medoto costruisce un oggetto <strong>DataOra</strong>.
     * @param anno anno della data.
     * @param mese mese della data.
     * @param giorno giorno della data.
     * @param ore ore dell'ora.
     * @param minuti minuti dell'ora.
     * @param secondi secondi dell'ora.
     */
    public DataOra(int anno, int mese, int giorno, int ore, int minuti, int secondi) {
        this(new Data(anno, mese, giorno), new Ora(ore, minuti, secondi));
    }

    /**
     * Questo medoto costruisce un oggetto <strong>DataOra</strong> tramite una stringa nel formato specificato.
     * @param dataOra stringa dell'orario nel formato yyyy-MM-dd hh:mm:ss.
     */
    public DataOra(String dataOra){
        String[] attributi = dataOra.split(" ");
        String data = attributi[0];
        String ora = attributi[1];

        this.data = new Data(data);
        this.ora = new Ora(ora);
    }

    /**
     * Questo metodo crea la stringa associata all'istanza.
     * @return stringa associata all'istanza.
     */
    @Override
    public String toString(){
        return data + " " + ora;
    }

    /**
     * Questo metodo compara due oggetti di tipo <strong>DataOra</strong>.
     * @param other the object to be compared.
     * @return 1 se <em>this dataOra</em> è maggiore della <em>other dataOra</em>, 0 se sono uguali, altrimenti -1.
     */
    @Override
    public int compareTo(DataOra other) {
        if (data.compareTo(other.data) == 0)
            return ora.compareTo(other.ora);
        return data.compareTo(other.data);
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>data</code>.
     * @return <code>data</code>
     * @see Data
     */
    public Data getData(){
        return data;
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>ora</code>.
     * @return <code>ora</code>
     * @see Ora
     */
    public Ora getOra() {
        return ora;
    }

    /**
     * Somma l'orario dell'istanza <em>this</em> con un <em>int</em> <code>minuti</code>.<br>
     * L'istanza <em>this</em> non viene modificata.
     * @param minuti minuti da aggiungere all'orario.
     * @return nuova istanza di tipo <strong>DataOra</strong> contenente l'orario aggiornato.
     */
    public DataOra aggiungi(int minuti){
        LocalDateTime tempoAttuale = LocalDateTime.parse(this.toString(), formatter);
        LocalDateTime tempoFuturo = tempoAttuale.plusMinutes(minuti);
        return new DataOra(tempoFuturo.format(formatter));
    }
}
