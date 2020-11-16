describe('Login page', () => {
  // go to web application
  beforeEach(() => {
    cy.visit('http://localhost:3000');
  });

  // Checks if the input field form has focus
  it('Focus on input form', () => {
    cy.focused().should('have.class', 'login__modal__input');
  });

  // Tests login
  it('Test login', () => {
    cy.login('data');
  });

  // Tries logging in to a non existing user
  it('Test login error handling', () => {
    cy.login('shouldNotExist');
    cy.get('#toastContainer').should(
      'have.text',
      'User not found! Please register a user'
    );
  });

  // Tests registering a user
  it('Register', () => {
    cy.get('.login__modal__input').type('registertest');
    cy.get('.login__modal__btnContainer').contains('Register').click();
  });
});
