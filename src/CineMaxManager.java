import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CineMaxManager {
    // tutta ricerca e funzionalità

    private static List<Proiezione> listaProiezioni = FileManager.deserializza_proiezioni();
    private static List<Film> listaFilm = FileManager.deserializza_film();
    private static List<Prenotazione> listaPrenotazioni = FileManager.leggiPrenotazioni_csv();

    public static List<Proiezione> getListaProiezioni(){
        return listaProiezioni;
    }
    public static List<Film> getListaFilm(){
        return listaFilm;
    }
    public static List<Prenotazione> getListaPrenotazioni() {
        return listaPrenotazioni;
    }
    public static int getUltimoCodicePrenotazione(){
        return listaPrenotazioni.get(listaPrenotazioni.size()-1).getCodicePrenotazione();
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
        boolean cancellato = listaProiezioni.remove(cercaProiezione(dataOra));
        // todo: Cancella SOLO SE non ci sono prenotazioni associate a questa proiezione. Oppure di deafault elimina tutte le prenotazioni associate.
        //if (cancellato)
            // FileManager.serializza_lista(listaProiezioni, FileManager.path_proiezioni);
        return cancellato;
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

    /* MODIFICA DATA PROIEZIONE */
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

    public static boolean aggiungiFilm(String titolo, String genere, String regista, int annoUscita, int durata, int vmeta){
        int inizio = 0;
        int fine = listaFilm.size() -1;

        while (inizio <= fine){
            int centro = inizio + (fine - inizio) / 2;
            String centrale = listaFilm.get(centro).getTitolo();
            if (titolo.compareTo(centrale) == 0)
                return false;

            if (titolo.compareTo(centrale) < 0)
                fine = centro-1;
            else
                inizio = centro+1;
        }

        // Punto dove si sono incrociati
        listaFilm.add(inizio, new Film(titolo, genere, regista, annoUscita, durata, vmeta));
        //FileManager.serializza_lista(listaFilm, FileManager.path_film);
        return true;
    }

    public static Film cercaFilm(String titolo){
        int inizio = 0;
        int fine = listaFilm.size() -1;

        while (inizio <= fine){
            int centro = inizio + (fine - inizio) / 2;
            String centrale = listaFilm.get(centro).getTitolo();
            if (titolo.compareTo(centrale) == 0)
                return listaFilm.get(centro);

            if (titolo.compareTo(centrale) < 0)
                fine = centro-1;
            else
                inizio = centro+1;
        }
        return null;
    }

    /* PRENOTAZIONI */
    public static void inserisciPrenotazione(Cliente cliente, Proiezione proiezione, String[] posti){
        listaPrenotazioni.add(new Prenotazione(cliente, proiezione, posti));
    }

    public static List<Prenotazione> getPrenotazioniCliente(Cliente cliente){
        List<Prenotazione> prenotazioni = new ArrayList<>();
        for (Prenotazione p : listaPrenotazioni) {
            if (p.getCliente().compareTo(cliente) == 0)
                prenotazioni.add(p);
        }
        return prenotazioni;
    }

    public static List<Prenotazione> getPrenotazioniClienteAttive(Cliente cliente){
        // attiva se now() < DataOra fine della proiezione.
        List<Prenotazione> prenotazioni = new ArrayList<>();
        for (Prenotazione p : listaPrenotazioni) {
            if (p.getCliente().compareTo(cliente) == 0 && DataOra.oggi.compareTo(p.getDataOra().aggiungi(p.getFilm().getDurata())) < 0)
                prenotazioni.add(p);
        }
        return prenotazioni;
    }

    public static List<Prenotazione> getPrenotazioniClienteAttuale(){
        return getPrenotazioniCliente((Cliente) LoginManager.getutenteLoggato());
    }

    public static Prenotazione cercaPrenotazione(int codicePrenotazione){
        int inizio = 0;
        int fine = listaPrenotazioni.size() -1;

        while (inizio <= fine){
            int centro = inizio + (fine - inizio) / 2;
            int centrale = listaPrenotazioni.get(centro).getCodicePrenotazione();
            if (codicePrenotazione == centrale)
                return listaPrenotazioni.get(centro);

            if (codicePrenotazione < centrale)
                fine = centro-1;
            else
                inizio = centro+1;
        }
        return null;
    }

    public static List<Prenotazione> cercaPrenotazioni(String titolo){
        List<Prenotazione> lista = new ArrayList<>();

        for (Prenotazione p : listaPrenotazioni){
            if (p.getFilm().getTitolo().equals(titolo))
                lista.add(p);
        }

        return lista;
    }

    public static List<Prenotazione> cercaPrenotazioni(Data min, Data max){
        List<Prenotazione> lista = new ArrayList<>();

        for (Prenotazione p : listaPrenotazioni){
            if (p.getDataOra().getData().compareTo(min) >= 0 && p.getDataOra().getData().compareTo(max) <= 0)
                lista.add(p);
        }

        return lista;
    }

    public static boolean eliminaPrenotazione(int codicePrenotazione){
        Prenotazione p = cercaPrenotazione(codicePrenotazione);
        boolean cancellato = listaPrenotazioni.remove(p);
        if (cancellato){
            for (String posto : p.getPosti()){
                p.getSala().liberaPosto(posto);
            }
            FileManager.carica_csv(listaPrenotazioni, FileManager.path_prenotazioni);
        }

        return cancellato;
    }

    /**
     * Cerca tutte le prenotazioni di proiezioni di oggi.
     * @return
     */
    public static List<Prenotazione> cercaPrenotazioniOggi(){
        List<Prenotazione> prenotazioni = new ArrayList<>();

        for (Prenotazione p : listaPrenotazioni){
            if (p.getDataOra().getData().compareTo(Data.oggi) == 0)
                prenotazioni.add(p);
        }

        return prenotazioni;
    }

}
