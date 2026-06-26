import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Questa classe viene usata per gestire l'hash delle password, utilizzando l'algoritmo di cifratura SHA-256.
 * @see Utente
 *
 * @author Matteo Germani
 */
public class Security {

    /**
     * Crea una stringa associata alla password utilizzando l'algoritmo di cifratura SHA-256
     * @param s <code>password</code> su cui effettuare l'hash.
     * @return l'hash associata alla <code>password</code>.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    //fonte: https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha-256-in-java
    public static String getHash(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    /**
     * Controlla se la password è associata all'hash.
     * @param password password da validare.
     * @param hash hash della password corretta.
     * @return true se la relazione è corretta, altrimenti false.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public static boolean checkPassword(String password, String hash) throws NoSuchAlgorithmException {
        return hash.equals(getHash(password));
    }
}
