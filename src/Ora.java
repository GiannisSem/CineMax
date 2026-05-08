public class Ora {
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
        return String.format("%d:%d:%d", ore, minuti, secondi);
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
