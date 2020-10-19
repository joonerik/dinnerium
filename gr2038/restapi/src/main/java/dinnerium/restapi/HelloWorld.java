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

public class HelloWorld {

    public static void main(String[] args) {

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
    }

    private static void saveNewUser(String username) {
        User newUser = new User(new IngredientContainer(), new RecipeContainer(), username);
        try {
            Path path = Paths.get(
                "core/src/main/resources/dinnerium/storage/" + username + ".json");
            HandlePersistency.writeUser(newUser, new FileWriter(path.toFile(), StandardCharsets.UTF_8));
        } catch (Exception e) {
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
        String file = "core/src/main/resources/dinnerium/storage/" + username + ".json";
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    private static String getUsernameList() {
        File[] files = new File("core/src/main/resources/dinnerium/storage/").listFiles();
        List<String> filenames = new ArrayList<>();
        if (files != null) {
            filenames = Arrays.stream(files)
                .map(file -> "\"" + file.getName().replace(".json", "") + "\"")
                .collect(Collectors.toList());
        }
        return "{ \"usernames\" : " + filenames.toString() + "}";
    }
}
