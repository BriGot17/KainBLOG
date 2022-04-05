// test_spec.js created with Cypress
//
// Start writing your Cypress tests below!
// If you're unfamiliar with how Cypress works,
// check out the link below and learn how to write your first test:
// https://on.cypress.io/writing-first-test

describe('Nav Test', () => {
  it('Tests the Nav', () => {
    cy.visit('http://localhost:3000/')

    cy.contains('Login').click()
    cy.url().should('include', '/login')
    
    cy.contains('Profil').click()
    cy.url().should('include', '/users/gotped17')

    cy.contains('Add new Article').click()
    cy.url().should('include', '/article/new')

    cy.contains('Hauptseite').click()
  })
})


describe('Login Test', () => {
    it('Tests the login', () => {
      cy.visit('http://localhost:3000/')
  
      cy.contains('Login').click()

      cy.url().should('include', '/login')
    
      cy.get('input[name="user"]')
      .type('gotped')
      .should('have.value', 'gotped')

      cy.get('input[name="pass"]')
      .type('ABCDE')
      .should('have.value', 'ABCDE')
    })
  })
  