import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    // paths
    public static final String path_proiezioni = "data/proiezioni.dat";
    public static final String path_film = "data/film.dat";
    public static final String path_utenti = "data/utenti.csv";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DataOra oggi = new DataOra(LocalDateTime.now().format(formatter));

    // metodi
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

    public static void carica_csv(List<?> lista, String path){
        File f = new File(path);

        String colonne;
        switch (path){
            case path_utenti -> colonne = "Nome;Cognome;Username;Password;DataNascita;Domicilio;Ruolo";
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


    private static Proiezione getProiezione(String riga) {
        String[] attributi = splitAttributi(riga);
        DataOra dataOra = new DataOra(attributi[0]);
        Film film = new Film(attributi[1], attributi[2], attributi[3],
                Integer.parseInt(attributi[4]), Integer.parseInt(attributi[5]), Integer.parseInt(attributi[6]));
        return new Proiezione(dataOra, film, Double.parseDouble(attributi[7]), new Sala());
    }

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

    // fonte: https://www.iprogrammatori.it/forum-programmazione/java/arraylist-con-file-binari-t37366.html
    public static void serializza_lista(List<?> lista, String file){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(lista);
            oos.close();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

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

    public static List<Prenotazione> deserializza_prenotazioni_csv(){
        return null;
    }

    public static void stampaLista(List<?> lista){
        int id = 1;
        for(Object p : lista){
            System.out.println(id++ + ": " + p);
        }
    }
}
