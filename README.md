
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

*Key : Value 
MainCat  : "nome macrocategoria"  
SubCat  : "nome sottocategoria" 
Paese :  "nome paese"* 

### Metadati

Per richiedere i metadati, ovvero le Macrocategorie, bisogna impostare il metodo  **GET**  e definire la rotta

```
localhost:8080/Metadati

```

### Statistiche

Per ottenere le statistiche rispetto ad una macrocateogoria e sottocategoria bisogna impostare il metodo **GET**, definire la rotta
``` 
localhost:8080/Statistiche
```
e impostare due parametri: MainCat e SubCat.

*Key : Value 
MainCat  : "nome macrocategoria"  
SubCat  : "nome sottocategoria"* 

Ad esempio, se si vogliono le statistiche sulla sottocategoria *Under 35* della macrocategoria *hh2b. Age* bisogna impostare come parametri 

*Key : Value 
MainCat  : hh2b. Age
SubCat  : Under 35*

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
 Se non viene specificata la sottocategoria allora vengono restituite le statistiche rispetto a tutte le sottocategorie della macrocategoria dichiarata.
Se non viene dichiarata la macrocategoria vengono restituite le statistiche rispetto a tutte le macrocategorie del csv.

### Filtri
Per ottenere i dati filtrati di una macrocateogoria e sottocategoria bisogna impostare il metodo **POST**, definire la rotta
``` 
localhost:8080/Filtri
```
e aggiungere nel *body* la stringa
```
{"MainCat": "nome macrocategoria", "SubCat":"nome sottocategoria", "Filtro":"tipo di filtro", "Param":"vettore di parametri"}
```
Ad esempio, se si vogliono filtrare i dati maggiori di 30 della sottocategoria *Under 35*, appartenente alla macrocategoria *hh2b. Age* bisogna aggiungere nel *body* la stringa:
{"MainCat": "hh2b. Age", "SubCat":"Under 35", "Filtro":"Gt", "Param":"30"}
e si ottiene con il seguente output:

  

     {
    " hh2b. Age :Under 35": {
    
    "Cyprus": 32.8,
    
    "United Kingdom": 32.7,
    
    "Portugal": 32.3,
    
    "Malta": 55.9,
    
    "Spain": 35.7,
    
    "Greece": 33.3,
    
    "Netherlands": 37.5,
    
    "Austria": 33.7,
    
    "Turkey": 53.2,
    
    "Kosovo": 52.4,
    
    "Belgium": 34.2,
    
    "EU27": 32.2,
    
    "Norway": 33.5,
    
    "Ireland": 41.8,
    
    "Luxembourg": 34.1,
    
    "Finland": 33.8,
    
    "Poland": 38.5,
    
    "Denmark": 33.9,
    
    "FYROM": 34.3,
    
    "Slovenia": 36.0,
    
    "France": 30.3,
    
    "Germany": 30.6,
    
    "Montenegro": 40.4
    
    }
    
    }
    
  Se non viene specificata la sottocategoria vengono restituiti i dati filtrati delle sottocategorie relative alla macrocategoria dichiarata.
Se non viene dichiarata la macrocategoria vengono restituiti i dati filtrati  relativi a tutte le macrocategorie del csv.
la mancata dichiarazione del filtro e/o dei parametri restituisce un errore.
Si possono applicare i seguenti filtri:


-   Gt : (greater than) elementi maggiori del valore passato

-   Lt : (less than) elementi minori al valore passato
   
-  In : elemento/i contenuto nei dati
   
 

   




<!--stackedit_data:
eyJoaXN0b3J5IjpbMjgwMzg4ODc5LC0xNDEyNDEzMDc1LC0yMD
k4NDYxNjQ3LC0yMDcwOTQxNzk0LC0xNDUwMjk0NDMwXX0=
-->