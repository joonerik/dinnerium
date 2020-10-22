import React from 'react';
import { useLocation } from 'react-router-dom';
import NewRecipe from '../../components/NewRecipe/NewRecipe';
import RecipeBoxComponent from '../../components/RecipeBoxComponent/RecipeBoxComponent';

const RecipePage = () => {
  const location = useLocation();
  const isActive = (path: string) => location.pathname === path;
  return (
    <div>
      <div>
        {isActive('/recipes') ? <RecipeBoxComponent /> : <NewRecipe />}
        {/* <RecipeBoxComponent /> */}
      </div>
    </div>
  );
};
export default RecipePage;
