# ********RoostGPT********

# Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview
# 
# ROOST_METHOD_HASH=dff35151d7
# ROOST_METHOD_SIG_HASH=3a7061432c
# 
#  ########## Scenario ########## 
# 
# {
#   background: 'Given the base URL is "http://localhost:8080"',
#   rule: null,
#   scenario: 'Create my account \r\n' +
#     '    When the client sends a POST request "/accounts" with the accounts_body payload\r\n' +
#     '    Then create an account with the specified informatio\r\n' +
#     '    And verify the account created using GET request for "/me"',
#   title: 'Create my account'
# }
# 

# ********RoostGPT********
Feature: Account management

  Background:
    * def urlBase = karate.properties['url.base'] || karate.get('urlBase', 'http://localhost:8080')
    * url urlBase

  Scenario: Create my account
    # Create an account with the specified information
    Given path '/accounts'
    And header commit-hash = '928d28d'
    And request { email: 'user@example.com' }
    When method post
    Then status 201
    And match responseHeaders['api-version'] == '1.0.0'
    And match responseHeaders['commit-hash'] == '928d28d'
    # Save the commit-hash from the response for the next request
    * def commitHash = responseHeaders['commit-hash']
    
    # Verify the account created using GET request for "/me"
    Given path '/me'
    And header Token = commitHash
    When method get
    Then status 200
    And match responseHeaders['api-version'] == '1.0.0'
    And match response contains { id: '#uuid', email: 'user@example.com' }
