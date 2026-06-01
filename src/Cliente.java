import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Utente {

    List<Prenotazione> prenotazioni;

    public Cliente(String nome, String cognome, String username, String password, Data dataNascita, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, dataNascita, domicilio, Ruolo.CLIENTE);
        //prenotazioni = CineMaxManager.getListaPrenotazioni();
    }

    public Cliente(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.CLIENTE);
       // prenotazioni = CineMaxManager.getListaPrenotazioni();
    }

    public Cliente(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.CLIENTE);
       // prenotazioni = CineMaxManager.getListaPrenotazioni();
    }

    // Da fare

    public boolean inserisciPrenotazione(String titolo, DataOra dataOra, String posti){
        try {
            prenotazioni.add(new Prenotazione(this, CineMaxManager.cercaProiezione(dataOra), posti));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean inserisciPrenotazione(String titolo, String dataOra, String posti){
        try {
            prenotazioni.add(new Prenotazione(this, CineMaxManager.cercaProiezione(dataOra), posti));
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public String visuaPrenotazione(){
        StringBuilder s = new StringBuilder();
        for(Prenotazione p : prenotazioni){
            s.append(p.toInfo());
        }
        return s.toString();
    }

    public boolean cancellaPrenotazione(int codPrenotazione){
        return prenotazioni.remove(CineMaxManager.cercaPrenotazione(prenotazioni,  codPrenotazione));
    }

    public boolean modificaPrenotazione(int codPrenotazione, DataOra dataNuova){
        Prenotazione p = CineMaxManager.cercaPrenotazione(prenotazioni, codPrenotazione);
        if(p == null)
            return false;
        return p.modificaData(dataNuova);
    }

}
