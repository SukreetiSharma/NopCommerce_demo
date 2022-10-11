Feature: Test to validate user is able to add any item to card and checkout the item

  Scenario: User is able to add product and checkout
    Given Go to website and navigate to Computer section
    When click on Notebook and Filter the product to get result
    And Add item to cart
    And Go to shopping cart and verify item
    And Checkout and register the user
    And Navigate to shopping cart to increase quality and fill details to buy item
    Then verify user item
