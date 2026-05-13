public class Cliente extends Utente {

    public Cliente(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.CLIENTE);
    }

    public Cliente(String nome, String cognome, String username, String password, String domicilio){
        super(nome, cognome, username, password, domicilio, Ruolo.CLIENTE);
    }

    /* Da fare
    public boolean inserisciPrenotazione(){ todo fare nel file manager file prenotazioni

    }
    public String visuaPrenotazione(){ todo fare nel file manager file prenotazioni

    }
    public boolean modificaPrenotazione(){ todo fare nel file manager file prenotazioni

    }
    */
}
