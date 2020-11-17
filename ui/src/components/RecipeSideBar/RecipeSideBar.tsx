import React, { FC } from 'react';
import { Link, useLocation } from 'react-router-dom';
import './recipeSideBar.scss';

//ISideBar - optional props hence isEmpty bool
interface ISideBar {
  recipeMetadata?: Metadata;
  recipeInstructions?: Recipe['recipeInstructions'];
  recipeIngredients?: IngredientContainer['ingredients'];
}

const RecipeSideBar: FC<ISideBar> = ({
  recipeIngredients,
  recipeInstructions,
  recipeMetadata,
}) => {
  const location = useLocation();
  const isActive = (path: string) => location.pathname === path;
  if (recipeMetadata === undefined) {
    return (
      <div className="sideBar">
        <header>
          <h2 id="titleRecipes">
            <Link
              to="/recipes"
              className={
                isActive('/recipes')
                  ? 'sidebar-link side-active'
                  : 'sidebar-link'
              }
            >
              Recipes
            </Link>
          </h2>
          <h2 id="titleNewRecipeLink">
            <Link
              to="/newRecipe"
              className={
                isActive('/newRecipe')
                  ? 'sidebar-link side-active'
                  : 'sidebar-link'
              }
            >
              New recipe
            </Link>
          </h2>
        </header>
        <div className="recipe__info">
          {isActive('/newRecipe') ? (
            <h2>
              <i>Add your favorite dish!</i>
            </h2>
          ) : (
            <h2>
              <i>Select a dish</i>
            </h2>
          )}
        </div>
      </div>
    );
  }
  return (
    <div className="sideBar">
      <header>
        <h2 id="titleRecipes">
          <Link
            to="/recipes"
            className={
              isActive('/recipes') ? 'sidebar-link side-active' : 'sidebar-link'
            }
          >
            Recipes
          </Link>
        </h2>
        <h2 id="titleNewRecipeLink">
          <Link
            to="/newRecipe"
            className={
              isActive('/newRecipe')
                ? 'sidebar-link side-active'
                : 'sidebar-link'
            }
          >
            New recipe
          </Link>
        </h2>
      </header>
      <div className="recipe__info">
        <>
          <h2 className="recipe__name">{recipeMetadata.recipeName}</h2>
          <p className="recipe__info__description">
            {recipeMetadata.recipeDescription}
          </p>
          <div id="recipeMetadata">
            <p>{recipeMetadata.author && 'made by ' + recipeMetadata.author}</p>
            <span>|</span>
            <p>
              {recipeMetadata.minutes && recipeMetadata.minutes + ' minutes'}
            </p>
            <span>|</span>
            <p>
              {recipeMetadata.portion && recipeMetadata.portion + ' portions'}
            </p>
          </div>
          <h3 className="ul-header">Ingredients</h3>
          <ul>
            {recipeIngredients?.map((ingredient: Ingredient, index: number) => (
              <li key={ingredient.toString() + index}>
                {ingredient.name} {ingredient.quantity.amount}{' '}
                {ingredient.quantity.unit}
              </li>
            ))}
          </ul>
          <h3 className="ul-header">Instructions</h3>
          <ul>
            {recipeInstructions?.map((instruction: string, index: number) => (
              <li key={instruction + index}>{instruction}</li>
            ))}
          </ul>
        </>
      </div>
    </div>
  );
};

export default RecipeSideBar;
