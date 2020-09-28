package dinnerium.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.Quantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DinneriumModuleTest {

    private ObjectMapper mapper;
    private String exampleIngredientContainer = "" +
            "{\"ingredients\":[" +
            "{\"quantity\":{\"unit\":\"stk\",\"amount\":10.0},\"name\":\"egg\"}," +
            "{\"quantity\":{\"unit\":\"gram\",\"amount\":500.0},\"name\":\"mel\"}," +
            "{\"quantity\":{\"unit\":\"dl\",\"amount\":5.0},\"name\":\"melk\"}" +
            "]}";

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
    }

    @Test
    public void testSerializers() throws JsonProcessingException {
        IngredientContainer container = new IngredientContainer();
        container.addItem(new Ingredient(new Quantity(10, "stk"), "egg"));
        container.addItem(new Ingredient(new Quantity(500, "gram"), "mel"));
        container.addItem(new Ingredient(new Quantity(5, "dl"), "melk"));

        try {
            String serializedObject = mapper.writeValueAsString(container);
            assertEquals(exampleIngredientContainer.replaceAll("\\s+", ""), serializedObject);
        } catch (JsonParseException e) {
            fail();
        }
    }

    @Test
    public void testDeserializers() {
        try {
            IngredientContainer container = mapper.readValue(exampleIngredientContainer, IngredientContainer.class);
            IngredientContainer exampleContainer = new IngredientContainer(createSampleIngredient());
            Iterator<Ingredient> it =  container.iterator();
            for (Ingredient i : exampleContainer) {
                Ingredient readIngredient = it.next();
                assertEquals(i.getName(), readIngredient.getName());
                assertEquals(i.getQuantity().getAmount(), readIngredient.getQuantity().getAmount());
                assertEquals(i.getQuantity().getUnit(), readIngredient.getQuantity().getUnit());
            }
        } catch (JsonProcessingException e) {
            fail();
        }
    }

    private Collection<Ingredient> createSampleIngredient() {
        List<Ingredient> sampleIngredients = new ArrayList<>();
        sampleIngredients.add(new Ingredient(new Quantity(10, "stk"), "egg"));
        sampleIngredients.add(new Ingredient(new Quantity(500, "gram"), "mel"));
        sampleIngredients.add(new Ingredient(new Quantity(5, "dl"), "melk"));

        return sampleIngredients;
    }
}