package dinnerium.core;

import java.util.ArrayList;
import java.util.List;

public class RecipeInstructions {

    private List<String> instructions = new ArrayList<>();
    private int number;
    private String instruction;

    /**
     * Construnctor for RecipeInstructions
     *
     * @param number of steps
     * @param instruction for recipe
     */
    public RecipeInstructions(int number, String instruction) {
        this.number = number;
        this.instruction = instruction;
    }

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
     *
     *
     * @param number
     * @param instruction
     */
    public void setInstruction(int number, String instruction) {
        instructions.add(number, instruction);
    }

    public int getNumber() {
        return number;
    }

    public String getInstruction() {
        return instruction;
    }

}
