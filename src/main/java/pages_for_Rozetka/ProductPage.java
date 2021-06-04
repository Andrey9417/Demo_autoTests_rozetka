package pages_for_Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    WebDriver webDriver;
    WebDriverWait wait;
    String image1;

    By compareListIcon = By.xpath("//button[@aria-label='Списки сравнения']");
    By linkOnModalWindow = By.cssSelector("div.modal__holder a");
    By compareButton = By.cssSelector("button.compare-button");
    By priceOfProduct = By.cssSelector("p.product-prices__big");
    By nameOfProduct = By.className("product__title");

    By colorOfProduct = By.className("goods-tile__colors");
    By colorWrapperFirst = By.cssSelector(".var-options__list > li:nth-child(1)");
    By imagePhone = By.cssSelector(".scrollbar__inner > li:nth-child(1) > a > img");

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
    public void checkColorTile(){

    }
    public void clicktoTheFirstChildOfColorWrapper(){
        wait.until(ExpectedConditions.elementToBeClickable(colorWrapperFirst)).click();
    }
    public void saveLinkImg(){
        WebElement image = webDriver.findElement(imagePhone);
        image1 = image.getText();
    }
    public void checkImg(){
        WebElement image = webDriver.findElement(imagePhone);
        String image2 = image.getText();
        image1.equals(image2);
    }
}
