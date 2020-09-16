package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Ingredient;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public class HandlePersistency {

    public static void writeJsonToFile(Ingredient ingredient) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("src/main/data.json"), ingredient);
    }

    public static Ingredient loadDataFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
        Ingredient i = mapper.readValue(Paths.get(("src/main/data.json")).toFile(), Ingredient.class);
        return i;
    }

}
