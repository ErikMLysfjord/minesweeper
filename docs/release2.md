# Andre iterasjon

## Hva har vi gjort
### Arkitektur
Vi startet med å dele opp prosjektet i to moduler, core og ui. Deretter delte vi opp core modulen i to pakker, minesweeper.json og minesweeper.core. Dette gir en full tre-lags arkitektur. Lagene er skilt for å følge model-view-controller prinsippet.

### Logikk og lagring
- Lagt til metoder for å plassere miner.
- Laget en HighscoreEntry klasse.
- Laget en HighscoreList klasse.
- Endret fra json simple til jackson.
- Nå lagrere vi til en highscore liste, istedenfor å skrive over en highscore entry.

### Testing av GUI
- Vi fant en måte å teste knapper og bilder på selv om de ikke har noe tekst ved gi de en id.
- Teste setting av flagg.
- Teste oppsettet av gridpane.

## Valg vi har tatt
### Standard commit beskjeder
Vi bestemte oss for å følge noen av konvensjonene fra denne nettsiden:  
[git-commit-conventions](https://dev.to/i5han3/git-commit-message-convention-that-you-can-follow-1709)

Dette ble bestemt i vårt andre møte denne sprinten.

Det innebærer (ikke alt er fra nettsiden):
- Små bokstaver og ingen punktum i subject
- Subject skrives i imperativ
- Body skrives som vanlig tekst med punktum og stor bokstav
- Engelsk språk
- Ingen issuenummer i vanlige commits. Bare i merge commiten står det f.eks. "Closes #23". Vi har det sånn fordi vi tenker at issues er knyttet til branches. Issuenummeret står i branchnavnet.
- "Co-authored by: 'navn'" i footer hvis vi har parprogrammert
- Starter commits med type commit:
    - feat:
    - fix:
    - docs:
    - style: (kodestil, ikke UI)
    - refactor:
    - test:
    - chore:
    
### Språk
Vi valgte å bruke norsk i dokumentasjonen, issues og merge requests.

Vi bruker engelske kommentarer og variabler fordi det er mer oversiktilg siden java er et engelsk kodespråk. Dette gir oss også fordeler i forhold til ferdiglagde kommentarer som javadocs.

### Javadocs
Vi valgte å bruke javadocs som kommenterings standard.

### Jackson
Vi valgte å bruke Jackson i stedet for simple JSON.

### Arbeidsdager
Vi har valgt å kjøre intesive arbeidsdager i stedenfor å jobbe individuelt. Dette gjør at vi kan jobbe mer i par.

Utenfor arbeidsdagene jobbet vi også individuelt.

### Testverktøy
For å forsikre kodekvalitet har vi valgt å bruke tre verktøy; spotbugs, checkstyle og jacoco. 
- Spotbugs
    - Spotbugs er konfigurert til å ignorere bugs som omhandler "DMI_RANDOM_USED_ONLY_ONCE". 
- Checkstyle
    - Vi valgte å ta utgangspunkt i "Sun Code"-konfigurasjonen, ettersom vi foretrakk den framfor Google sin konfigurasjon.
    - Checkstyle er i tillegg konfigurert litt på egenhånd:
        - JavaDocs for variabler er skrudd av, ettersom vi synes det var unødvendig å dokumentere hver enkelt variabel.
        - Vi valgte å skru av å ha en package-info.java-fil for hver package. Fordi vi ikke vet hva package-info er.
- Jacoco

## Arbeidsmetodikk
### Scrum
Vi fortsatte å jobbe utifra SCRUM modellen med en konsistent konvensjon.
- Hver issue har en branch.
- Andre iterasjons issues har mer utfyllende beskrivelser.
- Hver issue har en tillhørende branch der en person jobber. evt parprogramerer.
- Vi ble etterhvert strengere på at en merge request måtte bli sett over av en som ikke jobbet med issuen.
- Når man ser over en merge request kan man legge til kommentarer etter behov. Disse må løses før merge.

### Parprogrammering
Vi har parprogramert på alle arbeidsdager gjennom sprint 2. Vi har prøvd forskjellige kombinasjoner av par slik at alle får prøvd seg med alle.

### Testdrevet utvilking
Vi prøver å følge testdrevet utvikling. Vi skriver tester for en klasse for så å gjøre de nødvendige endringene for at klassen skal kjøre gjennom testen. Dette har kun blitt gjort i core modulen til nå fordi vi ikke hadde kontroll på ui- og persistens-testing.

## Brukerhistorier
Vi lagde tre brukerhistorier, i [brukerhistorier.md](../brukerhistorier.md).

De kommer til å bli mer brukt i neste iterasjon.

## Ting vi ikke rakk
### Issues:
- Åpne ruter.
- Vise miner i UI.
- Tilstand til åpne ruter.
- .gitignore fil for lagring.

### Testdekning
- Testing av persistens.
