import java.security.NoSuchAlgorithmException;

public class UtenteNR extends Utente {
    public  UtenteNR() throws NoSuchAlgorithmException {
        super(Ruolo.NONREGISTRATO);
    }
}
