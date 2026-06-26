import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe estende la classe <em>Utente</em> e si occupa dell'incapsulamento dei dati del <strong>Cliente</strong> in modo corretto.
 * @see Utente
 *
 * @author Matteo Germani
 */
public class Cliente extends Utente {
    /**
     * Costruisce un nuovo <strong>Cliente</strong>.
     * @param nome il nome del cliente.
     * @param cognome il cognome del cliente.
     * @param username lo username del cliente.
     * @param password la password del cliente.
     * @param dataNascita la data di nasciata del cliente.
     * @param domicilio l'indirizzo del domicilio del cliente.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Cliente(String nome, String cognome, String username, String password, Data dataNascita, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, dataNascita, domicilio, Ruolo.CLIENTE);
        //prenotazioni = CineMaxManager.getListaPrenotazioni();
    }
    
    /**
     * Costruisce un nuovo <strong>Cliente</strong>.
     * @param nome il nome del cliente.
     * @param cognome il cognome del cliente.
     * @param username lo username del cliente.
     * @param password la password del cliente.
     * @param giorno il giorno di nasciata del cliente.
     * @param mese il mese di nasciata del cliente.
     * @param anno l'anno di nasciata del cliente.
     * @param domicilio l'indirizzo del domicilio del cliente.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Cliente(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.CLIENTE);
       // prenotazioni = CineMaxManager.getListaPrenotazioni();
    }
    
    /**
     * Costruisce un nuovo <strong>Cliente</strong>.
     * @param nome il nome del cliente.
     * @param cognome il cognome del cliente.
     * @param username lo username del cliente.
     * @param password la password del cliente.
     * @param domicilio l'indirizzo del domicilio del cliente.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Cliente(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.CLIENTE);
       // prenotazioni = CineMaxManager.getListaPrenotazioni();
    }

}
