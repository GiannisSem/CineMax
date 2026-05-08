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
