import React, { createRef } from 'react';
import './newRecipe.scss';

const instructionTextArea = createRef<HTMLTextAreaElement>();
const instructionsList = createRef<HTMLOListElement>();
const ingredientsList = createRef<HTMLOListElement>();

const NewRecipe = () => {
  const addInstruction = () => {
    if (instructionTextArea.current?.value) {
      const listItem = document.createElement('li');
      listItem.innerHTML = instructionTextArea.current.value;
      instructionsList.current?.appendChild(listItem);
    }
  };
  const addIngredient = () => {
    //Check if input fields are all filled out and create element and append text if so.
    const listItem = document.createElement('li');
    // Retrive correct data from the fields and add it to the listitem.
    listItem.innerHTML = 'test :)';
    ingredientsList.current?.appendChild(listItem);
  };

  return (
    <div className="new-recipe-container">
      <form>
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
          type="text"
          name="ingredient"
          placeholder="ingredient"
          required
        />
        <input type="text" name="quantity" placeholder="quantity" required />
        <select name="unit" required>
          <option value="none" selected disabled hidden>
            Unit
          </option>
          <option value="gram">gram</option>
          <option value="dl">dl</option>
          <option value="stk">stk</option>
        </select>
        <button type="button" onClick={addIngredient}>
          Add
        </button>
        <br />
        <textarea
          name="instruction"
          placeholder="Instruction"
          ref={instructionTextArea}
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
          <ol ref={instructionsList}></ol>
        </div>
        <br />
        <div className="ingredients">
          <p>Ingredients</p>
          <ol ref={ingredientsList}></ol>
        </div>
        <br />
        <input type="submit" value="Add recipe" />
      </form>
    </div>
  );
};

export default NewRecipe;
