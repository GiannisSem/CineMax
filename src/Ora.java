import java.io.Serial;
import java.io.Serializable;

public class Ora implements Serializable, Comparable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int ore;
    private int minuti;
    private int secondi;

    public Ora(int ore, int minuti, int secondi){
        this.ore = ore;
        this.minuti = minuti;
        this.secondi = secondi;
    }

    @Override
    public String toString(){
        return String.format("%02d:%02d:%02d", ore, minuti, secondi);
    }

    @Override
    public int compareTo(Object o) {
        Ora other = (Ora) o;
        // hh:mm:ss
        if (ore == other.ore){
            if(minuti == other.minuti){
                return Integer.compare(secondi, other.secondi);
            }
            return Integer.compare(minuti, other.minuti);
        }
        return Integer.compare(ore, other.ore);
    }
    
    public int getOre() {
        return ore;
    }
    public int getMinuti() {
        return minuti;
    }
    public int getSecondi() {
        return secondi;
    }
}
