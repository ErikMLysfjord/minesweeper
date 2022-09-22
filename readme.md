[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2243/gr2243) 

# Group gr2243 repository 

Kodeprosjektet er i minesweeper-mappen. Her finner man pom.xml-filen, men man finner også en src-mappe som inneholder selve prosjektet, i src-mappen, og en test-mappe som inneholder testene til prosjektet. 

I main-mappen finner du selve kodeprosjektet, og i resources-mappen finner du ressurser som JSON-filen som tar for seg lagring.

I docs-mappen finner man dokumenter med informasjon om hver enkelt release. 

## Mappe-hierarki:
minesweeper/
-
-  src/
    - main/
        - java/
            - minesweeper/ (kodings prosjekt)
                - FileTreater.java
                    - utfører alle lagringsrelaterte oppgaver
                - Minefield.java
                    - logikk for selve minefeltet
                - MinesweeperApp.java
                    - initialiser app
                - MinesweeperController.java
                    - kontrollerer logikk
                - Square.java
                    - et sted å lagre verdier
            - module-info.java
        - resources/ (bilder, fxml og lagring)
            - minefield/
                - data.json
                - flag.png
                - Minesweeper.fxml
                - style.css
    - test/
        - java/
            - minesweeper/ (teste kodingsprosjektet)
                - MineFieldTest.java
                - SquareTest.java
            - README.md


