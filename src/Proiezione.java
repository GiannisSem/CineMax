import java.io.Serializable;

public class Proiezione implements Serializable {
    private static final long serialVersionUID = 1L;

    private DataOra dataOra;
    private Film film;
    private double costoBiglietto;

    public Proiezione(DataOra dataOra, Film film, double costoBiglietto){
        this.dataOra = dataOra;
        this.film = film;
        this.costoBiglietto = costoBiglietto;
    }

    public Proiezione(int anno, int mese, int giorno, int ore, int minuti, int secondi,
                      String titolo, String genere, String regista, int annoUscita, int durata, int vmeta, double costoBiglietto){
        this(new DataOra(anno, mese, giorno, ore, minuti, secondi), new Film(titolo, genere, regista, annoUscita, durata, vmeta), costoBiglietto);
    }

    public Proiezione(int anno, int mese, int giorno, int ore, int minuti, int secondi, Film film, double costoBiglietto){
        this(new DataOra(anno, mese, giorno, ore, minuti, secondi), film, costoBiglietto);
    }

    @Override
    public String toString(){
        return dataOra + " " + film + " " + costoBiglietto;
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
}
