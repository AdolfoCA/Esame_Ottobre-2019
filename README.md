
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

*Key : Value*

*MainCat  : "nome macrocategoria"  
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

*Key : Value*

*MainCat  : "nome macrocategoria"  
SubCat  : "nome sottocategoria"* 

Ad esempio, se si vogliono le statistiche sulla sottocategoria *Under 35* della macrocategoria *hh2b. Age* bisogna impostare come parametri 

*Key : Value*

*MainCat  : hh2b. Age*

*SubCat  : Under 35*

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
Se non viene dichiarata la macrocategoria vengono restituite le statistiche rispetto a tutte le macrocategorie del CSV.

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
Se non viene dichiarata la macrocategoria vengono restituiti i dati filtrati  relativi a tutte le macrocategorie del CSV.
la mancata dichiarazione del filtro e/o dei parametri restituisce un errore.

Si possono applicare i seguenti filtri:


-   Gt : (greater than) elementi maggiori del valore passato

-   Lt : (less than) elementi minori al valore passato
   
-  In : elemento/i contenuto nei dati

il filtro *In* implementa in maniera implicita il filtro *Nin*.

### *Di seguito viene elencata la lista con tutte le macrocategorie e relative sottocategorie:*

**hh2b. Age**

Under 35

  

36-49

  

50+

  

  

**ef1. Level of education**

(Less than) primary

  

Secondary

  

Tertiary (or more)

  

  

**q6. Employment status**

Self-employed without employees

  

Self-employed with employees

  

Employed

  

Other

  

  

**q7. Employment contract**

An indefinite contract

  

A fixed term contract

  

A temporary employment agency contract

  

An apprenticeship or other training scheme

  

No contract

  

Other

  

  

**Sector of activity (NACE Rev2 - 10 categories)**

Agriculture

  

Industry

  

Construction

  

Wholesale, retail, food and accommodation

  

Transport

  

Financial services

  

Public administration and defence

  

Education

  

Health

  

Other services

  

  

**Occupation (ISCO 08)**

Armed forces occupations

  

Managers

  

Professionals

  

Technicians and associate professionals

  

Clerical support workers

  

Service and sales workers

  

Skilled agricultural, forestry and fishery workers

  

Craft and related trades workers

  

Plant and machine operators, and assemblers

  

Elementary occupations

  

  

**q12. Years in organisation**

Up to four years

  

Between five and nine years

  

10 years or more

  

  

**q17. People under supervision**

None

  

Between five and nine subordinates

  

10 or more subordinates

  

  

**q16. Gender of workers at work with same job title**

Mostly men

  

Mostly women

  

More or less equal numbers of men and women

  

Nobody else has the same job title

  

  

**q59. Immediate boss is a woman**

A man

  

A woman

  

  

**q15a. New processes or technologies were introduced at workplace in past three years**

No

  

Yes

  

  

**q15b. Substantial restructuring or reorganisation was carried out at workplace in pas three years**

No

  

Yes

  

  

**q77a. Might lose job in next 6 months**

No

  

Yes

  

  

**q14a. Change in working hours since January 2009**

Decrease

  

No change

  

Increase

  

  

**q14b. Change in salary since January 2009**

Decrease

  

No change

  

Increase

  

  

**q77f. Ease of finding job with similar salary**

No

  

Yes

  

  

**q21. Workers with more than one job**

No

  

Yes

  

  

**q18. Usual weekly working hours (distribution)**

20 or less

  

21-34

  

35-40

  

41-47

  

48 or more

  

  

**q18. Usual weekly working hours**

Mean

  

  

**q31. Daily commuting time (minutes)**

Less than an hour

  

An hour or more

  

  

**q32. Times per month working nights**

Never

  

Once or twice

  

Three times or more

  

  

**q32. Times per month working evenings**

Never

  

Once or twice

  

Three times or more

  

  

**q32. Times per month working on Sundays**

Never

  

Once or twice

  

Three times or more

  

  

**q32. Times per month working on Saturdays**

Never

  

Once or twice

  

Three times or more

  

  

**q32. Times per month working more than 10 hours a day**

