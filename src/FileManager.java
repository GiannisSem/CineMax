import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe è l'interfaccia tra il programma e il file system.
 *
 * @author Samuele Baragiola
 */
public class FileManager {
    // paths
    /**
     * Indica il <em>path</em> per il file binario delle <strong>Proiezioni</strong>.
     */
    public static final String path_proiezioni = "data/proiezioni.dat";

    /**
     * Indica il <em>path</em> per il file binario dei <strong>Film</strong>.
     */
    public static final String path_film = "data/film.dat";

    /**
     * Indica il <em>path</em> per il file csv degli <strong>Utenti</strong>.
     */
    public static final String path_utenti = "data/utenti.csv";

    /**
     * Indica il <em>path</em> per il file csv delle <strong>Prenotazioni</strong>.
     */
    public static final String path_prenotazioni = "data/prenotazioni.csv";

    // metodi

    /**
     * <strong>Metodo di backup:</strong> da attivare manualmente in caso di errore, legge da un file csv delle <strong>Proiezioni di default</strong>.
     * @return lista di proiezioni letta dal file csv.
     */
    public static List<Proiezione> leggiProiezioni_csv() {
        List<Proiezione> lista = new ArrayList<>();
        File f = new File("data/proiezioni.csv");
        if (f.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String riga = br.readLine(); // la prima è l'intestazione
                while ((riga = br.readLine()) != null) {
                    Proiezione p = getProiezione(riga);
                    lista.add(p);
                }
                br.close();
            } catch (FileNotFoundException e) {
                System.out.println("Errore");
            } catch (IOException e) {
                System.out.println("io");
            }
        }

