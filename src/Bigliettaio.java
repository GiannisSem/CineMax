public class Bigliettaio extends Utente {
    public Bigliettaio(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.BIGLIETTAIO);
    }

    public Bigliettaio(String nome, String cognome, String username, String password, String domicilio){
        super(nome, cognome, username, password, domicilio, Ruolo.BIGLIETTAIO);
    }

//    public String visuaPrenotazioni(Data data){ todo fare nel file manager file prenotazioni
//
//    }
//
//    public boolean cercaPrenotazione(Prenotazione pre){ todo fare nel file manager file prenotazioni
//
//    }
}
