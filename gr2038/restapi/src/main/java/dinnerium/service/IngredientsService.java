package dinnerium.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Ingredient;
import dinnerium.json.DinneriumModule;
import java.io.IOException;

public class IngredientsService {

    private final ObjectMapper mapper;
    private final UserService userService;

    public IngredientsService() {
        mapper = new ObjectMapper();
        userService = new UserService();
        mapper.registerModule(new DinneriumModule());
    }

    public String addIngredient(String requestBody, String username) throws IOException {
        Ingredient ingredient = mapper.readValue(requestBody, Ingredient.class);
        return userService.addIngredient(ingredient, username);
    }
}