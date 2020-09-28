package dinnerium.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecipeInstructions implements Iterable<String> {

    private List<String> instructions = new ArrayList<>();

    /**
     * Getter for instruction
     *
     * @param number 
     * @return
     */
    public String getInstruction(int number) {
        return instructions.get(number);
    }

    /**
     * @param instruction
     */
    public void setInstruction(String instruction) {
        if (instruction != null) {
            instructions.add(instruction);
        } else {
            throw new IllegalArgumentException("String is null!");
        }
    }

    @Override
    public Iterator<String> iterator() {
        return this.instructions.iterator();
    }
}
