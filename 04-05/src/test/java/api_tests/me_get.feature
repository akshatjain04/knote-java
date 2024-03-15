# ********RoostGPT********

# Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview
# 
# Feature file generated for /me_get for http method type GET 
# RoostTestHash=ae6b8ce5ab
# 
# 

# ********RoostGPT********
Feature: Account Details

Background:
  * def urlBase = karate.properties['url.base'] || karate.get('urlBase', 'http://localhost:8080')
  * url urlBase
  * def authToken = karate.env['AUTH_TOKEN']
  * header Token = authToken

Scenario: Get account details with valid token
  Given path '/me'
  When method get
  Then status 200
  And match responseHeaders contains { 'api-version': '#string' }
  And match response == { id: '#uuid', email: '#regex(^(([^<>()[\\]\\\\.,;:\\s@\\\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$)' }
  And match response.createdAt == '#string'
  And match response.updatedAt == '#string'

Scenario: Get account details with invalid or missing token
  Given path '/me'
  And header Token = null
  When method get
  Then status 401
  And match responseHeaders contains { 'api-version': '#string' }
  And match response == { error: '#string', description: '#string' }

Scenario: Get account details with malformed request
  Given path '/invalidpath'
  When method get
  Then status 400
  And match responseHeaders contains { 'api-version': '#string' }
  And match response == { error: '#string', description: '#string' }
