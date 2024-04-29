#Author: ervivekgoswami@gmail.com
@Price
Feature: To verify Price API returns valid currency data
  As a user, I want to hit the currency API with valid currency code
  So that I can see updated currency rates for any country
  
  List of scenarios:
  1. API call is successful and returns valid price.
  2. Check the status code and status retuned by the API response.
     API could return multiple statuses like SUCCESS, FAILURE etc. Make sure this is catered for.
  3. Fetch the USD price against the AED and make sure the prices are in range on 3.6 – 3.7
  4. Make sure API response time is not less then 3 seconds then current time in second.
     Timestamp is returned in the API response.
  5. Verify that 162 currency pairs are retuned by the API.
  6. Make sure API response matches the Json schema

  Scenario Outline: <Scenario>. Fetch coversion rate for <Country> API runs successfully
    Given user has access to price endpoint "<Country>"
    When user makes a request to get price
    Then user should get the response code <StatusCode>
    And user verifies price API returns status "<Status>"
    And user verifies API response time is not less then 3

    Examples: 
      | Scenario | StatusCode | Status  | Country |
      |        1 |        200 | success | USD     |
      |        2 |        200 | success | AED     |
      |        3 |        200 | success | AUD     |
      |        4 |        200 | success | INR     |
      |        5 |        200 | success | EUR     |
      |        6 |        200 | success | AFN     |
      |        7 |        200 | error   | INU     |

  Scenario Outline: <Scenario>. Fetch the <Currency> price against the <ConvertTo>
    Given user has access to price endpoint "<Currency>"
    When user makes a request to get price
    And user verifies "<Currency>" conversion rate is <ConversionRate> in "<ConvertTo>"

    Examples: 
      | Scenario | Currency | ConvertTo | ConversionRate |
      |        8 | USD      | AED       |            3.7 |
      |        9 | AFN      | AED       |           0.05 |
      |       10 | EUR      | AED       |            3.9 |

  Scenario: 11. Verify that 162 currency pairs are retuned by the API.
    Given user has access to price endpoint "USD"
    When user makes a request to get price
    And user verifies total currency pairs are 162

  Scenario: 12. Verify API response matches the Json schema
    Given user has access to price endpoint "USD"
    When user makes a request to get price
    And user validates API response with JSON schema "PriceListSchema"
