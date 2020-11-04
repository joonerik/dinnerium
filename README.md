[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2038/gr2038/)

# Dinnerium 💥

![logo](http://folk.ntnu.no/anderobs/images/dinnerium.png "Our logo")

Dette prosjektet er en del av emnet IT1901 gjennomført høsten 2020. Målet er å lage en trelagsapplikasjon med et domenelag, brukergrensesnitt og lagring.
Man skal ha tester for alle lag, og prosjektet skal være konfigurert med maven. Bygget er rigget med jacoco plugin for å rapportere testdekningsgraden til prosjektet (mvn verify).
Det er også rigget opp med plugins for å sjekke kodekvalitet, samt for å se etter typiske bugs. Resultatet av sjekkene rapporteres inn til target-mappen.
I master-branchen kjøres også en pipeline for å sjekke testdekningsgraden på _core_-modulen. Grunnen til at det ikke kjøres på _fxui_-modulen er at testene på
denne modulen ikke støtter GitLab sin CI for Java 14.

I del tre av prosjektet valgte vi som gruppe å benytte oss av React for å bytte front end. _børge skriv litt her om react build_. For å teste dette brukergrensesnittet brukes rammeverket Cypress.

Vi ønsker å lage en applikasjon som skal hjelpe deg som bruker å planlegge middager. Dette skal skje gjennom en oversikt over varer man har tilgjengelig,
samt oppskrifter man har brukt. Gjennom [brukerhistorie 2](documentation/brukerhistorier.md) vil man derfor kunne se varene sine og oppskrifter man tidligere har brukt og lagret
i applikasjonen. Applikasjonen skal til syvende og sist hjelpe deg å finne aktuelle oppskrifter basert på varene man allerede har, og bidra i planleggingen av ukesmenyen.

Applikasjonen benytter Jackson-biblioteket for å lagre data i json-format. All data som utveksles mellom bruker og back end skjer gjennom et REST API, som er implementert ved hjelp av rammeverket Spark.

Vi bruker implisitt lagring hvor en bruker vil kunne lagre sine varer og oppskrifter
automatisk, uten å måtte eksplisitt tenke over lagring. Grunnen til at vi benytter implisitt lagring over en dokumentmetafor er fordi det er naturlig
for vår applikasjon å lagre data først når data er ferdig konstruert, f.eks at en hel oppskrift er blitt opprettet.

## Hvordan kjøre prosjektet 🚀

Fordi vi har to forskjellige brukergrensesnitt i _JavaFX_ og _React_ (som ser ganske like ut), har vi valgt å kjøre prosjektet på følgende måte:

- Back end og REST API må uansett kjøres. Deretter velger man om man vil kjøre JavaFX-applikasjonen eller React-applikasjonen.
- For å bygge prosjektet bruker man _mvn install_ fra roten, altså _gr2038_-mappen.

```bat
cd gr2038
mvn install
cd restapi
mvn exec:java
```

- For å kunne kjøre JavaFX-applikasjonen må man ha installert modulen som _fxui_ er avhengig av, altså _core_-modulen. Dette skjer ved _mvn install_ ovenfor.
- For å kjøre selve JavaFX-applikasjonen kan man gå inn i _fxui_-modulen, og deretter bruke _mvn javafx:run_.

```bat
cd gr2038/fxui
mvn javafx:run
```

- For å bygge React-applikasjonen bruker man _npm instakk_ fra ui-mappen som ligger på rotnivå.
- For å kjøre selve React-applikasjonen bruker man _npm start_.

```bat
cd ui
npm install
npm start
```

## Innhold og organisering 🎨

Mappestrukturen til prosjektet er organisert følgende:

- **core/src/main/java** utgjør kodingsprosjektet. Videre har vi mapper for å skille koden som brukes til hva.
- **core/src/main/resources/storage** for lagring av brukerdata.
- **core/src/test/java** for testkoden til kjernefunksjonaliteten til prosjektet.
- **fxui/src/main/java** utgjør kildekoden til JavaFX-applikasjonen.
- **fxui/src/main/resources** for ressurser som bilder, FXML-filer, stilark osv.
- **fxui/src/test/java** for testkoden til JavaFX-applikasjonen.
- **fxui/src/test/resources** ressurser til fxui-testene

Man kan også finne et [mappetre her](documentation/document_tree.md)

## Brukergrensesnitt 💄

![UI for fridge](http://folk.ntnu.no/anderobs/images/fridge.png "The fridge UI")

Som man ser, er de to ulike brukergrensesnittene relativt like, da det var et krav om at begge brukergrensesnittene skulle ha tilnærmet lik funksjonalitet og design. Små endringer i f.eks. fargevalg er likevel gjort.

## Dokumentasjon 📝

I mappen [documentation](documentation) kan finner man diverse diagrammer og javadoc-dokumentasjon. Denne mappen kan gjøre det lettere å forstå arkitekturen og informasjonsflyten i applikasjonen.
Her finner man også samtlige [brukerhistorier](documentation/brukerhistorier.md). Brukerhistoriene inneholder ikke mange krav om funksjonalitet, da man i innlevering 3 kunne velge om å utvide med mer funksjonalitet, eller bytte front end modulen til f.eks React.

```

```
