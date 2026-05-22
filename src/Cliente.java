import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Cliente extends Utente {

    public Cliente(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.CLIENTE);
    }

    public Cliente(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.CLIENTE);
    }

    // Da fare
    public List<Proiezione> cercaProiezioni(String titolo){
        return CineMaxManager.cercaProiezioni_Titolo(FileManager.deserializza_proiezioni(), titolo);
    }
    public boolean inserisciPrenotazione(String titolo, DataOra dataOra, String posti){
        return true;
    }
    public String visuaPrenotazione(){
        return  null;
    }
    public boolean cancellaPrenotazione(int codPrenotazione){
        return true;
    }
    public boolean modificaPrenotazione(int codPrenotazione, Data dataNuova){
        return true;
    }

}
