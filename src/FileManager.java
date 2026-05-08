import java.io.*;
import java.util.ArrayList;
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
                    System.out.println("ok");
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

    private static Proiezione getProiezione(String riga) {
        // System.out.println(riga);
        String[] attributi = splitAttributi(riga);
        DataOra dataOra = new DataOra(attributi[0]);
        Film film = new Film(attributi[1], attributi[2], attributi[3],
                Integer.parseInt(attributi[4]), Integer.parseInt(attributi[5]), Integer.parseInt(attributi[6]));
        return new Proiezione(dataOra, film, Double.parseDouble(attributi[7]));
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
}
