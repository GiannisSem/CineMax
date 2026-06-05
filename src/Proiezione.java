import java.io.Serial;
import java.io.Serializable;

public class Proiezione implements Serializable, Comparable<Proiezione> {
    @Serial
    private static final long serialVersionUID = 1L;

    private DataOra dataOra;
    private Film film;
    private double costoBiglietto;
    private Sala sala;

    public Proiezione(DataOra dataOra, Film film, double costoBiglietto, Sala sala){
        this.dataOra = dataOra;
        this.film = film;
        this.costoBiglietto = costoBiglietto;
        this.sala = sala;
    }

    public Proiezione(DataOra dataOra, Film film, double costoBiglietto){
        this(dataOra, film, costoBiglietto, new Sala());
    }

    public Proiezione(int anno, int mese, int giorno, int ore, int minuti, int secondi,
                      String titolo, String genere, String regista, int annoUscita, int durata, int vmeta, double costoBiglietto, Sala sala){
        this(new DataOra(anno, mese, giorno, ore, minuti, secondi), new Film(titolo, genere, regista, annoUscita, durata, vmeta), costoBiglietto, sala);
    }

    public Proiezione(int anno, int mese, int giorno, int ore, int minuti, int secondi, Film film, double costoBiglietto, Sala sala){
        this(new DataOra(anno, mese, giorno, ore, minuti, secondi), film, costoBiglietto, sala);
    }

    public Proiezione(String dataOra, Film film, double costoBiglietto){
        this(new DataOra(dataOra), film, costoBiglietto, new Sala());
    }

    @Override
    public int compareTo(Proiezione other){
        return this.dataOra.compareTo(other.dataOra);
    }

    @Override
    public String toString(){
        return dataOra + " " + film + " " + costoBiglietto + " " + sala;
    }

    public String toInfo(){
        return String.format(dataOra + "   " + film.getTitolo() + "   " + film.getRegista() + "   " + film.annoUscita() + "   " + film.getDurata() + "   " + film.getVmeta() + "   " + costoBiglietto + "   " + sala.postiDisponibili());
    }

    public String toInfo1(){
        return String.format("Data e ora:  " + dataOra + "%n%nTitolo:  " + film.getTitolo() + "%n%nRegista:  " + film.getRegista() + "%n%nAnno di uscita:  " + film.annoUscita() + "%n%nDurata:  " + film.getDurata() + "%n%nEtà minima:  " + film.getVmeta() + "%n%nCosto biglietto:  " + costoBiglietto);
    }

    public DataOra getDataOra(){
        return dataOra;
    }
    public Film getFilm(){
        return film;
    }
    public double getCostoBiglietto(){
        return costoBiglietto;
    }
    public Sala getSala() { return sala; }
}
