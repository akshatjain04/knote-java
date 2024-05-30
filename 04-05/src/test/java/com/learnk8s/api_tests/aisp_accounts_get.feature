# ********RoostGPT********

# Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview
# 
# Feature file generated for /aisp/accounts_get for http method type GET 
# RoostTestHash=b87a8a3304
# 
# 

# ********RoostGPT********
Feature: Account status management

  Background:
    * def urlBase = karate.properties['url.base'] || 'http://localhost:4010'
    * def authToken = karate.properties['AUTH_TOKEN']
    * url urlBase
    * header Authorization = 'Bearer ' + authToken
    * header Accept-Language = 'en-HK'
    * header Content-Type = 'application/json'
    * header x-fapi-auth-date = 'Sun, 10 Sep 2017 19:43:31 UTC'
    * header x-fapi-customer-ip-address = '169.254.169.254'
    * header x-fapi-interaction-id = 'unique-interaction-id'

  Scenario: Retrieve all accounts with valid authorization
    Given path '/aisp/accounts'
    When method get
    Then status 200
    And match response contains { data: '#object', links: '#object' }
    And match each response.data.account contains { accountId: '#string', accountNumber: '#string' }

  Scenario Outline: Retrieve accounts with various Accept-Language headers
    Given path '/aisp/accounts'
    And header Accept-Language = '<language>'
    When method get
    Then status 200
    And match response contains { data: '#object', links: '#object' }

    Examples:
      | read('aisp_accounts_get.csv') |

  Scenario: Retrieve accounts with missing Authorization header should return 401
    Given path '/aisp/accounts'
    And removeHeader Authorization
    When method get
    Then status 401

  Scenario: Retrieve accounts with invalid Authorization header should return 403
    Given path '/aisp/accounts'
    And header Authorization = 'InvalidToken'
    When method get
    Then status 403

  Scenario: Attempting to post to accounts endpoint should return 405 Method Not Allowed
    Given path '/aisp/accounts'
    When method post
    Then status 405

  Scenario: Retrieve accounts with unsupported Accept-Language header should return 406 Not Acceptable
    Given path '/aisp/accounts'
    And header Accept-Language = 'unsupported-language'
    When method get
    Then status 406

  Scenario: Retrieve accounts when service is unavailable should return 503
    Given path '/aisp/accounts'
    And header x-fapi-interaction-id = 'simulate-unavailable-service'
    When method get
    Then status 503

  Scenario: Retrieve accounts when gateway timeout occurs should return 504
    Given path '/aisp/accounts'
    And header x-fapi-interaction-id = 'simulate-gateway-timeout'
    When method get
    Then status 504
