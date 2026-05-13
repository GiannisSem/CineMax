import java.io.Serial;
import java.io.Serializable;

public class Data implements Serializable, Comparable {
    @Serial
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

    @Override
    public int compareTo(Object o) {
        Data other = (Data) o;
        // anno, mese, giorno
        if (anno == other.anno){
            if(mese == other.mese){
                return Integer.compare(giorno, other.giorno);
            }
            return Integer.compare(mese, other.mese);
        }
        return Integer.compare(anno, other.anno);
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
