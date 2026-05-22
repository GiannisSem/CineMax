import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class LoginManager {
    private List<Utente> utenti;
    private Utente utenteLoggato;
    public LoginManager(){
        utenti = new ArrayList<>(CineMaxManager.getListaUtenti());
        utenteLoggato = null;
    }
    public Utente getutenteLoggato(){
        return utenteLoggato;
    }
    public boolean signin(Utente utente){
        for(Utente u : utenti)
            if(u.getUsername().equals(utente.getUsername()))
                throw new IllegalArgumentException("Nome utente già usato");
        utenti.add(utente);
        utenteLoggato = utente;
        //salvare su file
        return true;
    }
    public Utente login(String username, String password) throws NoSuchAlgorithmException {
        for(Utente u : utenti)
            if(u.getUsername().equals(username) && u.checkPassword(password)) {
                utenteLoggato = u;
                return u;
            }
        return null;
    }
    public void logout(){
        utenteLoggato = null;
    }

}
