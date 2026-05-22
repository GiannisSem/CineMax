import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Utente {

    List<Prenotazione> prenotazioni;

    public Cliente(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.CLIENTE);
        prenotazioni = new ArrayList<>();
    }

    public Cliente(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.CLIENTE);
        prenotazioni = new ArrayList<>();
    }

    // Da fare
    public List<Proiezione> cercaProiezioni(String titolo){
        return CineMaxManager.cercaProiezioni_Titolo(FileManager.deserializza_proiezioni(), titolo);
    }
    public boolean inserisciPrenotazione(String titolo, DataOra dataOra, String posti){
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
    public Prenotazione cercaPrenotazione(int codice){
        for(Prenotazione p : prenotazioni)
            if(p.getCodicePrenotazione() == codice)
                return p;
        return null;
    }
    public boolean cancellaPrenotazione(int codPrenotazione){
        return prenotazioni.remove(cercaPrenotazione(codPrenotazione));
    }

    public boolean modificaPrenotazione(int codPrenotazione, DataOra dataNuova){
        Prenotazione p = cercaPrenotazione(codPrenotazione);
        if(p == null)
            return false;
        return p.modificaData(dataNuova);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(super.toString()).append(";");
        for(Prenotazione p : prenotazioni){
            s.append(p.toString());
        }
        return s.toString();
    }

}
