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
                //cerca proiezione
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
                System.out.println("inserimento non valido riprova:");
        }while(!(scelta==1 || scelta==2 || scelta==3 || scelta==0));


        switch (scelta)
        {
            case 0:
                utenteNonRegistrato();
                break;
            case 1:
                //cerca proiezione
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
                System.out.println("inserimento non valido riprova:");
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
                //cerca proiezione
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
                System.out.println("inserimento non valido riprova:");
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
                //cerca proiezione
                break;
            case 3:
                profilo(bigliettaio);
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
            for(int i=0; i<Ruolo.values().length;i++)
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
