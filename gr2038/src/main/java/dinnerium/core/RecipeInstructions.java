package dinnerium.core;

import java.util.ArrayList;
import java.util.List;

public class RecipeInstructions {
    // class not finalized as its not a part of assignment 1

    private List<String> instructions = new ArrayList<>();
    private int number;
    private String instruction;

    public RecipeInstructions (int number, String instruction){
        this.number = number;
        this.instruction = instruction;
    }

    private String getInstruction(int number) {
        return instructions.get(number);
    }

    private void setInstruction(int number, String instruction) {
        instructions.add(number, instruction);
    }

}
