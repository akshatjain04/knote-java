# ********RoostGPT********

# Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview
# 
# ROOST_METHOD_HASH=d4875ba7c9
# ROOST_METHOD_SIG_HASH=deac2363dc
# 
#  ########## Scenario ########## 
# 
# {
#   feature: 'Feature: Credential Wallet API\r\n' +
#     '  As a user of the Credential Wallet API\r\n' +
#     '  I want to get registration details of people allowed to receive credentials \r\n' +
#     '  So that I can manage my account effectively',
#   background: 'Background:\r\n    Given the base URL is "http://localhost:8080"',
#   rule: null,
#   scenario: {
#     title: 'Scenario: Get Registration Details Of Recipient Person',
#     steps: 'Given id of recipient as 27364922937 in path parameter\r\n' +
#       'And size=13 and offset=42 in request query\r\n' +
#       'When the client sends a GET request to endpoint "/recipients/persons/{id}/registrations" \r\n' +
#       'When authentication token is sent in request\r\n' +
#       'Then for successful request having status code 200, Verify that headers have api-version, page-size and  page-offset\r\n' +
#       'And response body must have element registrations and total.',
#     examples: ''
#   }
# }
# 

# ********RoostGPT********
Feature: Credential Wallet API
  As a user of the Credential Wallet API
  I want to get registration details of people allowed to receive credentials
  So that I can manage my account effectively

  Background:
    * def urlBase = karate.properties['url.base'] || karate.get('urlBase', 'http://localhost:8080')
    * url urlBase
    * def authToken = karate.properties['AUTH_TOKEN']
    * header Authorization = authToken

  Scenario: Get Registration Details Of Recipient Person
    Given path 'recipients/persons/27364922937/registrations'
    And param size = 13
    And param offset = 42
    When method get
    Then status 200
    And match header api-version == '1.0.0'
    And match header page-size == '13'
    And match header page-offset == '42'
    And match response contains { registrations: '#array', total: '#number' }
    And match each response.registrations[*] contains 
    """
    {
      id: '#uuid', 
      reference: '##string', 
      startDate: '#string', 
      endDate: '##string', 
      status: '#string', 
      credentialUrl: '#string', 
      suspendedUntil: '##string', 
      issuedAt: '##string', 
      createdAt: '#string', 
      updatedAt: '#string'
    }
    """