Never

  

Once or twice

  

Three times or more

  

  

**q37a. Work same number of hours every day**

No

  

Yes

  

  

**q37b. Work same number of days every week**

No

  

Yes

  

  

**q37c. Work same number of hours every week**

No

  

Yes

  

  

**q37d. Working fixed starting and finishing times**

No

  

Yes

  

  

**q37e. Working on call**

No

  

Yes

  

  

**q37f. Working shifts**

No

  

Yes

  

  

**index 1. Preferred number of working hours**

Fewer hours

  

Same amount of hours

  

More hours

  

  

**q20. Days per week usually worked in main paid job**

1

  

2

  

3

  

4

  

5

  

6

  

7

  

  

**q45a. Working at very high speed**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q45b. Working to tight deadlines**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**index 2. Number of work pace determinants**

Fewer than three

  

Three or more

  

  

**q46a. Pace of work dependent on colleagues**

No

  

Yes

  

  

**q46b. Pace of work dependent on direct demands from customers etc.**

No

  

Yes

  

  

**q46c. Pace of work dependent on numerical targets**

No

  

Yes

  

  

**q46d. Pace of work dependent on machine**

No

  

Yes

  

  

**q46e. Pace of work dependent on boss**

No

  

Yes

  

  

**q51g. Having enough time to get the job done**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q23a. Exposure to vibrations**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q23b. Exposure to noise**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q23c. Exposure to high temperatures**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q23d. Exposure to low temperatures**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q23e. Exposure to breathing in smoke, fumes, powder or dust etc**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q23f. Exposure to breathing in vapours such as solvents and thinners**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q23g. Exposure to handling chemical substances**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q23h.Exposure to tobacco smoke from other people**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q23i. Exposure to infectious materials**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q24a. Job involves tiring or painful positions**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q24b. Job involves lifting or moving people**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q24c. Job involves carrying or moving heavy loads**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q24d. Job involves standing or walking**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**q24e. Job involves repetitive hand or arm movements**

Less than a quarter of the time

  

At least a quarter of the time

  

  

**index 3. Requirement and use of personal protective clothing or equipment**

Don't need protective clothing

  

Job requires protective clothing but don't always use them

  

Job requires protective clothing and always use them

  

  

**q25. Work involves visiting customers, patients, clients or working at their premises**

No

  

Yes

  

  

**index 4. Exposure to posture and movement related related risks (1 = lowest, 7 = highest)**

Mean

  

  

**index 5. Exposure to biological and chemical risks (1 = lowest, 7 = highest)**

Mean

  

  

**index 6. Exposure to ambient risks (1 = lowest, 7 = highest)**

Mean

  

  

**q26. Main place of work**

My employers 'my own business' premises

  

Clients' premises

  

A car or another vehicle

  

An outside site

  

My own home

  

Other

  

  

**q44a. Job involves short repetitive tasks of less than one minute**

No

  

Yes

  

  

**q44b. Job involves short repetitive tasks of less than 10 minutes**

No

  

Yes

  

  

**index 7. Use of technologies at work**

IT

  

IT and machinery

  

Machinery

  

No technology

  

  

**q49a. Job involves meeting precise quality standards**

No

  

Yes

  

  

**q49b. Job involves assessing quality of own work**

No

  

Yes

  

  

**q49c. Job involves solving unforeseen problems**

No

  

Yes

  

  

**q49d. Job involves monotonous tasks**

No

  

Yes

  

  

**q49e. Job involves complex tasks**

No

  

Yes

  

  

**q49f. Job involves learning new things**

No

  

Yes

  

  

**index 8. Job involves task rotation**

No

  

Yes

  

  

**q51i. Able to apply own ideas in work**

No

  

Yes

  

  

**q24f. Job involves dealing with people who are not employees (e.g. customers)**

No

  

Yes

  

  

**q24g. Job involves handling angry clients**

No

  

Yes

  

  

**index 9. Frequency and disruptiveness of interruptions**

Interruptions occur never or occasionally

  

Interruptions occur frequently, but are not disruptive

  

