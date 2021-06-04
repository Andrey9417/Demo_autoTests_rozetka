package pages_for_Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage {
    WebDriver webDriver;
    WebDriverWait wait;

    By compareListIcon = By.xpath("//button[@aria-label='Списки сравнения']");
    By closeButton = By.cssSelector("button.modal__close");
    By compareButton = By.cssSelector("button.compare-button");
    By priceOfProduct = By.cssSelector("p.product-prices__big");
    By nameOfProduct = By.className("product__title");

    public BasketPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
    }

    public void closeBasket(){
        webDriver.findElement(closeButton).click();

    }
}
