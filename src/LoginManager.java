import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe gestisce funzionalità quali:
 * <ul>
 *     <li>Login di utenti: Clienti, Bigliettai, Proiezionisti.</li>
 *     <li>Tiene a memoria l'ultimo utente loggato e i suoi dati per operazioni su di lui.</li>
 *     <li>Signin di utenti: tramite <code>username</code> e <code>password</code>.</li>
 *     <li>Funzionalità varie: ricerca di utenti e modifica dei loro dati.</li>
 *     <li>Logout.</li>
 * </ul>
 *
 * @author Samuele Baragiola
 * @author Matteo Germani
 */
public class LoginManager {
    /**
     * La <code>lista degli utenti</code>, i dati sono presi dal relativo file csv.
     * @see FileManager#leggiUtenti_csv()
     */
    private static List<Utente> listaUtenti = FileManager.leggiUtenti_csv();

    /**
     * Tiene traccia dell'<code>ultimo Utente</code> che ha loggato.
     */
    private static Utente utenteLoggato;

    static {
        try {
            utenteLoggato = new UtenteNR();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo <em>getter</em> della <code>lista degli utenti</code>.
     * @return <code>lista degli utenti</code>
     */
    public static List<Utente> getListaUtenti() { return listaUtenti; }
    public static Utente getutenteLoggato(){
        return utenteLoggato;
    }

    /**
     * Registra un nuovo <strong>Utente</strong> nella <code>lista degli utenti</code>.
     * @param utente utente da aggiungere alla lista.
     * @return false se esiste un utente con lo stesso username, altrimenti true.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public static boolean signin(Utente utente) throws NoSuchAlgorithmException {
        int inizio = 0;
        int fine = listaUtenti.size() -1;

        while (inizio <= fine){
            int centro = inizio + (fine - inizio) / 2;
            Utente centrale = listaUtenti.get(centro);
            if (utente.compareTo(centrale) == 0)
                return false; // esiste già, lo username è già presente.

            if (utente.compareTo(centrale) < 0)
                fine = centro-1;
            else
                inizio = centro+1;
        }

        // Punto dove si sono incrociati
        utente.setHashPassword(utente.getPassword());
        listaUtenti.add(inizio, utente);
        FileManager.carica_csv(listaUtenti, FileManager.path_utenti);
        utenteLoggato = utente;
        return true;
    }

    /**
     * Questo metodo viene usato quando un <strong>Utente</strong> cambia lo <code>username</code>.<br>
     * A differenza del signin, qui non viene effettuato l'hash sulla password.
     * @param utente utente a cui è stato modificato lo <code>username</code>.
     */
    public static void reinserisci(Utente utente) {
        // funziona come signin ma è certo che lo user è nuovo e non va eseguito hash di password
        // serve per cambiare il nome, tenendo la lista binaria.
        int inizio = 0;
        int fine = listaUtenti.size() -1;

        while (inizio <= fine){
            int centro = inizio + (fine - inizio) / 2;
            Utente centrale = listaUtenti.get(centro);

            if (utente.compareTo(centrale) < 0)
                fine = centro-1;
            else
                inizio = centro+1;
        }

        // Punto dove si sono incrociati
        listaUtenti.add(inizio, utente);
        FileManager.carica_csv(listaUtenti, FileManager.path_utenti);
        utenteLoggato = utente;
    }

    /**
     * Verifica le credenziali di un <strong>Utente</strong> che vuole loggare nel suo account.
     * @param username username dell'utente.
     * @param password password su cui effettuare l'hash per la verifica delle credenziali.
     * @return se le credenziali sono valide, restituisce l'istanza di <strong>Utente</strong> richiesta.<br>Altrimenti null se l'utente non esiste o le credenziali sono errate.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     *
     * @see Security
     */
    public static Utente login(String username, String password) throws NoSuchAlgorithmException {
        Utente u = cercaUtente(username);
        if (u != null && Security.checkPassword(password, u.getPassword())) {
            utenteLoggato = u;
            return u;
        }
        return null;
    }

    /**
     * Ricerca dicotomica dell'<strong>Utente</strong> tramite lo <code>username</code>.
     * @param username username dell'utente.
     * @return istanza di utente se viene trovato, altrimenti null.
     */
    public static Utente cercaUtente(String username){
        int inizio = 0;
        int fine = listaUtenti.size() -1;

        while (inizio <= fine){
            int centro = inizio + (fine - inizio) / 2;
            String centrale = listaUtenti.get(centro).getUsername();
            if (username.compareTo(centrale) == 0)
                return listaUtenti.get(centro); // esiste.

            if (username.compareTo(centrale) < 0)
                fine = centro-1;
            else
                inizio = centro+1;
        }

        return null;
    }

    /**
     * Ricerca dell'utente tramite il <code>nome</code> e il <code>cognome</code>.<br>
     * <em>Questa ricerca può trovare utenti omonimi.</em>
     * @param nome nome dell'utente.
     * @param cognome cognome dell'utente.
     * @return lista di utenti omonimi, con il nome e cognome specificati.
     */
    public static List<Utente> cercaUtente(String nome, String cognome){
        // non si può usare ricerca binaria perché non conosciamo lo username e possono esserci omonimi.
        List<Utente> omonimi = new ArrayList<>();

        for (Utente u : listaUtenti)
            if (u.getNome().equals(nome) && u.getCognome().equals(cognome))
                omonimi.add(u);

        return omonimi;
    }

    /**
     * Questo metodo cambia lo <code>username</code> ad un <strong>Utente</strong>.<br>
     * <em>L'utente viene rimosso e reinserito nella lista per mantenerli ordinati per username, quindi favorire la ricerca dicotomica.</em>
     * @param utente utente a cui va cambiato lo <code>username</code>.
     * @param nuovoUsername nuovo username.
     * @return false se esiste già qualcuno con quello <code>username</code>, altrimenti true se l'operazione va a buon fine.
     */
    public static boolean setUsername(Utente utente, String nuovoUsername) {
        if (cercaUtente(nuovoUsername) != null)
            return false; // esiste già qualcuno con lo stesso username.

        listaUtenti.remove(utente);
        utente.setUsername(nuovoUsername);
        reinserisci(utente);
        return true;
    }

    /**
     * Esegue il logout dell'utente loggato.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public static void logout() throws NoSuchAlgorithmException {
        utenteLoggato = new UtenteNR();
    }

}
