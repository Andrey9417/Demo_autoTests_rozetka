package pages_for_Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    WebDriver webDriver;
    WebDriverWait wait;

    By compareListIcon = By.xpath("//button[@aria-label='Списки сравнения']");
    By linkOnModalWindow = By.cssSelector("div.modal__holder a");
    By compareButton = By.cssSelector("button.compare-button");
    By priceOfProduct = By.cssSelector("p.product-prices__big");
    By nameOfProduct = By.className("product__title");

    public ProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
    }

    public void navigateBack(){
        webDriver.navigate().back();
    }

    public void navigateToCompareList(){
        webDriver.findElement(compareListIcon).click();
        wait.until(ExpectedConditions.elementToBeClickable(linkOnModalWindow)).click();
    }

    public String getPrice() {
        return webDriver.findElement(priceOfProduct).getText().replaceAll("[ ₴]", "");
    }

    public String getName() {
        return webDriver.findElement(nameOfProduct).getText();
    }

    public void addToCompareList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(compareButton)).click();
    }
}
