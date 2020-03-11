# Database prosjekt

## Innledning

Programmet _filmDB_ er et java-program ment for å gi en lettere måte å interagere med film
databasen. Programmet lar brukere se detaljer om filmer, personer og selskaper, samt legge til nye
elementer i databasen. I tillegg er det funksjonalitet for å legge til brukerkommentarer og
anmeldelser av filmer og serier.

## Klasser i programmet

De ulike klassene i programmet er følgende:

- Main
- UI
- DBConn
- DBController
- BaseController
- PersonController
- UserController
- FilmController
- CategoryController
- FilmCompanyController

### Main

`Main` klassen har statiske attributter som inneholder relasjoner til henholdsvis en instans av alle
kontroller klassene. I tillegg har den en relasjon til en UI klasse, som håndterer alt av
brukergrensesnitt. Main klassen har en statisk main metode som starter hele programmet, ved å vise
frem en hovedmeny og håndtere logikk for å velge hvilke kontrollere som skal brukes basert på
brukeren sitt valg.

### UI

`UI` klassen definerer et sett med metoder som kan brukes av kontroller klassene for å kontrollere
input/output. UI metodene baserer seg alle sammen på host systemets input/output. Det gjør at alt av
brukergrensesnitt er tekst-basert. Viktige metoder i denne klasen er `menu`, som viser en menu og
returnerer brukerens valg, og `printItems` som tar inn et resultat fra en spørring og viser frem
dette. I tillegg er det definert flere andre metoder for bl.a. bruker input og error meldinger.

### DBConn

`DBConn` er en abstakt klasse som definerer metoder for å koble til, samt lage databasen. Denne
klassen brukes av alle kontrollere.

### DBController

`DBController` er en klasse som definerer et par metoder for å administrere databasen. Dette er
bl.a. å legge inn _fixtures_(eksempel data) og å opprette tabellene i databasen.

### BaseController

`BaseController` er en klasse som definerer metoder som vil være felles for alle kontrollerene.
Dette er kun `listAllItems()` som henter alle elementer fra den respektive tabellen, og viser disse
frem vha. `UI` klassen.

### PersonController

`PersonController` er en klasse som definerer metoder for å behandle personer i databasen. Den har
en metode for å vise en submeny for operasjoner den definerer. Dette
innebærer å vise frem alle personer, legge til nye personer, se detaljer om en bestemt person, og å
se alle roller for en skuespiller.

### UserController

`UserController` er en klasse som kontrollerer alt av bruker logikk i databasen. Den krever at man
har valgt en bruker, som enten kan gjøres ved å ta inn brukernavn, eller å lage en ny bruker. Videre
gir denne en meny som lar en liste alle kommentarer og anmeldelser, samt å legge inn en ny
kommentarer eller anmeldelse.

### FilmController

`FilmController` kontrollerer filmer/serier i databasen. Den har metoder for å liste alle filmer,
samt å legge til filmer.

### CategoryController

`CategoryController` kontrollerer kategorier. Den har metoder for å vise frem alle kategorier, og å
legge til nye.

### FilmCompanyController

`FilmCompanyController` har metoder som omhandler filmselskaper. Den gir metoder for å vise og legge
til selskaper, i tillegg til å liste opp alle filmselskaper som har produsert filmer innen en
kategori.
