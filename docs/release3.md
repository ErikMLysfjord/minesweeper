# Tredje iterasjon

## Hva vi har gjort
___
### Logikk og lagring
Hele logikken til minesweeper er nå lagt til. Dette inkluderer alle kravene fra brukerhistoriene:

- Gjennomføre spillet
    - Det går nå an å både tape og vinne et spill. Taper man så får man beskjed om dette, mens vinner man så får man mulighet til å lagre sekundene man har brukt, og navnet.
- Restarte banen
    - Dersom man trykker på "restart"-knappen så restartes hele spillet.
- Vanskelighetsgrad
    - Det går nå an å spille på tre forskjellige vanskelighetsgrader: easy, medium og hard. Disse tre vanskelighetsgradene lagrer "highscores" til andre "highscorelister".
- Poengtavle
    - Det går nå an å trykke seg inn på en egen side, der man kan se highscores. Highscores hentes fra json-filer som inneholder dataen. Det skal gå an å se highscores til alle de tre forskjellige vanskelighetsgradene.
- Chord
    - Det går nå an å utføre chord på et åpnet felt ved hjelp av spacebar. I tilegg kan man toggle et uåpnet felt ved hjelp av spacebar.

Lagring skjer nå på tre separate json-filer, en for hver vanskelighetsgrad. 

### Arkitektur
En ny server modul er lagt til så projektet er nå delt i tre moduler: core, server og ui. 

### REST API
Vi valgte å lage en server som har highscore listene. Én for hver vanskelighetsgrad. Vi tok ikke hele minesweeper spillet over på server. Det hadde blitt veldig komplisert. Da kunne vi ikke brukt det action systemet vi har heller, hvor Minesweeper "sier i fra" når spillet f.eks. er vunnet.  

Vi har bare remote access. Altså vi tok bort å lagre direkte på klienten. Dermed må man ha oppe serveren for å se highscorelisten, eller å lagre til den. Vi kunne hatt direkte og remote, men vi ville ikke bruke tid på det.

Vi brukte Spring Boot for REST APIet. Vi valgte dette siden vi hadde sett todolist-eksempelet bruke denne teknologien, og den virket overkommelig.

Klient bruker HighscoresAccess-klassen til å kommunisere med serveren. Den sender HTTP-requests, som blir mottatt av HighscoreController på serveren. 

Se i [REST.md](/docs/REST.md) for dokumentasjon av REST APIet.

## Valg vi har tatt
___

### JLink
JLink lager et runtime image som bare har de modulene som vi trenger. Se [readme.md](../readme.md) for hvordan JLink og Jpackage brukes.

### JPackage
For at minesweeper skal være mulig å laste ned uten å klone hele prosjektet, må det pakkes sammen med JPackage. Da kommer problemet med at mappen til lagring ligger i prosjektet. Derfor bestemte vi oss for å lagre data i en mappe utenfor prosjektet.

### Spotbugs
Underveis i utviklingen kom vi bort i flere spotbug feil som var unødvendige. Vi brukte en del tid på å sjekke om det var bedre måter å gjøre det på, men endte opp med å ignorere dem.

**De 5 vi spotbugsfeilen vi ignorerer er:**
- "DMI_RANDOM_USED_ONLY_ONCE"  
Vi brukte et Random-objekt til å generere miner. Det så ut som denne ville ha oss til å lagre Random-objektet som felt for å gjenbruke den. Den ville aldri blitt gjenbrukt uansett.
- "EQ_COMPARETO_USE_OBJECT_EQUALS"  
Denne var ikke relevant for oss, og det var mye styr med å lage de metodene den ville ha.
- "RV_RETURN_VALUE_IGNORED_BAD_PRACTICE"  
Denne kom av at vi brukte en metode for å lage en ny fil og mappe som returnerte en Boolean om hvorvidt den fikk til å lage filen, men vi brukte ikke denne verdien til noe, så vi ignorerte derfor denne feilen.
- "EI_EXPOSE_REP2"  
Denne var på grunn av at MinesweeperApp gir stage til SceneManager sånn at den kan bytte scenen som vises på stagen. Den exposer stage, men det er jo hele poenget. Vi fant ikke noen begrunning for at dette skal være et problem.
- "SA_LOCAL_SELF_COMPARISON"  
Denne feilen kom noen ganger når man ikke hadde clean installet på en stund. Etterhvert ble vi lei av å få en feil uten at det var noen feil i programmet så vi ignorerte den.

### Boards
Vi la inn to nye kategorier for oppdeling av issues for å gjøre det mer oversiktlig over hvem som gjør hva:
- In progress
- QA

**In progress**  
In progress er en kategori som er ment for å holde oversikt over hvilke issues som arbeides på, og skiller issuene fra resten av de åpne issuene.

**QA**  
QA står for "quality assurance". Hensikten med denne kategorien var å holde oversikt over hvilke issues som venter på at en reviewer skal se over, eller eventuelt at issuen har "threads" som ikke er løst opp.

### Ny funksjonalitet på eksisterende system
Vi valgte å fortsette på javafx applikasjonen vår. Vi hadde ikke funksjonalitet som et komplett minesweeper spill ville ha hatt, og vi valgte derfor å implementere dette istedenfor å endre teknologi.

