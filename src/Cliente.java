import java.security.NoSuchAlgorithmException;

public class Cliente extends Utente {

    public Cliente(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.CLIENTE);
    }

    public Cliente(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.CLIENTE);
    }

    // Da fare
    public Proiezione cercaProiezioni(DataOra data){
        return CineMaxManager.cercaProiezione(data);
    }
    public boolean inserisciPrenotazione(String titolo, DataOra dataOra, String posti){

    }
    public String visuaPrenotazione(){
        return
    }
    public boolean cancellaPrenotazione(int codPrenotazione){

    }
    public boolean modificaPrenotazione(int codPrenotazione, Data dataNuova){

    }

}
