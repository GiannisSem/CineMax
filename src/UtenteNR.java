import java.security.NoSuchAlgorithmException;

/**
 * Questa classe estende la classe <em>Utente</em> e si occupa dell'incapsulamento dei dati dell'Utente Non Registrato in modo corretto.
 * @see Utente
 *
 * @author Matteo Germani
 */
public class UtenteNR extends Utente {

    /**
     * Costruisce un nuovo <strong>Utente Non Registrato</strong>.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public  UtenteNR() throws NoSuchAlgorithmException {
        super(Ruolo.NONREGISTRATO);
    }
}
