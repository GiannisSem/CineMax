import java.io.Serial;
import java.io.Serializable;

public class Posto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int numero;
    private char fila;
    private boolean isOccupato;

    public Posto(int numero, char fila, boolean isOccupato){
        this.numero = numero;
        this.fila = fila;
        this.isOccupato = isOccupato;
    }

    @Override
    public String toString(){
        return String.format("%d%c-%b", numero, fila, isOccupato);
    }

    public String toUtente(){
        return String.format("%d%c", numero, fila);
    }

    public boolean isOccupato(){
        return isOccupato;
    }
}
