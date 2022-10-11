package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class AbleToAddItem {
    WebDriver driver;
    WebDriverWait wait;
    String filter  = "//select[@name='%s']";
    String option = "//option[@value='%s']";
    By Computer = By.xpath("(//a[@href='/computers'])[1]");
    By Notebook = By.xpath("(//a[contains(text(),'Notebooks')])[3]");
    By ProductName = By.xpath("//h2[@class='product-title']");
    By pro = By.xpath("//div[@class='product-selectors']");
    By AddToCart = By.xpath("(//div[@class='buttons']//button[contains(text(),'Add to cart')])[1]");
    public  AbleToAddItem(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public void navigateToComputerSection(){
        driver.findElement(Computer).click();
    }

    public void onNotebookAddFilter(){
        driver.findElement(Notebook).click();
        driver.findElement(By.xpath(String.format(filter,"products-orderby"))).click();
        driver.findElement(By.xpath(String.format(option,"10"))).click();
        driver.findElement(By.xpath(String.format(filter,"products-pagesize"))).click();
        driver.findElement(By.xpath(String.format(option,"9"))).click();
        List<WebElement> myElements = driver.findElements(ProductName);
        for(WebElement e : myElements) {
            System.out.println(e.getText());
        }
    }

    public void AddToCart(){
        driver.findElement(pro).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AddToCart));
        driver.findElement(AddToCart).click();
    }
}
