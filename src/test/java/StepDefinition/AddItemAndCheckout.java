package StepDefinition;

import BaseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddItemAndCheckout extends BaseClass {
    @Given("Go to website and navigate to Computer section")
    public void go_to_website_and_navigate_to_computer_section() {
       setup();
    }
    @When("click on Notebook and Filter the product to get result")
    public void click_on_notebook_and_filter_the_product_to_get_result() {
        pageFactory.getaddItem().navigateToComputerSection();
        pageFactory.getaddItem().onNotebookAddFilter();
    }
    @When("Add item to cart")
    public void add_item_to_cart() {
        pageFactory.getaddItem().AddToCart();
    }
    @When("Go to shopping cart and verify item")
    public void go_to_shopping_cart_and_verify_item() {
        System.out.println("o");
    }
    @When("Checkout and register the user")
    public void checkout_and_register_the_user() {
        System.out.println("o");
    }
    @When("Navigate to shopping cart to increase quality and fill details to buy item")
    public void navigate_to_shopping_cart_to_increase_quality_and_fill_details_to_buy_item() {
        System.out.println("o");
    }
    @Then("verify user item")
    public void verify_user_item() {
        System.out.println("o");
    }
}
