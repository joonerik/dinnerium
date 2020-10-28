import React, { useContext, useState } from 'react';
import './recipeBox.scss';
import { UserContext } from '../UserContext/UserContext';
import RecipeSideBar from '../RecipeSideBar/RecipeSideBar';
interface IDisplays {
  recipe: Recipe;
}

const RecipeBoxComponent = () => {
  const [recipe, setRecipe] = useState<Recipe | null>(null);

  const { user } = useContext(UserContext);
  //checks if user is null -- so we are certain the user exists before return
  if (!user) {
    return <p>No user found</p>;
  }
  const displayRecipeMetadata = (index: number) => {
    setRecipe(user.recipeContainer.recipes[index]);
  };
  return (
    <div className="page-wrapper">
      {recipe ? (
        <RecipeSideBar
          recipeAuthor={recipe.metadata.author}
          recipeDescription={recipe.metadata.recipeDescription}
          recipeIngredients={recipe.ingredientContainer.ingredients}
          recipeInstructions={recipe.recipeInstructions}
          recipeMinutes={recipe.metadata.minutes}
          recipeName={recipe.metadata.recipeName}
          recipePortion={recipe.metadata.portion}
          isEmpty={false}
        />
      ) : (
        <RecipeSideBar isEmpty={true} />
      )}
      <div className="grid-wrapper">
        {user.recipeContainer.recipes.map((item: Recipe, index: number) => (
          <div
            key={index + 1 * 6.292}
            className={
              item === recipe ? 'recipeBox active-recipe' : 'recipeBox'
            }
            onClick={() => displayRecipeMetadata(index)}
          >
            <div className="recipeBoxDescription">
              <h2 key={index + 1 * 2.539}>{item.metadata.recipeName}</h2>
              <span className="box-metadata">
                <p key={index + 1 * 0.73}>{item.metadata.minutes} minutes</p>
                <span>|</span>
                <p key={index + 1 * 0.89}>{item.metadata.portion} portions</p>
              </span>
              <h4 key={index + 1 * 1.238}>{item.metadata.recipeDescription}</h4>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default RecipeBoxComponent;