Interruptions occur frequently and are disruptive

  

  

**q51k. Knowing what is expected at work**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q51l. Job involves tasks that conflict with personal values**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q51p. Job requires hiding feelings**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q51m. Getting emotionally involved in work**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q51n. Experiencing stress in work**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q52a. Mistakes could cause physical injuries to others**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q52b. Mistakes could cause financial loss**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q30. Well-informed about health and safety risks**

Not (very) well informed

  

(Very) well informed

  

  

**q66. Considering health or safety at risk because of work**

No

  

Yes

  

  

**index 10. Well-being at risk (based on WHO5)**

Not at risk

  

At risk

  

  

**q67. Work affects health negatively**

No

  

Yes

  

  

**q68. General health status**

Good

  

Bad

  

  

**index 11. Suffering from a musculoskeletal disorder**

No

  

Yes

  

  

**q72. Absence from work due to health reasons for more than five days over the past 12 months**

No

  

Yes

  

  

**q73. Number of days absence from work due to work accidents in past year**

Not

  

Yes

  

  

**q74. Working while sick in past 12 months**

No

  

Yes

  

  

**q74b. Number of days working while sick in past 12 months**

Mean

  

  

**q75. Able to do same job aged 60**

Yes, I think so

  

No, I don't think so

  

I wouldn't want to

  

  

**index 12. Number of health problems**

Mean

  

  

**q72. Number of days of health-related absence in past 12 months**

Mean

  

  

**q77c. Job offers good prospects for career advancement**

No

  

Yes

  

  

**q61a. Having undergone training paid-for by employer in past year**

No

  

Yes

  

  

**q61b. Having undergone self-paid training in past year**

No

  

Yes

  

  

**q61c. Having undergone on-the-job training in past year**

No

  

Yes

  

  

**q61_2. Having asked for training**

No

  

Yes

  

  

**q60. Job-skills match**

I need further training to cope well with my duties

  

My present skills correspond well with my duties

  

I have the skills to cope with more demanding duties

  

  

**q61_1a.Training improved way of working**

Disagree

  

Agree

  

  

**q61_1b.Training improved job security**

Disagree

  

Agree

  

  

**q61_1c. Training improved prospects for future employment**

Disagree

  

Agree

  

  

**q50a. Can choose / change the order of tasks**

No

  

Yes

  

  

**q50b. Can choose / change the methods of work**

No

  

Yes

  

  

**q50c. Can choose / change the speed or rate of work**

No

  

Yes

  

  

**q51d. Involved in improving the work organisation or work processes**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q51e. Having a say in choice of working partners**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q51f. Can take a break when wishes**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q51o. Able to influence decisions that are important for work**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q62a. Subjected to formal assessment of work performance in past year**

No

  

Yes

  

  

**index 13. Presence of employee representation**

no

  

yes

  

  

**index 14. Team work and team autonomy**

No teamwork

  

Team with no autonomy

  

Team with some autonomy

  

Team with much automomy

  

  

**index 15. E-nomads: working away from employers premises using computer or internet**

No

  

Yes

  

  

**index 16. Workplace innovation - workers involvement (1 = lowest, 5 = highest)**

Mean

  

  

**q56. Teamwork**

No

  

Yes

  

  

**q51a. Receiving help and support from colleagues**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q51b. Receiving help and support from manager**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q51c.Being consulted before work targets are set**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**Q58a Immediate supervisor provides feedback on work**

No

  

Yes

  

  

**q58b. Immediate supervisor respects you as a person**

No

  

Yes

  

  

**q58c. Immediate supervisor is good at resolving conflicts**

No

  

Yes

  

  

**q58d. Immediate supervisor is good at planning and organising the work**

No

  

Yes

  

  

**q58e. Immediate supervisor encourages participation in important decisions**

No

  

Yes

  

  

**q51h. Job gives feeling of work well done**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q51j. Having the feeling of doing useful work**

Always or most of the time

  

Sometimes

  

Rarely or never

  

  

**q77b. Being well paid for the work**

No

  

Yes

  

  

**q77d. Feeling at home in the organisation**

No

  

