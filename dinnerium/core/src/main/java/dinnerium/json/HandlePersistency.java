package dinnerium.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.User;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Class for handling the saving/reading of objects to/from file in the project.
 */
public class HandlePersistency {

    private final ObjectMapper mapper;

    public HandlePersistency() {
        mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
    }

    /**
     * Writes a user to the writer.
     *
     * @param user the user object that is to be written to the writer.
     * @param writer the writer to be used to write the user object.
     * @throws IOException if it is not possible to write the user to the writer.
     */
    public void writeUser(User user, Writer writer) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(writer, user);
    }


    /**
     * Returns a user from the reader.
     *
     * @param reader the reader from which the User json file is gathered from.
     * @return the User object read from the reader.
     * @throws IOException if it is not possible to read from the reader.
     */
    public User readUserFromReader(Reader reader) throws IOException {
        return mapper.readValue(reader, User.class);
    }
}
