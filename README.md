[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2038/gr2038/)

# Dinnerium 💥

<img src="http://folk.ntnu.no/anderobs/images/dinnerium.png" alt="Our logo" width="800" height="300">

Dette prosjektet er en del av emnet IT1901 gjennomført høsten 2020. Målet er å lage en trelagsapplikasjon med et domenelag, brukergrensesnitt og lagring.
Man skal ha tester for alle lag, og prosjektet skal være konfigurert med maven. Bygget er rigget med jacoco plugin for å rapportere testdekningsgraden til prosjektet (mvn verify).
Det er også rigget opp med plugins for å sjekke kodekvalitet, samt for å se etter typiske bugs. Resultatet av sjekkene rapporteres inn til target-mappen.
I master-branchen kjøres også en pipeline for å sjekke testdekningsgraden på _core_-modulen. Grunnen til at det ikke kjøres på _fxui_-modulen er at testene på
denne modulen ikke støtter GitLab sin CI for Java 14.

JavaFX-applikasjonen benytter seg av [fem forskjellige kontrollere](documentation/diagrams/fxui_class_diagram.png) og tilhørende fxml-ark. Dette er gjort for å skille ut kode og gjør at de tilhørende scenene i applikasjonen har hver sin kontroller. NavbarController er "foreldre" controller
til FridgeController, RecipeController og SettingsController ettersom at NavBarControlleren har ansvar for å bytte mellom de forskjellige scenene. 

I del tre av prosjektet valgte vi som gruppe å benytte oss av React for å bytte frontend. Dette gjorde vi fordi vi ønsket å lage en webapplikasjon, samt utvide
kompetansen vår. Sammen med React bruker vi TypeScript for å få typesjekking som er sentralt for å sikre at det er lettere å oppdage bugs, og at vi sender riktig
informasjon til backend. Vi bruker også Prettier for å sørge for god kodekvalitet i kildekoden til React-applikasjonen. Ved kjøring av applikasjonen vil det da dukke opp
evt. varsler i konsollen om dårlig kodekvalitet i applikasjonen. Kommandoen _npm run format_ vil også formatere kildekoden dersom den kjøres fra ui-mappen.
For å teste dette brukergrensesnittet brukes rammeverket Cypress.

Vi ønsker å lage en applikasjon som skal hjelpe deg som bruker å planlegge middager. Dette skal skje gjennom en oversikt over varer man har tilgjengelig,
samt oppskrifter man har brukt. Gjennom [brukerhistorie 2](documentation/brukerhistorier.md) vil man derfor kunne se varene sine og oppskrifter man tidligere har brukt og lagret
i applikasjonen. Applikasjonen skal til syvende og sist hjelpe deg å finne aktuelle oppskrifter basert på varene man allerede har, og bidra i planleggingen av ukesmenyen.

Applikasjonen benytter Jackson-biblioteket for å lagre data i json-format. All data som utveksles mellom bruker og backend skjer gjennom et REST API, som er implementert ved hjelp av rammeverket Spark.

Vi bruker implisitt lagring hvor en bruker vil kunne lagre sine varer og oppskrifter automatisk, uten å måtte eksplisitt tenke over lagring. Grunnen til at vi benytter implisitt lagring over en dokumentmetafor er fordi det er naturlig
for vår applikasjon å lagre data først når data er ferdig konstruert, f.eks at en hel oppskrift er blitt opprettet.

## Hvordan kjøre prosjektet 🚀

Fordi vi har to forskjellige brukergrensesnitt i JavaFX og React, har vi valgt å kjøre prosjektet på følgende måte:

- Backend og REST API må uansett kjøres. Deretter velger man om man vil kjøre JavaFX-applikasjonen eller React-applikasjonen.
- For å bygge prosjektet bruker man _mvn install_ fra roten, altså _dinnerium_-mappen.

```bat
cd dinnerium
mvn install
cd restapi
mvn exec:java
```

- For å kunne kjøre JavaFX-applikasjonen må man ha installert modulen som _fxui_ er avhengig av, altså _core_-modulen. Dette skjer ved _mvn install_ ovenfor.
- For å kjøre selve JavaFX-applikasjonen kan man gå inn i _fxui_-modulen, og deretter bruke _mvn javafx:run_. 
- JavaFX-applikasjonen vil åpnes på port 6080 i gitpod. 

```bat
cd dinnerium/fxui
mvn javafx:run
```

- For å bygge React-applikasjonen bruker man _npm install_ fra ui-mappen som ligger på rotnivå.
- For å kjøre selve React-applikasjonen bruker man _npm start_.
- React-applikasjonen vil åpnes på port 3000 i gitpod.

```bat
cd ui
npm install
npm start
```

## Hvordan teste prosjektet 🧪

### JavaFX-applikasjonen

- Når man kjører _mvn install_ i _dinnerium_-mappen blir automatisk testene til alle moduler kjørt, utenom _ui_-modulen som ligger på toppnivå. Testene til JavaFX er satt opp med testrammeverket _TestFX_ som kan finne elementer i applikasjonen, og kan samhandle med de.

