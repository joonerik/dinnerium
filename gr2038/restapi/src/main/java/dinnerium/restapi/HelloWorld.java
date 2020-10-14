package dinnerium.restapi;

import static spark.Spark.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelloWorld {

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello, world");

        get("/hello/:name", (req, res) -> "Hello, " + req.params(":name"));

        get("/users/:name", (req, res) -> getUser(req.params(":name")));

        get("/users", (req, res) -> getUsernameList());
    }

    private static String getUser(String param) throws IOException {
        String file = "core/src/main/resources/dinnerium/storage/" + param + ".json";
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
