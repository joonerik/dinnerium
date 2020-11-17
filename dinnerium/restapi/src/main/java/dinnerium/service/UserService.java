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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for serving the RestServer with methods needed to handle user actions.
 */
public class UserService {
    private final ObjectMapper mapper;
    private final HandlePersistency handlePersistency;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    /**
     * Instantiate a UserService object with a HandlePersistency object to the handlePersistency
     * field to take care of file handeling, and registers DinneriumModule as a module to the
     * ObjectMapper.
     */
    public UserService() {
        mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
        handlePersistency = new HandlePersistency();
    }

    /**
     * Returns the user on json-format corresponding to the username provided, if no user with the
     * username exists return null.
     *
     * @param username the username of the user to be retrieved.
     * @return the user on json-format if the user exists, null if not.
     * @throws IOException if it is not possible to read the file containing the userdata.
     */
    public String getUser(String username) throws IOException {
        String formattedUsername = formatUsername(username);
        if (getUsernameList().contains(formattedUsername)) {
            return getUserString(formattedUsername);
        }
        return "null";
    }

    /**
     * Registers a new user with the username provided, and returns the new user on json format,
     * returns null if a user with the username already exists.
     *
     * @param username the username of the user to register.
     * @return the new user on json format, null if there already exists a user with the username.
     * @throws IOException if it is not possible to create a file for the new user, or if it is not
     *                     possible to read the userdata after it is created.
     */
    public String registerUser(String username) throws IOException {
        String formattedUsername = formatUsername(username);
        if (!getUsernameList().contains(formattedUsername)) {
            saveUser(new User(new IngredientContainer(), new RecipeContainer(), formattedUsername));
            return getUserString(formattedUsername);
        } else {
            return "null";
        }
    }

    /**
     * Adds the ingredient provided to the user with username corresponding to the one provided,
     * returns the user on json format with the ingredient added to it.
     *
     * @param ingredient the ingredient to be added to the user with corresponding username to
     *                   the one provided.
     * @param username   the username of the user where the ingredient is to be added.
     * @return the user on json-format with the new ingredient added to it.
     * @throws IOException if it is not possible to add the ingredient to the user.
     */
    public String addIngredient(Ingredient ingredient, String username) throws IOException {
        User user = mapper.readValue(getUserString(username), User.class);
        user.getIngredientContainer().addIngredient(ingredient);
        saveUser(user);
        return getUserString(username);
    }

    /**
     * summary.
     *
     * @param recipe   the recipe to be added to the user with corresponding username to
     *                 the one provided.
     * @param username the username of the user where the recipe is to be added.
     * @return the user on json-format with the new recipe added to it.
     * @throws IOException if it is not possible to add the recipe to the user.
     */
    public String addRecipe(Recipe recipe, String username) throws IOException {
        User user = mapper.readValue(getUserString(username), User.class);
        user.getRecipeContainer().addRecipe(recipe);
        saveUser(user);
        return getUserString(username);
    }

    private void saveUser(User user) throws IOException {
        Path path = Paths.get(
            "../restapi/src/main/resources/storage/" + user.getUsername() + ".json");
        FileWriter writer = null;
        try {
            writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8);
            handlePersistency.writeUser(user, writer);
        } catch (IOException e) {
            LOGGER.error("Could not store user to file username: {} ", user.getUsername());
            if (writer != null) {
                writer.close();
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private String getUserString(String username) throws IOException {
        String file = "../restapi/src/main/resources/storage/" + username + ".json";
        return Files.readString(Paths.get(file));
    }

    private List<String> getUsernameList() {
        File[] files = new File("../restapi/src/main/resources/storage/").listFiles();
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

}