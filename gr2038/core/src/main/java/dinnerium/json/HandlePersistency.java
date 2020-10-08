package dinnerium.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.User;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class HandlePersistency {

    /**
     *
     * @param user the user object that is to be written to the writer.
     * @param writer the writer to be used to write the user object.
     * @throws IOException if it is not possible to write the user to the writer.
     */
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
