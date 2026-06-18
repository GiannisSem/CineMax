public class Prenotazione {
    public static int ultimoCodicePrenotazione = CineMaxManager.getUltimoCodicePrenotazione();
    private int codicePrenotazione;
    private Cliente cliente;
    private Proiezione proiezione;
    private String posti;

    public Prenotazione(Cliente cliente, Proiezione proiezione, String[] posti) {
        this.codicePrenotazione = ultimoCodicePrenotazione++;
        this.cliente = cliente;
        this.proiezione = proiezione;
        if (posti.length == 1)
            this.posti = posti[0];
        else {
            StringBuilder sb = new StringBuilder(posti[0]);
            for (int i = 1; i < posti.length; i++) {
                sb.append("-").append(posti[i]);
            }
            this.posti = sb.toString();
        }
        // TODO: meglio prima assegnare e poi creare la prenotazione (meglio) oppure mandare i posti e pregare che siano liberi (FA SCHIFO)
    }

    public Prenotazione(int codicePrenotazione, String username, DataOra orarioProiezione, String[] posti) {
        this.codicePrenotazione = codicePrenotazione;
        this.cliente = (Cliente) LoginManager.cercaUtente(username);
        this.proiezione = CineMaxManager.cercaProiezione(orarioProiezione);
        if (posti.length == 1)
            this.posti = posti[0];
        else {
            StringBuilder sb = new StringBuilder(posti[0]);
            for (int i = 1; i < posti.length; i++) {
                sb.append("-").append(posti[i]);
            }
            this.posti = sb.toString();
        }
    }

    public int getCodicePrenotazione() {
        return codicePrenotazione;
    }

    public String getNominativo(){
        return String.format(cliente.getNome() + " " + cliente.getCognome());
    }
    public Cliente getCliente() { return cliente; }

    public DataOra getDataOra() {
        return proiezione.getDataOra();
    }
    public Sala getSala() { return proiezione.getSala(); }

    public double getPrezzo(){
        return proiezione.getCostoBiglietto();
    }

    public String[] getPosti() { return posti.split("-"); }

    public Proiezione getProiezione() {
        return proiezione;
    }

    /*
    // TODO: zini: prima controlla la disponibilità, poi la termine, per ogni posto prenotalo.
    private boolean assegnaPosto(String posti){                                 // formato stringa "2G-3G-4G"
        String [] arrPosti = posti.split("-");
        for(String s : arrPosti){
            char lettera = s.charAt(s.length() - 1);                            //prendo l'ultimo carattere
            int numero = Integer.parseInt(s.substring(0, s.length() - 1));      //faccio il substring dal primo valore all'ultimo non compreso anche se ho 12A prendo tutto il 12
            if(!proiezione.isPostoDisponibile(lettera, numero))
                return false;
            proiezione.prenotaPosto(lettera, numero);
        }
        return true;
    }*/

    // TODO: zini: nel main devi gestire tu la modifica: past = cercaPrenotazione -> modifica dei posti nel nuovo -> se tutto ok -> eliminaPrenotazione(past)

    @Override
    public String toString() {
        return String.format(codicePrenotazione + ";" + cliente.getUsername() + ";" + proiezione.getDataOra() + ";" + posti);
    }

    public  String toInfo(){
        return String.format("codice prenotazione: " + codicePrenotazione, " username cliente " + cliente.getUsername() + " data proiezione " + proiezione.getDataOra() + " titolo film" + proiezione.getFilm().getTitolo());
    }

    public String toInfoCliente()
    {
        return String.format(getCodicePrenotazione()+  "    " +getDataOra()+"    "+proiezione.getFilm().getTitolo()+  "    " +proiezione.getFilm().getGenere()+  "    " +proiezione.getFilm().getRegista()+  "    " +proiezione.getFilm().annoUscita()+  "    " +proiezione.getFilm().getDurata()+  "    " +proiezione.getFilm().getVmeta()+  "    " +proiezione.getCostoBiglietto()+  "    " +getPosti() );
    }

}