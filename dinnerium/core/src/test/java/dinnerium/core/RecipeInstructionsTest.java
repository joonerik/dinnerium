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
    void testGetInstructions() {
        assertEquals(ri.getInstructions(), c);
        assertThrows(IllegalArgumentException.class,
            () -> new RecipeInstructions(new ArrayList<>()));
    }

    @Test
    void testAddInstruction() {
        c.add("Do more");
        ri.addInstruction("Do more");
        assertEquals(ri.getInstructions(), c);

        assertThrows(IllegalArgumentException.class, () -> ri.addInstruction(""));

        assertThrows(IllegalArgumentException.class, () -> ri.addInstruction(null));

        assertThrows(IllegalArgumentException.class, () -> ri.addInstruction("   "));

    }

}