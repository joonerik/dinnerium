package dinnerium.server;

import static spark.Spark.get;
import static spark.Spark.post;

import dinnerium.core.Quantity;
import dinnerium.service.IngredientsService;
import dinnerium.service.RecipeService;
import dinnerium.service.UserService;
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
        final IngredientsService ingredientsService = new IngredientsService();
        final RecipeService recipeService = new RecipeService();
        //Check if we should add response type as res.type("application/json");
        //https://www.the-lazy-dev.com/en/spark-for-beginners-create-restful-api-with-java-and-mongodb/
        get("/users/:name", (req, res) -> userService.getUser(req.params(":name")));

        get("/users/:username/ingredients", (req, res) -> userService
            .getContainerFromUser(req.params(":username"), "ingredientContainer"));

        get("/users/:username/recipes",
            (req, res) -> userService
                .getContainerFromUser(req.params(":username"), "recipeContainer"));

        get("/units", (req, res) -> Quantity.units);

        post("/users/login", (req, res) -> userService.getUserRequest(req.body()));

        post("/users/register", (req, res) -> userService.saveNewUser(req.body()));

        post("/users/:username/ingredients/add", (req, res) -> {
            String response = ingredientsService.addIngredient(req.body(), req.params(":username"));
            LOGGER.info(response);
            return response;
        });

        post("/users/:username/recipes/add", (req, res) -> {
            String response = recipeService.addRecipe(req.body(), req.params(":username"));
            LOGGER.info(response);
            return response;
        });
    }
}
