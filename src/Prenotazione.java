/**
 * Questa classe crea e gestisce una Prenotazione.
 *
 * @author Samuele Baragiola
 * @author Matteo Germani
 */
public class Prenotazione {
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
     * @param codicePrenotazione codice della prenotazione.
     * @param cliente cliente che ha creato la prenotazione.
     * @param proiezione proiezione associata alla prenotazione.
     * @param posti vettore di posti prenotati.
     */
    public Prenotazione(int codicePrenotazione, Cliente cliente, Proiezione proiezione, String[] posti) {
        this.codicePrenotazione = codicePrenotazione;
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
     * Questo metodo crea la stringa associata all'istanza da mostrare a schermo.
     * @return stringa associata all'istanza da mostrare a schermo.
     */
    public String toInfoCliente()
    {

        String plus="                               ";
        String titolo=proiezione.getFilm().getTitolo();
        if(titolo.length()>29)
            titolo=titolo.substring(0,29)+"...";
        else
            titolo=(titolo+plus).substring(0,32);


        String genere=proiezione.getFilm().getGenere();
        if(genere.length()>12)
            genere=genere.substring(0,12)+"...";
        else
            genere=(genere+plus).substring(0,15);

        String regista=proiezione.getFilm().getRegista();
        if(regista.length()>19)
            regista=regista.substring(0,19)+"...";
        else
            regista=(regista+plus).substring(0,22);

        String bufferDurata;
        if(proiezione.getFilm().getDurata()<10)
            bufferDurata="  ";
        else {
            if (proiezione.getFilm().getDurata() <100)
                bufferDurata = " ";
            else
                bufferDurata = "";
        }
        String bufferEta=" ";
        if(proiezione.getFilm().getVmeta()>9)
            bufferEta="";

        String bufferCosto="      ";

        double costoTot=getPrezzo()*getPosti().length;
        if(costoTot<10)
            bufferCosto="   ";
        else if(costoTot<100)
            bufferCosto="  ";
        else if(costoTot<1000)
            bufferCosto=" ";
        else
            bufferCosto="";



        String bufferCodice;
        if(getCodicePrenotazione()<10)
            bufferCodice="    ";
        else {
            if (getCodicePrenotazione() <100) {
                bufferCodice = "   ";
            }
            else {
                if (getCodicePrenotazione() < 1000) {
                    bufferCodice = "  ";
                } else {
                    if (getCodicePrenotazione() < 10000) {
                        bufferCodice = " ";
                    }
                    else
                        bufferCodice="";
                }
            }
        }






        return String.format(getCodicePrenotazione()+ bufferCodice +  "   " +getDataOra()+"       " + titolo + "   " + genere + "   " + regista + "   " + getFilm().annoUscita() + "      " + getFilm().getDurata() + bufferDurata + "          " + getFilm().getVmeta()  + bufferEta+ "            " +costoTot+ bufferCosto + "     " + posti );
    }

}