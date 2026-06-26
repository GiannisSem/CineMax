import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe gestisce tutte le funzionalità quali:
 * <ul>
 * <li>Gestione delle <strong>Proiezioni</strong>.</li>
 * <li>Gestione dei <strong>Film</strong>.</li>
 * <li>Gestione delle <strong>Prenotazioni</strong>.</li>
 * </ul>
 * I dati iniziali vengono letti dai file e passati tramite la classe <strong>FileManager</strong>.<br>
 * E' interfaccia tra le classi e il <strong>FileManager</strong>.
 *
 * @see Proiezione
 * @see Film
 * @see Prenotazione
 * @see FileManager
 *
 * @author Samuele Baragiola
 */
public class CineMaxManager {

    /**
     * La <code>listaProiezioni</code> contiene tutte le proiezioni presenti nel file <em>proiezioni.dat</em>.
     */
    private static List<Proiezione> listaProiezioni = FileManager.deserializza_proiezioni();

    /**
     * La <code>listaFilm</code> contiene tutti i film presenti nel file <em>film.dat</em>.
     */
    private static List<Film> listaFilm = FileManager.deserializza_film();

    /**
     * La <code>listaPrenotazioni</code> contiene tutte le prenotazioni presenti nel file <em>prenotazioni.csv</em>.
     */
    private static List<Prenotazione> listaPrenotazioni = FileManager.leggiPrenotazioni_csv();

    /**
     * Restituisce l'intera lista di proiezioni.
     * @return tutta la lista di proiezioni.
     */
    public static List<Proiezione> getListaProiezioni(){
        return listaProiezioni;
    }

    /**
     * Restituisce l'intera lista di film.
     * @return tutta la lista di film.
     */
    public static List<Film> getListaFilm(){
        return listaFilm;
    }

    /**
     * Restituisce l'intera lista di prenotazioni.
     * @return tutta la lista di prenotazioni.
     */
    public static List<Prenotazione> getListaPrenotazioni() {
        return listaPrenotazioni;
    }

    /**
     * Serve per tenere traccia del codice dell'ultima prenotazione tra esecuzioni del programma diverse.
     * Il codice di ogni prenotazione è univoca.
     * @return l'ultimo codice prenotazione usato.
     * @see Prenotazione#ultimoCodicePrenotazione
     */
    public static int getUltimoCodicePrenotazione(){
        return listaPrenotazioni.get(listaPrenotazioni.size()-1).getCodicePrenotazione();
    }

    /* INSERIMENTO */

