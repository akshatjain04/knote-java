# ********RoostGPT********

# Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview
# 
# Feature file generated for /aisp/account-consents/{consentId}_get for http method type GET 
# RoostTestHash=6c81494c1a
# 
# 

# ********RoostGPT********
Feature: Test account availability endpoint

  Background:
    * def urlBase = karate.properties['url.base'] || 'http://localhost:4010'
    * url urlBase
    * def authToken = karate.properties['AUTH_TOKEN']

  Scenario Outline: Verify account availability with valid request body and headers
    Given path '/aisp/accounts/availability'
    And header Authorization = 'Bearer ' + authToken
    And header Content-Type = <ContentType>
    And header x-fapi-auth-date = <AuthDate>
    And header x-fapi-customer-ip-address = <CustomerIPAddress>
    And header x-fapi-interaction-id = <InteractionId>
    And header Accept-Language = <AcceptLanguage>
    And request <RequestBody>
    When method POST
    Then status 200
    And match response contains { data: '#array' }
    And match each response.data contains { paramName: '#string', status: '#string' }

    Examples:
      | ContentType        | AuthDate                        | CustomerIPAddress | InteractionId   | AcceptLanguage | RequestBody                                                            |
      | 'application/json' | 'Sun, 10 Sep 2017 19:43:31 UTC' | '169.254.169.254' | 'unique-id-123' | 'en-HK'        | { "data": [{ "paramName": "accountType", "paramValue": "Personal" }] } |

  Scenario Outline: Verify account availability with invalid Content-Type header
    Given path '/aisp/accounts/availability'
    And header Authorization = 'Bearer ' + authToken
    And header Content-Type = <ContentType>
    And header x-fapi-auth-date = 'Sun, 10 Sep 2017 19:43:31 UTC'
    And header x-fapi-customer-ip-address = '169.254.169.254'
    And header x-fapi-interaction-id = 'unique-id-123'
    And header Accept-Language = 'en-HK'
    And request { "data": [{ "paramName": "accountType", "paramValue": "Personal" }] }
    When method POST
    Then status 415

    Examples:
      | ContentType       |
      | 'text/plain'      |
      | 'application/xml' |

  Scenario Outline: Verify account availability with missing required headers
    Given path '/aisp/accounts/availability'
    And header Authorization = 'Bearer ' + authToken
    And header Content-Type = 'application/json'
    And header x-fapi-auth-date = <AuthDate>
    And header x-fapi-customer-ip-address = <CustomerIPAddress>
    And request { "data": [{ "paramName": "accountType", "paramValue": "Personal" }] }
    When method POST
    Then status 400
    And match response.errors contains { code: '#string', causes: '#string' }

    Examples:
      | AuthDate                        | CustomerIPAddress |
      | 'Sun, 10 Sep 2017 19:43:31 UTC' | '169.254.169.254' |
      | null                            | '169.254.169.254' |
      | 'Sun, 10 Sep 2017 19:43:31 UTC' | null    
