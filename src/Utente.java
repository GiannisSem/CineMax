import java.security.NoSuchAlgorithmException;

/**
 * Questa classe si occupa dell'incapsulamento dei dati dell'Utente in modo corretto.<br>
 * E' la classe <em>super</em> degli altri ruoli
 * @see Ruolo
 *
 * @author Matteo Germani
 */
public class Utente implements Comparable<Utente> {

    /**
     * Il <code>nome</code> dell'Utente.
     */
    private String nome;

    /**
     * Il <code>cognome</code> dell'Utente.
     */
    private String cognome;

    /**
     * Lo <code>username</code> dell'Utente.
     */
    private String username;

    /**
     * La <code>password</code> dell'Utente (SHA-256).
     */
    private String password;

    /**
     * La <code>data di nascita</code> dell'Utente.
     */
    private Data dataNascita = null;

    /**
     * Il <code>domicilio</code> dell'Utente.
     */
    private String domicilio;

    /**
     * Il <code>ruolo</code> dell'Utente.
     * @see Ruolo
     */
    private Ruolo ruolo;

    /**
     * Costruisce un nuovo <strong>Utente</strong>.
     * @param nome il nome del utente.
     * @param cognome il cognome del utente.
     * @param username lo username del utente.
     * @param password la password del utente.
     * @param dataNascita la data di nasciata del utente.
     * @param domicilio l'indirizzo del domicilio del utente.
     * @param ruolo il ruolo del utente.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
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

    /**
     * Costruisce un nuovo <strong>Utente</strong>.
     * @param nome il nome del utente.
     * @param cognome il cognome del utente.
     * @param username lo username del utente.
     * @param password la password del utente.
     * @param giorno il giorno di nasciata del utente.
     * @param mese il mese di nasciata del utente.
     * @param anno l'anno di nasciata del utente.
     * @param domicilio l'indirizzo del domicilio del utente.
     * @param ruolo il ruolo del utente.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Utente(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio, Ruolo ruolo) throws NoSuchAlgorithmException {
        this(nome, cognome, username, password, new Data(anno, mese, giorno), domicilio, ruolo);
    }

    /**
     * Costruisce un nuovo <strong>Utente</strong>.
     * @param nome il nome del utente.
     * @param cognome il cognome del utente.
     * @param username lo username del utente.
     * @param password la password del utente.
     * @param domicilio l'indirizzo del domicilio del utente.
     * @param ruolo il ruolo del utente.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Utente (String nome, String cognome, String username, String password, String domicilio, Ruolo ruolo) throws NoSuchAlgorithmException {
        this(nome, cognome, username, password, null, domicilio, ruolo);
    }

    /**
     * Costruisce un nuovo <strong>Utente</strong>.
     * @param ruolo il ruolo del utente.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public Utente(Ruolo ruolo) throws NoSuchAlgorithmException {
        this("","","","","", ruolo);
    }

    /**
     * Valide e imposta il <code>nome</code> dell'Utente.
     * @param nome <code>nome</code>
     */
    private void setNome(String nome) {
        if(nome == null)
            throw new IllegalArgumentException("Nome non valido");
        this.nome = nome;
    }

    /**
     * Valide e imposta il <code>cognome</code> dell'Utente.
     * @param cognome <code>cognome</code>
     */
    private void setCognome(String cognome) {
        if(cognome == null)
            throw  new IllegalArgumentException("Cognome non valido");
        this.cognome = cognome;
    }

    /**
     * Valide e imposta lo <code>username</code> dell'Utente.
     * @param username <code>username</code>
     */
    public void setUsername(String username) {
        if(username == null)
            throw new IllegalArgumentException("Username non valido");
        this.username = username;
    }

    /**
     * Valide e imposta la <code>password</code> dell'Utente.
     * @param password <code>password</code>
     */
     private void setPassword(String password) {
        if(password == null)
            throw new IllegalArgumentException("Password non valido");

        this.password = password;
     }

    /**
     * Valide e imposta il <code>domicilio</code> dell'Utente.
     * @param domicilio <code>domicilio</code>
     */
    public void setDomicilio(String domicilio) {
        if(domicilio == null)
            throw new IllegalArgumentException("Domicilio non valido");
        this.domicilio = domicilio;
    }

    /**
     * Valide e imposta la <code>data di nascita</code> dell'Utente.
     * @param data <code>data di nascita</code>
     */
    public void setDataNascita(Data data) {
        if(data == null)
            throw new IllegalArgumentException("Data di nascita non valida");
        this.dataNascita = data;
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>nome</code>.
     * @return <code>nome</code>
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>cognome</code>.
     * @return <code>cognome</code>
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>username</code>.
     * @return <code>username</code>
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>passsword</code>.
     * @return <code>password</code>
     */
    public String getPassword() {
        return  password;
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>domicilio</code>.
     * @return <code>domicilio</code>
     */
    public String getDomicilio() {
        return domicilio;
    }

    /**
     * Metodo <em>getter</em> dell'attributo <code>ruolo</code>.
     * @return <code>ruolo</code>
     */
    public String getRuolo() {
        return ruolo.toString();
    }

    /**
     * Questo metodo crea la stringa associata all'istanza.
     * @return stringa associata all'istanza.
     */
    @Override
    public String toString(){
        // stampa su file csv
        return String.format(nome + ";" + cognome + ";" +  username + ";" + getPassword() + ";" + dataNascita + ";" + domicilio + ";" + ruolo.toString());
    }

    /**
     * Questo metodo compara due oggetti di tipo <strong>Utente</strong>.
     * @param other the object to be compared.
     * @return 1 se <em>this username</em> è maggiore della <em>other username</em>, 0 se sono uguali, altrimenti -1.
     */
    @Override
    public int compareTo(Utente other){
        return this.username.compareTo(other.username);
    }

    /**
     * Assegna alla plain password la sua stringa hash associata (SHA-256).
     * @param password plain password.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public void setHashPassword(String password) throws NoSuchAlgorithmException {
        this.password = Security.getHash(password);
    }

    /**
     * Questo metodo crea la stringa associata all'istanza da mostrare a schermo.
     * @return stringa associata all'istanza da mostrare a schermo.
     */
    public String toInfo(){
        return String.format("Nome:   " + nome + "\n\nCognome:   " + cognome + "\n\nUsername:   " +  username + "\n\nData di nascita:   " + dataNascita + "\n\nDomicilio:   " + domicilio);
    }

    /**
     * Questo metodo crea la stringa associata all'istanza da mostrare a schermo.
     * @return stringa associata all'istanza da mostrare a schermo.
     */
    public String toInfo1(){
        return String.format("Nome: " + nome + "  Cognome: " + cognome + "  Username: " +  username + "  Data di nascita: " + dataNascita + "  Domicilio: " + domicilio);
    }

}
