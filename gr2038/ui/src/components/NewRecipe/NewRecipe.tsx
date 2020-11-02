import React, { useContext, useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import './newRecipe.scss';
import { UserContext } from '../UserContext/UserContext';
import RecipeSideBar from '../RecipeSideBar/RecipeSideBar';

const NewRecipe = () => {
  const [instructions, setInstructions] = useState<string[]>([]);
  const [ingredients, setIngredients] = useState<Ingredient[]>([]);
  const [units, setUnits] = useState<string[]>([]);
  const { user, updateUser } = useContext(UserContext);
  const history = useHistory();

  useEffect(() => {
    axios.get('/units').then((response) => {
      setUnits(response.data.replace('[', '').replace(']', '').split(', '));
    });
  }, []);

  const addInstruction = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);
    setInstructions((prevState) => [
      ...prevState,
      formData.get('instruction') as string,
    ]);
  };
  const removeInstruction = (index: number) => {
    let instructionsCopy = [...instructions];
    instructionsCopy.splice(index, 1);
    setInstructions(instructionsCopy);
  };

  const addIngredient = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData(e.target as HTMLFormElement);
    const i = {
      quantity: {
        amount: parseFloat(formData.get('quantity') as string),
        unit: formData.get('unit'),
      } as Quantity,
      name: formData.get('ingredient'),
    } as Ingredient;
    setIngredients((prevState) => [...prevState, i]);
  };
  const removeIngredient = (index: number) => {
    let ingredientsCopy = [...ingredients];
    ingredientsCopy.splice(index, 1);
    setIngredients(ingredientsCopy);
  };

  const submitNewRecipeForm = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (instructions.length && ingredients.length) {
      const formData = new FormData(e.target as HTMLFormElement);
      axios
        .post(`/users/${user?.username}/recipes/add`, {
          ingredientContainer: { ingredients: ingredients },
          recipeInstructions: instructions,
          metadata: {
            author: user?.username,
            portion: parseFloat(formData.get('portions') as string),
            recipeName: formData.get('name'),
            recipeDescription: formData.get('description'),
            minutes: parseFloat(formData.get('estimatedTime') as string),
          },
        })
        .then((response) => {
          updateUser(response.data);
          //Should add some feedback here of somekind.
          history.push('/recipes');
        });
    } else {
      const instructionsMessage =
        instructions.length > 0 ? '' : 'You need to add instrucgtions \n';
      const ingredientsMessage =
        ingredients.length > 0 ? '' : 'You need to add ingredients';
      alert(instructionsMessage + ingredientsMessage);
    }
  };

  return (
    <div className="new-recipe-wrapper">
      <RecipeSideBar isEmpty={true} />
      <div className="new-recipe-container">
        <div className="first-part-form">
          <form
            onSubmit={submitNewRecipeForm}
            id="recipeForm"
            className="form__name-port-time-description"
          >
            <input
              type="text"
              name="name"
              id="newRecipeForm"
              placeholder="The name of your recipe"
              required
            />
            <div className="portion-time-container">
              <label htmlFor="portions">Portions:</label>
              <input type="number" name="portions" placeholder="num" required />
              <label htmlFor="estimatedTime">Minutes: </label>
              <input
                type="number"
                name="estimatedTime"
                placeholder="Estimated time"
                required
              />
            </div>

            <textarea
              name="description"
              placeholder="description"
              required
            ></textarea>
          </form>
          <form onSubmit={addIngredient} className="form__ingredient">
            <input
              type="text"
              name="ingredient"
              placeholder="ingredient"
              required
              pattern="^[ A-Za-z]+$"
            />
            <input
              type="number"
              name="quantity"
              placeholder="Amount"
              required
            />
            <select
              defaultValue=""
              name="unit"
              placeholder="unit"
              required
              className="unit-recipe"
            >
              <option value="" disabled>
                Unit
              </option>
              {units.map((item: string, index: number) => {
                return (
                  <option key={index} value={item}>
                    {item}
                  </option>
                );
              })}
            </select>
            <button type="submit" id="addIngredientButton">
              Add
            </button>
          </form>
        </div>
        <div className="instruction-form-container">
          <form onSubmit={addInstruction} className="form__instruction">
            <textarea
              name="instruction"
              placeholder="Instruction"
              required
            ></textarea>
            <br />
            <button type="submit" id="addInstructionButton">
              Add instruction
            </button>
            <br />
            <div className="instructions">
              <p id="feedback_text">Instructions</p>
              <ol>
                {instructions.map((instruction, index) => (
                  <li key={index + instruction}>
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
                  <li key={ingredient.toString() + index}>
                    {ingredient.quantity.amount +
                      ' ' +
                      ingredient.quantity.unit +
                      ' ' +
                      ingredient.name}
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
            <button type="submit" form="recipeForm">
              Add recipe
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default NewRecipe;
