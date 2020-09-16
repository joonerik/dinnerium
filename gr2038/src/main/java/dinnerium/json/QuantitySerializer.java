package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

import dinnerium.core.Ingredient;
import dinnerium.core.Quantity;

public class QuantitySerializer extends JsonSerializer<Quantity> {

    /*
     * format: { "text": "...", "checked": false }
     */

    @Override
    public void serialize(Quantity q, JsonGenerator jsonGen, SerializerProvider serializerProvider)
            throws IOException {
        jsonGen.writeStartObject();
 /*       jsonGen.writeStringField("text", item.getText());
        jsonGen.writeBooleanField("checked", item.isChecked());*/
        jsonGen.writeStringField("unit", q.getUnit());
        jsonGen.writeNumberField("amount", q.getAmount());
        jsonGen.writeEndObject();
    }
}

