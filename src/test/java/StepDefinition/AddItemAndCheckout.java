package StepDefinition;

import BaseClass.BaseClass;
import Enums.PageEnum;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class AddItemAndCheckout extends BaseClass {
    @Given("Go to website and navigate to Computer section")
    public void go_to_website_and_navigate_to_computer_section() {
       setup();
    }
    @When("click on Notebook and Filter the product to get result")
    public void click_on_notebook_and_filter_the_product_to_get_result() {
        pageFactory.getaddItem().navigateToComputerSection();
        pageFactory.getaddItem().onNotebookAddFilter(PageEnum.NOTEBOOK);
    }
    @When("Add item to cart")
    public void add_item_to_cart() throws InterruptedException {
        pageFactory.getaddItem().AddToCart();
    }
    @When("Go to shopping cart and verify item")
    public void go_to_shopping_cart_and_verify_item() {
        pageFactory.getaddItem().ShoppingCartPage();

    }
    @When("Checkout and register the user")
    public void checkout_and_register_the_user() {
        pageFactory.getaddItem().CheckoutPage();;
    }
    @When("Navigate to shopping cart to increase quality and fill details to buy item")
    public void navigate_to_shopping_cart_to_increase_quality_and_fill_details_to_buy_item() throws IOException {
        pageFactory.getaddItem().FillShoppingDetails();
    }
    @Then("verify user item")
    public void verify_user_item() {
        pageFactory.getaddItem().verifyProduct();
        browserClose();
    }
}
