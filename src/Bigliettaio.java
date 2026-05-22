import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Bigliettaio extends Utente {
    private List<Prenotazione> prenotazioni;

    public Bigliettaio(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.BIGLIETTAIO);
    }

    public Bigliettaio(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.BIGLIETTAIO);
    }

    public String visuaPrenotazioniOggi(){
        return null;
    }
    public Prenotazione cercaPrenotazione(int codPrenotazione){
        return null;
    }
    public boolean cercaPrenotazione(String nome, String cognome){
        return true;
    }
    public boolean cercaPrenotazione(String titolo){
        return true;
    }
    public boolean cercaPrenotazione(Data dataInizio, Data dataFine){
        return true;
    }
}
