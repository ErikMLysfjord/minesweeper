# Standard commit beskjeder

Vi bestemte oss for å følge noen av konvensjonene fra denne nettsiden:  
https://dev.to/i5han3/git-commit-message-convention-that-you-can-follow-1709

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
