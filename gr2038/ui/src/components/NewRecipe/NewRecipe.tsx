import React, { useContext, useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';
import './newRecipe.scss';
import UserContext from '../UserContext/UserContext';

const NewRecipe = () => {
  const [instructions, setInstructions] = useState<string[]>([]);
  const [instructionField, setInstructionField] = useState('');
  const [ingredients, setIngredients] = useState<Ingredient[]>([]);
  const [ingredientNameField, setIngredientNameField] = useState('');
  const [ingredientQuantity, setIngredientQuantity] = useState('');
  const [unitField, setUnitField] = useState('none');
  const [units, setUnits] = useState<string[]>([]);
  const { user, setUser } = useContext(UserContext);
  const history = useHistory();

  useEffect(() => {
    axios.get('/units').then((response) => {
      setUnits(response.data.replace('[', '').replace(']', '').split(', '));
    });
  }, []);

  //Should maybe change these functions out with useState and having components for the ingredient list and instruction list.
  const addInstruction = () => {
    if (instructionField) {
      setInstructions((prevState) => [...prevState, instructionField]);
    }
  };
  const addIngredient = () => {
    if (ingredientNameField && ingredientQuantity && unitField !== 'none') {
      const i = {
        quantity: {
          amount: parseFloat(ingredientQuantity),
          unit: unitField,
        } as Quantity,
        name: ingredientNameField,
      } as Ingredient;
      setIngredients((prevState) => [...prevState, i]);
    } else {
      //Add updating a state here that causes a feedback to render
      //and use for example setTimeout to set the state back to empty when the
      //feedback should be hidden again.
      const ingredientNameFieldMessage = ingredientNameField
        ? ''
        : "ingredient can't be empty";
      const ingredientQuantityMessage = ingredientQuantity
        ? ''
        : "quantity can't be empty";
      const unitFieldMessage =
        unitField !== 'none' ? '' : 'Unit must be chosen';
      alert(
        ingredientNameFieldMessage +
          '\n' +
          ingredientQuantityMessage +
          '\n' +
          unitFieldMessage
      );
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

  const submitNewRecipeForm = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (instructions.length && ingredients.length) {
      const formData = new FormData(e.target as HTMLFormElement);
      axios
        .post(`/users/${user.username}/recipes/add`, {
          ingredientContainer: { ingredients: ingredients },
          recipeInstructions: instructions,
          metadata: {
            author: user.username,
            portion: parseFloat(formData.get('portions') as string),
            image: 'http://folk.ntnu.no/anderobs/images/tikkaMasala.png',
            recipeName: formData.get('name'),
            recipeDescription: formData.get('description'),
            minutes: parseFloat(formData.get('estimatedTime') as string),
          },
        })
        .then((response) => {
          setUser(response.data);
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
    <div className="new-recipe-container">
      <form onSubmit={submitNewRecipeForm}>
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
        />
        <input
          onChange={(event) => setIngredientQuantity(event.target.value)}
          type="number"
          name="quantity"
          placeholder="quantity"
        />
        <select
          onChange={(event) => setUnitField(event.target.value)}
          defaultValue={'None'}
          name="unit"
          placeholder="unit"
        >
          <option value="None" disabled>
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
          <p id="feedback_text">Instructions</p>
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
              <li key={index}>
                {ingredient.quantity.unit +
                  ' ' +
                  ingredient.quantity.amount +
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
        <input type="submit" value="Add recipe" />
      </form>
    </div>
  );
};

export default NewRecipe;
