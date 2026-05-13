import java.util.ArrayList;
import java.util.List;

public class FileManagerTester {

    public static void main(String[] args) {
         //FileManager.serializza_lista(FileManager.leggiProiezioni_csv(), FileManager.path_proiezioni);
         List<Proiezione> lp = FileManager.deserializza_proiezioni();
         List<Proiezione> nuova = new ArrayList<>();
         DataOra now = new DataOra(2026, 5, 13, 16, 30, 0);
         for (Proiezione p : lp){
             if (p.getDataOra().compareTo(now) >= 0){
                 nuova.add(p);
             }
         }

         FileManager.stampaLista(nuova);
    }
}
