[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2243/gr2243) 

# Group gr2243 repository 

Kodeprosjektet er i minesweeper-mappen. Her finner man pom.xml-filen, men man finner også de to hovedmodulene core og ui. 

### Core
Core inneholder kjerneklassene som styrer logikken og lagringen i prosjektet. I src finner man en main mappe som inneholder kjerneklassene og fillagringsklassen, i tillegg finner man resources som inneholder lagringsfilen data.json, og i test finner man tester for lagring og logikk.

### Ui
Ui inneholder frontendklassene som styrer fxml filen, og bruker kjerneklassene til å kjøre prosjektet. I src finner man main mappe som inneholder selve app klassen, kontrollerklassen, og view klasse som håndterer bildene som skal settes inn i appen. Man finner også en testmappe med tester som tester om logikken og frontenden spiller sammen på måten de var laget for.

I docs-mappen finner man dokumenter med informasjon om hver enkelt release. 

## Mappe-hierarki:
minesweeper/ (kodeprosjekt)
-
- config/
    - checkstyle/
        - sun_checks.xml
    - sputbugs/
        - exclude.xml
- [core/](/minesweeper/core/) (Kjernemodul)
    - src/
        - main/
            - java/
                - minesweeper/core
                    - HighscoreEntry.java
                        - Lager et objekt som inneholder navn og poeng.
                    - HighscoreList.java
                        - Tar inn liste med HighscoreEntry og en maks størelse.
                    - Minefield.java
                        - klasse som inneholder logikken til selve griddet
                    - Square.java
                        - Her lagres verdien til en rute.
                - minesweeper/json
                    - internal/
                        - HighscoreListDeserializer.java
                        - HighscoreListSerializer.java
                        - HighscoreEntryDeserializer.java
                        - HighscoreEntrySerializer.java
                    - FileTreater.java
                        - Utfører alle lagringsrelaterte oppgaver, og lagrer dataen i data.json.
                - module-info.java
            - resources/minesweeper/json (Lagring)
                - data.json (holder på data)
        - test/java/minesweeper/core (Teste kjerneklassene)
            - HighscoreListTest.java
                - Tester HighscoreList.
            - MinefieldTest.java
                - Tester Minefield klassen
            - SquareTest.java
                - Tester Square klassen
        - test/java/minesweeper/json (Teste lagringsklassene)
            - FileTreaterTest.java
                - Tester FileTreat klassen
    - pom.xml
- [ui/](/minesweeper/ui/) (Frontendmodul)
    - src/
        - main/
            - java/
                - minesweeper/ui
                    - MinefieldView.java
                        - Brukes til å vise frem bilder i appen.
                    - MinesweeperApp.java
                        - Initialiser app.
                    - MinesweeperController.java
                        - Kontrolerer appens funksjonalitet.
                - module-info.java
            - resources/minesweeper/ui (fxml, stil og bilder)
                - data.json
                - flag.png
                - Minesweeper.fxml
                - style.css
        - test/java/minesweeper/ui (Teste funksjonalitet til app)
            - MinefieldViewTest.java
                - Tester ut funksjonaliteten til appen.
    - pom.xml
- pom.xml
- Architecture.puml
- README.md

## Oppsett
1. Clone prosjektet fra [her](https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2243/gr2243.git), eller åpne i gitpod (lenke ligger øverst i [README](README.md)).
2. For å bygge kjør `mvn install` fra rot-prosjektet. Dette vil kjøre tester og installere pakker som trengs for prosjektet.
3. Kjør `mvn javafx:run -f ui/pom.xml`, eller `cd ui` -> `mvn javafx:run`.
4. For tester kjør `mvn test` fra rot-prosjektet

## Verktøy for kodekvalitet
Vi bruker tre verktøy knyttet til kodekvalitet. Alle tre kan bli kjørt ved å kjøre `mvn verify` fra rot-prosjektet.
Ellers kan de bli kjørt for seg selv slik:
- [Checkstyle](https://checkstyle.sourceforge.io) -> `mvn checkstyle:check`
- [Spotbugs](https://spotbugs.github.io) -> `mvn spotbugs:check`
- [Jacoco](https://www.jacoco.org) -> `mvn jacoco:report`
