package dinnerium.restapi;

import static spark.Spark.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HelloWorld {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello, world");

        get("/hello/:name", (req, res) -> "Hello, " + req.params(":name"));

        get("/users/:name", (req, res) -> getUser(req.params(":name")));
    }

    private static String getUser(String param) throws IOException {
        String file = "core/src/main/resources/dinnerium/storage/" + param + ".json";
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
