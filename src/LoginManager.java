import java.util.ArrayList;
import java.util.List;

public class LoginManager {
    private List<Utente> utenti;
    LoginManager(){
        utenti = new ArrayList<>(CineMaxManager.getListaUtenti());
    }
    public boolean signin(String username, String password){
        //controlla univocità username
        //salvare username e password su file
        return true;
    }
    public boolean login(String username, String password){
        //leggere da file
        return true;
    }

}
