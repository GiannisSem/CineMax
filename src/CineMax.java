import java.security.NoSuchAlgorithmException;
import java.time.Year;
import java.util.List;
import java.util.Scanner;


public class CineMax {
    static String passwordRuolo="vSEXpXw+nWZjsmTQD1acxTIOkwwihFbJ5tzuMpJ3LIQ=";

    /**
     * metodo main che contiene il menù dell'utente non registrato.
     * @throws NoSuchAlgorithmException  se l'algoritmo di cifratura della password non è disponibile.
     */
    public static void main() throws NoSuchAlgorithmException {
        int scelta;
        do {
            clearConsole();
            scelta = inputInt("1   Cerca proiezione\n2   Registrazione\n3   Login\n0    Esci","\nInserimento non valido. Riprova:",0,3);
            switch (scelta)
            {
                case 1:
                    cercaProiezione(new UtenteNR());
                    break;
                case 2:
                    registrazioneUtente();
                    break;
                case 3:
                    login();
                    break;
            }
        }while(scelta!=0);


    }

    /**
     * permette di creare 50 righe vuote cosi da dare l'illusione che si sia pulita la schermata
     */
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }



    /**
     * questo metodo gestisce il menù del cliente registrato.
     * @param utente è l'utente cliente che è loggato.
     * @throws NoSuchAlgorithmException  se l'algoritmo di cifratura della password non è disponibile.
     */
    public static void utenteRegistrato(Cliente utente) throws NoSuchAlgorithmException {
        int scelta;
        do {
            clearConsole();
            System.out.println("Ciao " + utente.getNome() + "!");
            scelta = inputInt("1   Cerca proiezione\n2   Visualizzare prenotazioni\n3   Profilo\n0   Logout","\nInserimento non valido. Riprova:",0,3);
            switch (scelta)
            {
                case 1:
                    cercaProiezione(utente);
                    break;
                case 2:
                    visualizzaPrenotazioneCliente(utente);
                    break;
                case 3:
                    profilo(utente);
                    break;
            }
        }while(scelta!=0);
    }


    /**
     * questo metodo gestisce il menù del proiezionista
     * @param proiezionista è utente proiezionista che è loggato.
     * @throws NoSuchAlgorithmException  se l'algoritmo di cifratura della password non è disponibile.
     */
    public static void proiezionistaRegistrato(Proiezionista proiezionista) throws NoSuchAlgorithmException {
        int scelta;
        do {
            clearConsole();
            System.out.println("Ciao " + proiezionista.getNome());
            scelta = inputInt("1   Inserire proiezione\n2   Cerca proiezione\n3   Profilo\n0   Logout","\nInserimento non valido. Riprova:",0,3);
            switch (scelta)
            {
                case 1:
                    inserisciProiezione();
                    break;
                case 2:
                    cercaProiezione(proiezionista);
                    break;
                case 3:
                    profilo(proiezionista);
                    break;
            }
        }while(scelta!=0);
    }

    /**
     * questo metodo permette l'inserimento di una nuova proiezione
     */
    public static void inserisciProiezione()
    {
        clearConsole();
        boolean rifare;
        do {

            rifare = false;

            String titolo = inputString("Inserisci titolo:", "\nInserimento non valido. Riprova:", ";");

            Film film = CineMaxManager.cercaFilm(titolo);
            if (film == null)
                film=inserireFilm(titolo);
            if(film!=null) {
                Data data;
                do {

                    int anno = inputInt("Inserisci l'anno della data:", "\nInserimento non valido. Riprova:", Year.now().getValue(), Year.now().getValue() + 5);
                    int mese = inputInt("Inserisci il mese della data:", "\nInserimento non valido. Riprova:", 1, 12);
                    int giorno = inputInt("Inserisci il giorno della data:", "\nInserimento non valido. Riprova:", 1, 31);
                    data = new Data(anno, mese, giorno);
                } while (data.compareTo(Data.oggi) < 0);
                DataOra dataOra;
                do {

                    int ore = inputInt("Inserisci l'ora:", "\nInserimento non valido. Riprova:", 0, 23);
                    int minuti = inputInt("Inserisci i minuti:", "\nInserimento non valido. Riprova:", 0, 59);
                    Ora orario = new Ora(ore, minuti, 0);
                    dataOra = new DataOra(data, orario);
                } while (dataOra.compareTo(DataOra.oggi) < 0);

                Scanner scanner = new Scanner(System.in);
                double costo;
                do {
                    System.out.println("\nInserisci il costo del biglietto:");

                    try {
                        costo = scanner.nextDouble();
                    } catch (Exception e) {
                        scanner.next();
                        costo = -1;
                    }
                    if (costo < 0)
                        System.out.println("\nValore inserito non valido. Riprova");
                } while (costo < 0);

                rifare = !CineMaxManager.inserisciProiezione(dataOra, film, costo);

                if (rifare) {
                    clearConsole();
                    System.out.println("\nInserimento della proiezione non eseguito perchè a quel orario di quella data la sala è occupata.");
                    inputInt("premere 0 per riprovare:", "\ninserimento non valido. Riprova:",0,0);
                }
            }
        }while (rifare);

    }

    /**
     * questo metodo permette di inserire un nuovo film.
     * @param titolo è il titolo del film che si vuole inserire che deve essere diverso da qualunque altro.
     * @return ritorna il film appena inserito o null nel caso non si voglia inserire il film.
     */
    public static Film inserireFilm(String titolo) {
        clearConsole();
        boolean riprova;
        do {
            riprova = false;
            String genere = inputString("Inserisci il genere del film:", "\nInserimento non valido. Riprova:", ";");
            if (genere.equals("0")) {
                return null;
            }
            String regista = inputString("Inserisci il regista del film:", "\nInserimento non valido. Riprova:", ";");
            int anno = inputInt("Inserisci l'anno del film:", "\nInserimento non valido. Riprova:", 1800, Year.now().getValue());
            int durata = inputInt("Inserisci la durata del film in minuti:", "\nInserimento non valido. Riprova:", 1, 1000);
            int eta = inputInt("Inserisci l'età minima di vision per il film:", "\nInserimento non valido. Riprova:", 0, 18);

            if (CineMaxManager.aggiungiFilm(titolo, genere, regista, anno, durata, eta)) {
                System.out.println("\nAggiunto il film");
                return CineMaxManager.cercaFilm(titolo);
            }
            else {
                clearConsole();
                System.out.println("\nFilm non valido.");
                System.out.println("\nVuoi riprovare?(S/n)");


                Scanner scanner = new Scanner(System.in);
                String risposta;
                do {
                    System.out.println("\nVuoi riprovare? (S o N)");
                    risposta = scanner.nextLine().toUpperCase();
                    if (!(risposta.equals("S") || risposta.equals("N")))
                        System.out.println("\nInserimento non valido riprova:");
                } while (!(risposta.equals("S") || risposta.equals("N")));
                if (risposta.equals("S"))
                    riprova = true;
            }
        } while (riprova);
        return null;
    }

    /**
     * questo metodo gestisce il menù dei bigliettai.
     * @param bigliettaio è l'utente bigliettaio che è loggato.
     * @throws NoSuchAlgorithmException  se l'algoritmo di cifratura della password non è disponibile.
     */
    public static void bigliettaioRegistrato(Bigliettaio bigliettaio) throws NoSuchAlgorithmException {
        int scelta;
        do {
            clearConsole();
            System.out.println("Ciao " + bigliettaio.getNome());
            scelta=inputInt("1   Visualizza prenotazioni odierne\n2   Cerca prenotazioni\n3   Profilo\n0   Logout","\nInserimento non valido. Riprova:",0,3);
            switch (scelta)
            {
                case 1:
                    if(!CineMaxManager.cercaPrenotazioniOggi().isEmpty())
                        visualizzaPrenotazioni(CineMaxManager.cercaPrenotazioniOggi());
                    else {
                        System.out.println("\nNon ci sono prenotazioni oggi.");
                        inputInt("premere 0 per ritornare al menù:", "\ninserimento non valido. Riprova:",0,0);
                    }
                    break;
                case 2:
                    cercaTipoPrenotazione();
                    break;
                case 3:
                    profilo(bigliettaio);
                    break;
            }
        }while(scelta!=0);



    }


    /**
     * questo metodo permette di cercare una proiezione per titolo, genere, intervallo date e costo biglietto.
     * @param utente è l'utente loggato.
     */
    public static void cercaProiezione(Utente utente){
        clearConsole();
        String data;
        Scanner scanner = new Scanner(System.in);
        List<Proiezione> lista=CineMaxManager.getListaProiezioni();
        do{
            System.out.println("\nVuoi cercare per il titolo? (S o N)");
            data=scanner.nextLine().toUpperCase();
            if(!(data.equals("S") || data.equals("N")))
                System.out.println("\nInserimento non valido riprova:");
        }while (!(data.equals("S") || data.equals("N")));
        if(data.equals("S"))
        {
            System.out.println("\nInserisci titolo film, anche parziale:");
            lista=CineMaxManager.cercaProiezioni_Titolo(lista,scanner.nextLine());
        }

        do{
            System.out.println("\nVuoi cercare per genere? (S o N)");
            data=scanner.nextLine().toUpperCase();
            if(!(data.equals("S") || data.equals("N")))
                System.out.println("\nInserimento non valido riprova:");
        }while (!(data.equals("S") || data.equals("N")));
        if(data.equals("S"))
        {
            System.out.println("\nInserisci genere del film:");
            lista=CineMaxManager.cercaProiezioni_Genere(lista,scanner.nextLine());
        }

        do{
            System.out.println("\nVuoi cercare per intervallo di date? (S o N)");
            data=scanner.nextLine().toUpperCase();
            if(!(data.equals("S") || data.equals("N")))
                System.out.println("\nInserimento non valido riprova:");
        }while (!(data.equals("S") || data.equals("N")));
        if(data.equals("S"))
        {
            int annoMin = inputInt("Inserisci l'anno della data minima:","\nInserimento non valido. Riprova:",Year.now().getValue(), 2126);
            int meseMin = inputInt("Inserisci il mese della data minima:","\nInserimento non valido. Riprova:",1,12);
            int giornoMin = inputInt("Inserisci il giorno della data minima:","\nInserimento non valido. Riprova:",1,31);

            int annoMax = inputInt("Inserisci l'anno della data massima:","\nInserimento non valido. Riprova:",annoMin, 2126);
            int meseMax;
            if (annoMax==annoMin)
                meseMax = inputInt("Inserisci il mese della data massima:","\nInserimento non valido. Riprova:",meseMin,12);
            else
                meseMax = inputInt("Inserisci il mese della data massima:","\nInserimento non valido. Riprova:",1,12);
            int giornoMax;
            if(annoMax==annoMin && meseMax==meseMin)
                giornoMax=inputInt("Inserisci il giorno della data massima:","\nInserimento non valido. Riprova:",giornoMin,31);
            else
                giornoMax=inputInt("Inserisci il giorno della data massima:","\nInserimento non valido. Riprova:",1,31);

            Data dataMin = new Data(annoMin,meseMin,giornoMin);
            Data dataMax = new Data(annoMax,meseMax,giornoMax);
            lista=CineMaxManager.cercaProiezioni_Date(lista,dataMin,dataMax);
        }
        else
            lista=CineMaxManager.cercaProiezioni_Date(lista,Data.oggi.toString(),"2126-01-01");

        do{
            System.out.println("\nVuoi cercare per l'intervallo di costo del biglietto? (S o N)");
            data=scanner.nextLine().toUpperCase();
            if(!(data.equals("S") || data.equals("N")))
                System.out.println("\nInserimento non valido riprova:");
        }while (!(data.equals("S") || data.equals("N")));
        if(data.equals("S"))
        {
            double costoMin;
            do{
                System.out.println("\nInserisci costo minimo del biglietto del film in euro:");
                try {
                    costoMin = scanner.nextDouble();
                }catch (Exception e) {
                    scanner.next();
                    costoMin=-1;
                }
                if(costoMin<0)
                    System.out.println("\nInserimento non valido riprova:");
            }while (costoMin<0);

            double costoMax;
            do{
                System.out.println("\nInserisci costo massimo del biglietto del film in euro:");
                try
                {
                    costoMax = scanner.nextDouble();
                }catch (Exception e)
                {
                    scanner.next();
                    costoMax=costoMin-1;
                }
                if(costoMax<costoMin)
                    System.out.println("\nInserimento non valido riprova:");
            }while (costoMax<costoMin);

            lista=CineMaxManager.cercaProiezioni_CostoBiglietto(lista,costoMin,costoMax);
        }
        if(lista.isEmpty()) {
            System.out.println("\nNon ci sono proiezioni con queste richieste.");
            inputInt("premere 0 per tornare al menù", "\ninserimento non valido. Riprova:",0,0);
        }
        else
            visualizzaProiezioni(utente,lista);

    }

    /**
     * questo metodo permette di visualizzare una lista di proiezioni e scegliere in quale entrare.
     * @param utente è l'utente loggato.
     * @param lista è la lista di proiezioni da visualizzare.
     */
    public static void visualizzaProiezioni(Utente utente, List<Proiezione> lista){
        int risposta;
        do {
            clearConsole();

            System.out.println("INDICE      DATAORA                   TITOLO                         GENERE            REGISTA                  ANNO     DURATA    ETA' MINIMA   COSTO       NUMERO POSTI LIBERI");
            int i=1;
            String space="      ";
            for (Proiezione p: lista)
            {
                System.out.println(" " + i + space + p.toInfo2());
                i++;
                if(i==10)
                    space="     ";
                if(i==100)
                    space="    ";
                if(i==1000)
                    space="   ";
                if(i==10000)
                    space="  ";
            }
            System.out.println();
            risposta = inputInt("Inserisci l'indice della proiezione che vuoi vedere o 0 per tornare al menù principale.","\nInserimento non valido riprova:",0,lista.size());

            if(risposta!=0)
            {
                Proiezione proiezione=(Proiezione) lista.toArray()[risposta-1];
                if(utente.getRuolo().equals("NONREGISTRATO"))
                    visualizzaProiezioneUtenteNR(proiezione);
                if(utente.getRuolo().equals("CLIENTE"))
                    visualizzaProiezioneCliente(proiezione,(Cliente)utente);
                if(utente.getRuolo().equals("PROIEZIONISTA"))
                    visualizzaProiezioneProiezionista(proiezione);
            }

        }while (risposta!=0);
    }


    /**
     * questo metodo permette di visualizzare la proiezione e di prenotare i posti.
     * @param proiezione è la proiezione da visualizzare.
     * @param utente è l'utente cliente loggato.
     */
    public static void visualizzaProiezioneCliente(Proiezione proiezione,Cliente utente){
        clearConsole();
        Scanner scanner = new Scanner(System.in);
        System.out.println(proiezione.toInfo1()+"\n");
        proiezione.getSala().stampa();
        if (Data.getEta(utente.getDataNascita()) < proiezione.getFilm().getVmeta())
            System.out.println("\nSei troppo piccolo per questo film, devi avere almeno " + proiezione.getFilm().getVmeta() + " anni");
        else if(proiezione.getSala().postiDisponibili()!=0) {
            int scelta = inputInt((String.format("Numero posti liberi: " + proiezione.getSala().postiDisponibili() + "\nInserisci quanti posti vuoi prenotare, 0 per tornare indietro")), "\nPosti liberi sono insufficienti:", 0, proiezione.getSala().postiDisponibili());
            if (scelta != 0) {
                char lettera;
                int numero;
                String posto;
                boolean ritest;
                String[] posti = new String[scelta];
                for (int i = 0; i < scelta; i++) {

                    do {
                        System.out.println("\nInserisci posto della " + (i+1) + "^ prenotazione: es: A9");
                        posto = scanner.nextLine().toUpperCase();
                        ritest = false;
                        try {
                            numero = Integer.parseInt(posto.substring(1));
                        } catch (Exception u) {
                            numero = 21;
                        }

                        lettera = posto.charAt(0);
                        if (numero < 1 || numero > 20 || lettera < 'A' || lettera > 'J') {
                            System.out.println("\nInserimento del posto non valido. Riprova:");
                            ritest = true;
                        } else {
                            if (!proiezione.getSala().isPostoDisponibile(lettera, numero)) {
                                System.out.println("\nIl posto scelto è già occupato. Riprova:");
                                ritest = true;
                            }
                        }

                        for (int j = 0; j < i; j++) {
                            if (posti[j].equals(lettera + Integer.toString(numero))) {
                                ritest = true;
                                System.out.println("\nIl posto scelto è già stato selezionato. Riprova:");
                                break;
                            }
                        }

                    } while (ritest);
                    posti[i] = lettera + Integer.toString(numero);
                }
                CineMaxManager.inserisciPrenotazione(utente, proiezione, posti);
                System.out.println("\nHai prenotato i posti");
            }
        } else
            System.out.println("\nNessun posto disponibile, la proiezione è piena.");
        inputInt("premere 0 per tornare indietro", "\ninserimento non valido. Riprova:",0,0);
    }


    public static void visualizzaProiezioneUtenteNR(Proiezione proiezione){
        clearConsole();
        System.out.println(proiezione.toInfo1()+"\n");
        System.out.println("Posti disponibili:   " + proiezione.getSala().postiDisponibili() + "\n");
        proiezione.getSala().stampa();
        inputInt("\nPremere 0 per tornare indietro", "\ninserimento non valido. Riprova:",0,0);

    }




    /**
     * questo metodo visualizza la proiezione e gestisce un menù per eliminazione e modifica della proiezione.
     * @param proiezione è la proiezione da visualizzare.
     */
    public static void visualizzaProiezioneProiezionista(Proiezione proiezione){
        int scelta;
        do {
            clearConsole();
            System.out.println(proiezione.toInfo1());

            scelta = inputInt("1   Elimina proiezione\n2   Modifica proiezione\n0   Torna indietro", "\nInserimento non valido. Riprova:", 0, 2);


            switch (scelta) {
                case 1:
                    eliminaProiezione(proiezione);
                    break;
                case 2:
                    modificaProiezione(proiezione);
                    break;
            };
        }while(scelta!=0);
    }

    /**
     * questo metodo permette di eliminare la proiezione se nessuno ha prenotato un posto nella proiezione.
     * @param proiezione è la proiezione che si vuole eliminare.
     */
    public static void eliminaProiezione(Proiezione proiezione) {

        if(proiezione.getSala().postiOccupati()==0) {
            if(CineMaxManager.eliminaProiezione(proiezione.getDataOra()))
                System.out.println("\nEliminazione riuscita.");
            else
                System.out.println("\nEliminazione non riuscita.");
        }
        else
            System.out.println("\nNon è modificabile perchè ci sono delle prenotazioni");
        inputInt("premere 0 per tornare indietro", "\ninserimento non valido. Riprova:",0,0);
    }

    /**
     * questo metodo permette di modificare la data e ora della proiezione.
     * @param proiezione è la proiezione alla quale si desidera cambiare data e ora.
     */
    public static void modificaProiezione(Proiezione proiezione){
        clearConsole();
        if(proiezione.getSala().postiOccupati()==0) {

            int anno = inputInt("Inserisci nuovo anno:", "\ninserimento non valido. Riprova:", Year.now().getValue(),2126);
            int mese = inputInt("Inserisci nuovo mese:", "\nInserimento non valido. Riprova:",1, 12);
            int giorno = inputInt("Inserisci nuovo giorno:","\nInserimento non valido. Riprova:",1,31);
            int ora = inputInt("Inserisci nuova ora:","\nInserimento non valido. Riprova:",0,23);
            int minuti = inputInt("Inserisci nuovi minuti:","\nInserimento non valido. Riprova:",0,59);

            if(CineMaxManager.modificaDataProiezione(proiezione.getDataOra(),new DataOra(anno,mese,giorno,ora,minuti,0)))
                System.out.println("\nCambiamento data riuscito.");
            else
                System.out.println("\nCambiamento data non riuscito.");
        }
        else
            System.out.println("\nNon è modificabile perchè ci sono delle prenotazioni");

        inputInt("premere 0 per tornare al menù", "\ninserimento non valido. Riprova:",0,0);

    }

    /**
     * questo metodo permette di leggere in input un numero intero.
     * @param richiesta è la domanda a cui bisogna rispondere con un numero intero.
     * @param errore è la risposta in caso l'input non sia corretto.
     * @param min è il valore intero minimo valido in input.
     * @param max è il valore intero massimo valido in input.
     * @return ritorna la risposta data in input.
     */
    private static int inputInt(String richiesta, String errore, int min,int max)
    {
        Scanner scanner = new Scanner(System.in);
        int risposta;
        String erroreWr;
        do {
            erroreWr=errore;
            System.out.println(richiesta);
            try {
                risposta = scanner.nextInt();
            }catch (Exception e)
            {
                scanner.next();
                risposta=min-1;
                erroreWr="Dato inserito non valido. Riprova:";
            }
            if(risposta<min || risposta>max) {
                System.out.println(erroreWr);
                System.out.println();
            }
        }while (risposta<min || risposta>max);
        System.out.println();
        return risposta;
    }

    /**
     * questo metodo permette di leggere in input una stringa.
     * @param richiesta è la domanda a cui bisogna rispondere con una stringa.
     * @param errore è la risposta in caso l'input non sia corretto.
     * @param caratteriNonValidi è una stringa che contiene tutti i simboli non validi nell'input.
     * @return ritorna la risposta data in input.
     */
    private static String inputString(String richiesta, String errore, String caratteriNonValidi)
    {
        Scanner scanner = new Scanner(System.in);
        String risposta;
        boolean valido;
        do {
            valido = true;
            System.out.println(richiesta);
            risposta = scanner.nextLine();
            for (char c : risposta.toCharArray()) {
                if (caratteriNonValidi.indexOf(c) >= 0) {
                    valido = false;
                    break;
                }
            }
            if (!valido || risposta.trim().isEmpty() || risposta.contains(caratteriNonValidi)) {
                System.out.println(errore);
                System.out.println();
            }
        }while (!valido || risposta.trim().isEmpty()|| risposta.contains(caratteriNonValidi));
        System.out.println();
        return risposta;
    }

    /**
     * questo metodo permette di loggare.
     * @throws NoSuchAlgorithmException  se l'algoritmo di cifratura della password non è disponibile.
     */
    public static void login() throws NoSuchAlgorithmException {
        clearConsole();
        boolean esci;
        do {
            clearConsole();
            esci=true;
            String username= inputString("Inserisci username:","\nInserimento non valido. Riprova:","; ");
            String password= inputString("Inserisci password:","\nInserimento non valido. Riprova:",";");
            Utente utente=LoginManager.login(username,password);
            if(utente==null)
            {
                Scanner scanner = new Scanner(System.in);
                String riprova;
                do{
                    System.out.println("Username o password sono errati. Vuoi riprovare? (S o N)");
                    riprova=scanner.nextLine().toUpperCase();
                    if(!(riprova.equals("S") || riprova.equals("N")))
                        System.out.println("\nInserimento non valido riprova:\n");
                }while (!(riprova.equals("S") || riprova.equals("N")));
                if(riprova.equals("S"))
                    esci=false;
            }
            else
            {
                if(utente.getRuolo().equals("CLIENTE"))
                    utenteRegistrato((Cliente) utente);
                else if(utente.getRuolo().equals("PROIEZIONISTA"))
                    proiezionistaRegistrato((Proiezionista) utente);
                else if(utente.getRuolo().equals("BIGLIETTAIO"))
                    bigliettaioRegistrato((Bigliettaio) utente);
            }
        }while (!esci);
    }


    /**
     * questo metodo permette la registrazione di un nuovo utente.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public static void registrazioneUtente() throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        boolean riprova;
        do {
            clearConsole();
            riprova = false;
            String nome = inputString("Nome:", "\nInserimento non valido. Riprova:", ";");
            String cognome = inputString("Cognome:", "\nInserimento non valido. Riprova:", ";");

            String username;
            do {
                username = inputString("Username:", "\nInserimento non valido. Riprova:", "; ");
                if (LoginManager.cercaUtente(username) != null)
                    System.out.println("\nUsername già usato");
            } while (LoginManager.cercaUtente(username) != null);

            String password = inputString("Password:", "\nInserimento non valido. Riprova:", ";");
            String domicilio = inputString("Luogo di domicilio", "\nInserimento non valido. Riprova:", ";");
            String data;

            do {
                System.out.println("\nVuoi inserire la data di nascita. Scrivi S o N:");
                data = scanner.nextLine().toUpperCase();
                if (!(data.equals("S") || data.equals("N")))
                    System.out.println("\nInserimento non valido riprova:");
            } while (!(data.equals("S") || data.equals("N")));
            int giorno = 0;
            int mese = 0;
            int anno = 0;
            if (data.equals("S")) {
                giorno = inputInt("Giorno di nascita:", "\nInserimento non valido. Riprova:", 1, 31);
                mese = inputInt("Mese di nascita:", "\nInserimento non valido. Riprova:", 1, 12);
                anno = inputInt("Anno di nascita:", "\ninserimento non valido. Riprova:", 1900, Year.now().getValue() - 1);
            }

            boolean ritorna;
            String ruolo;
            do {
                ritorna = false;
                do {
                    System.out.println("\nVuoi avere un ruolo diverso da cliente? Scrivi S o N:");
                    ruolo = scanner.nextLine().toUpperCase();
                    if (!(ruolo.equals("S") || ruolo.equals("N")))
                        System.out.println("\nInserimento non valido riprova:");
                } while (!(ruolo.equals("S") || ruolo.equals("N")));
                if (ruolo.equals("S")) {
                    String passwordRouloInserita;
                    do {
                        passwordRouloInserita = inputString("Inserisci la password per poter scegliere il ruolo o 0 per tornare indietro.", "\ninserimento non valido. Riprova:", ";");
                        if (!passwordRouloInserita.equals("0") && !Security.checkPassword(passwordRouloInserita, passwordRuolo))
                            System.out.println("\nPassword inserita è sbagliata. Riprova:");
                    }
                    while (!passwordRouloInserita.equals("0") && !Security.checkPassword(passwordRouloInserita, passwordRuolo));
                    if (passwordRouloInserita.equals("0"))
                        ritorna = true;
                    else {
                        int scelta;
                        scelta = inputInt("Inserisci l'indice del ruolo che vuoi:\n1   Proiezionista\n2   Bigliettaio", "\ninserimento non valido. Riprova:", 1, 2);
                        switch (scelta) {
                            case 1:
                                Proiezionista proiezionista;
                                if (data.equals("S"))
                                    proiezionista = new Proiezionista(nome, cognome, username, password, giorno, mese, anno, domicilio);
                                else
                                    proiezionista = new Proiezionista(nome, cognome, username, password, domicilio);
                                if (LoginManager.signin(proiezionista)) {
                                    proiezionistaRegistrato(proiezionista);
                                } else
                                    riprova = true;
                                break;
                            case 2:
                                Bigliettaio bigliettaio;
                                if (data.equals("S"))
                                    bigliettaio = new Bigliettaio(nome, cognome, username, password, giorno, mese, anno, domicilio);
                                else
                                    bigliettaio = new Bigliettaio(nome, cognome, username, password, domicilio);
                                if (LoginManager.signin(bigliettaio)) {
                                    bigliettaioRegistrato(bigliettaio);
                                } else
                                    riprova = true;
                                break;
                        }
                    }
                } else {
                    Cliente cliente;
                    if (data.equals("S"))
                        cliente = new Cliente(nome, cognome, username, password, giorno, mese, anno, domicilio);
                    else
                        cliente = new Cliente(nome, cognome, username, password, domicilio);
                    if (!LoginManager.signin(cliente)) {
                        riprova = true;
                    } else
                        utenteRegistrato(cliente);
                }
            }
            while(ritorna);
            if(riprova) {
                System.out.println("\nRegistrazione non valida.");
                inputInt("premere 0 per riprovare:", "\ninserimento non valido. Riprova:", 0, 0);
            }
        }while (riprova);
    }


    /**
     * questo metodo visualizza i dati del profilo dell'utente e gestisce il menù per la modifica della password, dell'username, del domicilio e della data di nascita.
     * @param utente è l'utente loggato.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public static void profilo(Utente utente) throws NoSuchAlgorithmException {

        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            clearConsole();
            System.out.println(utente.toInfo());
            scelta = inputInt("\n1   Modifica password\n2   Modifica username\n3   Modifica domicilio\n4   Modifica data di nascita\n0   Torna al menù principale","\nInserimento non valido. Riprova:",0,4);
            switch (scelta) {
                case 1:
                    modificaPassword(utente);
                    break;
                case 2:
                    modificaUsername(utente);
                    break;
                case 3:
                    modificaDomicilio(utente);
                    break;
                case 4:
                    modificaDataNascita(utente);
                    break;
            }
        }while(scelta!=0);
    }

    /**
     * questo metodo permette di modificare la data di nascita dell'utente.
     * @param utente è l'utente a cui si vuole cambiare la data di nascita.
     */
    static public void modificaDataNascita(Utente utente){
        clearConsole();
        int giorno = inputInt("Inserisci nuovo giorno di nascita:","\nInserimento non valido. Riprova:",1,31);
        int mese = inputInt("Inserisci nuovo mese di nascita:", "\nInserimento non valido. Riprova:",1, 12);
        int anno = inputInt("Inserisci nuovo anno di nascita:", "\ninserimento non valido. Riprova:", 1900, Year.now().getValue()-1);

        Data data=new Data(anno,giorno,mese);
        utente.setDataNascita(data);
    }

    /**
     * questo metodo permette di modificare la password dell'utente.
     * @param utente è l'utente a cui si vuole cambiare la password.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    static public void modificaPassword(Utente utente) throws NoSuchAlgorithmException {
        clearConsole();
        String password = inputString("Inserisci nuova password o 0 per tornare indietro","\nInserimento non valido. Riprova:",";");
        if(!password.equals("0"))
            utente.setHashPassword(password);
    }

    /**
     * questo metodo permette di modificare il domicilio dell'utente.
     * @param utente è l'utente a cui si vuole cambiare il domicilio.
     */
    static public void modificaDomicilio(Utente utente)
    {
        clearConsole();
        String domicilio = inputString("Inserisci nuovo domicilio o 0 per tornare indietro","\nInserimento non valido. Riprova:",";");
        if(!domicilio.equals("0"))
            utente.setDomicilio(domicilio);
    }


    /**
     * questo metodo permette di modificare l'username dell'utente.
     * @param utente è l'utente a cui si vuole cambiare l'username.
     */
    static public void modificaUsername(Utente utente){

        while (true){
            clearConsole();
            String username = inputString("Inserisci nuovo username o 0 per tornare indietro:","\nInserimento non valido. Riprova:","; ");
            if(username.equals("0") || LoginManager.setUsername(utente, username))
                break;
            System.out.println("Username già usato");
            inputInt("premere 0 per riprovare:", "\ninserimento non valido. Riprova:", 0, 0);
        }
    }

    /**
     * questo metodo permette di visualizzare le prenotazioni attive di un utente cliente
     * @param utente è l'utente cliente loggato.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public static void visualizzaPrenotazioneCliente(Cliente utente) throws NoSuchAlgorithmException {
        int scelta=0;
        do {
            clearConsole();

            List<Prenotazione> prenotazioni=CineMaxManager.getPrenotazioniClienteAttive(utente);
            if(!prenotazioni.isEmpty()) {
                System.out.println("INDICE  CODICE          DATAORA             TITOLO                             GENERE            REGISTA                  ANNO      DURATA   ETA' MINIMA   COSTO TOTALE   POSTI PRESI");

                int i = 1;
                String space="      ";
                for (Prenotazione p : prenotazioni) {
                    System.out.println("   " + i + space + p.toInfoCliente());
                    i++;
                    if(i==10)
                        space="     ";
                    if(i==100)
                        space="    ";
                    if(i==1000)
                        space="   ";
                    if(i==10000)
                        space="  ";
                }
                System.out.println();
                scelta = inputInt("Inserisci l'indice di quale vuoi modificare/eliminare o 0 per tornare al menù principale.", "\nInserimento non valido riprova:", 0, prenotazioni.size());
                if (scelta != 0) {
                    Prenotazione p = (Prenotazione) prenotazioni.toArray()[scelta - 1];
                    modificaEliminaPrenotazione(p, utente);
                }
            }
            else
            {
                System.out.println("Nessuna prenotazione.");
                scelta = inputInt("premere 0 per tornare al menù:", "\nInserimento non valido. Riprova:", 0, 0);
            }

        }while(scelta!=0);
    }

    /**
     * questo metodo visualizza una prenotazione e permette di eliminarla e di modificarla.
     * @param prenotazione è la prenotazione che si vuole eliminare o modificare.
     * @param utente è l'utente loggato che ha la prenotazione.
     * @throws NoSuchAlgorithmException se l'algoritmo di cifratura della password non è disponibile.
     */
    public static void modificaEliminaPrenotazione(Prenotazione prenotazione,Cliente utente) throws NoSuchAlgorithmException {
        int risposta;
        do {
            clearConsole();
            System.out.println("Indice Codice prenotazione data   ora  titolo   genere    regista    anno  durata  eta minima  posti preso");
            System.out.println(prenotazione.toInfoCliente());
            risposta = inputInt("1   Elimina\n2   Modifica\n0   Torna al menù principale","\nInserimento non valido. Riprova:",0,2);
            switch (risposta) {
                case 1:
                    if(CineMaxManager.eliminaPrenotazione(prenotazione.getCodicePrenotazione()))
                        System.out.println("\nEliminata la prenotazione");
                    else
                        System.out.println("\nC'è un problema. Eliminazione impossibile.");
                    inputInt("premere 0 per visualizzare il menù:", "\ninserimento non valido. Riprova:", 0, 0);
                    break;
                case 2:
                    int c = CineMaxManager.getPrenotazioniCliente(utente).size();
                    List<Proiezione> lista=CineMaxManager.getListaProiezioni();
                    lista=CineMaxManager.cercaProiezioni_Titolo(lista,prenotazione.getProiezione().getFilm().getTitolo());
                    Data dataM= new Data(2126,1,1);
                    lista=CineMaxManager.cercaProiezioni_Date(lista,Data.oggi.toString(),dataM.toString());
                    if(lista.isEmpty()) {
                        System.out.println("\nNon ci sono proiezioni con queste richieste.");
                        inputInt("premere 0 per tornare al menù", "\ninserimento non valido. Riprova:",0,0);
                    }
                    else {
                        visualizzaProiezioni(utente, lista);
                        if (c != CineMaxManager.getPrenotazioniCliente(utente).size())
                            CineMaxManager.eliminaPrenotazione(prenotazione.getCodicePrenotazione());
                    }
                    break;
            }
        }while(risposta==2);
    }

    /**
     * questo metodo gestisce il menù per la scelta di come cerecare la prenotazione, per codice, per nome e cognome, per titolo e per interevallo date.
     */
    public static void cercaTipoPrenotazione()
    {
        int risposta;
        do {
            clearConsole();
            risposta=inputInt("1   Cerca per codice\n2   Cerca per nome e cognome\n3   cerca per titolo film\n4   cerca per intervallo date\n0   torna al menù principale","\nInserimento non valido. Riprova:",0,4);

            switch (risposta)
            {
                case 1:
                    cercaPerCodice();
                    break;
                case 2:
                    cercaPerNominativo();
                    break;
                case 3:
                    cercaPerTitolo();
                    break;
                case 4:
                    cercaPerDate();
                    break;
            }
        }while (risposta!=0);
    }

    /**
     * questo metodo permette di cercare la prenotazione per il codice.
     */
    public static void cercaPerCodice()
    {
        int risposta;
        do {
            clearConsole();
            risposta=inputInt("Inserisci il codice della prenotazione, 0 per tornare indietro.","\nInserimento non valido. Riprova:",0,Integer.MAX_VALUE);
            if(risposta!=0) {
                Prenotazione p = CineMaxManager.cercaPrenotazione(risposta);
                if (p == null) {
                    System.out.println("\nIl codice inserito non esiste.");
                    inputInt("premere 0 per riprovare:", "\ninserimento non valido. Riprova:", 0, 0);
                } else
                    visualizzaPrenotazione(p);
            }
        }while (risposta!=0);
    }


    /**
     * questo metodo permette di cercare le prenotazioni per nominativo.
     */
    public static void cercaPerNominativo()
    {
        String nome, cognome;
        boolean continua;
        do {
            clearConsole();
            continua=false;
            nome=inputString("Inserisci nome, 0 per tornare indietro.","\nInserimento non valido. Riprova:",";");

            if(!nome.equals("0"))
            {
                cognome=inputString("Inserisci cognome, 0 per tornare indietro.","\nInserimento non valido. Riprova:",";");
                if(!cognome.equals("0")) {
                    List<Utente> omonimi = LoginManager.cercaUtente(nome, cognome);
                    if(!omonimi.isEmpty())
                    {
                        if(omonimi.size()==1) {
                            List<Prenotazione> l = CineMaxManager.getPrenotazioniClienteAttive((Cliente) omonimi.getFirst());
                            if (!l.isEmpty()) {
                                if (l.size() == 1)
                                    visualizzaPrenotazione(l.getFirst());
                                else
                                    visualizzaPrenotazioni(l);
                            } else {
                                System.out.println("\nNon ci sono prenotazioni per questo cliente. Riprova:");
                                continua = true;
                            }
                        }
                        else
                        {
                            listaUtenti(omonimi);
                        }
                    }
                    else
                    {
                        System.out.println("\nNon ci sono clienti con questo nome e cognome. Riprova:");
                        continua = true;
                    }
                }
            }
            if(continua)
                inputInt("premere 0 per riprovare:", "\ninserimento non valido. Riprova:", 0, 0);
        }while (continua);
    }

    /**
     * questo metodo permette di visualizzare la lista utenti e di scegliero quello per visualizzarne le prenotazioni
     * @param utenti è la lista utenti che viene visualizzata.
     */
    public static void listaUtenti(List<Utente> utenti)
    {
        int risposta;
        StringBuilder s= new StringBuilder();
        int c=0;
        for (Utente v: utenti)
        {
            c++;
            s.append(c).append(v.toInfo1()).append("\n");
        }
        s.append("\nInserisci 0 per tornare indietro.");
        do {
            clearConsole();
            risposta=inputInt(s.toString(),"\nInserimento non valido. Riprova:",0,c);
            if(risposta!=0)
            {
                List<Prenotazione> l = CineMaxManager.getPrenotazioniClienteAttive((Cliente) utenti.get(risposta-1));
                if (!l.isEmpty()) {
                    if (l.size() == 1)
                        visualizzaPrenotazione(l.getFirst());
                    else
                        visualizzaPrenotazioni(l);
                } else {
                    System.out.println("\nNon ci sono prenotazioni per questo cliente. Riprova:");
                    inputInt("premere 0 per riprovare:", "\ninserimento non valido. Riprova:", 0, 0);
                }

            }
        }while (risposta!=0);
    }


    /**
     * questo metodo permette di cercare le prenotazioni per titolo.
     */
    public static void cercaPerTitolo()
    {
        boolean continua;
        do {
            clearConsole();
            continua=false;
            String titolo=inputString("Inserisci il titolo del film, 0 per tornare indietro.","\nInserimento non valido. Riprova:",";");

            if(!titolo.equals("0"))
            {
                List<Prenotazione> l=CineMaxManager.cercaPrenotazioni(titolo);
                if(!l.isEmpty()) {
                    if (l.size() == 1)
                        visualizzaPrenotazione(l.getFirst());
                    else
                        visualizzaPrenotazioni(l);
                }
                else {
                    System.out.println("\nNon ci sono prenotazioni per film con questo titolo. Riprova:");
                    inputInt("premere 0 per riprovare:", "\ninserimento non valido. Riprova:", 0, 0);
                    continua=true;
                }
            }

        }while (continua);
    }

    /**
     * questo metodo permette di cercare le prenotazionì per intervallo date.
     */
    public static void cercaPerDate()
    {
        clearConsole();
        int annoMin = inputInt("Inserisci l'anno della data minima:","\nInserimento non valido. Riprova:",1900, Year.now().getValue());
        int meseMin = inputInt("Inserisci il mese della data minima:","\nInserimento non valido. Riprova:",1,12);
        int giornoMin = inputInt("Inserisci il giorno della data minima:","\nInserimento non valido. Riprova:",1,31);
        int annoMax = inputInt("Inserisci l'anno della data massima:","\nInserimento non valido. Riprova:",annoMin, Year.now().getValue());
        int meseMax;
        if (annoMax==annoMin)
            meseMax = inputInt("Inserisci il mese della data massima:","\nInserimento non valido. Riprova:",meseMin,12);
        else
            meseMax = inputInt("Inserisci il mese della data massima:","\nInserimento non valido. Riprova:",1,12);
        int giornoMax;
        if(annoMax==annoMin && meseMax==meseMin)
            giornoMax=inputInt("Inserisci il giorno della data massima:","\nInserimento non valido. Riprova:",giornoMin,31);
        else
            giornoMax=inputInt("Inserisci il giorno della data massima:","\nInserimento non valido. Riprova:",1,31);

        Data dataMin = new Data(annoMin,meseMin,giornoMin);
        Data dataMax = new Data(annoMax,meseMax,giornoMax);

        List<Prenotazione> l=CineMaxManager.cercaPrenotazioni(dataMin,dataMax);
        if(!l.isEmpty())
        {
            if(l.size()==1)
                visualizzaPrenotazione(l.getFirst());
            else
                visualizzaPrenotazioni(l);
        }
        else {
            System.out.println("\nNon ci sono prenotazioni in quelle date");
            inputInt("premere 0 per tornare al menù:", "\ninserimento non valido. Riprova:", 0, 0);
        }

    }

    /**
     * questo metodo visualizza le prenotazioni e permette di sceglierne una.
     * @param prenotazioni è la lista di prenotazioni da visualizzare.
     */
    public static void visualizzaPrenotazioni(List<Prenotazione> prenotazioni)
    {
        int scelta;
        do {
            clearConsole();
            System.out.println("Indice Codice prenotazione data   ora  titolo   genere    regista    anno  durata  eta minima  posti preso");
            int i = 1;
            for (Prenotazione p : prenotazioni) {
                System.out.println(" " + i + "  " + p.toInfoCliente());
                i++;
            }
            System.out.println();
            scelta = inputInt("Inserisci indice della prenotazione che vuoi vedere, 0 per tornare indietro.", "\nInserimento non valido. Riprova:", 0, prenotazioni.size());

            if (scelta != 0)
                visualizzaPrenotazione((Prenotazione) prenotazioni.toArray()[scelta - 1]);
        }while (scelta!=0);
    }

    /**
     * questo metodo visualizza la prenotazione.
     * @param p è la prenotazione da visualizzare.
     */
    public static void visualizzaPrenotazione(Prenotazione p)
    {
        clearConsole();
        System.out.println("Codice prenotazione    data  ora   titolo   genere   regista    anno   durata   età minima   costo   posti presi");
        System.out.println(p.toInfoCliente());
        inputInt("\nInserisci 0 per tornare indietro.","\nDevi inserire 0 se vuoi tornare indietro.",0,0);
    }
}




















