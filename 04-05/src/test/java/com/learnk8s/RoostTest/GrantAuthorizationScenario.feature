# ********RoostGPT********

# Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview
# 
# ROOST_METHOD_HASH=803fe763f5
# ROOST_METHOD_SIG_HASH=c56a467b87
# 
#  ########## Scenario ########## 
# 
# {
#   background: 'Given the base URL is "http://localhost:8080"',
#   rule: null,
#   scenario: 'Grant authorization\r\n' +
#     '    Given an existing account, create another account\r\n' +
#     '    When the client sends a POST request to "/authorizations"\r\n' +
#     '    Then grant access to your account and confirm access via GET request to "/authorizations"',
#   title: 'Grant authorization'
# }
# 

# ********RoostGPT********
Feature: Grant authorization

  Background: 
    * def urlBase = karate.properties['url.base'] || karate.get('urlBase', 'http://localhost:8080')
    * url urlBase
    * def authToken = karate.properties['AUTH_TOKEN']

  Scenario: Create an account
    Given path '/accounts'
    And header commit-hash = '928d28d'
    And request { "email": "user@example.com" }
    When method post
    Then status 201
    And match responseHeaders['api-version'] == '1.0.0'
    And match responseHeaders['commit-hash'] == '928d28d'

  Scenario: Grant access to the account
    Given path '/authorizations'
    And header Token = authToken
    And request { "email": "user@example.com" }
    When method post
    Then status 201
    And match responseHeaders['api-version'] == '1.0.0'
    And match responseHeaders['location'] != null

  Scenario: Confirm access via GET request
    Given path '/authorizations'
    And header Token = authToken
    When method get
    Then status 200
    And match responseHeaders['api-version'] == '1.0.0'
    And match each response.authorizations[*].id == '#uuid'
    And match each response.authorizations[*].name == '#string'
    And match each response.authorizations[*].email == '#regex(.+@.+\..+)'
    And match each response.authorizations[*].createdAt == '#string'
    And match each response.authorizations[*].updatedAt == '#string'
