import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.time.Year;
import java.util.List;
import java.util.Scanner;

public  class UtentiMenu {


    public static void utenteNonRegistrato() throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            scelta = inputInt("1   Cerca proiezione\n2   Registrazione\n3   Login\n0    Esci","Inserimento non valido. Riprova:",0,3);
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


    public static void utenteRegistrato(Cliente utente) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            scelta = inputInt("1   Cerca proiezione\n2   Visualizzare prenotazioni\n3   Profilo\n0   Logout","Inserimento non valido. Riprova:",0,3);
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






    public static void proiezionistaRegistrato(Proiezionista proiezionista) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            scelta = inputInt("1   Inserire proiezione\n2   Cerca proiezione\n3   Profilo\n0   Logout","Inserimento non valido. Riprova:",0,3);
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

    public static void inserisciProiezione()
    {
        boolean rifare;
        do {

            rifare = false;

            String titolo = inputString("Inserisci titolo:", "Inserimento non valido. Riprova:", ";");

            Film film = CineMaxManager.cercaFilm(titolo);
            if (film == null)
                film=inserireFilm(titolo);
            if(film!=null) {
                Data data;
                do {

                    int anno = inputInt("Inserisci l'anno della data:", "Inserimento non valido. Riprova:", Year.now().getValue(), Year.now().getValue() + 5);
                    int mese = inputInt("Inserisci il mese della data:", "Inserimento non valido. Riprova:", 1, 12);
                    int giorno = inputInt("Inserisci il giorno della data:", "Inserimento non valido. Riprova:", 1, 31);
                    data = new Data(anno, mese, giorno);
                } while (data.compareTo(Data.oggi) < 0);
                DataOra dataOra;
                do {

                    int ore = inputInt("Inserisci l'ora:", "Inserimento non valido. Riprova:", 0, 23);
                    int minuti = inputInt("Inserisci i minuti:", "Inserimento non valido. Riprova:", 0, 59);
                    Ora orario = new Ora(ore, minuti, 0);
                    dataOra = new DataOra(data, orario);
                } while (dataOra.compareTo(DataOra.oggi) < 0);

                Scanner scanner = new Scanner(System.in);
                double costo;
                do {
                    System.out.println("Inserisci il costo del biglietto:");

                    try {
                        costo = scanner.nextDouble();
                    } catch (Exception e) {
                        scanner.next();
                        costo = -1;
                    }
                    if (costo < 0)
                        System.out.println("Valore inserito non valido. Riprova");
                } while (costo < 0);

                rifare = !CineMaxManager.inserisciProiezione(dataOra, film, costo);

                if (rifare)
                    System.out.println("Inserimento della proiezione non valido");
            }
        }while (rifare);

    }


    public static Film inserireFilm(String titolo) {
        boolean riprova;
        do {
            riprova = false;
            String genere = inputString("Inserisci il genere del film:", "Inserimento non valido. Riprova:", ";");
            if (genere.equals("0")) {
                return null;
            }
            String regista = inputString("Inserisci il regista del film:o", "Inserimento non valido. Riprova:", ";");
            int anno = inputInt("Inserisci l'anno del film:", "Inserimento non valido. Riprova:", 1800, Year.now().getValue());
            int durata = inputInt("Inserisci la durata del film in minuti:", "Inserimento non valido. Riprova:", 1, 1000);
            int eta = inputInt("Inserisci l'età minima di vision per il film:", "Inserimento non valido. Riprova:", 0, 18);

            if (CineMaxManager.aggiungiFilm(titolo, genere, regista, anno, durata, eta)) {
                System.out.println("Aggiunto il film");
                return CineMaxManager.cercaFilm(titolo);
            }
            else {
                System.out.println("Film non valido.");
                System.out.println("Vuoi riprovare?(S/n)");


                Scanner scanner = new Scanner(System.in);
                String risposta;
                do {
                    System.out.println("Vuoi riprovare? (S o N)");
                    risposta = scanner.nextLine();
                    if (!(risposta.equals("S") || risposta.equals("N")))
                        System.out.println("Inserimento non valido riprova:");
                } while (!(risposta.equals("S") || risposta.equals("N")));
                if (risposta.equals("S"))
                    riprova = true;
            }
        } while (riprova);
        return null;
    }


