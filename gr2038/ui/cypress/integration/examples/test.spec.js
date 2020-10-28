describe('Add item to fridge', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000');
  });

  it('Focus on input form', () => {
    cy.focused().should('have.class', 'login__modal__input');
  });

  it('Login', () => {
    cy.get('.login__modal__input').type('data');
    cy.get('.login__modal__btnContainer').contains('Login').click();
  });

  it('Register', () => {
    cy.get('.login__modal__input').type('ruben');
    cy.get('.login__modal__btnContainer').contains('Register').click();
  });
});