Yes

  

  

**q77e. Having very good friends at work**

No

  

Yes

  

  

**q77g. Organisation is motivating to give best performance**

No

  

Yes

  

  

**q76. Satisfaction with working conditions in main paid job**

Not satisfied

  

Satisfied

  

  

**index 17. Gender-distribution of paid work in the household.**

Male breadwinner

  

Modified male breadwinner

  

Both partners work fulltime

  

Both partners work parttime

  

Modified female breadwinner

  

Female breadwinner

  

  

**q39. Setting of working time arrangments**

Set by employer, no possibility for change

  

Choice between several fixed schedules

  

Adaptable within limits (flexitime)

  

Entirely determined by employee

  

  

**q40. Regular changes to work schedule and notice given**

No

  

Yes, the same day

  

Yes, the day before

  

Yes, several days in advance

  

Yes, several weeks in advance

  

  

**q41. Fit between working hours and family / social commitments**

In balance

  

Off balance

  

  

**q42. Number of times worked in free time in order to meet work demands in the past year**

Less than once a month

  

Once a month or more

  

  

**q43. Difficulty of taking an hour or two off to take care of personal matters**

Not difficult

  

Difficult

  

  

**index 18. Number of hours spent on paid and unpaid work per week**

Mean

  

  

**ef6. Household is able to make ends meet**

(Very) easily

  

Fairly easily

  

With some difficulty

  

With (great) difficulty

  

  

**ef7a. Earnings include basic fixed salary/wage**

No

  

Yes

  

  

**ef7b. Earnings include piece rate or productivity payments**

No

  

Yes

  

  

**ef7c. Earnings include extra payments for additional hours of work/overtime**

No

  

Yes

  

  

**ef7d. Earnings include extra payments compensating for bad or dangerous working conditions**

No

  

Yes

  

  

**ef7e. Earnings include extra payments compensating for Sunday work**

No

  

Yes

  

  

**ef7g. Earnings include payments based on the overall performance of the company (profit sharing scheme) where you work**

No

  

Yes

  

  

**ef7i. Earnings include income from shares in the company you work for**

No

  

Yes

  

  

**ef7j. Earnings include advantages of other nature (for instance medical services, access to shops, etc.)**

No

  

Yes

  

  

**index 19. Adverse social behaviour (e.g. harassment, abuse) at work**

No

  

Yes

  

  

**q70a. Subjected to verbal abuse at work in past month**

No

  

Yes

  

  

**q70b. Subjected to unwanted sexual attention in past month**

No

  

Yes

  

  

**q70c. Subjected to threats and humiliating behaviour at work in past month**

No

  

Yes

  

  

**q71a.  Subjected to physical violence at work in past year**

No

  

Yes

  

  

**q71b.  Subjected to bullying / harassment at work in past year**

No

  

Yes

  

  

**q71c.  Subjected to sexual harassment at work in past year**

No

  

Yes

  

  

**index 20. Subjected to discrimination at work in past year**

No

  

Yes
# Struttura del codice

## Packaging

Le classi sono inserite in quattro package principali basati sulla logica MVC (Model View Controller):

_Controller_  racchiude tutti i metodi necessari per far fronte alle richieste GET e POST.

_Model_  contiene la classe principale su cui si basa l’intero programma.

_Services_  è utile nell’implementazione di metodi che gestiscono l’accesso a dati, metadati, statistiche e filtri.

_Download_Parsing_  include la classe che permette di fare il download e il parsing del file CSV.

   ## Diagrammi

### Diagramma delle classi
![diagramma classi](file:///Users/adolfocafaro/Documents/Esame_Ottobre-2019/Dati_Europa/diagramma_classi.png)
 
 

   




<!--stackedit_data:
eyJoaXN0b3J5IjpbLTMzNzU5Mzc0MSwtOTk5MTU3NTUsLTQzNT
M5MjY1MywtOTU4NDg2MDkzLC0xNDEyNDEzMDc1LC0yMDk4NDYx
NjQ3LC0yMDcwOTQxNzk0LC0xNDUwMjk0NDMwXX0=
-->