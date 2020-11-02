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

        get("/units", (req, res) -> {
            res.type("application/json");
            return Quantity.units;
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
                res.status(404);
            } else {
                res.status(200);
            }
            return response;
        });

        post("/users/:username/ingredients/add", (req, res) -> {
            res.type("application/json");
            LOGGER.debug(req.body()); //Change to debug something useful here.
            return ingredientsService.addIngredient(req.body(), req.params(":username"));
        });

        post("/users/:username/recipes/add", (req, res) -> {
            res.type("application/json");
            return recipeService.addRecipe(req.body(), req.params(":username"));
        });
    }
}
