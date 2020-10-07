package dinnerium.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.User;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class HandlePersistency {

    // writes a container containing a list of ingredients

    /**
     * Writes a IngredientContainer to file
     *
     * @throws IOException if file error
     */
    public static void writeJsonToFile(User user) throws IOException {
        String path = "../core/src/main/resources/dinnerium/storage/" + user.getUsername() + ".json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
        mapper
            .writerWithDefaultPrettyPrinter()
            .writeValue(new File(path), user);
    }

    public static void writeUser(User user, Writer writer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, user);

    }


    /**
     * @param reader the reader from which the User json file is gathered from.
     * @return the User object read from the reader.
     * @throws IOException if it is not possible to read from the reader.
     */
    public static User readUserFromReader(Reader reader) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
        return mapper.readValue(reader, User.class);
    }
}