        return lista;
    }

    /**
     * Legge dal file csv degli <strong>Utenti</strong> e li istanzia in base al ruolo.
     * @return lista di utenti letta dal file csv.
     */
    public static List<Utente> leggiUtenti_csv() {
        List<Utente> lista = new ArrayList<>();
        File f = new File(path_utenti);
        if (f.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String riga = br.readLine();
                while ((riga = br.readLine()) != null) {
                    String[] attributi = riga.split(";");
                    Ruolo r = Ruolo.valueOf(attributi[6]);
                    Utente u;
                    switch (r){
                        case CLIENTE-> u = (attributi[4] != null && !attributi[4].equals("null")) ?
                                new Cliente(attributi[0], attributi[1], attributi[2], attributi[3], new Data(attributi[4]), attributi[5]) :
                                new Cliente(attributi[0], attributi[1], attributi[2], attributi[3], attributi[5]);
                        case BIGLIETTAIO -> u = (attributi[4] != null && !attributi[4].equals("null")) ?
                                new Bigliettaio(attributi[0], attributi[1], attributi[2], attributi[3], new Data(attributi[4]), attributi[5]) :
                                new Bigliettaio(attributi[0], attributi[1], attributi[2], attributi[3], attributi[5]);
                        case PROIEZIONISTA -> u = (attributi[4] != null && !attributi[4].equals("null")) ?
                                new Proiezionista(attributi[0], attributi[1], attributi[2], attributi[3], new Data(attributi[4]), attributi[5]) :
                                new Proiezionista(attributi[0], attributi[1], attributi[2], attributi[3], attributi[5]);

                        default -> u = new UtenteNR();
                    }

                    lista.add(u);
                }
                br.close();
            } catch (FileNotFoundException e) {
                System.out.println("Errore");
            } catch (IOException e) {
                System.out.println("io");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

        return lista;
    }

    /**
     * Legge dal file csv delle <strong>Prenotazioni</strong>.
     * @return lista di prenotazioni letta dal file csv.
     */
    public static List<Prenotazione> leggiPrenotazioni_csv() {
        List<Prenotazione> lista = new ArrayList<>();
        File f = new File(path_prenotazioni);
        if (f.exists()){
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String riga = br.readLine();
                while ((riga = br.readLine()) != null) {
                    String[] attributi = riga.split(";");
                    lista.add(new Prenotazione(Integer.parseInt(attributi[0]), attributi[1], new DataOra(attributi[2]), attributi[3].split("-")));
                }
                br.close();
            } catch (FileNotFoundException e) {
                System.out.println("Errore");
            } catch (IOException e) {
                System.out.println("io");
            }
        }

        return lista;
    }

    /**
     * Data una lista e il relativo <em>path</em>, carica la prima riga (intestazione del file) e la lista in formato csv.
     * @param lista lista generica {@literal <?>} da caricare come csv.
     * @param path percorso del file csv.
     */
    public static void carica_csv(List<?> lista, String path){
        File f = new File(path);

        String colonne;
        switch (path){
            case path_utenti -> colonne = "Nome;Cognome;Username;Password;DataNascita;Domicilio;Ruolo";
            case path_prenotazioni -> colonne = "CodicePrenotazione;Username;DataOraProiezione;PostiPrenotati";
            default -> colonne = "";
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(colonne);
            bw.newLine();
            for (Object o : lista) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Errore");
        } catch (IOException e) {
            System.out.println("io");
        }
    }

    /**
     * Data una riga del file csv delle proiezioni, ne estrae correttamente i dati e crea un'istanza di <strong>Proiezione</strong>.
     * @param riga riga del file csv delle proiezioni.
     * @return istanza di tipo Proiezione con i dati parsati.
     * @see #leggiProiezioni_csv()
     */
    private static Proiezione getProiezione(String riga) {
        String[] attributi = splitAttributi(riga);
        DataOra dataOra = new DataOra(attributi[0]);
        Film film = new Film(attributi[1], attributi[2], attributi[3],
                Integer.parseInt(attributi[4]), Integer.parseInt(attributi[5]), Integer.parseInt(attributi[6]));
        return new Proiezione(dataOra, film, Double.parseDouble(attributi[7]), new Sala());
    }

    /**
     * Data una riga del file csv delle proiezioni, divide i dati e li inserisce in un array temporaneo.
     * @param riga riga del file csv delle proiezioni.
     * @return array temporaneo che contiene, per ogni i, un attributo diverso della proiezione.
     */
    private static String[] splitAttributi(String riga){
        String[] attributi = new String[8];
        int attr = 0;
        System.out.println(riga);
        String temp = "";
        boolean isDentro = false;
        for (int i = 0; i < riga.length(); i++) {
            char c = riga.charAt(i);
            if (c == '\"')
                isDentro = !isDentro;
            else if (c == ',' && !isDentro) {
                attributi[attr++] = temp;
                temp = "";
            } else {
                temp += c;
            }
        }

        attributi[attr] = temp;
        return attributi;
    }

    /**
     * Serializza una lista, scrivendo in un file binario.
     * @param lista lista generica {@literal <?>} da caricare come file binario.
     * @param path percorso del file binario.
     */
    // fonte: https://www.iprogrammatori.it/forum-programmazione/java/arraylist-con-file-binari-t37366.html
    public static void serializza_lista(List<?> lista, String path){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(lista);
            oos.close();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Deserializza il file binario delle <strong>Proiezioni</strong>.
     * @return lista di proiezioni letta dal file binario.
     */
    // fonte: https://www.iprogrammatori.it/forum-programmazione/java/arraylist-con-file-binari-t37366.html
    public static List<Proiezione> deserializza_proiezioni(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path_proiezioni));
            List<Proiezione> lista = (List<Proiezione>) ois.readObject();
            ois.close();
            return lista;
        } catch (ClassNotFoundException | IOException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Deserializza il file binario dei <strong>Film</strong>.
     * @return lista di film letta dal file binario.
     */
    public static List<Film> deserializza_film(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path_film));
            List<Film> lista = (List<Film>) ois.readObject();
            ois.close();
            return lista;
        } catch (ClassNotFoundException | IOException ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }

    /**
     * Stampa a video una lista.<br>
     * <em>Questo metodo è stato usato in fase di test.</em>
     * @param lista lista generica {@literal <?>}.
     */
    public static void stampaLista(List<?> lista){
        int id = 1;
        for(Object p : lista){
            System.out.println(id++ + ": " + p);
        }
    }
}
