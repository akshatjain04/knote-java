# ********RoostGPT********

# Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview
# 
# ROOST_METHOD_HASH=a12559ab6a
# ROOST_METHOD_SIG_HASH=03770d9067
# 
#  ########## Scenario ########## 
# 
# {
#   feature: 'Feature: Account Consent Setup',
#   background: 'Background:\r\n' +
#     "        * def urlBase = karate.properties['url.base'] || karate.get('urlBase', 'http://localhost:4010')\r\n" +
#     '        * url urlBase\r\n' +
#     "        * def authToken = karate.properties['AUTH_TOKEN']",
#   rule: null,
#   scenario: {
#     title: 'Scenario: Create account consent with missing required headers',
#     steps: "Given path '/aisp/account-consents'\r\n" +
#       "And header Authorization = 'Bearer ' + authToken\r\n" +
#       "And header Content-Type = 'application/json'\r\n" +
#       'And request\r\n' +
#       '            """\r\n' +
#       '            {\r\n' +
#       '                "data": {\r\n' +
#       '                    "permissions": [\r\n' +
#       '                        "ReadAccountBalance"\r\n' +
#       '                    ]\r\n' +
#       '                }\r\n' +
#       '            }\r\n' +
#       '            """\r\n' +
#       'When method POST\r\n' +
#       'Then status 400\r',
#     examples: ''
#   }
# }
# 

# ********RoostGPT********
Feature: Account Consent Setup

Background:
    * def urlBase = karate.properties['url.base'] || karate.get('urlBase', 'http://localhost:4010')
    * url urlBase
    * def authToken = karate.properties['AUTH_TOKEN']

Scenario: Create account consent with missing required headers
    Given path '/aisp/account-consents'
    And header Authorization = 'Bearer ' + authToken
    And header Content-Type = 'application/json'
    And request
        """
        {
            "data": {
                "permissions": [
                    "ReadAccountBalance"
                ]
            }
        }
        """
    When method post
    Then status 400
    And match response == { id: '#string', errors: '#array' }
    And match response.errors[0] == { code: '#string', causes: '#string', extendedDetails: '#object' }
    And match header 'Content-Type' == 'application/json'
    And match header 'x-fapi-interaction-id' == '#string'
