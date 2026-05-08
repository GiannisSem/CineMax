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
        String data = dataOra.split(" ")[0].substring(1);
        String[] attr_data = data.split("-");
        String ora = dataOra.split(" ")[1];
        ora = ora.substring(0, ora.length()-1);
        String[] attr_ora = ora.split(":");
        System.out.println(data);
        System.out.println(ora);

        this.data = new Data(Integer.parseInt(attr_data[0]), Integer.parseInt(attr_data[1]), Integer.parseInt(attr_data[2]));
        this.ora = new Ora(Integer.parseInt(attr_ora[0]), Integer.parseInt(attr_ora[1]), Integer.parseInt(attr_ora[2]));
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
