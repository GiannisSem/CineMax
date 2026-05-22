import java.io.File;
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

    /* INSERIMENTO */
    public static boolean inserisciProiezione(DataOra dataOra, Film film, Double costoBiglietto, Sala sala){
        // in ordine descrescente
        int inizio = 0;
        int fine = listaProiezioni.size() -1;

        while (inizio <= fine){
            int centro = inizio + (fine - inizio) / 2;
            DataOra dataOra_centro = listaProiezioni.get(centro).getDataOra();

            if (dataOra_centro.compareTo(dataOra) == 0)
                return false; // esiste già

            if (dataOra_centro.compareTo(dataOra) > 0)
                inizio = centro+1;
            else
                fine = centro-1;
        }

        int index = inizio; // Punto dove si sono incrociati
        Proiezione prec = (index < listaProiezioni.size()) ? listaProiezioni.get(index) : null;
        Proiezione succ = (index > 0) ? listaProiezioni.get(index-1) : null;

        DataOra limiteMin = null;
        if (prec != null)
            limiteMin = prec.getDataOra().aggiungi(prec.getFilm().getDurata() + 5);

        boolean okMin = (limiteMin == null) || (dataOra.compareTo(limiteMin) >= 0);
        boolean okMax = true;

        if (succ != null){
            DataOra limiteMax = dataOra.aggiungi(film.getDurata() + 5);
            okMax = succ.getDataOra().compareTo(limiteMax) >= 0;
        }

        if (okMin && okMax){
            Proiezione p = new Proiezione(dataOra, film, costoBiglietto, sala);
            listaProiezioni.add(index, p);
            //FileManager.serializza_lista(listaProiezioni, FileManager.path_proiezioni);
            return true;
        }
        return false;
    }

    public static boolean inserisciProiezione(String dataOra, Film film, Double costoBiglietto, Sala sala){
        return inserisciProiezione(new DataOra(dataOra), film, costoBiglietto, sala);
    }

    public static boolean inserisciProiezione(DataOra dataOra, Film film, Double costoBiglietto){
        return inserisciProiezione(dataOra, film, costoBiglietto, new Sala());
    }

    public static boolean inserisciProiezione(String dataOra, Film film, Double costoBiglietto){
        return inserisciProiezione(new DataOra(dataOra), film, costoBiglietto, new Sala());
    }

    /* CERCA PROIEZIONE */

    public static Proiezione cercaProiezione(DataOra dataOra) {
        // in ordine descrescente
        int inizio = 0;
        int fine = listaProiezioni.size() -1;

        while (inizio <= fine){
            int centro = inizio + (fine - inizio) / 2;
            DataOra dataOra_centro = listaProiezioni.get(centro).getDataOra();

            if (dataOra_centro.compareTo(dataOra) == 0)
                return listaProiezioni.get(centro);

            if (dataOra_centro.compareTo(dataOra) > 0)
                inizio = centro+1;
            else
                fine = centro-1;
        }

        return null;
    }

    public static Proiezione cercaProiezione(String dataOra){
        return cercaProiezione(new DataOra(dataOra));
    }

    public static List<Proiezione> cercaProiezioni_Titolo(List<Proiezione> lista, String titolo) {
        List<Proiezione> filtrata = new ArrayList<>();
        for (Proiezione proiezione : lista){
            if (proiezione.getFilm().getTitolo().contains(titolo))
                filtrata.add(proiezione);
        }
        return filtrata;
    }

    public static List<Proiezione> cercaProiezioni_Genere(List<Proiezione> lista, String genere) {
        List<Proiezione> filtrata = new ArrayList<>();
        for (Proiezione proiezione : lista){
            if (proiezione.getFilm().getGenere().equals(genere))
                filtrata.add(proiezione);
        }
        return filtrata;
    }

    public static List<Proiezione> cercaProiezioni_Date(List<Proiezione> lista, Data dataMin, Data dataMax) {
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

    public static List<Proiezione> cercaProiezioni_Date(List<Proiezione> lista, String dataMin, String dataMax) {
        return cercaProiezioni_Date(lista, new Data(dataMin), new Data(dataMax));
    }

    public static List<Proiezione> cercaProiezioni_CostoBiglietto(List<Proiezione> lista, Double costoMin, Double costoMax) {
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

    /* ELIMINA PROIEZIONE */

    /**
     *
     * @param dataOra Data e ora della Proiezione da cancellare.
     * @return True se l'ha cancellato, altrimenti false. Restituisce false anche se la Proiezione non è presente.
     */
    public static boolean eliminaProiezione(DataOra dataOra){
        boolean risultato = listaProiezioni.remove(cercaProiezione(dataOra));
        // FileManager.serializza_lista(listaProiezioni, FileManager.path_proiezioni);
        return risultato;
    }

    public static boolean eliminaProiezione(String dataOra){
        return eliminaProiezione(new DataOra(dataOra));
    }

    /* VISUALIZZA PROIEZIONE */
    public static String visualizzaProiezioni(List<Proiezione> lista){
        if (lista == null)
            return "";

        StringBuilder sb = new StringBuilder(lista.get(0).toInfo());
        for (int i = 1; i < lista.size(); i++) {
            sb.append("\n").append(lista.get(i).toInfo());
        }
        return sb.toString();
    }

    /* MODIFICA DATA PRENOTAZIONE */
    public static boolean modificaDataProiezione(DataOra old, DataOra nuova){
        Proiezione p = cercaProiezione(old);
        if (p == null)
            return false;

        eliminaProiezione(old);
        if (inserisciProiezione(nuova, p.getFilm(), p.getCostoBiglietto(), p.getSala()))
            return true;

        // Errore allora reinserisci
        inserisciProiezione(p.getDataOra(), p.getFilm(), p.getCostoBiglietto(), p.getSala());
        return false;
    }

    public static boolean modificaDataProiezione(String old, String nuova){
        return modificaDataProiezione(new DataOra(old), new DataOra(nuova));
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
