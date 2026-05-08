public class Data {
    private int giorno;
    private int mese;
    private int anno;

    public Data(int giorno, int mese, int anno){
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
    }

    @Override
    public String toString() {
        return String.format("%d-%d-%d", anno, mese, giorno);
    }
}
