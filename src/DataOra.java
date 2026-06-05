import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataOra implements Serializable, Comparable<DataOra> {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DataOra oggi = new DataOra(LocalDateTime.now().format(formatter));

    private final Data data;
    private final Ora ora;

    public DataOra(Data data, Ora ora){
        this.data = data;
        this.ora = ora;
    }

    public DataOra(int anno, int mese, int giorno, int ore, int minuti, int secondi) {
        this(new Data(anno, mese, giorno), new Ora(ore, minuti, secondi));
    }

    public DataOra(String dataOra){
        String[] attributi = dataOra.split(" ");
        String data = attributi[0];
        String ora = attributi[1];

        this.data = new Data(data);
        this.ora = new Ora(ora);
    }

    @Override
    public String toString(){
        return data + " " + ora;
    }

    @Override
    public int compareTo(DataOra other) {
        if (data.compareTo(other.data) == 0)
            return ora.compareTo(other.ora);
        return data.compareTo(other.data);
    }

    public Data getData(){
        return data;
    }
    public Ora getOra() {
        return ora;
    }

    public DataOra aggiungi(int minuti){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String dataStr = this.toString();
        LocalDateTime tempoAttuale = LocalDateTime.parse(dataStr, formatter);
        LocalDateTime tempoFuturo = tempoAttuale.plusMinutes(minuti);
        return new DataOra(tempoFuturo.format(formatter));
    }
}
