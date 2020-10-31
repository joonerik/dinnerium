describe('Login or register', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000');
  });

  it('Focus on input form', () => {
    cy.focused().should('have.class', 'login__modal__input');
  });

  it('Add new recipe', () => {
    cy.login('data');

    // uncomment when merged/updated
    // cy.contains('Recipes').click();
    // cy.contains('#titleNewRecipeLink').click();
    cy.get('input[name="name"]').type('Taco');
    cy.get('input[name="estimatedTime"]').type('20'); //string??
    cy.get('input[name="portions"]').type('2.5'); //string??
    cy.get('textarea[name="description"]').type(
      'A great Mexican dish, which happens to be quite popular in Norway, although the Norwegian taco is quite different'
    );

    cy.addIngredient('Minced meat', 400, 'gram');
    cy.addIngredient('Taco powder', 1, 'stk');
    cy.addIngredient('Grated cheese', 100, 'gram');
    cy.addIngredient('Salad', 1, 'stk');
    cy.addIngredient('Romme', 1, 'stk');
    cy.addIngredient('Tacosaus', 1, 'stk');
    cy.addIngredient('Beer', 3, 'liter');

    cy.addIngredient('Ananas', 5, 'kg');
    cy.get('.ingredients').find('ol').last().find('div').last().click();

    cy.addInstruction('Cook the minced meat');
    cy.addInstruction('Add the powder and mix it');
    cy.addInstruction('Cut the salad');
    cy.addInstruction('Eat it as you like, and enjoy!');

    cy.addInstruction('If any leftovers, throw it');
    cy.get('.instructions').find('ol').last().find('div').last().click();

    // uncomment when merged/updated
    // cy.get('#recipeForm').submit();
    // cy.get('#recipeForm').clear();
  });
});
