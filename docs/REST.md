
# REST-API


I iterasjon 3 har vi implementert et REST-API, som klient-siden skal interagere med for å aksessere data på server-siden.

  

## Metodene

Ettersom det eneste som blir lagret og aksessert er highscorelisten, trengte vi ikke veldig mange HTTP-metoder for å kunne behandle dataen slik vi trengte.

  

De to HTTP-metodene man trenger for å benytte seg av REST-API-et vårt er:

  

* GET:

    * 'http://localhost:8080/minesweeper/highscores/{difficulty}'

* POST:

    * 'http://localhost:8080/minesweeper/highscores/{difficulty}/save'

  

Her er `{difficulty}` en variabel, som kan være enten `Easy`, `Medium` eller `Hard`. Dersom variabelen er noe annet, vil det oppstå en feilkode, 404.

  

### GET

  

Sender man en GET-forespørsel på adressen nevnt over, vil man få returnert dataen som er lagret i easyHighscoreList.json.

Dataen vil først bli deserialisert til et Highscorelist-objekt, med HighscoreEntry-objekter inni. Deretter vil Spring Boot serialisere disse objektene med serialisereren vi har gitt til Spring Boot-applikasjonen. 

Deretter vil HighscoreAccess-filen i UI-modulen deserialisere JSON-informasjonen og gjenskape Highscorelisten med HighscoreEntries.

Eksempel på returnert data:
```json
{
    "maxSize": 5,
    "entries": [
        {
            "name": "Test1",
            "score": 1
        },
        {
            "name": "Test2",
            "score": 2
        }
    ]
}
```

### POST

Sender man en POST-forespørsel til adressen over, med en HighscoreEntry som body, så skal det (forsøke å) lagre HighscoreEntry-en i Highscorelisten på den gitte vanskelighetsgraden.

HighscoreFileHandler vil aller først lese highscorelisten, for så å kalle på addEntry-metoden inni Highscoreliste-objektet. Intern logikk vil deretter bestemme om dataen lagres i Highscorelisten.

Uansett hva Highscorelisten gjør, vil den eventuelt oppdaterte Highscorelisten bli lagret til JSON-filen som tilhører den bestemte vanskelighetsgraden.

Dersom forespørselen går gjennom, vil verdien <span style="color:rgb(50, 168, 82)">true</span> returneres.
