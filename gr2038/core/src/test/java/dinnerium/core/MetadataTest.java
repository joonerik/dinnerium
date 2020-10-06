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
    void testGetAuthor() {
        assertEquals(md.getAuthor(), "Bakemester Harepus");
    }

    @Test
    void testGetPortion() {
        assertEquals(md.getPortion(), 5.0);
    }

    @Test
    void testGetImage() {
        assertEquals(md.getImage(), "pepper.png");
    }

    @Test
    void testGetRecipeName() {
        assertEquals(md.getRecipeName(), "Pepperkaker");
    }

    @Test
    void testGetRecipeDescription() {
        assertEquals(md.getRecipeDescription(), "...");
    }

    @Test
    void testGetMinutes() {
        assertEquals(md.getMinutes(), 20);
    }

    @Test
    void testValidatePortion() {
        assertThrows(IllegalArgumentException.class, () -> {
            Metadata metadata = new Metadata(
                "Bakemester Harepus",
                0.0,
                "pepper.png",
                "Pepperkaker",
                "...",
                20
            );
        });
    }

    @Test
    void testValidateMinutes() {
        assertThrows(IllegalArgumentException.class, () -> {
            Metadata metadata = new Metadata(
                "Bakemester Harepus",
                5.0,
                "pepper.png",
                "Pepperkaker",
                "...",
                0
            );
        });
    }

    @Test
    void testValidateString() {
        assertThrows(IllegalArgumentException.class, () -> {
            Metadata metadata = new Metadata(
                "",
                5.0,
                "pepper.png",
                "Pepperkaker",
                "...",
                20
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Metadata metadata = new Metadata(
                "Bakemester Harepus",
                5.0,
                "",
                "Pepperkaker",
                "...",
                20
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Metadata metadata = new Metadata(
                "Bakemester Harepus",
                5.0,
                "pepper.png",
                "",
                "...",
                20
            );
        });

    }
}