import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Utente implements Comparable<Utente> {

    private String nome;
    private String cognome;
    private String username;
    private String password;
    private Data dataNascita = null;
    private String domicilio;
    private Ruolo ruolo;

    public Utente(String nome, String cognome, String username, String password, Data dataNascita, String domicilio, Ruolo ruolo) throws NoSuchAlgorithmException {
        setNome(nome);
        setCognome(cognome);
        setUsername(username);
        setPassword(password);
        this.dataNascita = dataNascita;
        setDomicilio(domicilio);
        if(ruolo == null){
            throw  new IllegalArgumentException("Ruolo non inserito");
        }
        this.ruolo = ruolo;
    }

    public Utente(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio, Ruolo ruolo) throws NoSuchAlgorithmException {
        this(nome, cognome, username, password, new Data(anno, mese, giorno), domicilio, ruolo);
    }

    public Utente (String nome, String cognome, String username, String password, String domicilio, Ruolo ruolo) throws NoSuchAlgorithmException {
        this(nome, cognome, username, password, null, domicilio, ruolo);
    }
    public Utente(Ruolo ruolo) throws NoSuchAlgorithmException {
        this("","","","","", ruolo);
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


     private void setPassword(String password) throws NoSuchAlgorithmException {
        if(password == null)
            throw new IllegalArgumentException("Password non valido");

         this.password = password;
     }

    public void setDomicilio(String domicilio) {
        if(domicilio == null)
            throw new IllegalArgumentException("Domicilio non valido");
        this.domicilio = domicilio;
    }

    public void setDataNascita(Data data) {
        if(data == null)
            throw new IllegalArgumentException("Data di nascita non valida");
        this.dataNascita = data;
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
    public String getPassword() {
        return  password;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public String getRuolo() {
        return ruolo.toString();
    }


    @Override
    public String toString(){
        // stampa su file csv
        return String.format(nome + ";" + cognome + ";" +  username + ";" + getPassword() + ";" + dataNascita + ";" + domicilio + ";" + ruolo.toString());
    }

    @Override
    public int compareTo(Utente other){
        return this.username.compareTo(other.username);
    }

    public void setHashPassword(String password) throws NoSuchAlgorithmException {
        this.password = getHash(password);
    }

    //zini se vuoi modifica
    public String toInfo(){
        return String.format("Nome:\n" + nome + "\nCognome:\n" + cognome + "\nUsername:\n" +  username + "\nData di nascita:\n" + dataNascita + "\nDomicilio:\n" + domicilio);
    }

    //fonte: https://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha-256-in-java
    private String getHash(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }
    public boolean checkPassword(String password) throws NoSuchAlgorithmException {
        return this.password.equals(getHash(password));
    }

}
