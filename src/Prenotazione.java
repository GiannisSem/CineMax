/**
 * Questa classe crea e gestisce una Prenotazione.
 *
 * @author Samuele Baragiola
 * @author Matteo Germani
 */
public class Prenotazione {
    /**
     * Tiene traccia dell'<code>ultimo codice prenotazione</code> usato.<br>
     * Il valore iniziale è dato staticamente.
     * @see CineMaxManager#getUltimoCodicePrenotazione()
     */
    public static int ultimoCodicePrenotazione = CineMaxManager.getUltimoCodicePrenotazione();

    /**
     * Il <code>codice</code> della prenotazione.
     */
    private int codicePrenotazione;

    /**
     * Il <strong>Cliente</strong> che ha effettuato la prenotazione.
     */
    private Cliente cliente;

    /**
     * La <strong>Proiezione</strong> associata alla prenotazione.
     */
    private Proiezione proiezione;

    /**
     * I <code>posti</code> scelti in sala.
     */
    private String posti;

    /**
     * Questo metodo costruisce un nuovo oggetto <strong>Prenotazione</strong>.
     * @param cliente cliente che ha creato la prenotazione.
     * @param proiezione proiezione associata alla prenotazione.
     * @param posti vettore di posti prenotati.
     */
    public Prenotazione(Cliente cliente, Proiezione proiezione, String[] posti) {
        this.codicePrenotazione = ++ultimoCodicePrenotazione;
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
    }

    /**
     * Questo metodo costruisce un oggetto <strong>Prenotazione</strong> già esistente.
     * @param codicePrenotazione codice della prenotazione.
     * @param username username del cliente.
     * @param orarioProiezione orario della proiezione.
     * @param posti vettore di posti prenotati.
     */
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

    /**
     * Metodo <em>getter</em> del <code>codice della prenotazione</code>.
     * @return <code>codice della prenotazione</code>
     */
    public int getCodicePrenotazione() {
        return codicePrenotazione;
    }

    /**
     * Metodo <em>getter</em> del <code>nominativo</code> del <strong>Cliente</strong>.
     * @return <code>nominativo</code> del cliente.
     */
    public String getNominativo(){
        return String.format(cliente.getNome() + " " + cliente.getCognome());
    }

    /**
     * Metodo <em>getter</em> del <strong>Cliente</strong>.
     * @return istanza del cliente.
     */
    public Cliente getCliente() { return cliente; }

    /**
     * Metodo <em>getter</em> dell'<code>orario</code> della <strong>Proiezione</strong>.
     * @return <code>orario</code> della proiezione.
     */
    public DataOra getDataOra() {
        return proiezione.getDataOra();
    }

    /**
     * Metodo <em>getter</em> del <code>film</code> proiettato.
     * @return <code>film</code> proiettato.
     */
    public Film getFilm() { return proiezione.getFilm(); }

    /**
     * Metodo <em>getter</em> della <code>sala</code> durante la <strong>Proiezione</strong>.
     * @return <code>sala</code> durante la proiezione.
     */
    public Sala getSala() { return proiezione.getSala(); }

    /**
     * Metodo <em>getter</em> del <code>prezzo</code> del biglietto.
     * @return <code>prezzo</code> del biglietto.
     */
    public double getPrezzo(){
        return proiezione.getCostoBiglietto();
    }

    /**
     * Metodo <em>getter</em> dei <code>posti</code> prenotati in sala durante la <strong>Proiezione</strong>.
     * @return vettore dei <code>posti</code> prenotati in sala durante la proiezione.
     */
    public String[] getPosti() { return posti.split("-"); }

    /**
     * Metodo <em>getter</em> della <strong>Proiezione</strong>.
     * @return istanza della proiezione.
     */
    public Proiezione getProiezione() {
        return proiezione;
    }

    // TODO: zini: prima controlla la disponibilità, poi la termine, per ogni posto prenotalo.
    // TODO: zini: nel main devi gestire tu la modifica: past = cercaPrenotazione -> modifica dei posti nel nuovo -> se tutto ok -> eliminaPrenotazione(past)

    /**
     * Questo metodo crea la stringa associata all'istanza.
     * @return stringa associata all'istanza.
     */
    @Override
    public String toString() {
        return String.format(codicePrenotazione + ";" + cliente.getUsername() + ";" + proiezione.getDataOra() + ";" + posti);
    }

    /**
     * Questo metodo crea la stringa associata all'istanza da mostrare a schermo.
     * @return stringa associata all'istanza da mostrare a schermo.
     */
    public  String toInfo(){
        return String.format("codice prenotazione: " + codicePrenotazione, " username cliente " + cliente.getUsername() + " data proiezione " + proiezione.getDataOra() + " titolo film" + proiezione.getFilm().getTitolo());
    }

    /**
     * TODO: Dario per favore cambia questo toinfo orribile, dagli tu una descrizione...
     */
    public String toInfoCliente()
    {
        return String.format(getCodicePrenotazione()+  "    " +getDataOra()+"    "+proiezione.getFilm().getTitolo()+  "    " +proiezione.getFilm().getGenere()+  "    " +proiezione.getFilm().getRegista()+  "    " +proiezione.getFilm().annoUscita()+  "    " +proiezione.getFilm().getDurata()+  "    " +proiezione.getFilm().getVmeta()+  "    " +proiezione.getCostoBiglietto()+  "    " +getPosti() );
    }

}