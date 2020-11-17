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

/**
 * Class for starting a RestServer on http://localhost:4567 with different api endpoints.
 */
public class RestServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestServer.class);

    /**
     * Main method to start the spark rest server on http://localhost:4567. Every response is in
     * json format.
     *
     * <p>The localhost will contain these endpoints:</p>
     *
     * <p>get(/units):</p>
     * Returns a list of the available units from the Enum Units.
     *
     * <p>post(/users/login):</p>
     * Checks if a user with the username provided in the request exists, if it does not exist
     * it return "null" and response status code 404. If the user exists in the backend it
     * returns the userdata on json format and status code 200.
     *
     * <p>post(/users/register):</p>
     * Checks if the username provided in the request is taken, if it is, returns "null" and
     * status code 400. If the username is not taken, a new user with the username is
     * registered, and the new user is returned on json format with status code 201.
     *
     * <p>post(/users/:username/ingredients/add):</p>
     * Tries to add a new ingredient to the user with username :username as provided in the
     * request. If it is not possible to add the ingredient to the user it sets the response
     * status code to 400. If it is successful in adding the ingredient to the user, it returns
     * the user on json format with the new ingredient included, and sets status code to 202.
     *
     * <p>post(/users/:username/recipes/add):</p>
     * Tries to add a new recipe to the user with username :username as provided in the
     * request. If it is not possible to add the recipe to the user it sets the response
     * status code to 400. If it is successful in adding the recipe to the user, it returns
     * the user on json format with the new recipe included, and sets status code to 202.
     *
     * @param args arguments of the main method.
     */
    public static void main(String[] args) {
        final UserService userService = new UserService();
        final IngredientService ingredientService = new IngredientService();
        final RecipeService recipeService = new RecipeService();

        get("/units", (req, res) -> {
            res.type("application/json");
            LOGGER.debug("getUnits request: {}", req);
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
            LOGGER.debug("Login user post request : {}, responseStatus: {}", req.body(),
                res.status());
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
            LOGGER.debug("Register user post request : {}, responseStatus: {}", req.body(),
                res.status());
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
            LOGGER.debug("Add ingredient PostRequest : {}, user: {} responseStatus: {}", req.body(),
                req.params(":username"), res.status());
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
            LOGGER.debug("Add recipe PostRequest : {}, user: {} responseStatus: {}", req.body(),
                req.params(":username"), res.status());
            return response;
        });
    }
}
