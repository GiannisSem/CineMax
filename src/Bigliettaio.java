import java.security.NoSuchAlgorithmException;

public class Bigliettaio extends Utente {
    public Bigliettaio(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.BIGLIETTAIO);
    }

    public Bigliettaio(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.BIGLIETTAIO);
    }

    public String visuaPrenotazioniOggi(){

    }
    public boolean cercaPrenotazione(int codPrenotazione){

    }
    public boolean cercaPrenotazione(String nome, String cognome){

    }
    public boolean cercaPrenotazione(String titolo){

    }
    public boolean cercaPrenotazione(Data dataInizio, Data dataFine){

    }
}
