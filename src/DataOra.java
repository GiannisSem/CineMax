public class DataOra {
    private Data data;
    private Ora ora;

    public DataOra(Data data, Ora ora){
        this.data = data;
        this.ora = ora;
    }

    public DataOra(int anno, int mese, int giorno, int ore, int minuti, int secondi) {
        this(new Data(anno, mese, giorno), new Ora(ore, minuti, secondi));
    }

    // vedere se tenerlo (errore virgolette interne)
    public DataOra(String dataOra){
        String[] data = dataOra.split(" ")[0].split("-");
        String[] ora = dataOra.split(" ")[1].split(":");

        this.data = new Data(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        this.ora = new Ora(Integer.parseInt(ora[0]), Integer.parseInt(ora[1]), Integer.parseInt(ora[2]));
    }

    @Override
    public String toString(){
        return data + " " + ora;
    }

    public Data getData(){
        return data;
    }
    public Ora getOra() {
        return ora;
    }
}
