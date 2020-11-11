package dinnerium.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Ingredient;
import dinnerium.json.DinneriumModule;
import java.io.IOException;

public class IngredientService {

    private final ObjectMapper mapper;
    private final UserService userService;

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
     * summary.
     *
     * @param requestBody the request body containing the ingredient to be added.
     * @param username the username of the user where the ingredient should be added.
     * @return the user on json format with the new ingredient added to it.
     * @throws IOException if the ingredient is not on the correct json-format.
     */
    public String addIngredient(String requestBody, String username) throws IOException {
        Ingredient ingredient = mapper.readValue(requestBody, Ingredient.class);
        if (ingredient == null) {
            throw new IOException("Ingredient not on proper json-format");
        }
        return userService.addIngredient(ingredient, username);
    }
}