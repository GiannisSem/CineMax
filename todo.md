OGGI
- creare classi Film e Proiezione. (FATTO)
- creare file binario corrispondente al csv. (FATTO)
- Creare classe FileManager - leggi, scrivi. (FATTO) 
- creare struttura classi Cliente NR, R, Bigli, Proiez + loginMager
- Progettazione grafica / backend menù.

- CSV è in ordine descrescente, leggere dal csv ma terminare quando la data è già passata. (sia in csv che leggi proiezioni)

POI
- tutti i file ordinati
  - proiezioni: order by data -> inserimento ordinato
  - film: ordine alfabetico
  - utenti: anche
- Implementare bene le classi utenti
- Capire quando scrivere / aggiornare files

RICHIESTE:
- Proiezionisti:
  - Inserire un film. 
  - Inserire (data, costo), modifare (data) e eliminare proiezioni -> da controllare se lo slot va bene -> proiez_prec + durata_prec + 5 <= data <= proiez_succ - durata_inserita - 5. Se errore restituire la fascia di orario in cui poteva andare bene.
  - Logout
- Clienti NR:
  - Cercare Proiezioni (in base a titolo, genere, data o costo_biglietto)
  - Visualizza dettagli della Proiezione.
  - Registrarsi e Login.
- Clienti:
  - Cercare Proiezioni (in base a titolo, genere, data o costo_biglietto)
  - Prenotare n posti per una o più proiezione.
  - Visualizzare le prenotazioni attive (fino a che il film non termina è valida).
  - Cancellare le prenotazioni (se riusciamo anche modifica).
  - Logout.
- Bigliettai:
  - Cercare Proiezioni (in base a titolo, genere, data o costo_biglietto)
  - Logout
- Utenti: 
  - Cifrare password HASH DIGEST.
  - Pagina utente per cambio dei dati.
  - (Da finire Utenti tutto prima di fare file_utenti.csv, usiamo la virgola -> 2Proiez, 5Bigliett)
- File Proiezioni / Prenotazioni:
  - Ogni riga ha: data_proiezione, username, n_biglietti.

FUNZIONALITA':
- cercaProiezione()
  - Per titolo (anche parziale -> LIKE titolo%)
  - Per genere
  - Per intervallo date -> d1 <= list Proiezioni <= d2
  - Per costo biglietto -> p1 <= list Proiezioni <= p2
  - Combo dei precedenti -> basta continuare a filtrare dai risultati, fino a reset filtri.
- visualizzaProiezione() -> dopo aver fatto la ricerca..
  - Visua Proiezione.toInfo() e numero posti liberi in sala.
- registraCliente() -> inserisci i dati
- visualizzaPrenotazione() -> dopo la ricerca
  - Visualizza codice prenotazione, nome e cognome, dataOra proiezione, n_biglietti, costo CAD, totale
- ALTRO... pag 12+ slide