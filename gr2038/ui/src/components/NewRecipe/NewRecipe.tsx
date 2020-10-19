import React, { useState } from 'react';
import './newRecipe.scss';

const NewRecipe = () => {
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
        <button id="addIngredientButton">Add</button>
        <br />
        <textarea
          name="instruction"
          placeholder="Instruction"
          required
        ></textarea>
        <br />
        <button id="addInstructionButton">Add instruction</button>
        <br />
        <div className="instructions">
          <p>Instructions</p>
          <ol id="instructionList"></ol>
        </div>
        <br />
        <div className="ingredients">
          <p>Ingredients</p>
          <ol id="ingredientList"></ol>
        </div>
        <br />
        <input type="submit" value="Add recipe" />
      </form>
    </div>
  );
};

export default NewRecipe;
