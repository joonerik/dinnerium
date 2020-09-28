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

<<<<<<< HEAD:gr2038/src/main/java/dinnerium/core/RecipeInstructions.java
    /**
     * Getter for instruction
     *
     * @param number 
     * @return
     */
    private String getInstruction(int number) {
        return instructions.get(number);
    }

    /**
     *
     *
     * @param number
     * @param instruction
     */
    private void setInstruction(int number, String instruction) {
=======
    public String getInstruction(int number) {
        return instructions.get(number);
    }

    public void setInstruction(int number, String instruction) {
>>>>>>> b5704315e9899610f7110f6707affaddc0201ea8:gr2038/core/src/main/java/dinnerium/core/RecipeInstructions.java
        instructions.add(number, instruction);
    }

    public int getNumber() {
        return number;
    }

    public String getInstruction() {
        return instruction;
    }

}
