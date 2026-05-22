import java.io.Serial;
import java.io.Serializable;

public class Proiezione implements Serializable {
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
    public String toString(){
        return dataOra + " " + film + " " + costoBiglietto + " " + sala;
    }

    public String toInfo(){
        // todo: format info proiezione
        return "";
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

    public boolean isPostoDisponibile(char lettera, int numero){
        return sala.isPostoDisponibile(lettera, numero);
    }

    public boolean prenotaPosto(char lettera, int numero){
        return sala.setPosto(lettera, numero, true);
    }
}
