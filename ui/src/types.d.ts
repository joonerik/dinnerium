interface IUser {
  ingredientContainer: IngredientContainer;
  recipeContainer: RecipeContainer;
  username: string;
}

interface Ingredient {
  quantity: Quantity;
  name: string;
}

interface Quantity {
  unit: string;
  amount: number;
}

interface IngredientContainer {
  ingredients?: Ingredient[];
}

interface RecipeContainer {
  recipes: Recipe[];
}

interface Recipe {
  ingredientContainer: IngredientContainer;
  recipeInstructions: RecipeInstructions;
  metadata: Metadata;
}
type RecipeInstructions = string[];

interface Metadata {
  author: string;
  portion: number;
  recipeName: string;
  recipeDescription: string;
  minutes: number;
}
