describe('Add item to fridge', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000');
  });

  it('Focus on input form', () => {
    cy.focused().should('have.class', 'login__modal__input');
  });

  it('Login', () => {
    cy.login('data');
    cy.contains('Fridge').click();
    cy.get('input[name="ingredient"]').type('flour');
    cy.get('input[name="quantity"]').type('8');
    cy.get('select[name="unit"]').select('dl');
    cy.get('button').contains('Add').click();
    cy.get('.item').contains('8dl flour');
    cy.get('#toastContainer').should(
      'have.text',
      'Added new ingredient to fridge'
    );
  });

  /*it('Test error handling', () => {
    cy.login('data');
    cy.contains('Fridge').click();
    cy.get('input[name="ingredient"]').type('12345');
    cy.get('input[name="quantity"]').type('8');
    cy.get('select[name="unit"]').select('dl');
    cy.get('button').contains('Add').click();
    cy.get('.itemList').should('be.empty');
  });*/
  //Needs to find a solution to clearing out the data file before this works.
});
