import java.io.Serial;
import java.io.Serializable;

/**
 * Questa classe crea e gestisce una Proiezione.
 *
 * @author Samuele Baragiola
 */
public class Proiezione implements Serializable, Comparable<Proiezione> {
    /**
     * <code>serialVersionUID</code> serve per la creazione automatica dei file binari.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Indica l'<code>orario</code> (data + ora) della Proiezione.
     */
    private DataOra dataOra;
    /**
     * Il <strong>Film</strong> da proiettare.
     */
    private Film film;

    /**
     * Il <code>costo del biglietto</code> del film proiettato.
     */
    private double costoBiglietto;

    /**
     * Lo stato della <strong>Sala</strong> durante la proiezione.
     * @see Sala
     */
    private Sala sala;

    /**
     * Questo metodo costruisce un oggetto <strong>Proiezione</strong>.
     * @param dataOra orario della proiezione.
     * @param film film proiettato.
     * @param costoBiglietto costo del biglietto del film proiettato.
     * @param sala stato della sala durante la proiezione.
     */
    public Proiezione(DataOra dataOra, Film film, double costoBiglietto, Sala sala){
        this.dataOra = dataOra;
        this.film = film;
        this.costoBiglietto = costoBiglietto;
        this.sala = sala;
    }

    /**
     * Questo metodo costruisce un nuovo oggetto <strong>Proiezione</strong>.
     * @param dataOra orario della proiezione.
     * @param film film proiettato.
     * @param costoBiglietto costo del biglietto del film proiettato.
     */
    public Proiezione(DataOra dataOra, Film film, double costoBiglietto){
        this(dataOra, film, costoBiglietto, new Sala());
    }

    /**
     * Questo metodo costruisce un oggetto <strong>Prenotazione</strong>.
     * @param anno anno della proiezione.
     * @param mese mese della proiezione.
     * @param giorno giorno della proiezione.
     * @param ore ore dell'orario della prenotazione.
     * @param minuti minuti dell'orario della prenotazione.
     * @param secondi secondi dell'orario della prenotazione.
     * @param titolo titolo del film proiettato.
     * @param genere genere del film proiettato.
     * @param regista regista del film proiettato.
     * @param annoUscita anno di uscita del film proiettato.
     * @param durata durata del film proiettato.
     * @param vmeta età minima consentita del film proiettato.
     * @param costoBiglietto costo del biglietto del film proiettato.
     * @param sala stato della sala durante la proiezione.
     */
    public Proiezione(int anno, int mese, int giorno, int ore, int minuti, int secondi,
                      String titolo, String genere, String regista, int annoUscita, int durata, int vmeta, double costoBiglietto, Sala sala){
        this(new DataOra(anno, mese, giorno, ore, minuti, secondi), new Film(titolo, genere, regista, annoUscita, durata, vmeta), costoBiglietto, sala);
    }

    /**
     * Questo metodo costruisce un oggetto <strong>Prenotazione</strong>.
     * @param anno anno della proiezione.
     * @param mese mese della proiezione.
     * @param giorno giorno della proiezione.
     * @param ore ore dell'orario della prenotazione.
     * @param minuti minuti dell'orario della prenotazione.
     * @param secondi secondi dell'orario della prenotazione.
     * @param film film proiettato.
     * @param costoBiglietto costo del biglietto del film proiettato.
     * @param sala stato della sala durante la proiezione.
     */
    public Proiezione(int anno, int mese, int giorno, int ore, int minuti, int secondi, Film film, double costoBiglietto, Sala sala){
        this(new DataOra(anno, mese, giorno, ore, minuti, secondi), film, costoBiglietto, sala);
    }

    /**
     * Questo metodo costruisce un nuovo oggetto <strong>Prenotazione</strong>.
     * @param dataOra sotto forma di stringa: yyyy-MM-dd hh:mm:ss.
     * @param film film proiettato.
     * @param costoBiglietto costo del biglietto del film proiettato.
     */
    public Proiezione(String dataOra, Film film, double costoBiglietto){
        this(new DataOra(dataOra), film, costoBiglietto, new Sala());
    }

    /**
     * Questo metodo compara due oggetti di tipo <strong>Proiezione</strong> in base all'<code>orario</code>.
     * @param other the object to be compared.
     * @return 1 se <em>this dataOra</em> è maggiore della <em>other dataOra</em>, 0 se sono uguali, altrimenti -1.
     */
    @Override
    public int compareTo(Proiezione other){
        return this.dataOra.compareTo(other.dataOra);
    }

    /**
     * Questo metodo crea la stringa associata all'istanza.
     * @return stringa associata all'istanza.
     */
    @Override
    public String toString(){
        return dataOra + " " + film + " " + costoBiglietto + " " + sala;
    }

    /**
     * Questo metodo crea la stringa associata all'istanza, da stampare a video.
     * @return stringa associata all'istanza.
     */
    public String toInfo(){
        return String.format(dataOra + "       " + film.getTitolo() + "   " + film.getGenere() + "   " + film.getRegista() + "   " + film.annoUscita() + "      " + film.getDurata() + "         " + film.getVmeta() + "          " + costoBiglietto + "           " + sala.postiDisponibili());
    }

    /**
     * Questo metodo crea la stringa associata all'istanza, da stampare a video.
     * @return stringa associata all'istanza.
     */
    public String toInfo1(){
        return String.format("Data e ora:  " + dataOra + "%n%nTitolo:  " + film.getTitolo() + "%n%nGenere:  " + film.getGenere() +"%n%nRegista:  " + film.getRegista() + "%n%nAnno di uscita:  " + film.annoUscita() + "%n%nDurata:  " + film.getDurata() + "%n%nEtà minima:  " + film.getVmeta() + "%n%nCosto biglietto:  " + costoBiglietto);
    }

    /**
     * Metodo <em>getter</em> dell'<code>orario</code> della Proiezione.
     * @return <code>orario</code> della proiezione.
     */
    public DataOra getDataOra(){
        return dataOra;
    }

    /**
     * Metodo <em>getter</em> del <strong>Film</strong> proiettato.
     * @return <code>film</code> proiettato.
     */
    public Film getFilm(){
        return film;
    }

    /**
     * Metodo <em>getter</em> del <code>costo del biglietto</code> del <strong>Film</strong> proiettato.
     * @return <code>costo del biglietto</code> del film proiettato.
     */
    public double getCostoBiglietto(){
        return costoBiglietto;
    }

    /**
     * Metodo <em>getter</em> dello stato della <strong>Sala</strong> durante la Proiezione.
     * @return stato della <code>sala</code> durante la proiezione.
     */
    public Sala getSala() { return sala; }
}
