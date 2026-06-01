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

    public static Utente login(String username, String password) throws NoSuchAlgorithmException {
        Utente u = cercaUtente(username);
        if (u != null && u.checkPassword(password))
            utenteLoggato = u;

        return utenteLoggato;
    }

    public static Utente cercaUtente(String username){
        int inizio = 0;
        int fine = listaUtenti.size() -1;

        while (inizio <= fine){
            int centro = inizio + (fine - inizio) / 2;
            String centrale = listaUtenti.get(centro).getUsername();
            if (username.compareTo(centrale) == 0)
                return listaUtenti.get(centro); // esiste già, lo username è già presente.

            if (username.compareTo(centrale) < 0)
                fine = centro-1;
            else
                inizio = centro+1;
        }

        return null;
    }

    public static void logout() throws NoSuchAlgorithmException {
        utenteLoggato = new UtenteNR();
    }

}
