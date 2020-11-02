package dinnerium.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.Recipe;
import dinnerium.core.RecipeContainer;
import dinnerium.core.User;
import dinnerium.json.DinneriumModule;
import dinnerium.json.HandlePersistency;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final ObjectMapper mapper;
    private final HandlePersistency handlePersistency;

    public UserService() {
        mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
        handlePersistency = new HandlePersistency();
    }

    public String getUser(String username) throws IOException {
        String file = "src/main/resources/storage/" + username + ".json";
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public String getUserRequest(String username) throws IOException {
        String formattedUsername = formatUsername(username);
        if (getUsernameList().contains(formattedUsername)) {
            return getUser(formattedUsername);
        }
        return "null";
    }

    public String addIngredient(Ingredient ingredient, String username) throws IOException {
        User user = mapper.readValue(getUser(username), User.class);
        user.getIngredientContainer().addItem(ingredient);
        Path path = Paths.get("src/main/resources/storage/" + username + ".json");
        handlePersistency.writeUser(user, new FileWriter(path.toFile(), StandardCharsets.UTF_8));
        return getUser(username);
    }

    public String addRecipe(Recipe recipe, String username) throws IOException {
        User user = mapper.readValue(getUser(username), User.class);
        user.getRecipeContainer().addItem(recipe);
        Path path = Paths.get("src/main/resources/storage/" + username + ".json");
        handlePersistency.writeUser(user, new FileWriter(path.toFile(), StandardCharsets.UTF_8));
        return getUser(username);
    }

    public String registerNewUser(String username) {
        String formattedUsername = formatUsername(username);
        if (!getUsernameList().contains(formattedUsername)) {
            User newUser =
                new User(new IngredientContainer(), new RecipeContainer(), formattedUsername);
            try {
                Path path = Paths.get(
                    "src/main/resources/storage/" + formattedUsername + ".json");
                handlePersistency
                    .writeUser(newUser, new FileWriter(path.toFile(), StandardCharsets.UTF_8));
                return getUser(formattedUsername);
            } catch (Exception e) {
                //LOG.error("Could not write userdata to file");
                throw new IllegalArgumentException("Could not write userdata to file");
            }
        } else {
            return "null";
        }
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

    private String formatUsername(String username) {
        return username
            .replace("\"", "")
            .replace("{username:", "")
            .replace("}", "")
            .toLowerCase();
    }

    public String getContainerFromUser(String username, String container) throws IOException {
        return JsonParser
            .parseString(getUser(username))
            .getAsJsonObject()
            .get(container)
            .toString();
    }
}