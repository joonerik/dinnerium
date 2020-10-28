describe('Login or register', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000');
  });

  it('Focus on input form', () => {
    cy.focused().should('have.class', 'login__modal__input');
  });

  it('Add new recipe', () => {
    cy.login('data');
    // h2 id="titlNewRecipeLink"
    cy.get('input[name="name"]').type('Taco');
    cy.get('input[name="estimatedTime"]').type('20'); //string??
    cy.get('input[name="portions"]').type('2.5'); //string??
    cy.get('textarea[name="description"]').type(
      'A great Mexican dish, which happens to be quite popular in Norway, although the Norwegian taco is quite different'
    );

    // cy.get('input[name="ingredient"]').type('Minced meat');
    // cy.get('input[name="quantity"]').type('400'); // string?
    // cy.get('select').select('gram');
    // cy.get('#addIngredientButton').click();
    cy.addIngredient('Minced meat', 400, 'gram');

    // cy.get('input[name="ingredient"]').type('Grated cheese');
    // cy.get('input[name="quantity"]').type('100'); // string?
    // cy.get('select').select('gram');
    // cy.get('#addIngredientButton').click();

    // cy.get('input[name="ingredient"]').type('Minced meat');
    // cy.get('input[name="quantity"]').type('400'); // string?
    // cy.get('select').select('gram');
    // cy.get('#addIngredientButton').click();
  });
});
