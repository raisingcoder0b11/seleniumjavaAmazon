@AmazonProductSearch
Feature: Amazon Product Search and Display Tech Specification

  Scenario: Search for a laptop on Amazon and print its technical specifications
    Given User is on the Amazon.in website
    When User types "laptop" in the search bar
    And User selects first two filters under Brands
    And User selects the three-star rating filter
    And User selects the first search result on the page
    And User scrolls to the bottom of the result page
    Then User views the technical specifications
