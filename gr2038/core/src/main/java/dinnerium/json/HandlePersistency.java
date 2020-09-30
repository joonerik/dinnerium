package dinnerium.json;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.IngredientContainer;
import dinnerium.core.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public class HandlePersistency {

    // writes a container containing a list of ingredients
    /**
     * Writes a IngredientContainer to file
     *
     * @throws IOException
     *         if file error
     */
    public static void writeJsonToFile(User user) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/data.json"), user);
    }

    // returns a container containing a list of ingredients
    // this list/container is our current stock of ingredients "in our fridge"
    /**
     * loads a IngredientContainer from file
     *
     * @return ingredientContainer
     * @throws IOException
     *         if file error
     */
    public static User loadDataFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
        User user =
                mapper.readValue(Paths.get(("src/main/data.json")).toFile(),
                                 User.class);
        return user;
    }

}
