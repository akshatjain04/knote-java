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

  Scenario: Given an existing account, create another account
    # Test for creating an account with a POST request
    Given path '/accounts'
    And header commit-hash = '928d28d'
    And request { email: 'test@example.com' }
    When method post
    Then status 201
    And match responseHeaders contains { 'api-version': '1.0.0', 'commit-hash': '#string' }
    And print 'Account created successfully.'

  Scenario: Grant access to your account and confirm access via GET request
    # Test for granting authorization with a POST request
    Given path '/authorizations'
    And header Token = 'someApiKey'
    And request { email: 'test@example.com' }
    When method post
    Then status 201
    And match responseHeaders contains { 'api-version': '1.0.0', 'location': '#string' }
    And print 'Authorization granted successfully.'

    # Confirming the granted authorization with a GET request
    Given path '/authorizations'
    And header Token = 'someApiKey'
    When method get
    Then status 200
    And match responseHeaders contains { 'api-version': '1.0.0' }
    And match response contains { total: '#number', authorizations: '#array' }
    And match each response.authorizations contains { id: '#uuid', email: '#string', createdAt: '#string', updatedAt: '#string' }
    And print 'Authorization confirmed successfully.'
