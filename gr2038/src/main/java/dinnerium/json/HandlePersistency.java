package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Ingredient;

import java.io.File;


public class HandlePersistency {

    static String i;

    public static void writeJsonToFile(Ingredient ingredient) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Nesten suksess");
        mapper.writeValue(new File("test.json"), ingredient);
        /*i = mapper.writeValueAsString(ingredient);
        System.out.println(i);*/
        System.out.println("suksess");
        System.out.println(mapper.writeValueAsString(ingredient));

    }

}
