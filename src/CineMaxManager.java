import java.util.ArrayList;
import java.util.List;

public class CineMaxManager {
    // tutta ricerca e funzionalità

    private static final List<Proiezione> listaProiezioni = FileManager.deserializza_proiezioni();
    private static final List<Film> listaFilm = FileManager.deserializza_film();
    private static final List<Utente> listaUtenti = FileManager.deserializza_utenti_csv();
    private static final List<Prenotazione> listaPrenotazioni = FileManager.deserializza_prenotazioni_csv();

    public static List<Proiezione> getListaProiezioni(){
        return listaProiezioni;
    }
    public static List<Film> getListaFilm(){
        return listaFilm;
    }
    public static List<Utente> getListaUtenti(){
        return listaUtenti;
    }

    /* CERCA PROIEZIONE */

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

    /* VISUALIZZA PROIEZIONE */
    public static String visualizzaProiezione(List<Proiezione> lista){
        if (lista == null)
            return "";

        StringBuilder sb = new StringBuilder(lista.get(0).toInfo());
        for (int i = 1; i < lista.size(); i++) {
            sb.append("\n").append(lista.get(i).toInfo());
        }
        return sb.toString();
    }

    /* REGISTRA CLIENTE */
    public static boolean registraCliente(String[] params){
        // todo: da fare per ogni tipo (più metodi)
        return false;
    }

    /* Prenotazioni */
    public static Prenotazione cercaPrenotazione(List<Prenotazione> lista, int id) {
        int inizio = 0;
        int fine = lista.size()-1;

        while (inizio <= fine){
            int centro = inizio + (fine - inizio) / 2;
            int idCentrale = lista.get(centro).getCodicePrenotazione();

            if (idCentrale == id)
                return lista.get(centro);

            if (idCentrale > id)
                fine = centro+1;

            if (idCentrale < id)
                inizio = centro+1;
        }

        return null;
    }

    // public static String visualizzaPrenotazione()

}
