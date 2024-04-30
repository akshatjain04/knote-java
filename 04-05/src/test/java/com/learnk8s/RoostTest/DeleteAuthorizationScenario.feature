# ********RoostGPT********

# Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview
# 
# ROOST_METHOD_HASH=87771cdd6d
# ROOST_METHOD_SIG_HASH=0a68a12aee
# 
#  ########## Scenario ########## 
# 
# {
#   background: 'Given the base URL is "http://localhost:8080"',
#   rule: null,
#   scenario: 'Delete authorization\r\n' +
#     '    Given authorization ID\r\n' +
#     '    When the client sends a DELETE request to "/authorizations/ID"\r\n' +
#     '    Then the response status code should be 200 for a successful delete\r\n' +
#     '    And the authorizations should not appear in GET request for "/authorizations"',
#   title: 'Delete authorization'
# }
# 

# ********RoostGPT********
Feature: Delete authorization

  Background:
    * def urlBase = karate.properties['url.base'] || karate.get('urlBase', 'http://localhost:8080')
    * url urlBase
    * def authToken = karate.properties['AUTH_TOKEN']
    * def authHeader = { 'Token': '#(authToken)' }
    * def authorizationId = '123e4567-e89b-12d3-a456-426614174000'

  Scenario: Delete an existing authorization
    Given path 'authorizations', authorizationId
    And headers authHeader
    When method delete
    Then status 200
    And match header 'api-version' == '1.0.0'

  Scenario: Ensure the deleted authorization cannot be retrieved
    Given path 'authorizations'
    And headers authHeader
    When method get
    Then status 200
    And match each response.authorizations[*].id != authorizationId
    And match header 'api-version' == '1.0.0'
