package dinnerium.restapi;

import static spark.Spark.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestApi {
    private static final Logger LOG = LoggerFactory.getLogger(RestApi.class);

    public static void main(String[] args) {
        UserApi userApi = new UserApi();
        //Se på det om man skal legge til response type ved res.type("application/json");
        //https://www.the-lazy-dev.com/en/spark-for-beginners-create-restful-api-with-java-and-mongodb/

        get("/users/:name", (req, res) -> userApi.getUser(req.params(":name")));

        get("/users/:username/ingredients", (req, res) -> userApi
            .getContainerFromUser(req.params(":username"), "ingredientContainer"));

        get("/users/:username/recipes",
            (req, res) -> userApi.getContainerFromUser(req.params(":username"), "recipeContainer"));

        post("/users/login", (req, res) -> userApi.getUserRequest(req.body()));

        post("/users/register", (req, res) -> userApi.saveNewUser(req.body()));

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
