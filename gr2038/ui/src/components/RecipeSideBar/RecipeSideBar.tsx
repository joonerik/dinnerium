import React, { FC } from 'react';
import { Link, useLocation } from 'react-router-dom';
import './recipeSideBar.scss';

//ISideBar - optional props hence isEmpty bool
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
  const location = useLocation();
  const isActive = (path: string) => location.pathname === path;

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
        {isEmpty && isActive('/newRecipe') ? (
          <h2>
            <i>Add your favorite dish!</i>
          </h2>
        ) : isEmpty && isActive('/recipes') ? (
          <h2>
            <i>Select a dish</i>
          </h2>
        ) : (
          <>
            <h2 className="recipe__name">{recipeName}</h2>
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
                  <li key={ingredient.toString() + index}>
                    {ingredient.name} {ingredient.quantity.amount}{' '}
                    {ingredient.quantity.unit}
                  </li>
                )
              )}
            </ul>
            <h3 className="ul-header">Instructions</h3>
            <ul>
              {recipeInstructions?.map((instruction: string, index: number) => (
                <li key={instruction + index}>{instruction}</li>
              ))}
            </ul>
          </>
        )}
      </div>
    </div>
  );
};

export default RecipeSideBar;
