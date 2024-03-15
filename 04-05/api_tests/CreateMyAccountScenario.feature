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
Feature: Account Management

Background:
  * def urlBase = karate.properties['url.base'] || karate.get('urlBase', 'http://localhost:8080')
  * url urlBase

Scenario: Create and verify a new account
  # Create a new account using POST request with valid payload
  Given path '/accounts'
  And header commit-hash = '928d28d'
  And request { email: 'test@example.com' }
  When method post
  Then status 201
  And match responseHeaders['api-version'] == '1.0.0'
  And match responseHeaders['commit-hash'] == '928d28d'
  # Extract the Token from the response for use in subsequent requests
  * def accountToken = responseHeaders['Token']
  
  # Verify the account created using GET request for "/me"
  Given path '/me'
  And header Token = accountToken
  When method get
  Then status 200
  And match responseHeaders['api-version'] == '1.0.0'
  And match response contains { id: '#uuid', email: 'test@example.com' }
  And match response contains { createdAt: '#string', updatedAt: '#string' }

  # Test with an invalid commit-hash header for account creation
  Given path '/accounts'
  And header commit-hash = 'invalid-hash'
  And request { email: 'test@example.com' }
  When method post
  Then status 400
  And match responseHeaders['api-version'] == '1.0.0'

  # Test the /me endpoint with an invalid or missing Token
  Given path '/me'
  And header Token = 'invalid-or-missing-token'
  When method get
  Then status 401
  And match responseHeaders['api-version'] == '1.0.0'
