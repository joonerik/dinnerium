describe('Add item to fridge', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000');
  });

  it('Focus on input form', () => {
    cy.focused().should('have.class', 'login__modal__input');
  });

  it('Register', () => {
    cy.get('.login__modal__btnContainer').contains('Register').click();
  });

  it('Login', () => {
    cy.get('.login__modal__input').type('data');
    cy.get('.login__modal__btnContainer').contains('Login').click();
    cy.contains('Fridge').click();
    cy.get('input[name="name"]').type('flour');
    cy.get('input[name="quantity"]').type('8');
    cy.get('select[name="unit"]').select('dl');
    cy.get('button').contains('Add').click();
    cy.get('.item').contains('8dl flour');

    cy.contains('Settings').click();
    cy.contains('Logout').click();
  });
});
