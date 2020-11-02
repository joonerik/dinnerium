package dinnerium.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.Recipe;
import dinnerium.core.RecipeContainer;
import dinnerium.core.User;
import dinnerium.json.DinneriumModule;
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

public class UserService {
    private final ObjectMapper mapper;
    private final HandlePersistency handlePersistency;

    public UserService() {
        mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
        handlePersistency = new HandlePersistency();
    }

    public String getUser(String username) throws IOException {
        String formattedUsername = formatUsername(username);
        if (getUsernameList().contains(formattedUsername)) {
            return getUserString(formattedUsername);
        }
        return "null";
    }

    public String registerUser(String username) throws IOException {
        String formattedUsername = formatUsername(username);
        if (!getUsernameList().contains(formattedUsername)) {
            saveUser(new User(new IngredientContainer(), new RecipeContainer(), formattedUsername));
            return getUserString(formattedUsername);
        } else {
            return "null";
        }
    }

    public String addIngredient(Ingredient ingredient, String username) throws IOException {
        User user = mapper.readValue(getUserString(username), User.class);
        user.getIngredientContainer().addItem(ingredient);
        saveUser(user);
        return getUserString(username);
    }

    public String addRecipe(Recipe recipe, String username) throws IOException {
        User user = mapper.readValue(getUserString(username), User.class);
        user.getRecipeContainer().addItem(recipe);
        saveUser(user);
        return getUserString(username);
    }

    private void saveUser(User user) throws IOException {
        Path path = Paths.get(
            "src/main/resources/storage/" + user.getUsername() + ".json");
        handlePersistency
            .writeUser(user, new FileWriter(path.toFile(), StandardCharsets.UTF_8));
    }

    private String getUserString(String username) throws IOException {
        String file = "src/main/resources/storage/" + username + ".json";
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    private List<String> getUsernameList() {
        File[] files = new File("src/main/resources/storage/").listFiles();
        List<String> filenames = new ArrayList<>();
        if (files != null) {
            return Arrays.stream(files)
                .map(file -> file.getName().replace(".json", ""))
                .collect(Collectors.toList());
        }
        return filenames;
    }

    private String formatUsername(String username) {
        return username
            .replace("\"", "")
            .replace("{username:", "")
            .replace("}", "")
            .toLowerCase();
    }

    /*public String getContainerFromUser(String username, String container) throws IOException {
        return JsonParser
            .parseString(getUser(username))
            .getAsJsonObject()
            .get(container)
            .toString();
    }*/
}