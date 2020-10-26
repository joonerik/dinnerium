import React, { useContext, useState } from 'react';
//import data from '../../../../core/src/main/resources/dinnerium/storage/data.json';
//import data from '../data.json';
import './recipeBox.scss';
import { UserContext } from '../UserContext/UserContext';
import { Link } from 'react-router-dom';

const RecipeBoxComponent = () => {
  const [displayRecipeName, setDisplayRecipeName] = useState<
    Metadata['recipeName'] | undefined
  >('');
  const [displayRecipeDescription, setDisplayRecipeDescription] = useState<
    Metadata['recipeDescription'] | undefined
  >('');
  const [displayRecipeAuthor, setDisplayRecipeAuthor] = useState<
    Metadata['author'] | undefined
  >('');
  const [displayRecipePortion, setDisplayRecipePortion] = useState<
    Metadata['portion'] | undefined
  >(0);
  const [displayRecipeMinutes, setDiplayRecipeMinutes] = useState<
    Metadata['minutes'] | undefined
  >(0);
  const [displayRecipeInstructions, setDisplayRecipeInstructions] = useState<
    RecipeInstructions
  >();
  const [displayRecipeIngredients, setDisplayRecipeIngredients] = useState<
    Ingredient[]
  >();

  const { user } = useContext(UserContext);
  const displayRecipeMetadata = (index: number) => {
    console.log(index);
    setDisplayRecipeName(
      user?.recipeContainer?.recipes[index].metadata.recipeName
    );
    setDisplayRecipeDescription(
      user?.recipeContainer?.recipes[index].metadata.recipeDescription
    );
    setDisplayRecipeAuthor(
      user?.recipeContainer?.recipes[index].metadata.author
    );
    setDisplayRecipePortion(
      user?.recipeContainer.recipes[index].metadata.portion
    );
    setDiplayRecipeMinutes(
      user?.recipeContainer.recipes[index].metadata.minutes
    );
    setDisplayRecipeInstructions(
      user?.recipeContainer.recipes[index].recipeInstructions
    );
    setDisplayRecipeIngredients(
      user?.recipeContainer.recipes[index].ingredientContainer.ingredients
    );
  };
  return (
    <div>
      <div className="wrapper">
        <div className="sideBar">
          <div>
            <h1 id="titleRecipes">Recipes</h1>
            <h1 id="titleNewRecipeLink">
              <Link to="/newRecipe">New recipe</Link>
            </h1>
          </div>
          <hr></hr>
          <div id="recipeInfo">
            <h2>{displayRecipeName}</h2>
            <p>{displayRecipeDescription}</p>

            <ul id="f">
              {displayRecipeInstructions?.map(
                (instruction: string, index: number) => (
                  <li key={index}>{instruction}</li>
                )
              )}
            </ul>
            <ul>
              {displayRecipeIngredients?.map(
                (ingredient: Ingredient, index: number) => (
                  <li key={index + 0.1}>
                    {ingredient.name}
                    {ingredient.quantity.amount}
                    {ingredient.quantity.unit}
                  </li>
                )
              )}
            </ul>
            <p>{displayRecipeAuthor}</p>
            <p>{displayRecipeMinutes}</p>
            <p>{displayRecipePortion}</p>
          </div>
        </div>
        {user?.recipeContainer?.recipes?.map((item: Recipe, index: number) => (
          <div key={index + 4} className="recipeBox">
            <div
              className="recipeBoxDescription"
              onClick={() => displayRecipeMetadata(index)}
            >
              <h1 key={index}>{item.metadata.recipeName}</h1>
              <h3 key={index + 1}>{item.metadata.recipeDescription}</h3>
              <span id="metadata">
                <p key={index + 2}>
                  <img
                    id="minutesIcon"
                    src="https://cdn4.iconfinder.com/data/icons/cooking/100/1-512.png"
                    alt="Minutes: "
                  ></img>
                  {item.metadata.minutes}
                </p>
                <p key={index + 3}>
                  <img
                    id="portionsIcon"
                    src="https://cdn4.iconfinder.com/data/icons/election-and-campaign/128/2-512.png"
                    alt="Portions: "
                  ></img>
                  {item.metadata.portion}
                </p>
              </span>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default RecipeBoxComponent;
