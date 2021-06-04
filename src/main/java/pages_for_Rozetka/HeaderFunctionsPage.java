package pages_for_Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderFunctionsPage {

    WebDriver webDriver;

    By compareListCounter = By.cssSelector("rz-comparison span.counter");
    By basketCounter = By.cssSelector("rz-cart span.counter");
    By basketButton = By.cssSelector("header button[opencart]");


    public HeaderFunctionsPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public String getCompareListCounter() throws InterruptedException {
        Thread.sleep(1000);
        return webDriver.findElement(compareListCounter).getText();
    }

    public String getBasketCounter() throws InterruptedException {
        Thread.sleep(1000);
        return webDriver.findElement(basketCounter).getText();
    }

    public void openBasket(){
        webDriver.findElement(basketButton).click();
    }
}
