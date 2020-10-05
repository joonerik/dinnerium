package dinnerium.core;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MetadataTest {

    Metadata md;

    @BeforeEach
    public void setUp() {
        md = new Metadata(
            "Bakemester Harepus",
            5.0,
            "pepper.png",
            "Pepperkaker",
            "...",
            20
        );
    }

    @Test
    void getAuthor() {
        assertEquals(md.getAuthor(), "Bakemester Harepus");
    }

    @Test
    void getPortion() {
        assertEquals(md.getPortion(), 5.0);
    }

    @Test
    void getImage() {
        assertEquals(md.getImage(), "pepper.png");
    }

    @Test
    void getRecipeName() {
        assertEquals(md.getRecipeName(), "Pepperkaker");
    }

    @Test
    void getRecipeDescription() {
        assertEquals(md.getRecipeDescription(), "...");
    }

    @Test
    void getMinutes() {
        assertEquals(md.getMinutes(), 20);
    }
}