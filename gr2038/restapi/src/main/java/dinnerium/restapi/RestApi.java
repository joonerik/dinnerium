package dinnerium.restapi;

import static spark.Spark.*;

import com.google.gson.JsonParser;
import dinnerium.core.IngredientContainer;
import dinnerium.core.RecipeContainer;
import dinnerium.core.User;
import dinnerium.json.HandlePersistency;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestApi {
    private static final Logger LOG = LoggerFactory.getLogger(RestApi.class);

    public static void main(String[] args) {
        //Se på det om man skal legge til response type ved res.type("application/json");
        //https://www.the-lazy-dev.com/en/spark-for-beginners-create-restful-api-with-java-and-mongodb/

        get("/users/:name", (req, res) -> getUser(req.params(":name")));

        get("/users/:username/ingredients",
            (req, res) -> getJsonObjectFromUser(req.params(":username"), "ingredientContainer"));

        get("/users/:username/recipes",
            (req, res) -> getJsonObjectFromUser(req.params(":username"), "recipeContainer"));

        post("/users/login", (req, res) -> {
            String username = formatUsername(req.body());

            if (getUsernameList().contains(username)) {
                return getUser(username);
            } else {
                return "null";
            }
        });
        post("/users/register", (req, res) -> {
            String username = formatUsername(req.body());
            if (!getUsernameList().contains(username)) {
                saveNewUser(username);
                return getUser(username);
            } else {
                return "null";
            }
        });
        post("/users/:username/ingredients/add", (req, res) -> {
            LOG.info("add new ingredient");
            return "";
        });
        post("/users/:username/recipes/add", (req, res) -> {
            LOG.info("add new recipe");
            LOG.info("Body: " + req.body());
            return "";
        });
    }

    private static void saveNewUser(String username) {
        User newUser = new User(new IngredientContainer(), new RecipeContainer(), username);
        try {
            Path path = Paths.get(
                "src/main/resources/storage/" + username + ".json");
            HandlePersistency.writeUser(newUser, new FileWriter(path.toFile(), StandardCharsets.UTF_8));
        } catch (Exception e) {
            LOG.error("Could not write userdata to file");
            throw new IllegalArgumentException("Could not write userdata to file");
        }
    }

    private static String formatUsername(String username) {
        return username
            .replace("\"", "")
            .replace("{username:", "")
            .replace("}", "")
            .toLowerCase();
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
        String file = "src/main/resources/storage/" + username + ".json";
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    private static String getUsernameList() {
        File[] files = new File("src/main/resources/storage/").listFiles();
        List<String> filenames = new ArrayList<>();
        if (files != null) {
            filenames = Arrays.stream(files)
                .map(file -> "\"" + file.getName().replace(".json", "") + "\"")
                .collect(Collectors.toList());
        }
        return "{ \"usernames\" : " + filenames.toString() + "}";
    }
}
