[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.idi.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2020/gr2038/gr2038/)

# Dinnerium 💥

![logo](http://folk.ntnu.no/anderobs/images/dinnerium.png "Our logo")

Dette prosjektet er en del av emnet IT1901 gjennomført høsten 2020. Målet er å lage en trelagsapplikasjon med et domenelag, brukergrensesnitt og lagring.
Man skal ha tester for alle lag, og prosjektet skal være konfigurert med maven. Bygget er rigget med jacoco plugin for å rapportere testdekningsgraden til prosjektet (mvn verify).
Det er også rigget opp med plugins for å sjekke kodekvalitet, samt for å se etter typiske bugs. Resultatet av sjekkene rapporteres inn til target-mappen.

Vi ønsker å lage en applikasjon som skal hjelpe deg som bruker å planlegge middager. Dette skal skje gjennom en oversikt over varer man har tilgjengelig,
samt oppskrifter man har brukt. Gjennom [brukerhistorie 2](brukerhistorier.md) vil man derfor kunne se varene sine og oppskrifter man tidligere har brukt og lagret
i applikasjonen. Applikasjonen skal til syvende og sist hjelpe deg å finne aktuelle oppskrifter basert på varene man allerede har, og bidra i planleggingen av ukesmenyen.

Applikasjonen benytter Jackson-biblioteket for å lagre data i json-format. Vi bruker implisitt lagring hvor en bruker vil kunne lagre sine varer og oppskrifter
automatisk, uten å måtte eksplisitt tenke over lagring. Grunnen til at vi benytter implisitt lagring over en dokumentmetafor er fordi det er naturlig
for vår applikasjon å lagre data først når data er ferdig konstruert, f.eks at en hel oppskrift er blitt opprettet.

## Hvordan kjøre prosjektet 🚀

Prosjektet er satt opp med maven.

```bat
cd gr2038
mvn install
cd fxui
mvn javafx:run
```

- For å bygge prosjektet bruker man _mvn install_ fra roten, altså _gr2038_-mappen.
- For å kjøre prosjektet kan man gå inn i _fxui_-modulen, og deretter bruke _mvn javafx:run_.
  Man må først ha installert modulen som _fxui_ er avhengig av, altså _core_-modulen.

## Innhold og organisering🎨

Mappestrukturen til prosjektet er organisert følgende:

- **core/src/main/java** utgjør kodingsprosjektet. Videre har vi mapper for å skille koden som brukes til hva.
- **fxui/main/resources** for ressurser som bilder, FXML-filer osv.
- **core/src/test/java** for testkoden til applikasjonen
- **core/src/test/resources** for ressurser som brukes av testene.

Det viktigste fra mappetreet finnes her:

```
📦gr2038
 ┣ 📂config
 ┃ ┗ 📂maven-checkstyle
 ┃ ┃ ┗ 📜google_checks.xml
 ┣ 📂core
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂dinnerium
 ┃ ┃ ┃ ┃ ┃ ┣ 📂core
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Container.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Ingredient.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IngredientContainer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Metadata.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Quantity.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Recipe.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipeContainer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipeInstructions.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜User.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂json
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ContainerDeserializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ContainerSerializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜DinneriumModule.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HandlePersistency.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IngredientDeserializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IngredientSerializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MetadataDeserializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MetadataSerializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜QuantityDeserializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜QuantitySerializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipeDeserializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipeInstructionsDeserializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipeInstructionsSerializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipeSerializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜UserDeserializer.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserSerializer.java
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┃ ┗ 📂dinnerium
 ┃ ┃ ┃ ┃ ┃ ┗ 📂storage
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜anders.json
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜data.json
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜testUser.json
 ┃ ┃ ┗ 📂test
 ┃ ┃ ┃ ┗ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂dinnerium
 ┃ ┃ ┃ ┃ ┃ ┣ 📂core
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IngredientContainerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IngredientTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MetadataTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜QuantityTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipeContainerTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipeInstructionsTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipeTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜UserTest.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂json
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜DinneriumModuleTest.java
 ┃ ┗ 📜pom.xml
 ┣ 📂fxui
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂dinnerium
 ┃ ┃ ┃ ┃ ┃ ┗ 📂ui
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜App.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜AppController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜FeedbackHandler.java
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┃ ┗ 📂dinnerium
 ┃ ┃ ┃ ┃ ┃ ┗ 📂ui
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜app.fxml
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜feedback-handler.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜recipe-pane.css
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜table-view-style.css
 ┃ ┃ ┗ 📂test
 ┃ ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂dinnerium
 ┃ ┃ ┃ ┃ ┃ ┗ 📂ui
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜AppTest.java
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┃ ┗ 📂dinnerium
 ┃ ┃ ┃ ┃ ┃ ┗ 📂ui
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜appTest.fxml
 ┃ ┗ 📜pom.xml
 ┗ 📜pom.xml
```

## Interfacet💄

![UI for fridge](http://folk.ntnu.no/anderobs/images/fridge.png "The fridge UI")

Nytt design er implementert, og vi er strålende fornøyd! Det nye designet er mye mer intuitivt, og ser visuelt bra ut.

I det nye designet har vi tenkt på brukervennlighet og at det skal være lettere å utvide programmet.

## Dokumentasjon📝

I mappen `documentation` kan man finne en oversikt over klassediagrammer og javadoc-dokumentasjon.  
Denne mappen gjør det lettere å sette seg inn i hvordan vi har tenkt når vi har satt opp  
arkitekturen, og gjør det lettere for oss mens vi jobber også.
