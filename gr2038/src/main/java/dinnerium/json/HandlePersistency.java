package dinnerium.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.IngredientContainer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public class HandlePersistency {

    // writes a container containing a list of ingredients
    public static void writeJsonToFile(IngredientContainer ingredientContainer) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("src/main/data.json"), ingredientContainer);
    }

    // returns a container containing a list of ingredients
    // this list/container is our current stock of ingredients "in our fridge"
    public static IngredientContainer loadDataFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
        IngredientContainer ingredientContainer = mapper.readValue(Paths.get(("src/main/data.json")).toFile(), IngredientContainer.class);
        return ingredientContainer;
    }

}
