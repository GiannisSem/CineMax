public class Film {
    private String titolo;
    private String genere;
    private String regista;
    private int annoUscita;
    private int durata;
    private int vmeta; // Vietato ai minori di _ anni.

    public Film(String titolo, String genere, String regista, int annoUscita, int durata, int vmeta){
        this.titolo = titolo;
        this.genere = genere;
        this.regista = regista;
        this.annoUscita = annoUscita;
        this.durata = durata;
        this.vmeta = vmeta;
    }

    @Override
    public String toString(){
        return String.format("%s %s %s %d %d %d", titolo, genere, regista, annoUscita, durata, vmeta);
    }

    public String getTitolo(){
        return titolo;
    }
    public String getGenere() {
        return genere;
    }
    public String getRegista() {
        return regista;
    }
    public int annoUscita() {
        return annoUscita;
    }
    public int getDurata() {
        return durata;
    }
    public int getVmeta(){
        return vmeta;
    }
}
