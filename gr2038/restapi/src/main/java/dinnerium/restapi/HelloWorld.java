package dinnerium.restapi;

import static spark.Spark.*;

import dinnerium.json.HandlePersistency;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class HelloWorld {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello, world");

        get("/hello/:name", (req, res) -> "Hello, " + req.params(":name"));

        get("/users/:name", (req, res) ->
            //HandlePersistency.readUserFromReader(
                new FileReader(Paths.get("core/src/main/resources/dinnerium/storage/"
                    + req.params(":name") + ".json").toFile(),
                    StandardCharsets.UTF_8).toString());//);
    }
}