    public static void bigliettaioRegistrato(Bigliettaio bigliettaio) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            scelta=inputInt("1   Visualizza prenotazioni odierne\n2   Cerca proiezione\n3   Profilo\n0   Logout","Inserimento non valido. Riprova:",0,3);
            switch (scelta)
            {
                case 1:
                    if(!CineMaxManager.cercaPrenotazioniOggi().isEmpty())
                        visualizzaPrenotazioni(CineMaxManager.cercaPrenotazioniOggi());
                    else
                        System.out.println("Non ci sono prenotazioni oggi.");
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



    public static void cercaProiezione(Utente utente) throws NoSuchAlgorithmException {
        String data;
        Scanner scanner = new Scanner(System.in);
        List<Proiezione> lista=CineMaxManager.getListaProiezioni();
        do{
            System.out.println("Vuoi cercare per il titolo? (S o N)");
            data=scanner.nextLine();
            if(!(data.equals("S") || data.equals("N")))
                System.out.println("Inserimento non valido riprova:");
        }while (!(data.equals("S") || data.equals("N")));
        if(data.equals("S"))
        {
            System.out.println("Inserisci titolo film, anche parziale:");
            lista=CineMaxManager.cercaProiezioni_Titolo(lista,scanner.nextLine());
        }

        do{
            System.out.println("Vuoi cercare per genere? (S o N)");
            data=scanner.nextLine();
            if(!(data.equals("S") || data.equals("N")))
                System.out.println("Inserimento non valido riprova:");
        }while (!(data.equals("S") || data.equals("N")));
        if(data.equals("S"))
        {
            System.out.println("Inserisci genere del film:");
            lista=CineMaxManager.cercaProiezioni_Genere(lista,scanner.nextLine());
        }

        do{
            System.out.println("Vuoi cercare per intervallo di date? (S o N)");
            data=scanner.nextLine();
            if(!(data.equals("S") || data.equals("N")))
                System.out.println("Inserimento non valido riprova:");
        }while (!(data.equals("S") || data.equals("N")));
        if(data.equals("S"))
        {
            int annoMin = inputInt("Inserisci l'anno della data minima:","Inserimento non valido. Riprova:",1900, Year.now().getValue());
            int meseMin = inputInt("Inserisci il mese della data minima:","Inserimento non valido. Riprova:",1,12);
            int giornoMin = inputInt("Inserisci il giorno della data minima:","Inserimento non valido. Riprova:",1,31);

            int annoMax = inputInt("Inserisci l'anno della data massima:","Inserimento non valido. Riprova:",annoMin, Year.now().getValue());
            int meseMax;
            if (annoMax==annoMin)
                meseMax = inputInt("Inserisci il mese della data massima:","Inserimento non valido. Riprova:",meseMin,12);
            else
                meseMax = inputInt("Inserisci il mese della data massima:","Inserimento non valido. Riprova:",1,12);
            int giornoMax;
            if(annoMax==annoMin && meseMax==meseMin)
                giornoMax=inputInt("Inserisci il giorno della data massima:","Inserimento non valido. Riprova:",giornoMin,31);
            else
                giornoMax=inputInt("Inserisci il giorno della data massima:","Inserimento non valido. Riprova:",1,31);

            Data dataMin = new Data(annoMin,meseMin,giornoMin);
            Data dataMax = new Data(annoMax,meseMax,giornoMax);
            lista=CineMaxManager.cercaProiezioni_Date(lista,dataMin,dataMax);
        }

        do{
            System.out.println("Vuoi cercare per il costo del biglietto? (S o N)");
            data=scanner.nextLine();
            if(!(data.equals("S") || data.equals("N")))
                System.out.println("Inserimento non valido riprova:");
        }while (!(data.equals("S") || data.equals("N")));
        if(data.equals("S"))
        {
            double costoMin;
            do{
                System.out.println("Inserisci costo minimo del biglietto del film:");
                try {
                    costoMin = scanner.nextDouble();
                }catch (Exception e) {
                    scanner.next();
                    costoMin=-1;
                }
                if(costoMin<0)
                    System.out.println("Inserimento non valido riprova:");
            }while (costoMin<0);

            double costoMax;
            do{
                System.out.println("Inserisci costo massimo del biglietto del film:");
                try
                {
                    costoMax = scanner.nextDouble();
                }catch (Exception e)
                {
                    scanner.next();
                    costoMax=costoMin-1;
                }
                if(costoMax<costoMin)
                    System.out.println("Inserimento non valido riprova:");
            }while (costoMax<costoMin);

            lista=CineMaxManager.cercaProiezioni_CostoBiglietto(lista,costoMin,costoMax);
        }
        visualizzaProiezioni(utente,lista);

    }

    public static void visualizzaProiezioni(Utente utente, List<Proiezione> lista) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int risposta;
        do {


            System.out.println("Indice   DataOra          titolo    genere     regista     anno     durata     età minima     costo    nPostiLiberi");
            int i=1;

            for (Proiezione p: lista)
            {
                System.out.println(" " + i + "   " + p.toInfo());
                i++;
            }
            System.out.println("");
            risposta = inputInt("Inserisci l'indice della proiezione che vuoi vedere o 0 per tornare al menù principale.","Inserimento non valido riprova:",0,lista.size());

            if(risposta!=0)
            {
                Proiezione proiezione=(Proiezione) lista.toArray()[risposta-1];
                if(utente.getRuolo().equals("CLIENTE") || utente.getRuolo().equals("NONREGISTRATO"))
                    visualizzaProiezioneCliente(proiezione,(Cliente)utente);
                if(utente.getRuolo().equals("PROIEZIONISTA"))
                    visualizzaProiezioneProiezionista((Proiezionista) utente,proiezione);
            }
        }while (risposta!=0);
    }

