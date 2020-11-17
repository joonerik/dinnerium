package dinnerium.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Ingredient;
import dinnerium.json.DinneriumModule;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for serving the RestServer with methods needed to add ingredients to a User.
 */
public class IngredientService {

    private final ObjectMapper mapper;
    private final UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(IngredientService.class);

    /**
     * Instantiate a IngredientService object with a UserService to the userService field, and
     * registers DinneriumModule as a module to the ObjectMapper.
     */
    public IngredientService() {
        mapper = new ObjectMapper();
        userService = new UserService();
        mapper.registerModule(new DinneriumModule());
    }

    /**
     * Adds a ingredient to the user with the corresponding username that is provided, and returns
     * the user on json format with the new ingredient added to it.
     *
     * @param requestBody the request body containing the ingredient to be added.
     * @param username    the username of the user where the ingredient should be added.
     * @return the user on json format with the new ingredient added to it.
     * @throws IOException if the ingredient is not on the correct json-format.
     */
    public String addIngredient(String requestBody, String username) throws IOException {
        Ingredient ingredient = mapper.readValue(requestBody, Ingredient.class);
        if (ingredient == null) {
            LOGGER.error("Couldn't create ingredient of request body: {}, for user {}", requestBody,
                username);
            throw new IOException("Ingredient not on proper json-format");
        }
        return userService.addIngredient(ingredient, username);
    }
}