import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Bigliettaio extends Utente {
    private List<Prenotazione> prenotazioni;

    public Bigliettaio(String nome, String cognome, String username, String password, Data dataNascita, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, dataNascita, domicilio, Ruolo.BIGLIETTAIO);
        //prenotazioni = CineMaxManager.getListaPrenotazioni();
    }

    public Bigliettaio(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.BIGLIETTAIO);
        //prenotazioni = CineMaxManager.getListaPrenotazioni();
    }

    public Bigliettaio(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.BIGLIETTAIO);
       // prenotazioni = CineMaxManager.getListaPrenotazioni();
    }
}
