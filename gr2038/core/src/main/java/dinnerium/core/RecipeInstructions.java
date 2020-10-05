package dinnerium.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class RecipeInstructions implements Iterable<String> {

    private Collection<String> instructions;

    public RecipeInstructions(Collection<String> instructions) {
        this.instructions = instructions;
    }

    /**
     * @param instruction
     *        description of a step
     * @throws IllegalArgumentException
     *         if the instruction is empty
     */
    public void setInstruction(String instruction) {
        if (instruction != null && !instruction.isEmpty()) {
            instructions.add(instruction);
        } else {
            throw new IllegalArgumentException("String is null!");
        }
    }

    /**
     *
     * @return a copy of the recipe instructions.
     */
    public Collection<String> getInstructions() {
        return new ArrayList<>(this.instructions);
    }

    @Override
    public Iterator<String> iterator() {
        return this.instructions.iterator();
    }
}
