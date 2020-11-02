package dinnerium.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Recipe;
import dinnerium.json.DinneriumModule;
import java.io.IOException;

public class RecipeService {
    private final ObjectMapper mapper;
    private final UserService userService;

    public RecipeService() {
        mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
        userService = new UserService();
    }

    public String addRecipe(String requestBody, String username) throws IOException {
        Recipe recipe = mapper.readValue(requestBody, Recipe.class);
        if (recipe == null) {
            throw new IOException("Recipe not on the proper format");
        }
        return userService.addRecipe(recipe, username);
    }
}