### Flere sprints
Vi valgte å ha 2 sprints i denne iterasjonen. 

I den første sprinten fokuserte vi på å implementere all funksjonalitet.

 I den andre sprinten fokuserte vi på REST-API'et og dokumentasjon. Dette ble gjort for å holde fokus på én del av projektet om gangen. I tillegg ville vi få unnagjort funksjonaliteten før vi begynte på REST-API.

### Pipeline
Etter en uheldig hendelse, der en rekke feilmeldinger ble merget inn i master ved et uhell, bestemte vi oss for å sette opp en pipeline.

Pipelinen som ble satt opp har to steg: verify og build.

**Verify**  
Verify kjører først kommandoen `cd minesweeper`, deretter `mvn clean verify -DskipUItests=true`. Den siste kommandoen ble lagt til siden pipelinen ikke kan få opp skjermen som kommer opp når UI-testene gjennomføres.

Når verify gjennomføres så blir testene kjørt, og checkstyle og spotbugs blir også kjørt. Dersom det enten er test-feil, checkstyle failures, eller bugs, så stopper pipelinen opp.

**Build**  
Build kjører først kommandoen `cd minesweeper`, deretter `mvn clean install -DskipUItests=true`, av de samme grunnene som tidligere nevnt.

Når clean install gjennomføres installerer den hele prosjektet på nytt. Som resultat av dette vil det være som å kjøre det første steget om igjen, bare enda litt mer grundig.

## Arbeidsmetodikk
___
Vi prøvde vårt beste å følge standardene vi satt opp, men det var noen ganger vi glemte oss bort.

### Scrum
Vi fortsatte å jobbe utifra SCRUM modellen med en konsistent konvensjon.

### Parprogramering
Vi brukte mindre og mindre parprogrammering. Parprogrammering var greit for å lære av hverandre, men det var ikke så effektivt. Etterhvert ble det mange veldig spesifike issues, og da ga det mer mening å spre arbeidskraften ut så mye som mulig. Dette gjorde at vi fikk gjort mange små issues på kortere tid.

Parprogrammering hjalp for kodekvalitet også, siden vi hadde to par øyner til å se feil, men code reviews gjorde denne jobben bedre.

### Branch- og issue-navn
I denne iterasjonen har vi blitt litt strengere på både issue-navn og branch-navn. 

Vi begynte å ha kortere navn på issues, slik at vi kunne lage branches med samme navn. Tidligere i prosjektet skjedde det at vi lagde branch-navn som kortet ned på issue-navnet. Etter vi begynte med strengere issue-navn ble resultatet tydeligere sammenheng mellom issues og branches.

### Code Reviews
Vi startet med code reviews for å øke kodekvaliteten. I starten hadde vi ikke noe serlig system på det, men så bestemte vi oss for at:
- Assignee er den som gjennomførte issuen.
- Assignee skal helst beskrive endringene utført i merge requesten, sånn at det blir lett for reviewer å få oversikt. I visse tilfeller også legge ved et illustrerende bilde.
- Reviewer ser gjennom. Hvis revieweren finner noen feil eller en bedre måte å gjøre det på, skriver den review på GitLab.
- Assignee må rette opp "trådene" i reviewet til reviewer. Dette må gjøres før reviewer approver.
- Reviewer må approve merge requesten før den blir merget.
- Til slutt skal assignee merge, og eventuelt håndtere merge conflicts.

Code reviews funket veldig bra. Det viste seg at det var veldig lett å gjøre et par feil her og der, og code reviews fikset ofte opp i dem. Det førte også ofte til bedre navn, og smartere løsninger.

## Brukerhistorier
___
Vi har fem brukerhistorier til sammen i [brukerhistorier.md](../brukerhistorier.md). "Chord" og "Restarte banen" er nye.

Brukerhistoriene er kravene for at spillet skal fungere som minesweeper. 

## Testing
___

### Integration tests
I server-modulen ble det skrevet integration tests for å teste samhandlinga mellom kontroller-laget og persistens-laget. MockMvc ble brukt for å kunne skape både POST- og GET-forespørsler. 

Funksjonaliteten til RestController-en ble testet i denne integration-testen, og passet på at riktig informasjon ble hentet ut. Dermed fikk vi testet at kontroller- og persistens-laget samhandler. 

### Persistens
I forrige iterasjon fikk vi ikke tid til å teste persistens. Det har vi gjort nå. 

### WireMock
I UI-testene som trenger å hente en Highscoreliste, bruker vi nå WireMock. Dette er så UI-testene kan testes, selv uten å måtte kjøre `mvn spring-boot:run` i server-modulen.

WireMock ble også brukt for å teste HighscoresAccess. Dette ble gjort så vi kunne teste at klassen sendte riktig GET- og POST-forespørsler, uten å faktisk utveksle informasjon og involvere RESP API-et.

### Jacoco
Alle delene ved applikasjonen får nå jacoco-report. Alle klassene har en viss del av klassen som blir testet, utenom MinesweeperApp.java, men dette syntes vi ikke det var noe vits å teste.