
> Written with [StackEdit](https://stackedit.io/).
> # Introduzione

Il seguente progetto è stato svolto per il corso di Programmazione ad Oggetti offerto dall’Università Politecnica delle Marche.

L’applicazione, basata sul linguaggio di programmazione Java, sfrutta il framework **Spring** e i vantaggi di un linguaggio OOP (incapsulamento, ereditarietà e polimorfismo).
> # Utilizzo del software
## Cosa può fare

All’avvio, l’applicazione apre una connessione con il web-server in locale sula porta 8080. Viene scaricato in automatico il dataset ed effettua il parsing del file CSV contenuto nell’[URL](http://data.europa.eu/euodp/data/api/3/action/package_show?id=fifth-european-working-conditions-survey-2010) assegnatoci.
Per testare il software è possibile utilizzare **Postman**, ambiente attraverso il quale è possibile richiedere dati, metadati, statistiche sui dati e si possono applicare filtri a dati e statistiche.

### Modellazione
La moderazione del csv è fatta ad "albero". Il tronco è la macrocategoria **MainCat** che presenta un numero *n* di sottocategorie **SubCat**,i rami dell'albero. Ciascuna SubCat è caratterizzata da una mappa di dati, a ciuascuno dei quali è riferito un paese.

###  Dati

Per richiedere i dati contenuti nel file CSV bisogna impostare il metodo **GET** e definire la rotta

```
localhost:8080/Dati
```
Se si desidera ottenere un dato relativo ad un particolare paese, rispetto ad una categoria e sottocategoria bisogna definire il metodo **GET** e definire la rotta
```
localhost:8080/DatoPaese
```
e passare come parametri la Macrocategoria, Sottocategoria e paese.
Key : Value 

 MainCat  : "nome macrocategoria"  
SubCat  : "nome sottocategoria" 
Paese :  "nome paese" 



### Metadati

Per richiedere i metadati, ovvero le Macrocategorie, bisogna impostare il metodo  **GET**  e definire la rotta

```
localhost:8080/Metadati

```

### Statistiche

Per ottenere le statistiche rispetto ad una Macrocateogoria e sottocategoria bisogna impostare il metodo **GET**, definire la rotta
``` 
localhost:8080/Statistiche
```
e impostare due parametri: MainCat e SubCat.
|Key| Value |
|--|--|
| MainCat | "nome macrocategoria" |
|SubCat  | "nome sottocategoria" |
Ad esempio, se si vogliono le statistiche sulla sottocategoria *Under 35* della macrocategoria *hh2b. Age* bisogna impostare come parametri 
|Key| Value |
|--|--|
| MainCat | hh2hb.Age |
|SubCat  |Under 35|
e si ottiene con il seguente output:

    [
    
    {
    
    "Somma": 1193.6000000000001,
    
    "Media": 34.10285714285715,
    
    "min:Czech Republic": 26.1,
    
    "max:Malta": 55.9,
    
    "Deviazione Standard": 42.070057217523654
    
    }
    
    ]
    




<!--stackedit_data:
eyJoaXN0b3J5IjpbMTgyMzY0MzU4MCwtMjA3MDk0MTc5NCwtMT
Q1MDI5NDQzMF19
-->