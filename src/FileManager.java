import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {
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

            } catch (FileNotFoundException e) {
                System.out.println("Errore");
            } catch (IOException e) {
                System.out.println("io");
            }
        }

        return lista;
    }

    // ERRORE MI DA DOPPIE "" QUANDO SI SPLIT FA PARTE DELLA STRINGA
    private static Proiezione getProiezione(String riga) {
        String[] attributi = riga.split(",");
        System.out.println(Arrays.toString(attributi));
        DataOra dataOra = new DataOra(attributi[0]);
        Film film = new Film(attributi[1], attributi[2], attributi[3],
                Integer.parseInt(attributi[4]), Integer.parseInt(attributi[5]), Integer.parseInt(attributi[6]));
        System.out.println(film.getTitolo());
        return new Proiezione(dataOra, film, Double.parseDouble(attributi[7]));
    }


}
