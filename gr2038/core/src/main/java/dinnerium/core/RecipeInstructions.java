package dinnerium.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Class for storing instructions for a recipe.
 */
public class RecipeInstructions implements Iterable<String> {

    private Collection<String> instructions;

    public RecipeInstructions(Collection<String> instructions) {
        setInstructions(instructions);
    }

    /**
     * sets the collection and thus the instructions.
     *
     * @param instructions
     *        of collection
     * @throws IllegalArgumentException
     *        if instructions is empty
     */
    public void setInstructions(Collection<String> instructions) {
        if (instructions.isEmpty()) {
            throw new IllegalArgumentException(
                "Missing instructions");
        }
        this.instructions = instructions;
    }

    /**
     * Sets an instruction.
     *
     * @param instruction description of a step
     *
     * @throws IllegalArgumentException if the instruction is empty
     */
    public void setInstruction(String instruction) {
        if (instruction != null && !instruction.isBlank()) {
            instructions.add(instruction);
        } else {
            throw new IllegalArgumentException("String is null!");
        }
    }

    /**
     * Returns a copy of the collection containing the instructions.
     *
     * @return a copy of the recipe instructions.
     *
     */
    public Collection<String> getInstructions() {
        return new ArrayList<>(this.instructions);
    }

    @Override
    public Iterator<String> iterator() {
        return this.instructions.iterator();
    }
}
