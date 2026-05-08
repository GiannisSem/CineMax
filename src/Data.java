public class Data {
    private int giorno;
    private int mese;
    private int anno;

    public Data(int anno, int mese, int giorno){
        this.anno = anno;
        this.mese = mese;
        this.giorno = giorno;
    }

    @Override
    public String toString() {
        return String.format("%d-%d-%d", anno, mese, giorno);
    }
}