    /**
     * Inserisce la <strong>Proiezione</strong> alla lista di proiezioni se lo slot orario scelto è libero.
     * @param dataOra data e ora della proiezione.
     * @param film film che verrà proiettato in sala.
     * @param costoBiglietto costo del biglietto associato alla proiezione.
     * @param sala tiene traccia dei posti occupati durante la proiezione.
     * @return true se l'operazione è andata a buon fine, altrimenti false.
     * Questo metodo funge da base ad altri metodi in overload.
     */
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
            FileManager.serializza_lista(listaProiezioni, FileManager.path_proiezioni);
            return true;
        }
        return false;
    }

    /**
     * Inserisce la <strong>Proiezione</strong> alla lista di proiezioni se lo slot orario scelto è libero.
     * @param dataOra data e ora (espressi come <strong>String</strong> unica) della proiezione.
     * @param film film che verrà proiettato in sala.
     * @param costoBiglietto costo del biglietto associato alla proiezione.
     * @param sala tiene traccia dei posti occupati durante la proiezione.
     * @return true se l'operazione è andata a buon fine, altrimenti false.
     */
    public static boolean inserisciProiezione(String dataOra, Film film, Double costoBiglietto, Sala sala){
        return inserisciProiezione(new DataOra(dataOra), film, costoBiglietto, sala);
    }

    /**
     * Inserisce una nuova <strong>Proiezione</strong> alla lista di proiezioni se lo slot orario scelto è libero.
     * @param dataOra data e ora della proiezione.
     * @param film film che verrà proiettato in sala.
     * @param costoBiglietto costo del biglietto associato alla proiezione.
     * @return true se l'operazione è andata a buon fine, altrimenti false.
     */
    public static boolean inserisciProiezione(DataOra dataOra, Film film, Double costoBiglietto){
        return inserisciProiezione(dataOra, film, costoBiglietto, new Sala());
    }

    /**
     * Inserisce una nuova <strong>Proiezione</strong> alla lista di proiezioni se lo slot orario scelto è libero.
     * @param dataOra data e ora (espressi come <strong>String</strong> unica) della proiezione.
     * @param film film che verrà proiettato in sala.
     * @param costoBiglietto costo del biglietto associato alla proiezione.
     * @return true se l'operazione è andata a buon fine, altrimenti false.
     */
    public static boolean inserisciProiezione(String dataOra, Film film, Double costoBiglietto){
        return inserisciProiezione(new DataOra(dataOra), film, costoBiglietto, new Sala());
    }

    /* CERCA PROIEZIONE */

    /**
     * Ricerca dicotomica sulla lista di proiezioni.
     * @param dataOra data e ora della proiezione.
     * @return la proiezione con l'orario indicato dal parametro, null se non è presente. 
     */
    public static Proiezione cercaProiezione(DataOra dataOra) {
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
    
    /**
     * Ricerca dicotomica sulla lista di proiezioni.
     * @param dataOra data e ora (espressi come <strong>String</strong> unica) della proiezione.
     * @return la proiezione con l'orario indicato dal parametro, null se non è presente.
     */
    public static Proiezione cercaProiezione(String dataOra){
        return cercaProiezione(new DataOra(dataOra));
    }

    /**
     * Questo metodo serve a selezionare in una lista solo le <strong>Proiezioni</strong> di un certo <strong>Film</strong>.
     * @param lista lista di proiezioni iniziale.
     * @param titolo titolo del film da filtrare.
     * @return una lista di proiezioni che contiene solo quelle del film specificato dal titolo.
     */
    public static List<Proiezione> cercaProiezioni_Titolo(List<Proiezione> lista, String titolo) {
        List<Proiezione> filtrata = new ArrayList<>();
        for (Proiezione proiezione : lista){
            if (proiezione.getFilm().getTitolo().contains(titolo))
                filtrata.add(proiezione);
        }
        return filtrata;
    }

    /**
     * Questo metodo serve a selezionare in una lista solo le <strong>Proiezioni</strong> di un certo <strong>Genere</strong>.
     * @param lista lista di proiezioni iniziale.
     * @param genere genere dei film da filtrare.
     * @return una lista di proiezioni che contiene solo quelle dei film di un certo genere.
     */
    public static List<Proiezione> cercaProiezioni_Genere(List<Proiezione> lista, String genere) {
        List<Proiezione> filtrata = new ArrayList<>();
        for (Proiezione proiezione : lista){
            if (proiezione.getFilm().getGenere().equals(genere))
                filtrata.add(proiezione);
        }
        return filtrata;
    }

    /**
     * Questo metodo serve a selezionare in una lista solo le <strong>Proiezioni</strong> comprese tra due date.
     * @param lista lista di proiezioni iniziale.
     * @param dataMin data più piccola del range.
     * @param dataMax data più grande del range.
     * @return la lista delle Proiezioni comprese tra due date.
     */
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

    /**
     * Questo metodo serve a selezionare in una lista solo le <strong>Proiezioni</strong> comprese tra due date.
     * @param lista lista di proiezioni iniziale.
     * @param dataMin data (espressa come <strong>String</strong>) più piccola del range.
     * @param dataMax data (espressa come <strong>String</strong>) più grande del range.
     * @return la lista delle proiezioni comprese tra due date.
     */
    public static List<Proiezione> cercaProiezioni_Date(List<Proiezione> lista, String dataMin, String dataMax) {
        return cercaProiezioni_Date(lista, new Data(dataMin), new Data(dataMax));
    }

    /**
     * Questo metodo serve a selezionare in una lista solo le <strong>Proiezioni</strong> comprese tra due costi di biglietto.
     * @param lista lista di proiezioni iniziale.
     * @param costoMin costo minore del range.
     * @param costoMax costo maggiore del range.
     * @return la lista delle proiezioni comprese tra due costi di biglietto.
     */
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

    /**
     * Resetta la lista filtrata.
     * @return lista di proiezioni di partenza.
     * @see #getListaPrenotazioni()
     */
    public static List<Proiezione> resetFiltri(){
        return getListaProiezioni();
    }

    /* ELIMINA PROIEZIONE */

    /**
     * Elimina una proiezione dato il suo orario.
     * @param dataOra data e ora della proiezione da cancellare.
     * @return true se l'ha cancellato, altrimenti false. Restituisce false anche se la Proiezione non è presente.
     */
    public static boolean eliminaProiezione(DataOra dataOra){
        boolean cancellato = listaProiezioni.remove(cercaProiezione(dataOra));

        if (cancellato)
            FileManager.serializza_lista(listaProiezioni, FileManager.path_proiezioni);

        return cancellato;
    }

    /**
     * Elimina una proiezione dato il suo orario.
     * @param dataOra data e ora (espressi come <strong>String</strong> unica) della proiezione da cancellare.
     * @return true se l'ha cancellato, altrimenti false. Restituisce false anche se la Proiezione non è presente.
     */
    public static boolean eliminaProiezione(String dataOra){
        return eliminaProiezione(new DataOra(dataOra));
    }

    /* VISUALIZZA PROIEZIONE */

    /**
     * Prepara una stringa da stampare.
     * @param lista lista di proiezioni da stampare.
     * @return una stringa che contiene le informazioni delle proiezioni nella lista.
     * @see Proiezione#toInfo() 
     */
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

    /**
     * Cambia la data e l'ora di una proiezione. In caso di errore il vecchio orario rimane tale.
     * @param old vecchio orario, serve per identificare la proiezione da modificare.
     * @param nuova nuovo orario.
     * @return false se l'orario <code>old</code> non esiste oppure lo slot è troppo piccolo per la proiezione. Altrimenti true.
     * @see #inserisciProiezione(DataOra, Film, Double, Sala) 
     */
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

    /**
     Cambia la data e l'ora di una proiezione. In caso di errore il vecchio orario rimane tale.
     * @param old vecchio orario (espresso come <strong>String</strong> unica), serve per identificare la proiezione da modificare.
     * @param nuova nuovo orario (espresso come <strong>String</strong> unica).
     * @return false se l'orario <code>old</code> non esiste oppure lo slot è troppo piccolo per la proiezione. Altrimenti true.
     * @see #inserisciProiezione(DataOra, Film, Double, Sala)
     */
    public static boolean modificaDataProiezione(String old, String nuova){
        return modificaDataProiezione(new DataOra(old), new DataOra(nuova));
    }

    /**
     * Aggiunge un <strong>Film</strong> alla lista dei Film
     * @param titolo titolo del film.
     * @param genere genere del film.
     * @param regista regista del film.
     * @param annoUscita anno di uscita del film.
     * @param durata durata in minuti del film.
     * @param vmeta indica l'età sotto il quale non si può entrare.
     * @return false se il film è già presente, altrimenti true.
     */
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
        FileManager.serializza_lista(listaFilm, FileManager.path_film);
        return true;
    }

    /**
     * Ricerca dicotomica sulla lista dei film in base a un titolo dato.
     * @param titolo titolo del film da cercare.
     * @return il film con quel titolo, altrimenti null.
     */
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

    /**
     * Inserisce alla lista una <strong>Prenotazione</strong> associata ad un <strong>Cliente</strong>.
     * @param cliente cliente associato alla prenotazione.
     * @param proiezione proiezione selezionata dal cliente.
     * @param posti posti in sala scelti.
     * @see Sala
     */
    public static void inserisciPrenotazione(Cliente cliente, Proiezione proiezione, String[] posti){
        listaPrenotazioni.add(new Prenotazione(cliente, proiezione, posti));
    }

    /**
     * Cerca tutte <strong>Prenotazioni</strong> associate al <strong>Cliente</strong>.
     * @param cliente cliente selezionato per la ricerca.
     * @return lista di prenotazioni effettuate dal cliente.
     */
    public static List<Prenotazione> getPrenotazioniCliente(Cliente cliente){
        List<Prenotazione> prenotazioni = new ArrayList<>();
        for (Prenotazione p : listaPrenotazioni) {
            if (p.getCliente().compareTo(cliente) == 0)
                prenotazioni.add(p);
        }
        return prenotazioni;
    }

    /**
     * Cerca tutte le <strong>Prenotazioni</strong> attive associate ad un <strong>Cliente</strong>.<br>
     * Per attive si intende che il film della proiezione non è ancora terminato.
     * @param cliente cliente selezionato per la ricerca.
     * @return lista di prenotazioni attive effettuate dal cliente.
     */
    public static List<Prenotazione> getPrenotazioniClienteAttive(Cliente cliente){
        // attiva se now() < DataOra fine della proiezione.
        List<Prenotazione> prenotazioni = new ArrayList<>();
        for (Prenotazione p : listaPrenotazioni) {
            if (p.getCliente().compareTo(cliente) == 0 && DataOra.oggi.compareTo(p.getDataOra().aggiungi(p.getFilm().getDurata())) < 0)
                prenotazioni.add(p);
        }
        return prenotazioni;
    }

    /**
     * Prende le prenotazioni del cliente attuale nel menù.
     * @return lista delle prenotazioni di un cliente.
     */
    public static List<Prenotazione> getPrenotazioniClienteAttuale(){
        return getPrenotazioniCliente((Cliente) LoginManager.getutenteLoggato());
    }

    /**
     * Ricerca dicotomica sulla lista di prenotazioni in base al codice di prenotazione.
     * @param codicePrenotazione codice univoco della prenotazione.
     * @return la prenotazione con il codice dato, altrimenti null se non esiste.
     */
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

    /**
     * Ricerca delle prenotazioni che proiettano un certo <strong>Film</strong>.
     * @param titolo titolo del film da cercare.
     * @return lista delle prenotazioni che proiettano un certo <strong>Film</strong>
     */
    public static List<Prenotazione> cercaPrenotazioni(String titolo){
        List<Prenotazione> lista = new ArrayList<>();

        for (Prenotazione p : listaPrenotazioni){
            if (p.getFilm().getTitolo().equals(titolo))
                lista.add(p);
        }

        return lista;
    }

    /**
     * Ricerca delle prenotazioni comprese tra due date.
     * @param min data minore del range.
     * @param max data maggiore del range.
     * @return lista che contiene le prenotazioni comprese tra due date.
     */
    public static List<Prenotazione> cercaPrenotazioni(Data min, Data max){
        List<Prenotazione> lista = new ArrayList<>();

        for (Prenotazione p : listaPrenotazioni){
            if (p.getDataOra().getData().compareTo(min) >= 0 && p.getDataOra().getData().compareTo(max) <= 0)
                lista.add(p);
        }

        return lista;
    }

    /**
     * Elimina una <strong>Prenotazione</strong> dato il relativo codice.<br>
     * I posti associati vengono liberati.
     * @param codicePrenotazione codice della prenotazione.
     * @return false se il <code>codicePrenotazione</code> non è associato ad alcuna prenotazione, altrimenti true.
     */
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
     * @return lista di prenotazioni con la proiezione in data odierna.
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
