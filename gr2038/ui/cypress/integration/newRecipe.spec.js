describe('New recipe page', () => {
  // go to web application
  beforeEach(() => {
    cy.visit('http://localhost:3000');
  });

  it('Focus on input form', () => {
    cy.focused().should('have.class', 'login__modal__input');
  });

  it('Test error handling to add new recipe', () => {
    cy.login('data');
    cy.contains('Recipes').click();
    cy.get('#titleNewRecipeLink').click();
    cy.get('input[name="name"]').type('Test recipe');
    cy.get('input[name="estimatedTime"]').type('20');
    cy.get('input[name="portions"]').type('2.5');
    cy.get('textarea[name="description"]').type('This recipe is a test');

    cy.get('#recipeForm').submit();
    cy.get('#toastContainer').should(
      'have.text',
      'You need to add instructions'
    );
    cy.addInstruction('A instruction');
    cy.wait(3000);
    cy.get('#recipeForm').submit();
    cy.get('#toastContainer').should(
      'have.text',
      'You need to add ingredients'
    );
  });

  it('Add new recipe', () => {
    cy.login('data');

    cy.contains('Recipes').click();
    cy.get('#titleNewRecipeLink').click();

    // write recipe info
    cy.get('input[name="name"]').type('Taco');
    cy.get('input[name="estimatedTime"]').type('20');
    cy.get('input[name="portions"]').type('2.5');
    cy.get('textarea[name="description"]').type(
      'A great Mexican dish, which happens to be quite popular in Norway, although the Norwegian taco is quite different'
    );

    // add ingredients
    cy.addIngredient('Minced meat', 400, 'gram');
    cy.addIngredient('Taco powder', 1, 'stk');
    cy.addIngredient('Grated cheese', 100, 'gram');
    cy.addIngredient('Salad', 1, 'stk');
    cy.addIngredient('Romme', 1, 'stk');
    cy.addIngredient('Tacosaus', 1, 'stk');
    cy.addIngredient('Beer', 3, 'liter');

    // add an ingredient and check remove button
    cy.addIngredient('Ananas', 5, 'kg');
    cy.get('.ingredients').find('ol').last().find('div').last().click();

    // add instructions
    cy.addInstruction('Cook the minced meat');
    cy.addInstruction('Add the powder and mix it');
    cy.addInstruction('Cut the salad');
    cy.addInstruction('Eat it as you like, and enjoy!');

    // add an instruction and check remove button
    cy.addInstruction('If any leftovers, throw it');
    cy.get('.instructions').find('ol').last().find('div').last().click();

    // uncomment when merged/updated
    cy.get('#recipeForm').submit();
    // cy.get('.recipeBox').last().should('have.class', 'active-recipe');
    cy.get('.recipeBox').last().click();
    cy.get('.recipe__name').should('have.text', 'Taco');
  });
});
