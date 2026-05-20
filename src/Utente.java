import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Utente {

    private String nome;
    private String cognome;
    private String username;
    private String password;
    private Data dataNascita = null;
    private String domicilio;
    private Ruolo ruolo;

    public Utente(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio, Ruolo ruolo) throws NoSuchAlgorithmException {
        setNome(nome);
        setCognome(cognome);
        setUsername(username);
        setPassword(password);
        this.dataNascita = new Data(anno, mese, giorno);
        setDomicilio(domicilio);
        if(ruolo == null){
            throw  new IllegalArgumentException("Ruolo non inserito");
        }
        this.ruolo = ruolo;
    }

    public Utente (String nome, String cognome, String username, String password, String domicilio, Ruolo ruolo) throws NoSuchAlgorithmException {
        setNome(nome);
        setCognome(cognome);
        setUsername(username);
        setPassword(password);
        setDomicilio(domicilio);
        this.ruolo = ruolo;
    }

    private void setNome(String nome) {
        if(nome == null)
            throw new IllegalArgumentException("Nome non valido");
        this.nome = nome;
    }

    private void setCognome(String cognome) {
        if(cognome == null)
            throw  new IllegalArgumentException("Cognome non valido");
        this.cognome = cognome;
    }

    public void setUsername(String username) {
        if(username == null)
            throw new IllegalArgumentException("Username non valido");
        this.username = username;
    }

    //fonte: https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha-256-in-java
     private void setPassword(String password) throws NoSuchAlgorithmException {
        if(password == null)
            throw new IllegalArgumentException("Password non valido");

         MessageDigest digest = MessageDigest.getInstance("SHA-256");
         byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
         this.password = Base64.getEncoder().encodeToString(hash);
     }

    public void setDomicilio(String domicilio) {
        if(domicilio == null)
            throw new IllegalArgumentException("Domicilio non valido");
        this.domicilio = domicilio;
    }


    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public String getUsername() {
        return username;
    }
    private String getPassword() {
        return  password;
    }
    private String getDomicilio() {
        return domicilio;
    }
    private String getRuolo() {
        return ruolo.toString();
    }


    @Override
    public String toString(){
        return String.format(nome + ";" + cognome + ";" +  username + ";" + dataNascita + ";" + getPassword() + ";" + domicilio + ";" + ruolo.toString());
    }

    //zini se vuoi modifica
    public String toInfo(){
        return String.format("Nome:%n" + nome + "Cognome:%n" + cognome + "Username:%n" +  username + "Data di nascita:%n" + dataNascita + "Domicilio:%n " + domicilio);
    }

}
