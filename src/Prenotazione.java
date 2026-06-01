public class Prenotazione {
    private static int codicePrenotazione = 0;
    private Cliente cliente;
    private Proiezione proiezione;
    private String posti;
    public Prenotazione(Cliente cliente, Proiezione proiezione, String posti) {
        codicePrenotazione++;
        this.cliente = cliente;
        this.proiezione = proiezione;
        this.posti = posti;
        if(!assegnaPosto(posti))
            throw new IllegalArgumentException("Posto non valido");
    }
    public int getCodicePrenotazione() {
        return codicePrenotazione;
    }
    public String getNominativo(){
        return String.format(cliente.getNome() + " " + cliente.getCognome());
    }
    public DataOra getDataOra() {
        return proiezione.getDataOra();
    }
    public double getPrezzo(){
        return proiezione.getCostoBiglietto();
    }


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
    }
    public boolean modificaData(DataOra data){
        Proiezione proiezioneNuova = CineMaxManager.cercaProiezione(data);
        if(proiezioneNuova == null || proiezioneNuova.equals(proiezione))
            return false;
        if(assegnaPosto(this.posti)) {
            this.proiezione = proiezioneNuova;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format(codicePrenotazione + ";" + cliente.getUsername() + ";" + posti + ";" + proiezione.getDataOra());
    }

    public  String toInfo(){
        return String.format("codice prenotazione: " + codicePrenotazione, " username cliente " + cliente.getUsername() + " data proiezione " + proiezione.getDataOra() + " titolo film" + proiezione.getFilm().getTitolo());
    }
}