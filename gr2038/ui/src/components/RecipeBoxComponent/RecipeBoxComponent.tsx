import React, { FC, useContext, useState } from 'react';
import './recipeBox.scss';
import { UserContext } from '../UserContext/UserContext';
import RecipeSideBar from '../RecipeSideBar/RecipeSideBar';

const RecipeBoxComponent: FC = () => {
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
            key={item.metadata.recipeName + index}
            className={
              item === recipe ? 'recipeBox active-recipe' : 'recipeBox'
            }
            onClick={() => displayRecipeMetadata(index)}
          >
            <div className="recipeBoxDescription">
              <h2>{item.metadata.recipeName}</h2>
              <span className="box-metadata">
                <p>{item.metadata.minutes} minutes</p>
                <span>|</span>
                <p>{item.metadata.portion} portions</p>
              </span>
              <h4>{item.metadata.recipeDescription}</h4>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default RecipeBoxComponent;
