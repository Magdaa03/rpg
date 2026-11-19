# Monster Hunter RPG 125861

Monster Hunter RPG è un gioco di ruolo a turni realizzato in Java con interfaccia grafica JavaFX. L’eroe affronta Goblin, Orco e Drago, scegliendo tra un attacco rapido sicuro e un attacco potente con il 70% di probabilità di colpire.

## Funzionalità

- Combattimento a turni con punti vita e due tipi di attacco.
- Progressione del rank: ogni mostro sconfitto assegna un punto; dopo tre punti l’eroe sale di rank.
- Barra visibile per il rank e il progresso verso il rank successivo.
- Salvataggio e caricamento della partita in formato JSON, inclusi rank e progresso.

## Requisiti

- JDK 25.
- Git, per clonare il repository.

Il progetto include Gradle Wrapper: non è necessario installare Gradle separatamente.

## Clonazione ed esecuzione

Clona il repository e apri la cartella del progetto:

```bash
git clone https://github.com/Magdaa03/rpg.git
cd rpg
```

Su Linux e macOS compila e avvia l’applicazione con:

```bash
./gradlew build
./gradlew run
```

Su Windows PowerShell usa:

```powershell
.\gradlew.bat build
.\gradlew.bat run
```

Il file dei salvataggi `savegame.json` viene creato nella directory di esecuzione dell’applicazione.

## Uso di strumenti di AI

Durante lo sviluppo ho utilizzato ChatGPT come supporto per confrontare idee sulle regole di gioco, sui personaggi e sul design dell’interfaccia, oltre che per chiarire concetti di Java e JavaFX e risolvere problemi. Le decisioni finali sul gioco e sulle modifiche da adottare sono state mie. La Wiki descrive nel dettaglio l’uso dell’AI.
