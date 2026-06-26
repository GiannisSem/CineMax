import java.io.Serial;
import java.io.Serializable;

/**
 * Questa classe crea e gestisce i Film.
 *
 * @author Samuele Baragiola
 */
public class Film implements Serializable, Comparable<Film> {
    /**
     * <code>serialVersionUID</code> serve per la creazione automatica dei file binari.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Il <code>titolo</code> del film.
     */
    private String titolo;

    /**
     * Il <code>genere</code> del film.
     */
    private String genere;

    /**
     * Il <code>regista</code> del film.
     */
    private String regista;

    /**
     * L'<code>anno di uscita</code> del film.
     */
    private int annoUscita;

    /**
     * La <code>durata</code> del film.
     */
    private int durata;

    /**
     * Indica l'<code>età sotto il quale non si può entrare</code>.
     */
    private int vmeta; // Vietato ai minori di _ anni.

    /**
     * Questo metodo costruisce un oggetto <strong>Film</strong>
     * @param titolo titolo del film.
     * @param genere genere del film.
     * @param regista regista del film.
     * @param annoUscita anno di uscita del film.
     * @param durata durata in minuti del film.
     * @param vmeta indica l'età sotto il quale non si può entrare.
     */
    public Film(String titolo, String genere, String regista, int annoUscita, int durata, int vmeta){
        this.titolo = titolo;
        this.genere = genere;
        this.regista = regista;
        this.annoUscita = annoUscita;
        this.durata = durata;
        this.vmeta = vmeta;
    }

    /**
     * Questo metodo crea la stringa associata all'istanza.
     * @return stringa associata all'istanza.
     */
    @Override
    public String toString(){
        return String.format("%s %s %s %d %d %d", titolo, genere, regista, annoUscita, durata, vmeta);
    }

    /**
     * Questo metodo compara due oggetti di tipo <strong>Film</strong> tramite ordine alfabetico del <code>titolo</code>.
     * @param other the object to be compared.
     * @return 1 se <em>this Film</em> è maggiore della <em>other Film</em>, 0 se sono uguali, altrimenti -1.
     */
    @Override
    public int compareTo(Film other) {
        return titolo.compareTo(other.titolo);
    }

    /**
     * Metodo <em>getter</em> del <code>titolo</code>.
     * @return <code>titolo</code>
     */
    public String getTitolo(){
        return titolo;
    }

    /**
     * Metodo <em>getter</em> del <code>genere</code>.
     * @return <code>genere</code>
     */
    public String getGenere() {
        return genere;
    }

    /**
     * Metodo <em>getter</em> del <code>regista</code>.
     * @return <code>regista</code>
     */
    public String getRegista() {
        return regista;
    }

    /**
     * Metodo <em>getter</em> dell'<code>anno di uscita</code>.
     * @return <code>anno di uscita</code>
     */
    public int annoUscita() {
        return annoUscita;
    }

    /**
     * Metodo <em>getter</em> della <code>durata</code>.
     * @return <code>durata</code> (int minuti)
     */
    public int getDurata() {
        return durata;
    }

    /**
     * Metodo <em>getter</em> dell'<code>età minima consentita</code>.
     * @return <code>età minima consentita</code>
     */
    public int getVmeta(){
        return vmeta;
    }
}
