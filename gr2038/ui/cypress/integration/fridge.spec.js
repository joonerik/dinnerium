describe('Fridge page', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000');
  });

  it('Login', () => {
    cy.login('data');
    cy.contains('Fridge').click();
    cy.get('input[name="name"]').type('flour');
    cy.get('input[name="quantity"]').type('8');
    cy.get('select[name="unit"]').select('dl');
    cy.get('button').contains('Add').click();
    cy.get('.item').contains('8dl flour');
  });

  it('Test error handling', () => {
    cy.login('data');
    cy.contains('Fridge').click();
    cy.get('input[name="name"]').type('12345');
    cy.get('input[name="quantity"]').type('8');
    cy.get('select[name="unit"]').select('dl');
    cy.get('button').contains('Add').click();
    cy.get('#toastContainer').should('have.text', 'Invalid ingredient name!');
  });
});
