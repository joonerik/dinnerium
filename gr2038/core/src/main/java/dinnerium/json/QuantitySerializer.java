package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Quantity;
import java.io.IOException;

class QuantitySerializer extends JsonSerializer<Quantity> {


    // converts the Quantity object to a string in json format
    @Override
    public void serialize(Quantity q, JsonGenerator jsonGen, SerializerProvider serializerProvider)
            throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeStringField("unit", q.getUnit());
        jsonGen.writeNumberField("amount", q.getAmount());
        jsonGen.writeEndObject();
    }
}

