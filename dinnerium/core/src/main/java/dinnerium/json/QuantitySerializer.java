package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Quantity;
import java.io.IOException;

/**
 * Class for serializing a Quantity object to json format.
 */
class QuantitySerializer extends JsonSerializer<Quantity> {


    /**
     * Converts the Quantity object to a string in json format.
     *
     * @param q the user to be serialized to a json string.
     * @param jsonGen the jsonGen used to generate the json objects.
     * @param serializerProvider the serializerProvider.
     * @throws IOException if it is not possible to format the Quantity to a json string.
     */
    @Override
    public void serialize(Quantity q, JsonGenerator jsonGen, SerializerProvider serializerProvider)
            throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeStringField("unit", q.getUnit());
        jsonGen.writeNumberField("amount", q.getAmount());
        jsonGen.writeEndObject();
    }
}

