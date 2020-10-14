package dinnerium.restapi;

import static spark.Spark.*;

import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloWorld {

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello, world");

        get("/hello/:name", (req, res) -> "Hello, " + req.params(":name"));

        get("/users/:name", (req, res) -> getUser(req.params(":name")));

        get("/users/:username/ingredients",
            (req, res) -> getJsonObjectFromUser(req.params(":username"), "ingredientContainer"));

        get("/users/:username/recipes",
            (req, res) -> getJsonObjectFromUser(req.params(":username"), "recipeContainer"));
    }

    private static String getJsonObjectFromUser(String username, String jsonObject)
        throws IOException {
        return JsonParser
            .parseString(getUser(username))
            .getAsJsonObject()
            .get(jsonObject)
            .toString();
    }

    private static String getUser(String username) throws IOException {
        String file = "core/src/main/resources/dinnerium/storage/" + username + ".json";
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
