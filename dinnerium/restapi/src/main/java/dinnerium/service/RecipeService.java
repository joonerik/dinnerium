package dinnerium.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Recipe;
import dinnerium.json.DinneriumModule;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecipeService {
    private final ObjectMapper mapper;
    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RecipeService.class);

    /**
     * Instantiate a RecipeService object with a UserService to the userService field, and registers
     * DinneriumModule as a module to the ObjectMapper.
     */
    public RecipeService() {
        mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
        userService = new UserService();
    }

    /**
     * Adds a recipe to the user with the corresponding username that is provided, and returns the
     * user on json format with the new recipe added to it.
     *
     * @param requestBody the request body containing the recipe to be added.
     * @param username    the username of the user where the recipe should be added.
     * @return the user on json format with the new Recipe added to it.
     * @throws IOException if the recipe is not on the correct json-format.
     */
    public String addRecipe(String requestBody, String username) throws IOException {
        Recipe recipe = mapper.readValue(requestBody, Recipe.class);
        if (recipe == null) {
            LOGGER.error("Couldn't create ingredient of request body: {}, for user {}", requestBody,
                username);
            throw new IOException("Recipe not on the proper format");
        }
        return userService.addRecipe(recipe, username);
    }
}
