package dinnerium.server;

import static spark.Spark.get;
import static spark.Spark.post;

import dinnerium.core.Units;
import dinnerium.service.IngredientService;
import dinnerium.service.RecipeService;
import dinnerium.service.UserService;
import java.io.IOException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestServer.class);

    /**
     * Main method to start the spark rest server on localhost:4567.
     *
     * @param args arguments of the main method.
     */
    public static void main(String[] args) {
        final UserService userService = new UserService();
        final IngredientService ingredientService = new IngredientService();
        final RecipeService recipeService = new RecipeService();

        get("/units", (req, res) -> {
            res.type("application/json");
            return Arrays.toString(Units.values());
        });

        post("/users/login", (req, res) -> {
            res.type("application/json");
            String response = userService.getUser(req.body());
            if (response.equals("null")) {
                res.status(404);
            } else {
                res.status(200);
            }
            return response;
        });

        post("/users/register", (req, res) -> {
            res.type("application/json");
            String response = userService.registerUser(req.body());
            if (response.equals("null")) {
                res.status(400);
            } else {
                res.status(201);
            }
            return response;
        });

        post("/users/:username/ingredients/add", (req, res) -> {
            res.type("application/json");
            String response = "null";
            try {
                response = ingredientService.addIngredient(req.body(), req.params(":username"));
                res.status(202);
            } catch (IOException e) {
                res.status(400);
            }
            return response;
        });

        post("/users/:username/recipes/add", (req, res) -> {
            res.type("application/json");
            String response = "null";
            try {
                response = recipeService.addRecipe(req.body(), req.params(":username"));
                res.status(202);
            } catch (IOException e) {
                res.status(400);
            }
            return response;
        });
    }
}
