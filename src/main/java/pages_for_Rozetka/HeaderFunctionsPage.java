package pages_for_Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderFunctionsPage {
    static int productsInBasketCount=0;
    static int productsInCompareListCount=0;

    WebDriver webDriver;
    WebDriverWait wait;

    By compareListCounter = By.cssSelector("rz-comparison span.counter");
    By basketCounter = By.cssSelector("rz-cart span.counter");
    By basketButton = By.cssSelector("header button[opencart]");


    public HeaderFunctionsPage(WebDriver webDriver){
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
    }

    public String getCompareListCounter(){
        wait.until(ExpectedConditions.textToBePresentInElementLocated(compareListCounter, Integer.toString(productsInCompareListCount)));
        return webDriver.findElement(compareListCounter).getText();
    }

    public String getBasketCounter() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(basketCounter, Integer.toString(productsInBasketCount)));
        return webDriver.findElement(basketCounter).getText();
    }

    public void openBasket(){
        webDriver.findElement(basketButton).click();
    }
}
