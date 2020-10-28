import React, { FC } from 'react';
import { Link } from 'react-router-dom';
import './recipeSideBar.scss';

interface ISideBar {
  recipeName?: Metadata['recipeName'];
  recipeDescription?: Metadata['recipeDescription'];
  recipeAuthor?: Metadata['author'];
  recipeMinutes?: Metadata['minutes'];
  recipePortion?: Metadata['portion'];
  recipeInstructions?: Recipe['recipeInstructions'];
  recipeIngredients?: IngredientContainer['ingredients'];
  isEmpty: boolean;
}

const RecipeSideBar: FC<ISideBar> = ({
  recipeAuthor,
  recipeDescription,
  recipeIngredients,
  recipeInstructions,
  recipeMinutes,
  recipeName,
  recipePortion,
  isEmpty,
}) => {
  return (
    <div className="sideBar">
      <header>
        <h2 id="titleRecipes">Recipes</h2>
        <h2 id="titleNewRecipeLink">
          <Link to="/newRecipe">New recipe</Link>
        </h2>
      </header>

      <div className="recipe__info">
        {isEmpty ? (
          <h2>
            <i>Select a dish</i>
          </h2>
        ) : (
          <>
            <h2>{recipeName}</h2>
            <p className="recipe__info__description">{recipeDescription}</p>
            <div id="recipeMetadata">
              <p>{recipeAuthor && 'made by ' + recipeAuthor}</p>
              <span>|</span>
              <p>{recipeMinutes && recipeMinutes + ' minutes'}</p>
              <span>|</span>
              <p>{recipePortion && recipePortion + ' portions'}</p>
            </div>
            <h3 className="ul-header">Ingredients</h3>
            <ul>
              {recipeIngredients?.map(
                (ingredient: Ingredient, index: number) => (
                  <li key={index + 1 * 0.90023}>
                    {ingredient.name} {ingredient.quantity.amount}{' '}
                    {ingredient.quantity.unit}
                  </li>
                )
              )}
            </ul>
            <h3 className="ul-header">Instructions</h3>
            <ul>
              {recipeInstructions?.map((instruction: string, index: number) => (
                <li key={index + 1 * 3.2213}>{instruction}</li>
              ))}
            </ul>
          </>
        )}
      </div>
    </div>
  );
};

export default RecipeSideBar;
