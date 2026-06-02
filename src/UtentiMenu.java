import javax.lang.model.util.SimpleElementVisitor7;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

public  class UtentiMenu {


    public static void utenteNonRegistrato()
    {

        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            System.out.println("1   Cerca proiezione");
            System.out.println("2   Registrazione");
            System.out.println("3   Login");
            scelta = scanner.nextInt();

            if(!(scelta==1 || scelta==2 || scelta==3))
                System.out.println("Inserimento non valido riprova:");
        }while(!(scelta==1 || scelta==2 || scelta==3));

        switch (scelta)
        {
            case 1:
                try {
                    cercaProiezione(new UtenteNR());
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }

                break;
            case 2:
                //registrazioneUtente();
                break;
            case 3:
                //login
                break;
        };
    }


    public static void utenteRegistrato(Cliente utente) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            System.out.println("1   Cerca proiezione");
            System.out.println("2   Visualizzare prenotazioni");
            System.out.println("3   Profilo");
            System.out.println("0   Logout");
            scelta = scanner.nextInt();

            if(!(scelta==1 || scelta==2 || scelta==3 || scelta==0))
                System.out.println("Inserimento non valido riprova:");
        }while(!(scelta==1 || scelta==2 || scelta==3 || scelta==0));


        switch (scelta)
        {
            case 0:
                utenteNonRegistrato();
                break;
            case 1:
                cercaProiezione(utente);
                break;
            case 2:
                //visualizza prenotazioni
                break;
            case 3:
                profilo(utente);
                break;
        };
    }


    public static void proiezionistaRegistrato(Proiezionista proiezionista) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            System.out.println("1   Inserire proiezione");
            System.out.println("2   Cerca proiezione");
            System.out.println("3   Profilo");
            System.out.println("0   Logout");
            scelta = scanner.nextInt();

            if(!(scelta==1 || scelta==2 || scelta==3 || scelta==0))
                System.out.println("Inserimento non valido riprova:");
        }while(!(scelta==1 || scelta==2 || scelta==3 || scelta==0));


        switch (scelta)
        {
            case 0:
                utenteNonRegistrato();
                break;
            case 1:
                //visualizza prenotazioni
                break;
            case 2:
                cercaProiezione(proiezionista);
                break;
            case 3:
                profilo(proiezionista);
                break;
        };
    }


    public static void bigliettaioRegistrato(Bigliettaio bigliettaio)
    {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            System.out.println("1   Visualizza prenotazioni odierne");
            System.out.println("2   Cerca proiezione");
            System.out.println("3   Profilo");
            System.out.println("0   Logout");
            scelta = scanner.nextInt();

            if(!(scelta==1 || scelta==2 || scelta==3 || scelta==0))
                System.out.println("Inserimento non valido riprova:");
        }while(!(scelta==1 || scelta==2 || scelta==3 || scelta==0));


        switch (scelta)
        {
            case 0:
                utenteNonRegistrato();
                break;
            case 1:
                //visualizza prenotazioni odierne
                break;
            case 2:
                //cerca prenotazioni
                break;
            case 3:
                profilo(bigliettaio);
                break;
        };
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
            System.out.println("Inserisci l'anno della data minima:");
            int annoMin = scanner.nextInt();
            System.out.println("Inserisci il mese della data minima:");
            int meseMin = scanner.nextInt();
            System.out.println("Inserisci il giorno della data minima:");
            int giornoMin = scanner.nextInt();

            System.out.println("Inserisci l'anno della data massima:");
            int annoMax = scanner.nextInt();
            System.out.println("Inserisci il mese della data massima:");
            int meseMax = scanner.nextInt();
            System.out.println("Inserisci il giorno della data massima:");
            int giornoMax = scanner.nextInt();
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
            System.out.println("Inserisci costo minimo del biglietto del film:");
            double costoMin = scanner.nextDouble();
            System.out.println("Inserisci costo minimo del biglietto del film:");
            double costoMax = scanner.nextDouble();
            lista=CineMaxManager.cercaProiezioni_CostoBiglietto(lista,costoMin,costoMax);
        }

        visualizzaProiezioni(utente,lista);

    }

    public static void visualizzaProiezioni(Utente utente, List<Proiezione> lista) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Indice   DataOra   titolo   genere   regista   anno   durata   età minima   costo   nPostiLiberi");
        int i=1;
        int risposta;
        for (Proiezione p: lista)
        {
            System.out.println(" " + i + "   " + p.toInfo());
            i++;
        }
        do {
            System.out.println("Inserisci l'indice della proiezione che vuoi vedere o 0 per tornare al menù principale.");
            risposta = scanner.nextInt();
            if(risposta<0 || risposta>lista.size())
                System.out.println("Inserimento non valido riprova:");
        }while (risposta<0 || risposta>lista.size());

        if(risposta==0)
        {
            if(utente.getRuolo().equals("CLIENTE"))
                utenteRegistrato((Cliente) utente);
            if(utente.getRuolo().equals("PROIEZIONISTA"))
                proiezionistaRegistrato((Proiezionista) utente);
            if(utente.getRuolo().equals("BIGLIETTAIO"))
                bigliettaioRegistrato((Bigliettaio) utente);
            if(utente.getRuolo().equals("NONREGISTRATO"))
                utenteNonRegistrato();

        }
        else
        {
            Proiezione proiezione=(Proiezione) lista.toArray()[risposta];
            if(utente.getRuolo().equals("CLIENTE"))
                visualizzaProiezioneCliente(utente, proiezione,lista);
            if(utente.getRuolo().equals("PROIEZIONISTA"))
                visualizzaProiezioneProiezionista(utente,proiezione,lista);
        }

    }

    public static void visualizzaProiezioneCliente(Utente utente, Proiezione proiezione, List<Proiezione> lista) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(proiezione.toInfo1());
        proiezione.getSala().stampa();
        int scelta;
        do {
            System.out.println("Numero posti liberi: " + proiezione.getSala().postiDisponibili());
            System.out.println("Quanti posti vuoi prenotare? 0 per tornare indietro");
            scelta = scanner.nextInt();

            if(scelta>proiezione.getSala().postiDisponibili())
                System.out.println("Posti liberi sono insufficienti:");
        }while(scelta>proiezione.getSala().postiDisponibili());


        char lettera;
        int numero;
        String posto;
        boolean ritest;
        for (int i=0; i<scelta;i++) {

            do {
                System.out.println("Inserisci posto della " + i + "^ prenotazione: es: A9");
                posto=scanner.nextLine();
                ritest=false;
                numero=Integer.parseInt(posto.substring(1,posto.length()-1));
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
        visualizzaProiezioni(utente,lista);
    }


    public static void visualizzaProiezioneProiezionista(Utente utente,Proiezione proiezione,List<Proiezione> lista) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        int scelta;
        do {
            System.out.println(proiezione.toInfo1());
            System.out.println("1   Elimina proiezione");
            System.out.println("2   Modifica proiezione");
            System.out.println("0   Torna indietro");
            scelta = scanner.nextInt();

            if(!(scelta==1 || scelta==2 || scelta==0))
                System.out.println("Inserimento non valido riprova:");
        }while(!(scelta==1 || scelta==2 || scelta==0));


        switch (scelta)
        {
            case 0:
                visualizzaProiezioni(utente,lista);
                break;
            case 1:
                eliminaProiezione(utente,proiezione,lista);
                break;
            case 2:
                modificaProiezione(utente,proiezione,lista);
                break;
        };
    }

    public static void eliminaProiezione(Utente utente,Proiezione proiezione,List<Proiezione> lista) throws NoSuchAlgorithmException {



        if(proiezione.getSala().postiOccupati()==0) {
            if(((Proiezionista)utente).eliminaProiezione(proiezione.getDataOra()))
                System.out.println("Eliminazione riuscita.");
            else
                System.out.println("Eliminazione non riuscita.");
        }
        else
            System.out.println("Non è modificabile perchè ci sono delle prenotazioni");
        visualizzaProiezioneProiezionista(utente,proiezione,lista);
    }

    public static void modificaProiezione(Utente utente,Proiezione proiezione,List<Proiezione> lista) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        if(proiezione.getSala().postiOccupati()==0) {

            System.out.println("Inserisci nuovo anno:");
            int anno = scanner.nextInt();
            System.out.println("Inserisci nuovo mese:");
            int mese = scanner.nextInt();
            System.out.println("Inserisci nuovo giorno:");
            int giorno = scanner.nextInt();
            System.out.println("Inserisci nuova ora:");
            int ora = scanner.nextInt();
            System.out.println("Inserisci nuovi minuti:");
            int minuti = scanner.nextInt();
            if(((Proiezionista)utente).modificaDataProiezione(proiezione.getDataOra(),new DataOra(anno,mese,giorno,ora,minuti,0)))
                System.out.println("Cambiamento data riuscito.");
            else
                System.out.println("Cambiamento data non riuscito.");
        }
        else
            System.out.println("Non è modificabile perchè ci sono delle prenotazioni");
        visualizzaProiezioneProiezionista(utente,proiezione,lista);

    }



    public static void registrazioneUtente() throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        boolean riprova;
        do {

            //mettere i controlli che non siano vuoti e con il ;
            riprova = false;
            System.out.println("Nome:");
            String nome = scanner.nextLine();
            System.out.println("Cognome:");
            String cognome = scanner.nextLine();
            //controllare con il cerca username nel LoginManager
            System.out.println("Username:");
            String username = scanner.next();
            System.out.println("Password:");
            String password = scanner.next();
            System.out.println("Luogo di domicilio:");
            String domicilio = scanner.nextLine();
            String data;
            int giorno = 0;
            int mese = 0;
            int anno = 0;
            do {
                System.out.println("Vuoi inserire la data di nascita. Scrivi S o N:");
                data = scanner.nextLine();
                if (!(data.equals("S") || data.equals("N")))
                    System.out.println("Inserimento non valido riprova:");
            } while (!(data.equals("S") || data.equals("N")));
            if (data.equals("S")) {
                System.out.println("Giorno di nascita:");
                giorno = scanner.nextInt();
                System.out.println("Mese di nascita:");
                mese = scanner.nextInt();
                System.out.println("Anno di nascita:");
                anno = scanner.nextInt();
            }

            int scelta;
            do {
                for (int i = 0; i < Ruolo.values().length - 2; i++) {
                    System.out.println(i + 1 + "   " + Ruolo.values()[i]);
                }
                System.out.println("0   Logout");
                scelta = scanner.nextInt();

                if (!(scelta < Ruolo.values().length - 2 && scelta >= 0))
                    System.out.println("inserimento non valido riprova:");
            } while (!(scelta < Ruolo.values().length - 2 && scelta >= 0));
            if (scelta == 0)
                utenteNonRegistrato();
            else {

                if (data.equals("S")) {
                    switch (Ruolo.values()[scelta]) {
                        case CLIENTE:
                            Cliente cliente = new Cliente(nome, cognome, username, password, giorno, mese, anno, domicilio);
                            if (LoginManager.signin(cliente)) {
                                System.out.println("Registrazione non valida. Riprova.");
                                riprova = true;
                            } else
                                utenteRegistrato(cliente);
                            break;
                        case PROIEZIONISTA:
                            Proiezionista proiezionista = new Proiezionista(nome, cognome, username, password, giorno, mese, anno, domicilio);
                            if (LoginManager.signin(proiezionista)) {
                                System.out.println("Registrazione non valida. Riprova.");
                                riprova = true;
                            } else
                                proiezionistaRegistrato(proiezionista);
                            break;
                        case BIGLIETTAIO:
                            Bigliettaio bigliettaio = new Bigliettaio(nome, cognome, username, password, giorno, mese, anno, domicilio);
                            if (LoginManager.signin(bigliettaio)) {
                                System.out.println("Registrazione non valida. Riprova.");
                                riprova = true;
                            } else
                                bigliettaioRegistrato(bigliettaio);
                            break;
                    }


                } else {
                    switch (Ruolo.values()[scelta]) {
                        case CLIENTE:
                            Cliente cliente = new Cliente(nome, cognome, username, password, domicilio);
                            if (LoginManager.signin(cliente)) {
                                System.out.println("Registrazione non valida. Riprova.");
                                riprova = true;
                            } else
                                utenteRegistrato(cliente);
                            break;
                        case PROIEZIONISTA:
                            Proiezionista proiezionista = new Proiezionista(nome, cognome, username, password, domicilio);
                            if (LoginManager.signin(proiezionista)) {
                                System.out.println("Registrazione non valida. Riprova.");
                                riprova = true;
                            } else
                                proiezionistaRegistrato(proiezionista);
                            break;
                        case BIGLIETTAIO:
                            Bigliettaio bigliettaio = new Bigliettaio(nome, cognome, username, password, domicilio);
                            if (LoginManager.signin(bigliettaio)) {
                                System.out.println("Registrazione non valida. Riprova.");
                                riprova = true;
                            } else
                                bigliettaioRegistrato(bigliettaio);
                            break;
                    }
                    ;
                }
            }
        }while (riprova);
    }



    public static void profilo(Utente utente)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(utente.toInfo());

        int scelta;
        do {
            System.out.println("1   Modifica password");
            System.out.println("2   Modifica username");
            System.out.println("3   Modifica domicilio");
            System.out.println("4   Modifica data di nascita");
            System.out.println("0   Torna al menù principale");
            scelta = scanner.nextInt();

            if(!(scelta==1 || scelta==2 || scelta==3 || scelta==4 || scelta==0))
                System.out.println("inserimento non valido riprova:");
        }while(!(scelta==1 || scelta==2 || scelta==3 || scelta==4 || scelta==0));


        switch (scelta)
        {
            case 0:
                //fare  getRuolo e poi in base al ruolo riportare al menù indicato
                //utenteRegistrato(utente);
                break;
            case 1:
                //set password
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
    }


    static public void modificaDomicilio(Utente utente)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci nuovo domicilio o 0 per tornare indietro");
        String username = scanner.next();
        if(username.equals("0"))
            profilo(utente);
        else
           utente.setDomicilio(username);
    }

    static public void modificaUsername(Utente utente)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserisci nuovo username (di solo una parola) o 0 per tornare indietro");
        String username = scanner.next();
        if(username.equals("0"))
            profilo(utente);
       else
            utente.setUsername(username);

    }

}
