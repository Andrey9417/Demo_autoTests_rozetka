package pages_for_Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private static int count = 0;
    WebDriver webDriver;
    WebDriverWait wait;

    By compareListIcon = By.xpath("//button[@aria-label='Списки сравнения']");
    By linkOnModalWindow = By.cssSelector("div.modal__holder a");
    By compareListCounter = By.cssSelector("span.counter");
    By compareButton = By.cssSelector("button.compare-button");
    By priceOfProduct = By.cssSelector("p.product-prices__big");
    By nameOfProduct = By.className("product__title");

    public ProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
    }

    public void moveBack(){
        webDriver.navigate().back();
    }

    public void moveToCompareList(){
        webDriver.findElement(compareListIcon).click();
        wait.until(ExpectedConditions.elementToBeClickable(linkOnModalWindow)).click();
    }

    public String getPrice() {
        return webDriver.findElement(priceOfProduct).getText().replaceAll("[ ₴]", "");
    }

    public String getName() {
        return webDriver.findElement(nameOfProduct).getText();
    }

    private void addToEmptyCompareList() throws Exception {
        wait.until(ExpectedConditions.visibilityOfElementLocated(compareButton)).click();
        count++;
        if (!webDriver.findElement(compareListCounter).getText().equals("1")){
            throw new Exception("Product wasn't added to compare list");
        }
    }

    public void addToCompareList() throws Exception {
        if(count ==0){
            addToEmptyCompareList();
            return;
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(compareButton)).click();
        count++;
        if (!webDriver.findElement(compareListCounter).getText().equals(count+"")){
            throw new Exception("Product wasn't added to compare list");
        }
    }
}
