// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })

Cypress.Commands.add('login', (username) => {
  cy.get('.login__modal__input').type(username);
  cy.get('.login__modal__btnContainer').contains('Login').click();
  //   cy.get('input[name=ingredient]').clear();

  //   cy.get('input[name=quantity]').clear();
});

Cypress.Commands.add('addIngredient', (ingredient, quantity, unit) => {
  cy.get('input[name="ingredient"]').type(ingredient);
  cy.get('input[name="quantity"]').type(quantity);
  cy.get('select').select(unit);
  cy.get('#addIngredientButton').click();
});
