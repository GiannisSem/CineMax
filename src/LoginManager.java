import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class LoginManager {
    private static List<Utente> listaUtenti = FileManager.leggiUtenti_csv();
    private static Utente utenteLoggato;

    static {
        try {
            utenteLoggato = new UtenteNR();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Utente> getListaUtenti() { return listaUtenti; }
    public static Utente getutenteLoggato(){
        return utenteLoggato;
    }

    // todo: creare un passcode per utenti con permessi (Bigliettaio e Proiezionista)
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

    public static void reinserisci(Utente utente) throws NoSuchAlgorithmException {
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

    public static Utente login(String username, String password) throws NoSuchAlgorithmException {
        Utente u = cercaUtente(username);
        if (u != null && u.checkPassword(password)) {
            utenteLoggato = u;
            return u;
        }
        return null;
    }

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

    public static List<Utente> cercaUtente(String nome, String cognome){
        // non si può usare ricerca binaria perché non conosciamo lo username e possono esserci omonimi.
        List<Utente> omonimi = new ArrayList<>();

        for (Utente u : listaUtenti)
            if (u.getNome().equals(nome) && u.getCognome().equals(cognome))
                omonimi.add(u);

        return omonimi;
    }

    public boolean setUsername(Utente u, String nuovoUsername) throws NoSuchAlgorithmException {
        // todo: il nome utente nuovo DEVE essere validato e corretto.
        if (cercaUtente(nuovoUsername) != null)
            return false; // esiste già qualcuno con lo stesso username.

        listaUtenti.remove(u);
        u.setUsername(nuovoUsername);
        reinserisci(u);
        return true;
    }

    public static void logout() throws NoSuchAlgorithmException {
        utenteLoggato = new UtenteNR();
    }

}