For å f.eks kjøre testene til kun JavaFX applikasjonen kan man:

```bat
cd dinnerium/fxui
mvn verify
```

- Etter at testene har kjørt får du en tilbakemelding i terminalen om hvordan det har gått. Det blir også generert en html rapport i _target_-mappen under _fxui_-modulen. Denne ligger i site/jacoco/index.html.

### React-applikasjonen

- For å teste _React_-applikasjonen har vi valgt å bruke testrammeverket _Cypress_. Når testene kjøres vil ulike funksjoner i applikasjonen testes på samme måte som JavaFX-applikasjonen. For at testene skal fungere må _RestServer_ kjøre, slik at forespørslene som testene foretar seg kan besvares. Derfor må man først installere _restapi_-modulen og kjøre serveren derfra før man starter testene. Merk at i Gitpod vil testene kun kjøres headless.

For å starte serveren:

```bat
cd dinnerium/restapi
mvn install
mvn exec:java
```

Deretter starter man testene fra en annen terminal:

```bat
cd ui
npm install
npm run test:react
```

- Etter at testene har kjørt genereres det testrapporter fra hver testfil. For å sammenfatte disse i et brukervennlig format, kan man åpne en html-fil ved kommandoen under. I Gitpod kan man deretter bruke _preview_-funksjonen for å se rapporten.

```bat
cd ui
npm run cypress:report
```

## Innhold og organisering 🎨

Mappestrukturen til prosjektet er organisert følgende:

- [**core/src/main/java**](/dinnerium/core/src/main/java) utgjør kodingsprosjektet. Videre har vi mapper for å skille koden som brukes til hva.
- [**core/src/test/java**](/dinnerium/core/src/test/java) for testkoden til kjernefunksjonaliteten til prosjektet.
- [**fxui/src/main/java**](/dinnerium/fxui/src/main/java) utgjør kildekoden til JavaFX-applikasjonen.
- [**fxui/src/main/resources**](/dinnerium/fxui/src/main/resources) for ressurser som bilder, FXML-filer, stilark osv.
- [**fxui/src/test/java**](/dinnerium/fxui/src/test/java) for testkoden til JavaFX-applikasjonen.
- [**fxui/src/test/resources**](/dinnerium/fxui/src/test/resources) ressurser til fxui-testene
- [**integrationtest/src/test/java**](/dinnerium/integrationtest/src/test/java) for testkoden til integrasjonstesten for JavaFX og restapiet.
- [**integrationtest/src/test/resources**](/dinnerium/integrationtest/src/test/resources) ressurser til integrasjonstestene.
- [**restapi/src/main/java**](/dinnerium/restapi/src/main/java) utgjør kildekoden til RestServeren vår og service klassene den bruker.
- [**restapi/src/main/resources**](/dinnerium/restapi/src/main/resources) Utgjør brukerdata til alle brukerne som er registrert.
- [**restapi/src/test/java**](/dinnerium/restapi/src/test/java) Utgjør testkoden til restapi modulen.
- [**ui/src**](/ui/src) Utgjør kildekode til react-applikasjonen med alle sidene, og komponentene våre, samt stilark.
- [**ui/cypress**](/ui/cypress) Kode for testene til react-applikasjonen, samt rapporter fra testene.

Man kan også finne et [mappetre her](documentation/document_tree.md)

## Brukergrensesnitt 💄

<img src="http://folk.ntnu.no/anderobs/images/fridge.png" alt="JavaFX UI" width="600" height="400">
<img src="http://folk.ntnu.no/anderobs/images/fridgePageStyleReact.png" alt="React UI" width="600" height="300">

Som man ser, er de to ulike brukergrensesnittene relativt like, da det var et krav om at begge brukergrensesnittene skulle ha tilnærmet lik funksjonalitet og design. Små endringer i f.eks. fargevalg er likevel gjort.

## Dokumentasjon 📝

I mappen [documentation](documentation) finner man all tilhørende dokumentasjon som ikke befinner seg i denne README-en.

- Diagrammer finner man [her](documentation/diagrams). Denne mappen kan gjøre det lettere å forstå arkitekturen og informasjonsflyten i applikasjonen.
- Brukerhistorier finner man [her](documentation/brukerhistorier.md). Brukerhistoriene inneholder ikke mange krav om funksjonalitet, da man i innlevering 3
  kunne velge om å utvide med mer funksjonalitet, eller bytte frontend modulen til f.eks React.
- Java-doc dokumentasjon finner man [her](http://folk.ntnu.no/anderobs/javadoc/). Dette er skrevet for alle Java-klasser med
  tilhørende metodebeskrivelser i hele prosjektet.

## Gitlab CI/CD

Vi har implementert en Gitlab CI/CD pipeline som installerer og tester _core_-modulen vår, og bygger React-applikasjonen for å detektere eventuelle byggfeil. Dette sjekkes hver gang noe pushes opp til Gitlab, og forsikrer oss at nye endringer ikke "brekker" applikasjonen. Testene til _fxui_-modulen og integrasjonstesten er ikke med, ettersom pipelinen ikke støtter måten TestFX tester JavaFX-applikasjonen på.
