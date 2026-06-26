import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe estende la classe <em>Utente</em> e si occupa dell'incapsulamento dei dati del <strong>Proiezionista</strong> in modo corretto.
 * @see Utente
 *
 * @author Matteo Germani
 */
public class Proiezionista extends Utente {

    /**
     * Costruisce un nuovo <strong>Proiezionista</strong>.
     * @param nome il nome del proiezionista.
     * @param cognome il cognome del proiezionista.
     * @param username lo username del proiezionista.
     * @param password la password del proiezionista.
     * @param dataNascita la data di nasciata del proiezionista.
     * @param domicilio l'indirizzo del domicilio del proiezionista.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Proiezionista(String nome, String cognome, String username, String password, Data dataNascita, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, dataNascita, domicilio, Ruolo.PROIEZIONISTA);
    }

    /**
     * Costruisce un nuovo <strong>Proiezionista</strong>.
     * @param nome il nome del proiezionista.
     * @param cognome il cognome del proiezionista.
     * @param username lo username del proiezionista.
     * @param password la password del proiezionista.
     * @param giorno il giorno di nasciata del proiezionista.
     * @param mese il mese di nasciata del proiezionista.
     * @param anno l'anno di nasciata del proiezionista.
     * @param domicilio l'indirizzo del domicilio del proiezionista.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Proiezionista(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.PROIEZIONISTA);
    }

    /**
     * Costruisce un nuovo <strong>Proiezionista</strong>.
     * @param nome il nome del proiezionista.
     * @param cognome il cognome del proiezionista.
     * @param username lo username del proiezionista.
     * @param password la password del proiezionista.
     * @param domicilio l'indirizzo del domicilio del proiezionista.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Proiezionista(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.PROIEZIONISTA);
    }
}
