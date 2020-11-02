describe('Logout', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000');
  });

  it('Logout', () => {
    cy.login('test');
    cy.contains('Settings').click();
    cy.get('button').click();
    cy.get('.login__modal').should('have.class', 'login__modal');
  });
});
