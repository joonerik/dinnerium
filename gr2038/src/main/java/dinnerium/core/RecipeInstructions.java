package dinnerium.core;

import java.util.ArrayList;
import java.util.List;

public class RecipeInstructions {

    private List<String> instructions = new ArrayList<>();

    private String getInstruction(int number) {
        return instructions.get(number);
    }

    private void setInstruction(int number, String instruction) {
        instructions.add(number, instruction);
    }

}
