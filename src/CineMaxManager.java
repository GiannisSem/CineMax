import java.util.ArrayList;
import java.util.List;

public class CineMaxManager {
    // tutta ricerca e funzionalità

    private static List<Proiezione> listaProiezioni = FileManager.deserializza_proiezioni();
    private static List<Film> listaFilm = FileManager.deserializza_film();
    private static List<Utente> listaUtenti = FileManager.deserializza_utenti();

    public static List<Proiezione> getListaProiezioni(){
        return listaProiezioni;
    }
    public static List<Film> getListaFilm(){
        return listaFilm;
    }
    public static List<Utente> getListaUtenti(){
        return listaUtenti;
    }

    public static List<Proiezione> cercaProiezione_Titolo(List<Proiezione> lista, String titolo) {
        List<Proiezione> filtrata = new ArrayList<>();
        for (Proiezione proiezione : lista){
            if (proiezione.getFilm().getTitolo().contains(titolo))
                filtrata.add(proiezione);
        }
        return filtrata;
    }

    public static List<Proiezione> cercaProiezione_Genere(List<Proiezione> lista, String genere) {
        List<Proiezione> filtrata = new ArrayList<>();
        for (Proiezione proiezione : lista){
            if (proiezione.getFilm().getGenere().equals(genere))
                filtrata.add(proiezione);
        }
        return filtrata;
    }

    public static List<Proiezione> cercaProiezione_Date(List<Proiezione> lista, Data dataMin, Data dataMax) {
        if (dataMin.compareTo(dataMax) > 0)
            return null;

        List<Proiezione> filtrata = new ArrayList<>();
        for (Proiezione proiezione : lista){
            Data data = proiezione.getDataOra().getData();
            // dataMin <= data <= dataMax
            if (dataMin.compareTo(data) <= 0 && data.compareTo(dataMax) <= 0)
                filtrata.add(proiezione);
        }
        return filtrata;
    }

    public static List<Proiezione> cercaProiezione_CostoBiglietto(List<Proiezione> lista, Double costoMin, Double costoMax) {
        if (costoMin > costoMax || costoMin < 0)
            return null;

        List<Proiezione> filtrata = new ArrayList<>();
        for (Proiezione proiezione : lista){
            if (costoMin <= proiezione.getCostoBiglietto() && proiezione.getCostoBiglietto() <= costoMax)
                filtrata.add(proiezione);
        }
        return filtrata;
    }



    public static List<Proiezione> resetFiltri(){
        return getListaProiezioni();
    }
}
