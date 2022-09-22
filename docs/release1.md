# Første iterasjon

I første møte satte vi som mål å implementere ganske grunnleggende GUI og logikk, samt litt mer grovarbeid som å fikse filstrukturer og passe på at maven kan kompilere, teste, og kjøre javafx. Andre krav som README-filer har også blitt utført.

 -----

## Funksjoner ved første iterasjon er som følger:
 - Det skal gå an å kompilere, teste og kjøre javafx via maven.
 - Dersom man kjører `mvn javafx:run` skal det åpnes et vindu med et grunnleggende minesweeper-GUI.
    - I dette vinduet skal det gå an å fylle to tekstfelt, og sende dette inn. Da skal teksten som sto i feltene bli lagret til en JSON-fil.
    - Det skal og være et minesweeper-felt (eller grid). Dette feltet skal ha funksjonen at man kan høyreklikke på en rute for å legge til et flagg. Applikasjonen lagrer i tillegg flaggtilstanden i Square objekter som ligger i Minefield objektet. Det går også ann å ta flagget vekk igjen.
 - Det er laget test-filer som skal teste funksjonaliteten til minesweeper-applikasjonen. Ved å kjøre "mvn jacoco:report" får vi resultatet at vi dekker 48% av instruksjoner og 64% av "branches". Til nå tester de enkel funksjonalitet i Square.java og Minefield.java.
 - Det er også laget README-filer:
   - README-filen i rotnivå gir en oversikt over prosjektet sin mappestruktur. I tillegg har den satt opp gitpod.
   - README-filen i kodelageret har en beskrivelse av minesweeper. Det er også skrevet en brukerhistorie.
