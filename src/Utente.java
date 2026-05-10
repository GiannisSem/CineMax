import java.io.Serializable;

public class Utente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String cognome;
    private String username;
    private String password;
    private Data dataNascita = null;
    private String domicilio;
    private Ruolo ruolo;

    public Utente(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio, Ruolo ruolo){
        setNome(nome);
        setCognome(cognome);
        setUsername(username);
        //setPassword(password);
        this.dataNascita = new Data(anno, mese, giorno);
        setDomicilio(domicilio);
        if(ruolo == null){
            throw  new IllegalArgumentException("Ruolo non inserito");
        }
        this.ruolo = ruolo;
    }

    public Utente (String nome, String cognome, String username, String password, String domicilio, Ruolo ruolo){
        setNome(nome);
        setCognome(cognome);
        setUsername(username);
        //setPassword(password);
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

    private void setUsername(String username) {
        if(username == null)
            throw new IllegalArgumentException("Username non valido");
        this.username = username;
    }

    // private void setPassword(String password) {} //todo fare hash

    private void setDomicilio(String domicilio) {
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
    public String getPassword() { //todo fare hash
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
        return String.format(nome + " " + cognome + " " +  username + " " + dataNascita + " " + getPassword() + " " + domicilio + " " + ruolo.toString());
    }

}
