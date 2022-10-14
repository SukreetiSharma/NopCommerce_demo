package Enums;

public enum NopCommerceEnums {
    Filter_ProductType("products-orderby"),
    Option_ProductType("10"),
    Filter_ProductPrice("products-pagesize"),
    Option_ProductPrice("9"),
    First_name("FirstName"),
    Last_name("LastName"),
    Email("Email"),
    Password("Password"),
    ConfirmPassword("ConfirmPassword"),
    Company("BillingNewAddress.Company"),
    City("BillingNewAddress.City"),
    Address("BillingNewAddress.Address1"),
    ZipCode("BillingNewAddress.ZipPostalCode"),
    PhoneNumber("BillingNewAddress.PhoneNumber"),
    ContAddress("new-address-next-step-button"),
    ContShipping("shipping-method-next-step-button"),
    ContPayment("payment-method-next-step-button"),
    ContPaymentNext("payment-info-next-step-button"),
    ConfirmOrder("confirm-order-next-step-button");

    private String name;
    private NopCommerceEnums(String name) {
        this.name = name;
    }

    public String getResourcesName() {
        return name;
    }

}