    public static void visualizzaProiezioneCliente(Proiezione proiezione,Cliente utente) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(proiezione.toInfo1());
        proiezione.getSala().stampa();

        int scelta =inputInt((String.format("Numero posti liberi: " + proiezione.getSala().postiDisponibili())),"Posti liberi sono insufficienti:",1,proiezione.getSala().postiDisponibili());

        char lettera;
        int numero;
        String posto;
        boolean ritest;
        String[] posti=new String[scelta];
        for (int i=0; i<scelta;i++) {

            do {
                System.out.println("Inserisci posto della " + i + "^ prenotazione: es: A9");
                posto=scanner.nextLine();
                ritest=false;
                try {
                    numero=Integer.parseInt(posto.substring(1,posto.length()));
                }catch (Exception u)
                {
                    numero=21;
                }

                lettera=posto.charAt(0);
                if(numero<1 || numero>20 || lettera<'A' || lettera>'J')
                {
                    System.out.println("Inserimento del posto non valido. Riprova");
                    ritest=true;
                }
                else
                {
                    if(proiezione.getSala().isPostoDisponibile(lettera,numero)) {
                        System.out.println("Il posto scelto è già occupato. Riprova:");
                        ritest=true;
                    }
                }

            }while(ritest);
            posti[i]=lettera+Integer.toString(numero);
        }
        CineMaxManager.inserisciPrenotazione(utente,proiezione,posti);
        System.out.println("Hai prenotato i posti");
    }


    public static void visualizzaProiezioneProiezionista(Proiezionista utente,Proiezione proiezione) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            System.out.println(proiezione.toInfo1());

            scelta = inputInt("1   Elimina proiezione\n2   Modifica proiezione\n0   Torna indietro", "Inserimento non valido. Riprova:", 0, 2);


            switch (scelta) {
                case 1:
                    eliminaProiezione(utente, proiezione);
                    break;
                case 2:
                    modificaProiezione(utente, proiezione);
                    break;
            };
        }while(scelta!=0);
    }

    public static void eliminaProiezione(Proiezionista utente,Proiezione proiezione) throws NoSuchAlgorithmException {


        if(proiezione.getSala().postiOccupati()==0) {
            if(CineMaxManager.eliminaProiezione(proiezione.getDataOra()))
                System.out.println("Eliminazione riuscita.");
            else
                System.out.println("Eliminazione non riuscita.");
        }
        else
            System.out.println("Non è modificabile perchè ci sono delle prenotazioni");
    }

    public static void modificaProiezione(Proiezionista utente,Proiezione proiezione) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        if(proiezione.getSala().postiOccupati()==0) {

            int anno = inputInt("Inserisci nuovo anno:", "inserimento non valido. Riprova:", 1900, Year.now().getValue());
            int mese = inputInt("Inserisci nuovo mese:", "Inserimento non valido. Riprova:",1, 12);
            int giorno = inputInt("Inserisci nuovo giorno:","Inserimento non valido. Riprova:",1,31);
            int ora = inputInt("Inserisci nuova ora:","Inserimento non valido. Riprova:",0,23);
            int minuti = inputInt("Inserisci nuovi minuti:","Inserimento non valido. Riprova:",0,59);

            if(CineMaxManager.modificaDataProiezione(proiezione.getDataOra(),new DataOra(anno,mese,giorno,ora,minuti,0)))
                System.out.println("Cambiamento data riuscito.");
            else
                System.out.println("Cambiamento data non riuscito.");
        }
        else
            System.out.println("Non è modificabile perchè ci sono delle prenotazioni");

    }


    private static int inputInt(String richiesta, String errore, int min,int max)
    {
        Scanner scanner = new Scanner(System.in);
        int risposta;
        do {
            System.out.println(richiesta);
            try {
                risposta = scanner.nextInt();
            }catch (Exception e)
            {
                scanner.next();
                risposta=min-1;
            }
            if(risposta<min || risposta>max) {
                System.out.println(errore);
                System.out.println();
            }
        }while (risposta<min || risposta>max);
        return risposta;
    }

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
        return risposta;
    }


    public static void login() throws NoSuchAlgorithmException {
        boolean esci;
        do {
            esci=true;
            String username= inputString("Inserisci username:","Inserimento non valido. Riprova:","; ");
            String password= inputString("Inserisci password:","Inserimento non valido. Riprova:",";");
            Utente utente=LoginManager.login(username,password);
            if(utente==null)
            {
                Scanner scanner = new Scanner(System.in);
                String riprova;
                do{
                    System.out.println("Dati non corretti. Vuoi riprovare? (S o N)");
                    riprova=scanner.nextLine();
                    if(!(riprova.equals("S") || riprova.equals("N")))
                        System.out.println("Inserimento non valido riprova:");
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



    public static void registrazioneUtente() throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        boolean riprova;
        do {

            riprova = false;
            String nome = inputString("Nome:", "Inserimento non valido. Riprova:", ";");
            String cognome = inputString("Cognome:","Inserimento non valido. Riprova:",";");

            String username;
            do {
                username = inputString("Username:","Inserimento non valido. Riprova:","; ");
                if(LoginManager.cercaUtente(username)!=null)
                    System.out.println("Username già usato");
            }while (LoginManager.cercaUtente(username)!=null);

            String password = inputString("Password:", "Inserimento non valido. Riprova:",";");
            String domicilio = inputString("Luogo di domicilio","Inserimento non valido. Riprova:",";");
            String data;

            do {
                System.out.println("Vuoi inserire la data di nascita. Scrivi S o N:");
                data = scanner.nextLine();
                if (!(data.equals("S") || data.equals("N")))
                    System.out.println("Inserimento non valido riprova:");
            } while (!(data.equals("S") || data.equals("N")));
            int giorno = 0;
            int mese = 0;
            int anno = 0;
            if (data.equals("S")) {
                giorno = inputInt("Giorno di nascita:","Inserimento non valido. Riprova:",1,31);
                mese = inputInt("Mese di nascita:", "Inserimento non valido. Riprova:",1, 12);
                anno = inputInt("Anno di nascita:", "inserimento non valido. Riprova:", 1900, Year.now().getValue()-1);
            }

            int scelta;
            do {
                for (int i = 0; i < Ruolo.values().length - 1; i++) {
                    System.out.println(i + 1 + "   " + Ruolo.values()[i]);
                }
                System.out.println("0   Torna alla home");
                scelta = scanner.nextInt();

                if (!(scelta < Ruolo.values().length - 1 && scelta >= 0))
                    System.out.println("%ninserimento non valido riprova:%n");
            } while (!(scelta < Ruolo.values().length - 1 && scelta >= 0));
            if (scelta != 0)
            {
                //da mettere o solo cliente o una key se vuoi crearlo proiezionista o bigliettaio
                switch (Ruolo.values()[scelta-1]) {
                    case CLIENTE:
                        Cliente cliente;
                        if(data.equals("S"))
                            cliente = new Cliente(nome, cognome, username, password, giorno, mese, anno, domicilio);
                        else
                            cliente = new Cliente(nome, cognome, username, password, domicilio);
                        if (!LoginManager.signin(cliente)) {
                            riprova = true;
                        } else
                            utenteRegistrato(cliente);
                        break;
                    case PROIEZIONISTA:
                        Proiezionista proiezionista;
                        if(data.equals("S"))
                            proiezionista = new Proiezionista(nome, cognome, username, password, giorno, mese, anno, domicilio);
                        else
                            proiezionista = new Proiezionista(nome, cognome, username, password, domicilio);
                        if (LoginManager.signin(proiezionista)) {
                            proiezionistaRegistrato(proiezionista);
                        } else
                            riprova = true;
                        break;
                    case BIGLIETTAIO:
                        Bigliettaio bigliettaio;
                        if(data.equals("S"))
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
            if(riprova)
                System.out.println("Registrazione non valida. Riprova.");
        }while (riprova);
    }



    public static void profilo(Utente utente) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            System.out.println(utente.toInfo());
            scelta = inputInt("1   Modifica password\n2   Modifica username\n3   Modifica domicilio\n4   Modifica data di nascita\n0   Torna al menù principale","Inserimento non valido. Riprova:",0,4);
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

    static public void modificaDataNascita(Utente utente) throws NoSuchAlgorithmException {

        int giorno = inputInt("Inserisci nuovo giorno di nascita:","Inserimento non valido. Riprova:",1,31);
        int mese = inputInt("Inserisci nuovo mese di nascita:", "Inserimento non valido. Riprova:",1, 12);
        int anno = inputInt("Inserisci nuovo anno di nascita:", "inserimento non valido. Riprova:", 1900, Year.now().getValue()-1);

        Data data=new Data(anno,giorno,mese);
        utente.setDataNascita(data);
    }


    static public void modificaPassword(Utente utente) throws NoSuchAlgorithmException {
        String password = inputString("Inserisci nuova password o 0 per tornare indietro","Inserimento non valido. Riprova:",";");
        if(!password.equals("0"))
            utente.setHashPassword(password);
    }

    static public void modificaDomicilio(Utente utente)
    {
        String domicilio = inputString("Inserisci nuovo domicilio o 0 per tornare indietro","Inserimento non valido. Riprova:",";");
        if(!domicilio.equals("0"))
           utente.setDomicilio(domicilio);
    }

    //problemi perchè il setUsername non cambia la posizione dell'utente nella lista quindi poi la ricerca non funziona
    static public void modificaUsername(Utente utente) throws NoSuchAlgorithmException {
        while (true){
            String username = inputString("Inserisci nuovo username o 0 per tornare indietro:","Inserimento non valido. Riprova:","; ");
            if(username.equals("0") || LoginManager.setUsername(utente, username))
                break;

            System.out.println("Username già usato");
        }
    }


    public static void visualizzaPrenotazioneCliente(Cliente utente) throws NoSuchAlgorithmException {
        int scelta;
        do {
            System.out.println("Indice Codice prenotazione data   ora  titolo   genere    regista    anno  durata  eta minima  posti preso");

            List<Prenotazione> prenotazioni=CineMaxManager.getPrenotazioniClienteAttive(utente);
            int i=1;
            for (Prenotazione p: prenotazioni)
            {
                System.out.println(" "+ i+ "  "+p.toInfoCliente());
                i++;
            }
            System.out.println("");
            scelta = inputInt("Inserisci l'indice di quale vuoi modificare/eliminare o 0 per tornare al menù principale.","Inserimento non valido riprova:",0,prenotazioni.size());
            if(scelta!=0)
            {
                Prenotazione p=(Prenotazione) prenotazioni.toArray()[scelta-1];
                modificaEliminaPrenotazione(p,utente);
            }

        }while(scelta!=0);
    }

    public static void modificaEliminaPrenotazione(Prenotazione prenotazione,Cliente utente) throws NoSuchAlgorithmException {
        int risposta;
        do {
            System.out.println("Indice Codice prenotazione data   ora  titolo   genere    regista    anno  durata  eta minima  posti preso");
            System.out.println(prenotazione.toInfoCliente());

            risposta = inputInt("1  elimina\n2   Modifica\n0   Torna al menù principale","Inserimento non valido. Riprova:",0,2);
            switch (risposta) {
                case 1:
                    if(CineMaxManager.eliminaPrenotazione(prenotazione.getCodicePrenotazione()))
                        System.out.println("Elimato la prenotazione");
                    else
                        System.out.println("C'è un problema. Eliminazione impossibile.");
                    break;
                case 2:
                    int c = CineMaxManager.getPrenotazioniCliente(utente).size();
                    List<Proiezione> lista=CineMaxManager.getListaProiezioni();
                    lista=CineMaxManager.cercaProiezioni_Titolo(lista,prenotazione.getProiezione().getFilm().getTitolo());
                    String dataM=Data.oggi.getAnno()+"-"+(Data.oggi.getMese()+1)+"-"+Data.oggi.getGiorno();
                    lista=CineMaxManager.cercaProiezioni_Date(lista,Data.oggi.toString(),dataM);
                    visualizzaProiezioni(utente,lista);
                    if(c!=CineMaxManager.getPrenotazioniCliente(utente).size())
                        CineMaxManager.eliminaPrenotazione(prenotazione.getCodicePrenotazione());
                    break;
            }
        }while(risposta==2);
    }


    public static void cercaTipoPrenotazione()
    {
        int risposta;
        do {
            risposta=inputInt("1   Cerca per codice\n2   Cerca per nome e cognome\n3   cerca per titolo film\n4   cerca per intervallo date\n0   torna al menù principale","Inserimento non valido. Riprova:",0,4);

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

    public static void cercaPerCodice()
    {
        int risposta;
        do {
            risposta=inputInt("Inserisci il codice della prenotazione, 0 per tornare indietro.","Inserimento non valido. Riprova:",0,Integer.MAX_VALUE);

            Prenotazione p=CineMaxManager.cercaPrenotazione(risposta);
            if(p==null)
                System.out.println("Il codice inserito non esiste. Riprova:");
            else
                visualizzaPrenotazione(p);
        }while (risposta!=0);
    }

    public static void cercaPerNominativo()
    {
        String nome, cognome;
        boolean continua;
        do {
            continua=false;
            nome=inputString("Inserisci nome, 0 per tornare indietro.","Inserimento non valido. Riprova:",";");

            if(!nome.equals("0"))
            {
                cognome=inputString("Inserisci cognome, 0 per tornare indietro.","Inserimento non valido. Riprova:",";");
                if(!cognome.equals("0")) {
                    List<Utente> omonimi = LoginManager.cercaUtente(nome, cognome);
                    if(!omonimi.isEmpty())
                    {
                        if(omonimi.size()==1) {
                            List<Prenotazione> l = CineMaxManager.getPrenotazioniClienteAttive((Cliente) omonimi.get(0));
                            if (!l.isEmpty()) {
                                if (l.size() == 1)
                                    visualizzaPrenotazione(l.get(0));
                                else
                                    visualizzaPrenotazioni(l);
                            } else {
                                System.out.println("Non ci sono prenotazioni per questo cliente. Riprova:");
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
                        System.out.println("Non ci sono clienti con questo nome e cognome. Riprova:");
                        continua = true;
                    }
                }
            }

        }while (continua);
    }

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

            risposta=inputInt(s.toString(),"Inserimento non valido. Riprova:",0,c);
            if(risposta!=0)
            {
                List<Prenotazione> l = CineMaxManager.getPrenotazioniClienteAttive((Cliente) utenti.get(risposta-1));
                if (!l.isEmpty()) {
                    if (l.size() == 1)
                        visualizzaPrenotazione(l.get(0));
                    else
                        visualizzaPrenotazioni(l);
                } else {
                    System.out.println("Non ci sono prenotazioni per questo cliente. Riprova:");
                }

            }
        }while (risposta!=0);
    }



    public static void cercaPerTitolo()
    {
        boolean continua;
        do {
            continua=false;
            String titolo=inputString("Inserisci il titolo del film, 0 per tornare indietro.","Inserimento non valido. Riprova:",";");

            if(!titolo.equals("0"))
            {
                List<Prenotazione> l=CineMaxManager.cercaPrenotazioni(titolo);
                if(!l.isEmpty()) {
                    if (l.size() == 1)
                        visualizzaPrenotazione(l.get(0));
                    else
                        visualizzaPrenotazioni(l);
                }
                else {
                    System.out.println("Non ci sono prenotazioni per film con questo titolo. Riprova:");
                    continua=true;
                }
            }

        }while (continua);
    }

    public static void cercaPerDate()
    {
        int annoMin = inputInt("Inserisci l'anno della data minima:","Inserimento non valido. Riprova:",1900, Year.now().getValue());
        int meseMin = inputInt("Inserisci il mese della data minima:","Inserimento non valido. Riprova:",1,12);
        int giornoMin = inputInt("Inserisci il giorno della data minima:","Inserimento non valido. Riprova:",1,31);
        int annoMax = inputInt("Inserisci l'anno della data massima:","Inserimento non valido. Riprova:",annoMin, Year.now().getValue());
        int meseMax;
        if (annoMax==annoMin)
            meseMax = inputInt("Inserisci il mese della data massima:","Inserimento non valido. Riprova:",meseMin,12);
        else
            meseMax = inputInt("Inserisci il mese della data massima:","Inserimento non valido. Riprova:",1,12);
        int giornoMax;
        if(annoMax==annoMin && meseMax==meseMin)
            giornoMax=inputInt("Inserisci il giorno della data massima:","Inserimento non valido. Riprova:",giornoMin,31);
        else
            giornoMax=inputInt("Inserisci il giorno della data massima:","Inserimento non valido. Riprova:",1,31);

        Data dataMin = new Data(annoMin,meseMin,giornoMin);
        Data dataMax = new Data(annoMax,meseMax,giornoMax);

        List<Prenotazione> l=CineMaxManager.cercaPrenotazioni(dataMin,dataMax);
        if(!l.isEmpty())
        {
            if(l.size()==1)
                visualizzaPrenotazione(l.get(0));
            else
                visualizzaPrenotazioni(l);
        }
        else
            System.out.println("Non ci sono prenotazioni in quelle date");

    }


    public static void visualizzaPrenotazioni(List<Prenotazione> prenotazioni)
    {
        int scelta;
        do {

            System.out.println("Indice Codice prenotazione data   ora  titolo   genere    regista    anno  durata  eta minima  posti preso");
            int i = 1;
            for (Prenotazione p : prenotazioni) {
                System.out.println(" " + i + "  " + p.toInfoCliente());
                i++;
            }
            System.out.println("");
            scelta = inputInt("Inserisci indice della prenotazione che vuoi vedere, 0 per tornare indietro.", "Inserimento non valido. Riprova:", 0, prenotazioni.size());

            if (scelta != 0)
                visualizzaPrenotazione((Prenotazione) prenotazioni.toArray()[scelta - 1]);
        }while (scelta!=0);
    }


    public static void visualizzaPrenotazione(Prenotazione p)
    {
        System.out.println("Codice prenotazione    data  ora   titolo   genere   regista    anno   durata   età minima   costo   posti presi");
        System.out.println(p.toInfoCliente());
        System.out.println("Inserisci 0 per tornare indietro.");
        inputInt("","Devi inserire 0 se vuoi tornare indietro.",0,0);
    }




}
