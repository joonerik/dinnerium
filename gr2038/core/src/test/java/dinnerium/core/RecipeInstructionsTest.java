package dinnerium.core;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeInstructionsTest {

    RecipeInstructions ri;
    Collection<String> c;

    @BeforeEach
    void setUp() {
        c = new ArrayList<>();
        c.add("Do something");
        ri = new RecipeInstructions(c);


    }

    @Test
    void getInstrictions() {
        assertEquals(ri.getInstructions(), c);
    }

    @Test
    void setInstruction() {
        c.add("Do more");
        ri.setInstruction("Do more");
        assertEquals(ri.getInstructions(), c);
    }

}