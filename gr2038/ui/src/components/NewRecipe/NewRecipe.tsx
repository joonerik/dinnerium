import React, { useState } from 'react';
import './newRecipe.scss';

const NewRecipe = () => {
  const [instructions, setInstructions] = useState<string[]>([]);
  const [instructionField, setInstructionField] = useState('');
  const [ingredients, setIngredients] = useState<string[]>([]);
  const [ingredientNameField, setIngredientNameField] = useState('');
  const [ingredientQuantity, setIngredientQuantity] = useState('');
  const [unitField, setUnitField] = useState('none');

  //Should maybe change these functions out with useState and having components for the ingredient list and instruction list.
  const addInstruction = () => {
    if (instructionField) {
      setInstructions((prevState) => [...prevState, instructionField]);
    }
  };
  const addIngredient = () => {
    if (ingredientNameField && ingredientQuantity && unitField !== 'none') {
      setIngredients((prevState) => [
        ...prevState,
        ingredientQuantity + ' ' + unitField + ', ' + ingredientNameField,
      ]);
    }
  };
  const removeIngredient = (index: number) => {
    let ingredientsCopy = [...ingredients];
    ingredientsCopy.splice(index, 1);
    setIngredients(ingredientsCopy);
  };
  const removeInstruction = (index: number) => {
    let instructionsCopy = [...instructions];
    instructionsCopy.splice(index, 1);
    setInstructions(instructionsCopy);
  };

  const submitNewRecipeForm = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    //Axiom post to the correct path
  };
  return (
    <div className="new-recipe-container">
      <form onSubmit={(e) => submitNewRecipeForm(e)}>
        <input
          type="text"
          name="name"
          id="newRecipeForm"
          placeholder="Name"
          required
        />
        <br />
        <label htmlFor="portions">Portions:</label>
        <input type="number" name="portions" placeholder="num" required />
        <input
          type="number"
          name="estimatedTime"
          placeholder="Estimated time"
          required
        />
        <br />
        <textarea
          name="description"
          placeholder="description"
          required
        ></textarea>
        <br />
        <input
          onChange={(event) => setIngredientNameField(event.target.value)}
          type="text"
          name="ingredient"
          placeholder="ingredient"
          required
        />
        <input
          onChange={(event) => setIngredientQuantity(event.target.value)}
          type="number"
          name="quantity"
          placeholder="quantity"
          required
        />
        <select
          onChange={(event) => setUnitField(event.target.value)}
          name="unit"
          required
          placeholder="unit"
        >
          <option value="none" selected disabled hidden>
            Unit
          </option>
          <option value="gram">gram</option>
          <option value="dl">dl</option>
          <option value="stk">stk</option>
        </select>
        <button type="button" id="addIngredientButton" onClick={addIngredient}>
          Add
        </button>
        <br />
        <textarea
          name="instruction"
          placeholder="Instruction"
          onChange={(event) => setInstructionField(event.target.value)}
          required
        ></textarea>
        <br />
        <button
          type="button"
          id="addInstructionButton"
          onClick={addInstruction}
        >
          Add instruction
        </button>
        <br />
        <div className="instructions">
          <p>Instructions</p>
          <ol>
            {instructions.map((instruction, index) => (
              <li key={index}>
                {instruction}
                <div
                  onClick={(e) => removeInstruction(index)}
                  className="tooltip delete-div"
                >
                  X<span className="tooltiptext">Remove instruction</span>
                </div>
              </li>
            ))}
          </ol>
        </div>
        <br />
        <div className="ingredients">
          <p>Ingredients</p>
          <ol>
            {ingredients.map((ingredient, index) => (
              <li>
                {ingredient}
                <div
                  onClick={(e) => removeIngredient(index)}
                  className="tooltip delete-div"
                >
                  X<span className="tooltiptext">Remove ingredient</span>
                </div>
              </li>
            ))}
          </ol>
        </div>
        <br />
        <input type="submit" value="Add recipe" />
      </form>
    </div>
  );
};

export default NewRecipe;
