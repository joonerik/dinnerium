package dinnerium.core;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeMetadataTest {

    RecipeMetadata md;

    @BeforeEach
    public void setUp() {
        md = new RecipeMetadata(
            "Bakemester Harepus",
            5.0,
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
            RecipeMetadata recipeMetadata = new RecipeMetadata(
                "Bakemester Harepus",
                0.0,
                "Pepperkaker",
                "...",
                20
            );
        });
    }

    @Test
    void testValidateMinutes() {
        assertThrows(IllegalArgumentException.class, () -> {
            RecipeMetadata recipeMetadata = new RecipeMetadata(
                "Bakemester Harepus",
                5.0,
                "Pepperkaker",
                "...",
                0
            );
        });
    }

    @Test
    void testValidateString() {
        assertThrows(IllegalArgumentException.class, () -> {
            RecipeMetadata recipeMetadata = new RecipeMetadata(
                "",
                5.0,
                "Pepperkaker",
                "...",
                20
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            RecipeMetadata recipeMetadata = new RecipeMetadata(
                "Bakemester Harepus",
                5.0,
                "Pepperkaker",
                "",
                20
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            RecipeMetadata recipeMetadata = new RecipeMetadata(
                "Bakemester Harepus",
                5.0,
                "",
                "...",
                20
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            RecipeMetadata recipeMetadata = new RecipeMetadata(
                "Bakemester Harepus",
                5.0,
                "Pepperkaker",
                "",
                20
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            RecipeMetadata recipeMetadata = new RecipeMetadata(
                "  ",
                5.0,
                "Pepperkaker",
                "...",
                20
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            RecipeMetadata recipeMetadata = new RecipeMetadata(
                "Bakemester Harepus",
                5.0,
                "",
                "...",
                20
            );
        });

    }
}