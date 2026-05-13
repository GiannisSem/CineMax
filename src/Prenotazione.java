import java.io.Serializable;

public class Prenotazione implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int codicePrenotazione = 0;
    private Cliente cliente;
    private Proiezione proiezione;
    private Posto posto;
    public Prenotazione(Cliente cliente, Proiezione proiezione, int numero, char fila) {
        codicePrenotazione++;
        this.cliente = cliente;
        this.proiezione = proiezione;
        posto = new Posto(numero, fila);
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
    public String posto(){posto.toString()}

}