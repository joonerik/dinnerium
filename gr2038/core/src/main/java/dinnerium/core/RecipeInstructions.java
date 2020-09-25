package dinnerium.core;

import java.util.ArrayList;
import java.util.List;

public class RecipeInstructions {

    private List<String> instructions = new ArrayList<>();
    private int number;
    private String instruction;

    public RecipeInstructions (int number, String instruction){
        this.number = number;
        this.instruction = instruction;
    }

    public String getInstruction(int number) {
        return instructions.get(number);
    }

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
