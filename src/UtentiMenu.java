import java.lang.classfile.CodeBuilder;
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
                    //visualizza prenotazioni
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
                    //inserisci proiezione
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


    public static void bigliettaioRegistrato(Bigliettaio bigliettaio) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            scelta=inputInt("1   Visualizza prenotazioni odierne\n2   Cerca proiezione\n3   Profilo\n0   Logout","Inserimento non valido. Riprova:",0,3);
            switch (scelta)
            {
                case 1:
                    //visualizza prenotazioni odierne
                    break;
                case 2:
                    //cerca prenotazioni
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
            //da vedere se serve chiedere in input con il meno 0,1,2,3
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
                    visualizzaProiezioneCliente(proiezione);
                if(utente.getRuolo().equals("PROIEZIONISTA"))
                    visualizzaProiezioneProiezionista((Proiezionista) utente,proiezione);
            }
        }while (risposta!=0);
    }

    public static void visualizzaProiezioneCliente(Proiezione proiezione) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(proiezione.toInfo1());
        proiezione.getSala().stampa();

        int scelta =inputInt((String.format("Numero posti liberi: " + proiezione.getSala().postiDisponibili())),"Posti liberi sono insufficienti:",1,proiezione.getSala().postiDisponibili());

        char lettera;
        int numero;
        String posto;
        boolean ritest;
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
                    lettera='R';
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
            System.out.println("Hai prenotato il Posto " + lettera + numero);
        }
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
                anno = inputInt("Anno di nascita:", "inserimento non valido. Riprova:", 1900, Year.now().getValue());
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
                    //set dataNascita
                    break;
            }
        }while(scelta!=0);
    }

    static public void modificaDataNascita(Utente utente) throws NoSuchAlgorithmException {

        int giorno = inputInt("Inserisci nuovo giorno di nascita:","Inserimento non valido. Riprova:",1,31);
        int mese = inputInt("Inserisci nuovo mese di nascita:", "Inserimento non valido. Riprova:",1, 12);
        int anno = inputInt("Inserisci nuovo anno di nascita:", "inserimento non valido. Riprova:", 1900, Year.now().getValue());
        //set datanascita da fare
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
    static public void modificaUsername(Utente utente)
    {
        String username;
        do {
            username = inputString("Inserisci nuovo username o 0 per tornare indietro:","Inserimento non valido. Riprova:","; ");
            if(!username.equals("0") && LoginManager.cercaUtente(username)!=null)
                System.out.println("Username già usato");
        }while (!username.equals("0") && LoginManager.cercaUtente(username)!=null);
        if(!username.equals("0"))
            utente.setUsername(username);
    }

}
