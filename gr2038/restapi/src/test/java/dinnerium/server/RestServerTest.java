package dinnerium.server;

import static org.junit.jupiter.api.Assertions.*;

import dinnerium.core.Units;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RestServerTest {

    private final String testIngredient = "{\n" +
        "        \"quantity\": {\n" +
        "          \"unit\": \"stk\",\n" +
        "          \"amount\": 2.0\n" +
        "        },\n" +
        "        \"name\": \"bolle\"\n" +
        "      }";
    private final String testRecipe = "{\n" +
        "      \"ingredientContainer\" : {\n" +
        "        \"ingredients\" : [ {\n" +
        "          \"quantity\" : {\n" +
        "            \"unit\" : \"dl\",\n" +
        "            \"amount\" : 3.0\n" +
        "          },\n" +
        "          \"name\" : \"milk\"\n" +
        "        } ]\n" +
        "      },\n" +
        "      \"recipeInstructions\" : [ \"inst1\", \"inst2\" ],\n" +
        "      \"metadata\" : {\n" +
        "        \"author\" : \"data\",\n" +
        "        \"portion\" : 5.0,\n" +
        "        \"recipeName\" : \"test recipe\",\n" +
        "        \"recipeDescription\" : \"description\",\n" +
        "        \"minutes\" : 3\n" +
        "      }\n" +
        "    }";


    @BeforeAll
    static void setUpFile() {
        try {
            File file = new File("src/main/resources/storage/servertest.json");
            assertTrue(file.createNewFile());
            FileWriter writer = new FileWriter("src/main/resources/storage/servertest.json");
            writer.write("{\n" +
                "  \"ingredientContainer\" : {\n" +
                "    \"ingredients\" : [ ]\n" +
                "  },\n" +
                "  \"recipeContainer\" : {\n" +
                "    \"recipes\" : [ ]\n" +
                "  },\n" +
                "  \"username\" : \"servertest\"\n" +
                "}");
            writer.close();
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @AfterAll
    static void removeFile() {
        File file = Paths.get("../restapi/src/main/resources/storage/servertest.json").toFile();
        assertTrue(file.delete());
    }

    @Test
    void testGetUser() {
        try {
        RestServer.main(new String[] {});
        HttpResponse<String> response = getResponse(createUserRequest("login", "servertest"));
        assertEquals(200, response.statusCode());
        } catch (IOException | InterruptedException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void getNotExistingUser() {
        try {
        RestServer.main(new String[] {});
        HttpResponse<String> response = getResponse(createUserRequest("login", "notExisting"));
        assertEquals(404, response.statusCode());
        } catch (IOException | InterruptedException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testRegisterInvalidUser() {
        try {
        RestServer.main(new String[] {});
        HttpResponse<String> response = getResponse(createUserRequest("register", "servertest"));
        assertEquals(400, response.statusCode());
        } catch (IOException | InterruptedException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testRegisterValidUser() {
        try {
            RestServer.main(new String[] {});
            HttpResponse<String> response = getResponse(createUserRequest("register", "usernotexist"));
            assertEquals(201, response.statusCode());
            String s = "{\"ingredientContainer\":{\"ingredients\":[]},\"recipeContainer\":{\"recipes\":[]},\"username\":\"usernotexist\"}";
            assertEquals(s, response.body().replaceAll("\\s+", ""));
            File file = Paths.get("../restapi/src/main/resources/storage/usernotexist.json").toFile();
            assertTrue(file.delete());
        } catch (IOException | InterruptedException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testGetUnits() {
        try {
            RestServer.main(new String[] {});
            HttpRequest request = HttpRequest.newBuilder(URI.create("http://localhost:4567/units"))
                .header("Accept", "application/json")
                .GET()
                .build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            assertEquals(Arrays.toString(Units.values()), response.body());
        } catch (IOException | InterruptedException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testPostIngredient() {
        try {
            HttpRequest request = createPostRequest("users/servertest/ingredients/add", testIngredient);
            HttpResponse<String> response = getResponse(request);
            assertEquals(202, response.statusCode());
            assertTrue(response.body().replaceAll("\\s+", "").contains(testIngredient.replaceAll("\\s+", "")));

        } catch (IOException | InterruptedException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testPostRecipe() {
        try {
            HttpRequest request =createPostRequest("users/servertest/recipes/add", testRecipe);
            HttpResponse<String> response = getResponse(request);
            assertEquals(202, response.statusCode());
            assertTrue(response.body().replaceAll("\\s+", "").contains(testRecipe.replaceAll("\\s+", "")));

        } catch (IOException | InterruptedException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void postInvalidIngredient() {
        try {
            HttpRequest request = createPostRequest("users/servertest/ingredients/add", "{\"attribute\":\"not ingredient\"}");
            HttpResponse<String> response = getResponse(request);
            assertEquals(400, response.statusCode());
            assertEquals("null", response.body());
        } catch (IOException | InterruptedException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void postInvalidRecipe() {
        try {
            HttpRequest request = createPostRequest("users/servertest/recipes/add", "{\"attribute\":\"not a recipe\"}");
            HttpResponse<String> response = getResponse(request);
            assertEquals(400, response.statusCode());
            assertEquals("null", response.body());
        } catch (IOException | InterruptedException e) {
            fail(e.getMessage());
        }
    }

    private HttpRequest createPostRequest(String apiEndpoint, String body) {
        return HttpRequest
            .newBuilder(URI.create("http://localhost:4567/" + apiEndpoint))
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();
    }

    private HttpRequest createUserRequest(String action, String username) {
        return HttpRequest.newBuilder(URI.create("http://localhost:4567/users/" + action))
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{username:\"" + username + "\"}"))
            .build();
    }

    private HttpResponse<String> getResponse(HttpRequest request)
        throws IOException, InterruptedException {
        return HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
    }
}