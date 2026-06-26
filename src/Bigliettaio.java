import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Questa classe estende la classe <em>Utente</em> e si occupa dell'incapsulamento dei dati del Bigliettaio in modo corretto.
 * @see Utente
 *
 * @author Matteo Germani
 */
public class Bigliettaio extends Utente {
    /**
     * Costruisce un nuovo <strong>Bigliettaio</strong>.
     * @param nome il nome del bigliettaio.
     * @param cognome il cognome del bigliettaio.
     * @param username lo username del bigliettaio.
     * @param password la password del bigliettaio.
     * @param dataNascita la data di nasciata del bigliettaio.
     * @param domicilio l'indirizzo del domicilio del bigliettaio.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Bigliettaio(String nome, String cognome, String username, String password, Data dataNascita, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, dataNascita, domicilio, Ruolo.BIGLIETTAIO);
    }

    /**
     * Costruisce un nuovo <strong>Bigliettaio</strong>.
     * @param nome il nome del bigliettaio.
     * @param cognome il cognome del bigliettaio.
     * @param username lo username del bigliettaio.
     * @param password la password del bigliettaio.
     * @param giorno il giorno di nasciata del bigliettaio.
     * @param mese il mese di nasciata del bigliettaio.
     * @param anno l'anno di nasciata del bigliettaio.
     * @param domicilio l'indirizzo del domicilio del bigliettaio.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Bigliettaio(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.BIGLIETTAIO);
    }

    /**
     * Costruisce un nuovo <strong>Bigliettaio</strong>.
     * @param nome il nome del bigliettaio.
     * @param cognome il cognome del bigliettaio.
     * @param username lo username del bigliettaio.
     * @param password la password del bigliettaio.
     * @param domicilio l'indirizzo del domicilio del bigliettaio.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Bigliettaio(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.BIGLIETTAIO);
    }
}
