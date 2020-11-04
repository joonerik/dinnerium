package dinnerium.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Ingredient;
import dinnerium.core.Recipe;
import dinnerium.core.User;
import dinnerium.json.DinneriumModule;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class RemoteDinneriumAccess implements DinneriumAccess {

    private final ObjectMapper mapper;
    private final URI apiBaseUri;

    public RemoteDinneriumAccess(URI apiBaseUri) {
        this.apiBaseUri = apiBaseUri;
        mapper = new ObjectMapper().registerModule(new DinneriumModule());
    }

    @Override
    public User registerUser(String username) throws IllegalArgumentException {
        HttpRequest request = createUserRequest("register", username);
        try {
            return getResponseUser(request);
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Username already taken or invalid.");
        }
    }

    @Override
    public User login(String username) {
        HttpRequest request = createUserRequest("login", username);
        try {
            return getResponseUser(request);
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            throw new IllegalArgumentException("No user with username: " + username);
        }
    }

    private HttpRequest createUserRequest(String action, String username) {
        return HttpRequest.newBuilder(URI.create(apiBaseUri + "users/" + action))
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{username:\"" + username + "\"}"))
            .build();
    }

    private User getResponseUser(HttpRequest request) throws IOException, InterruptedException {
        final HttpResponse<String> response =
            HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        String responseString = response.body();
        User user = mapper.readValue(responseString, User.class);
        if (user == null) {
            throw new IllegalArgumentException();
        }
        return user;
    }

    @Override
    public User postIngredient(String username, Ingredient ingredient)
        throws IllegalArgumentException {
        try {
            HttpRequest request = HttpRequest
                .newBuilder(URI.create(apiBaseUri + "users/" + username + "/ingredients/add"))
                .headers("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(ingredient)))
                .build();
            return getResponseUser(request);
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not store the new ingredient at the server");
        }
    }

    @Override
    public User postRecipe(String username, Recipe recipe) throws IllegalArgumentException {
        try {
            HttpRequest request = HttpRequest
                .newBuilder(URI.create(apiBaseUri + "users/" + username + "/recipes/add"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(recipe)))
                .build();
            return getResponseUser(request);
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not store the new recipe at the server.");
        }
    }

    @Override
    public List<String> getUnits() {
        HttpRequest request = HttpRequest.newBuilder(URI.create(apiBaseUri + "units"))
            .header("Accept", "application/json")
            .GET()
            .build();
        try {
            final HttpResponse<String> response =
                HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return Arrays.asList(response.body()
                .replace("[", "")
                .replace("]", "")
                .split(", "));
        } catch (IOException | InterruptedException e) {
            throw new IllegalArgumentException("Could not get units from the server.");
        }
    }
}
