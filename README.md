# sna4slack [![Build Status](https://travis-ci.com/softeng-inf-uniba/progetto1718-ritchie.svg?token=bUDdGhespuiKseaaysck&branch=master)](https://travis-ci.com/softeng-inf-uniba/progetto1718-ritchie) [![Coverage Status](https://coveralls.io/repos/github/softeng-inf-uniba/progetto1718-ritchie/badge.svg?branch=master&service=github&t=lFn3m4)](https://coveralls.io/github/softeng-inf-uniba/progetto1718-ritchie)
La struttura della repository si presenta nel seguente modo:
```
|–– build
|    |–– reports
|–– config
|    |–– checkstyle
|–– doc
|    |–– drawings
|    |–– javadoc 
|	 |–– reports
|    |–– Report.md
|–– gradle
|–– lib
|–– res
|–– src
|    |–– main
|    |–– test
|–– .coveralls.yml
|–– .gitignore
|–– .travis.yml
|–– build.gradle
|–– Assegnazione progetto.md
|–– Guida per lo studente.md
|–– README.md
|–– gradlew
|–– gradlew.bat
|–– settings.gradle
```

Nel seguito si dettagliano i ruoli dei diversi componenti:
- **build**: ospita la sottocartella *reports*, contenente gli output dei tool automatici di test e controllo di qualità;
- **config**: ospita i file di configurazione. L’unica configurazione di base richiesta è quella per il tool checkstyle;
- **doc**: in questa cartella deve essere inserita tutta la documentazione relativa al progetto. In particolare, in *drawings* dovranno essere salvati i diagrammi UML e *javadoc* ospiterà la documentazione generata automaticamente per il codice Java. Il file *Report.md* rappresenta la relazione finale del progetto; all'interno della cartella *reports* sono invece salvate le immagini corrispondenti ai vari report di Jacoco, PMD ecc.
- **gradle**: contiene il jar per il sistema di gestione delle dipendenze *Gradle*.
- **lib**: creata per includere eventuali *jar* di librerie esterne utilizzate dal progetto.
- **res**: la cartella deve contenere tutte le risorse usate dal sistema (immagini, testi ecc.)
- **src**: la cartella principale del progetto, in cui scrivere tutto il codice dell’applicazione. In *main* ci saranno i file sorgente e *test* conterrà i test di unità previsti.
- **.coveralls.yml**: specifica il servizio travis-ci e il token del repository (privato) per il quale visualizzare la percentuale di code coverage dei casi test generata dal tool _JaCoCo_;
- **.gitignore**: specifica tutti i file che devono essere esclusi dal sistema di controllo versione.
- **.travis.yml**: dettaglia le direttive per assicurare la *continuous integration* attraverso l’uso di Travis CI;
- **build.gradle**: esplicita le direttive e la configurazione per *Gradle*. 
- **Assegnazione progetto.md**: fare riferimento a questo file per la descrizione dettagliata del progetto assegnato;
- **Guida per lo studente.md:** elenca e descrive tutti i passi di configurazione necessari per attivare l’intero flusso di lavoro dietro lo sviluppo del progetto;
- **gradlew & gradlew.bat**: sono i file eseguibili di *Gradle*, rispettivamente per Unix e per Windows. Vengono generati automaticamente da Eclipse;
- **settings.gradle**: file di configurazione di *Gradle*. Anche quest’ultimo viene generato automaticamente da Eclipse.


In alcune cartelle è possibile notare la presenza di un unico file nascosto “.keep”: questo ha il solo scopo di richiedere a Git l’inclusione delle cartelle in cui è contenuto (Git esclude dal *versioning* le cartelle vuote). Pertanto, il file può essere ignorato o eventualmente cancellato nel momento in cui si inserisca almeno un altro file all’interno della cartella.