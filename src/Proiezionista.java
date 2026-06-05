import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Proiezionista extends Utente {

    public Proiezionista(String nome, String cognome, String username, String password, Data dataNascita, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, dataNascita, domicilio, Ruolo.PROIEZIONISTA);
    }

    public Proiezionista(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.PROIEZIONISTA);
    }

    public Proiezionista(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.PROIEZIONISTA);
    }
}
