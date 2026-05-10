import java.io.Serializable;

public class Data implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public int getAnno() {
        return anno;
    }
    public int getMese() {
        return mese;
    }
    public int getGiorno() {
        return giorno;
    }
}
