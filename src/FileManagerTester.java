public class FileManagerTester {

    public static void main(String[] args) {
        FileManager.stampaLista(FileManager.deserializza_proiezioni());
        FileManager.stampaLista(FileManager.deserializza_film());
        System.out.println(FileManager.path_film);
    }
}
