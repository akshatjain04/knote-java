# ********RoostGPT********

# Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview
# 
# ROOST_METHOD_HASH=12ece3c33e
# ROOST_METHOD_SIG_HASH=acd4a6d263
# 
#  ########## Scenario ########## 
# 
# {
#   background: 'Given the base URL is "http://localhost:8080"',
#   rule: null,
#   scenario: 'Request must fail with error if request not authenticated\r\n' +
#     '    Given id of recipient as 09237482347 in path parameter\r\n' +
#     '    And size=43 and offset=22 in request query\r\n' +
#     '    When the client sends a GET request to endpoint "/recipients/persons/{id}/registrations" \r\n' +
#     '    When authentication token is not sent in request\r\n' +
#     '    Then for unauthenticated request having status code 401, Verify that headers have api-version\r\n' +
#     '    And response body must have appropriate error schema',
#   title: 'Request must fail with error if request not authenticated'
# }
# 

# ********RoostGPT********
Feature: Validate recipients API authentication

Background:
  * def urlBase = karate.properties['url.base'] || karate.get('urlBase', 'http://localhost:8080')
  * url urlBase
  * def authToken = karate.properties['AUTH_TOKEN']
  * def id = '09237482347'

Scenario: Request must fail with error if request not authenticated
  Given path 'recipients/persons/', id, '/registrations'
  And param credential-url = null
  And param size = 43
  And param offset = 22
  When method get
  Then status 401
  And match responseHeaders['api-version'] == '1.0.0'
  And match response == { error: '#string' }
