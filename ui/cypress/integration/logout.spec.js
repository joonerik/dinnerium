describe('Logout page', () => {
  // go to web application
  beforeEach(() => {
    cy.visit('http://localhost:3000');
  });

  // Tests the logout functionality
  it('Logout', () => {
    cy.login('data');
    cy.contains('Settings').click();
    cy.get('button').click();
    cy.get('.login__modal').should('have.class', 'login__modal');
  });
});
