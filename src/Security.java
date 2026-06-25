import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Security {

    //fonte: https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha-256-in-java
    public static String getHash(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    public static boolean checkPassword(String password, String hash) throws NoSuchAlgorithmException {
        return hash.equals(getHash(password));
    }
}
