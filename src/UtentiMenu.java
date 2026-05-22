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
                //cercaProiezione(new UtenteNR());
                break;
            case 2:
                //registrazioneUtente();
                break;
            case 3:
                //login
                break;
        };
    }


    public static void utenteRegistrato(Cliente utente)
    {
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


    public static void proiezionistaRegistrato(Proiezionista proiezionista)
    {
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


    public static void cercaProiezione(Utente utente)
    {
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

    public static void visualizzaProiezioni(Utente utente, List<Proiezione> lista)
    {
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
            if(utente.getRuolo().equals("CLIENTE"));
                //visuallizza proiezione cliente;
            if(utente.getRuolo().equals("PROIEZIONISTA"))
                visualizzaProiezioneProiezionista(utente,proiezione,lista);
        }

    }

    public static void visualizzaProiezioneCliente(Utente utente, Proiezione proiezione, List<Proiezione> lista)
    {
        Scanner scanner = new Scanner(System.in);
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
        for (int i=0; i<scelta;i++) {
            System.out.println(proiezione.toInfo1());
            proiezione.getSala().stampa();
            do {
                //cambiare e fare che l'utente inserisce direttamente nome del posto es: A15

                do {
                    System.out.println("Inserisci lettera del posto della " + i + "^ prenotazione:");
                    lettera = scanner.nextLine().charAt(0);
                    if (lettera < 'A' || lettera > 'J')
                        System.out.println("Lettera non valida, riprova:");
                } while (lettera < 'A' || lettera > 'J');

                do {
                    System.out.println("Inserisci numero del posto della " + i + "^ prenotazione.");
                    numero = scanner.nextInt();
                    if (numero < 1 || numero > 20)
                        System.out.println("Numero non valida, riprova:");
                } while (numero < 1 || numero > 20);

                if(proiezione.getSala().isPostoDisponibile(lettera,numero))
                    System.out.println("Il posto scelto è già occupato. Riprova:");
            }while(proiezione.getSala().setPosto(lettera,numero,true));
            System.out.println("Hai prenotato il Posto " + lettera + numero);
        }
        visualizzaProiezioni(utente,lista);
    }


    public static void visualizzaProiezioneProiezionista(Utente utente,Proiezione proiezione,List<Proiezione> lista)
    {
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
                //elimina proiezione
                break;
            case 2:
                //modifica proiezione
                break;
        };
    }





   /* public static void registrazioneUtente()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nome:");
        String nome=scanner.nextLine();
        System.out.println("Cognome:");
        String cognome=scanner.nextLine();
        System.out.println("Username:");
        String username=scanner.next();
        System.out.println("Password:");
        String password=scanner.next();
        System.out.println("Luogo di domicilio:");
        String domicilio=scanner.nextLine();
        String data;
        do{
            System.out.println("Vuoi inserire la data di nascita. Scrivi S o N:");
            data=scanner.nextLine();
            if(!(data.equals("S") || data.equals("N")))
                System.out.println("Inserimento non valido riprova:");
        }while (!(data.equals("S") || data.equals("N")));
        if(data.equals("S"))
        {
            System.out.println("Giorno di nascita:");
            int giorno=scanner.nextInt();
            System.out.println("Mese di nascita:");
            int mese=scanner.nextInt();
            System.out.println("Anno di nascita:");
            int anno=scanner.nextInt();
        }

        int scelta;
        do {
            for(int i=0; i<Ruolo.values().length -1;i++)
            {
                System.out.println(i+1 + "   " + Ruolo.values()[i]);
            }
            System.out.println("0   Logout");
            scelta = scanner.nextInt();

            if(!(scelta<Ruolo.values().length && scelta>=0) )
                System.out.println("inserimento non valido riprova:");
        }while(!(scelta<Ruolo.values().length && scelta>=0));

        if(data.equals("S"))
        {
            switch (Ruolo.values()[scelta])
            {
                case CLIENTE:
                    //Ceccare unicità
                    Cliente cliente = new Cliente(nome, cognome, username, password, giorno, mese, anno, domicilio);
                    utenteRegistrato(cliente);
                    break;
                case PROIEZIONISTA:
                    //Ceccare unicità
                    Proiezionista proiezionista = new Proiezionista(nome, cognome, username, password, giorno, mese, anno, domicilio);
                    proiezionistaRegistrato(proiezionista);
                    break;
                case BIGLIETTAIO:
                    //Ceccare unicità
                    Bigliettaio bigliettaio = new Bigliettaio(nome, cognome, username, password, giorno, mese, anno, domicilio);
                    bigliettaioRegistrato(bigliettaio);
                    break;
            };


        }
        else
        {
            switch (Ruolo.values()[scelta])
            {
                case CLIENTE:
                    //Ceccare unicità
                    Cliente cliente = new Cliente(nome, cognome, username, password, domicilio);
                    utenteRegistrato(cliente);
                    break;
                case PROIEZIONISTA:
                    //Ceccare unicità
                    Proiezionista proiezionista = new Proiezionista(nome, cognome, username, password, domicilio);
                    proiezionistaRegistrato(proiezionista);
                    break;
                case BIGLIETTAIO:
                    //Ceccare unicità
                    Bigliettaio bigliettaio = new Bigliettaio(nome, cognome, username, password, domicilio);
                    bigliettaioRegistrato(bigliettaio);
                    break;
            };
        }
    }*/



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
