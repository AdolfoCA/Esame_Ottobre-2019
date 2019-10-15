
> Written with [StackEdit](https://stackedit.io/).
> # Introduzione

Il seguente progetto è stato svolto per il corso di Programmazione ad Oggetti offerto dall’Università Politecnica delle Marche.

L’applicazione, basata sul linguaggio di programmazione Java, sfrutta il framework **Spring** e i vantaggi di un linguaggio OOP (incapsulamento, ereditarietà e polimorfismo).
> # Utilizzo del software
## Cosa può fare

All’avvio, l’applicazione apre una connessione con il web-server in locale sula porta 8080. Viene scaricato in automatico il dataset ed effettua il parsing del file CSV contenuto nell’[URL](http://data.europa.eu/euodp/data/api/3/action/package_show?id=fifth-european-working-conditions-survey-2010) assegnatoci.

Per testare il software è possibile utilizzare **Postman**, ambiente attraverso il quale è possibile richiedere dati, metadati, statistiche sui dati e si possono applicare filtri a dati e statistiche.

###  Dati

Per richiedere i dati contenuti nel file CSV bisogna impostare il metodo “GET” e definire la rotta

```
localhost:8080/Dati

```

### Metadati

Per richiedere i metadati bisogna impostare il metodo  **GET**  e definire la rotta

```
localhost:8080/Metadati

```

### [](https://github.com/ErmelindaBegotaraj/Progetto/tree/master/Progetto/src/main/java/com/lindatato/Progetto#statistiche)Statistiche

Per ottenere le statistiche di ogni attributo del dataset bisogna impostare il metodo  **GET**  e definire la rotta

```
localhost:8080/stats
```


<!--stackedit_data:
eyJoaXN0b3J5IjpbMzc1NTAyNzg1XX0=
-->