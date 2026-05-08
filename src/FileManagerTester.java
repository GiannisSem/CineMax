import java.util.List;

public class FileManagerTester {
    public static void main(String[] args) {
        List<Proiezione> lista = FileManager.leggiProiezioni_csv();

        int id = 1;
        for(Proiezione p : lista){
            System.out.println(id++ + ": " + p);
        }
    }
}
