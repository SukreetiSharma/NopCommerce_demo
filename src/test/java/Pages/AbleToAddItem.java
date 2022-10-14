package Pages;

import Enums.NopCommerceEnums;
import Enums.PageEnum;
import net.jodah.failsafe.internal.util.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class AbleToAddItem {
    WebDriver driver;
    WebDriverWait wait;
    public static Properties prop;
    String s = RandomStringUtils.randomAlphabetic(8);
    String filter = "//select[@name='%s']";
    String option = "//option[@value='%s']";
    String Input = "//input[@name='%s']";
    String Cont = "//button[@class='button-1 %s']";
    By Computer = By.xpath("(//a[@href='/computers'])[1]");
    By ProductName = By.xpath("//h2[@class='product-title']");
    By Productprice = By.xpath("//span[@class='price actual-price']");
    By pro = By.xpath("//div[@class='product-selectors']");
    By AddToCart = By.xpath("(//div[@class='buttons'])[1]");
    By verifyMessage = By.xpath("//p[@class='content'][contains(text(),'The product has been added to your ')]");
    By ShoppingCart = By.xpath("//span[@class='cart-label']");
    By verify = By.xpath("//a[@class='product-name']");
    By Checkbox = By.xpath("//input[@name='termsofservice']");
    By Checkout = By.xpath("//button[@name='checkout']");
    By Register = By.xpath("//button[contains(text(),'Register')]");
    By Continue = By.xpath("//a[contains(text(),'Continue')]");
    By Quantity = By.xpath("//input[@class='qty-input']");
    By countryDropDown = By.xpath("//select[@name='BillingNewAddress.CountryId']");
    By country = By.xpath("//option[contains(text(),'Austria')]");
    By verifyProduct = By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]");
    By Close = By.xpath("//span[@class='close']");

    private String NOTEBOOK = "(//a[contains(text(),'%s')])[%d]";
    public AbleToAddItem(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void navigateToComputerSection() {
        driver.findElement(Computer).click();
    }
//PageEnum.NOTEBOOK
    public void onNotebookAddFilter(PageEnum value) {
        driver.findElement(By.xpath(String.format(NOTEBOOK, value.getName(), value.getIndex()))).click();
        driver.findElement(By.xpath(String.format(filter, NopCommerceEnums.Filter_ProductType.getResourcesName()))).click();
        driver.findElement(By.xpath(String.format(option, NopCommerceEnums.Option_ProductType.getResourcesName()))).click();
        driver.findElement(By.xpath(String.format(filter, NopCommerceEnums.Filter_ProductPrice.getResourcesName()))).click();
        driver.findElement(By.xpath(String.format(option, NopCommerceEnums.Option_ProductPrice.getResourcesName()))).click();
        List<WebElement> myElements = driver.findElements(ProductName);
        System.out.println("Product Name :");
        for (WebElement e : myElements) {
            System.out.println(e.getText());
        }
        List<WebElement> Elements = driver.findElements(Productprice);
        System.out.println("Product Price :");
        for (WebElement e : Elements) {
            System.out.println(e.getText());
        }
    }

    public void AddToCart() throws InterruptedException {
        driver.findElement(pro).click();
        Thread.sleep(2000);
        try {
            driver.findElement(AddToCart).click();
        } catch (StaleElementReferenceException elementHasDisappeared) {
            driver.findElement(AddToCart).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(verifyMessage));
        String actual = driver.findElement(verifyMessage).getText();
        System.out.println(actual);
        Assert.isTrue(actual.equals("The product has been added to your shopping cart"), "Expected result does not match with actual result");
    }
    public void ShoppingCartPage() {
        driver.findElement(Close).click();
        driver.findElement(ShoppingCart).click();
        String actual = driver.findElement(verify).getText();
        Assert.isTrue(actual.equals("HP Spectre XT Pro UltraBook"), "Expected result does not match with actual result");
    }

    public void CheckoutPage() {
        driver.findElement(Checkbox).click();
        driver.findElement(Checkout).click();
        driver.findElement(Register).click();
        try {
            prop = new Properties();
            FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//TestData//Data.properties");
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath(String.format(Input, NopCommerceEnums.First_name.getResourcesName()))).sendKeys(prop.getProperty("FName"));
        driver.findElement(By.xpath(String.format(Input, NopCommerceEnums.Last_name.getResourcesName()))).sendKeys(prop.getProperty("LName"));
        driver.findElement(By.xpath(String.format(Input, NopCommerceEnums.Email.getResourcesName()))).sendKeys(s + "@gmail.com");
        driver.findElement(By.xpath(String.format(Input, NopCommerceEnums.Password.getResourcesName()))).sendKeys(prop.getProperty("Password"));
        driver.findElement(By.xpath(String.format(Input, NopCommerceEnums.ConfirmPassword.getResourcesName()))).sendKeys(prop.getProperty("Password"));
        driver.findElement(Register).click();
    }

    public void FillShoppingDetails() throws IOException {
        driver.findElement(Continue).click();
        driver.findElement(Quantity).clear();
        driver.findElement(Quantity).sendKeys("4");
        driver.findElement(Checkbox).click();
        driver.findElement(Checkout).click();
        String path = System.getProperty("user.dir") + "//src//test//java//TestData//Shopping_Details.xlsx";
        FileInputStream prop1 = null;
        try {
            prop1 = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook(prop1);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String Company = sheet.getRow(1).getCell(0).getStringCellValue();
        String City = sheet.getRow(1).getCell(1).getStringCellValue();
        String Address = sheet.getRow(1).getCell(2).getStringCellValue();
        String PostalCode = sheet.getRow(1).getCell(3).getStringCellValue();
        String PhoneNumber = sheet.getRow(1).getCell(4).getStringCellValue();
        driver.findElement(By.xpath(String.format(Input, NopCommerceEnums.Company.getResourcesName()))).sendKeys(Company);
        driver.findElement(countryDropDown).click();
        driver.findElement(country).click();
        driver.findElement(By.xpath(String.format(Input, NopCommerceEnums.City.getResourcesName()))).sendKeys(City);
        driver.findElement(By.xpath(String.format(Input, NopCommerceEnums.Address.getResourcesName()))).sendKeys(Address);
        driver.findElement(By.xpath(String.format(Input, NopCommerceEnums.ZipCode.getResourcesName()))).sendKeys(PostalCode);
        driver.findElement(By.xpath(String.format(Input, NopCommerceEnums.PhoneNumber.getResourcesName()))).sendKeys(PhoneNumber);
        driver.findElement(By.xpath(String.format(Cont, NopCommerceEnums.ContAddress.getResourcesName()))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(Cont, NopCommerceEnums.ContShipping.getResourcesName()))));
        driver.findElement(By.xpath(String.format(Cont, NopCommerceEnums.ContShipping.getResourcesName()))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(Cont, NopCommerceEnums.ContPayment.getResourcesName()))));
        driver.findElement(By.xpath(String.format(Cont, NopCommerceEnums.ContPayment.getResourcesName()))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(Cont, NopCommerceEnums.ContPaymentNext.getResourcesName()))));
        driver.findElement(By.xpath(String.format(Cont, NopCommerceEnums.ContPaymentNext.getResourcesName()))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(Cont, NopCommerceEnums.ConfirmOrder.getResourcesName()))));
        driver.findElement(By.xpath(String.format(Cont, NopCommerceEnums.ConfirmOrder.getResourcesName()))).click();
    }

    public void verifyProduct(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(verifyProduct));
        String actual = driver.findElement(verifyProduct).getText();
        Assert.isTrue(actual.equals("Your order has been successfully processed!"), "Expected result does not match with actual result");
    }
}
