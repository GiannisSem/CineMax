import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Proiezionista extends Utente {

    public Proiezionista(String nome, String cognome, String username, String password, Data dataNascita, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, dataNascita, domicilio, Ruolo.PROIEZIONISTA);
    }

    public Proiezionista(String nome, String cognome, String username, String password, int giorno, int mese, int anno, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, giorno, mese, anno, domicilio, Ruolo.PROIEZIONISTA);
    }

    public Proiezionista(String nome, String cognome, String username, String password, String domicilio) throws NoSuchAlgorithmException {
        super(nome, cognome, username, password, domicilio, Ruolo.PROIEZIONISTA);
    }


    public boolean aggiungiFilm(String titolo, String genere, String regista, int annoUscita, int durata, int vmeta){
        return true;
    }
    public boolean aggiungiProiezione(DataOra dataOra, String titolo, String genere, String regista, int annoUscita, int durata, int vmeta, double costoBiglietto){
        return CineMaxManager.inserisciProiezione(dataOra, new Film(titolo, genere, regista, annoUscita, durata, vmeta), costoBiglietto);
    }
    public boolean aggiungiProiezione(DataOra dataOra, Film film, double costoBiglietto){
        return CineMaxManager.inserisciProiezione(dataOra, film, costoBiglietto);
    }
    public boolean modificaDataProiezione(DataOra dataOraOld, DataOra dataOraNew){
        return CineMaxManager.modificaDataProiezione(dataOraOld, dataOraNew);
    }
    public boolean modificaDataProiezione(String dataOraOld, String dataOraNew){
        return CineMaxManager.modificaDataProiezione(dataOraOld, dataOraNew);
    }
    public boolean eliminaProiezione(DataOra dataOra){
        return CineMaxManager.eliminaProiezione(dataOra);
    }

//    public boolean aggiungiFilm(String titolo, String genere, String regista, int annoUscita, int durata, int vmeta){
//        List<Film> film = new ArrayList<Film>(FileManager.deserializza_film());
//        for(Film f : film){
//            if(f.getTitolo().equals(titolo)){
//                return false;
//            }
//        }
//        film.add(new Film(titolo, genere, regista, annoUscita, durata, vmeta));
//        FileManager.serializza_lista(film, FileManager.path_film);
//        return true;
//    }

}
