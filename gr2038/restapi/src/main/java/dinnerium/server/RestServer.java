package dinnerium.server;

import static spark.Spark.*;


import dinnerium.core.Quantity;
import dinnerium.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestServer {
    private static final Logger LOG = LoggerFactory.getLogger(RestServer.class);

    public static void main(String[] args) {
        UserService userService = new UserService();
        //Se på det om man skal legge til response type ved res.type("application/json");
        //https://www.the-lazy-dev.com/en/spark-for-beginners-create-restful-api-with-java-and-mongodb/
        get("/users/:name", (req, res) -> userService.getUser(req.params(":name")));

        get("/users/:username/ingredients", (req, res) -> userService
            .getContainerFromUser(req.params(":username"), "ingredientContainer"));

        get("/users/:username/recipes",
            (req, res) -> userService
                .getContainerFromUser(req.params(":username"), "recipeContainer"));

        post("/users/login", (req, res) -> userService.getUserRequest(req.body()));

        post("/users/register", (req, res) -> userService.saveNewUser(req.body()));

        get("/units", (req, res) -> Quantity.units);

        post("/users/:username/ingredients/add", (req, res) -> {
            LOG.info("add new ingredient");
            LOG.info("Body: " + req.body());
            return "";
        });

        post("/users/:username/recipes/add", (req, res) -> {
            LOG.info("add new recipe");
            LOG.info("Body: " + req.body());
            return "";
        });
    }
}
