# ********RoostGPT********

# Test generated by RoostGPT for test MiniProjects using AI Type Open AI and AI Model gpt-4-1106-preview
# 
# Feature file generated for /aisp/accounts/{accountId}/balances_get for http method type GET 
# RoostTestHash=7af08f6287
# 
# 

# ********RoostGPT********
Feature: Account Balances API Endpoints

  Background:
    * def urlBase = karate.properties['url.base'] || 'http://localhost:4010'

  Scenario Outline: Get account balances with valid account ID and headers
    Given url urlBase + '/aisp/accounts/' + <accountId> + '/balances'
    And header Authorization = 'Bearer ' + karate.properties['AUTH_TOKEN']
    And header x-fapi-auth-date = <x-fapi-auth-date>
    And header x-fapi-customer-ip-address = <x-fapi-customer-ip-address>
    And header x-fapi-interaction-id = <x-fapi-interaction-id>
    And header Accept-Language = <Accept-Language>
    When method get
    Then status 200
    And match response contains { data: '#object' }
    And match response.data.account[*].accountId is '#string'
    And match response.data.account[*].balance[*].type is '#string'
    And match response.data.account[*].balance[*].creditDebitIndicator is '#string'
    And match response.data.account[*].balance[*].amount is '#regex[\\d{1,13}(\\.\\d{1,5})?]'
    And match response.data.account[*].balance[*].currency is '#regex[^[A-Z]{3,3}$]'
    And match response.data.account[*].balance[*].datetime is '#string'
    And match response.links.self is '#string'

    Examples:
      | read('aisp_accounts_accountId_balances_get.csv') |

  Scenario: Get account balances with missing Authorization header
    Given url urlBase + '/aisp/accounts/ThR-RpLMV5lZzDu8vrfEFg/balances'
    And header x-fapi-auth-date = 'Sun, 10 Sep 2017 19:43:31 UTC'
    And header x-fapi-customer-ip-address = '169.254.169.254'
    And header x-fapi-interaction-id = 'unique-interaction-id'
    And header Accept-Language = 'en-HK'
    When method get
    Then status 401

  Scenario: Get account balances with invalid account ID
    Given url urlBase + '/aisp/accounts/invalid-account-id/balances'
    And header Authorization = 'Bearer ' + karate.properties['AUTH_TOKEN']
    And header x-fapi-auth-date = 'Sun, 10 Sep 2017 19:43:31 UTC'
    And header x-fapi-customer-ip-address = '169.254.169.254'
    And header x-fapi-interaction-id = 'unique-interaction-id'
    And header Accept-Language = 'en-HK'
    When method get
    Then status 400

  Scenario: Get account balances with unsupported Accept-Language header
    Given url urlBase + '/aisp/accounts/ThR-RpLMV5lZzDu8vrfEFg/balances'
    And header Authorization = 'Bearer ' + karate.properties['AUTH_TOKEN']
    And header x-fapi-auth-date = 'Sun, 10 Sep 2017 19:43:31 UTC'
    And header x-fapi-customer-ip-address = '169.254.169.254'
    And header x-fapi-interaction-id = 'unique-interaction-id'
    And header Accept-Language = 'unsupported-language'
    When method get
    Then status 406
