package pages_for_Rozetka;

import helpClassesRozetka.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchPage {

    WebDriver webDriver;
    WebDriverWait wait;
    List<WebElement> listOfElements;

    By productOnSearchPage = By.xpath("//div[@class='goods-tile__inner']");
    By priceOfProduct = By.xpath(".//span[@class='goods-tile__price-value']");
    By linkToProductPage = By.xpath(".//a");
    By banner = By.cssSelector("a#rz-banner");
    By banner_close_button = By.cssSelector("span.exponea-close-cross");
    By addToBasketButton = By.cssSelector("button.goods-tile__buy-button");
    By productName = By.cssSelector("span.goods-tile__title");
    By productPrice = By.cssSelector("span.goods-tile__price-value");

    public SearchPage (WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
    }

    public void findProductWithPriceLessThan(String maxPrice) throws Exception {
        listOfElements = webDriver.findElements(productOnSearchPage);
        boolean found = false;
        for (WebElement webElem : listOfElements) {
            String price = webElem.findElement(priceOfProduct).getText().replace(" ", "");
            if (Integer.parseInt(price) < Integer.parseInt(maxPrice)) {
                moveToProductsPage(webElem);
//                webElem.findElement(linkToProductPage).click();
                found = true;
                break;
            }
        }
        if(!found){
            throw new Exception("product with price less than "+maxPrice+" wasn't found");
        }
    }
    private void moveToProductsPage(WebElement product) {
        try {
            product.findElement(linkToProductPage).click();
        } catch (ElementClickInterceptedException e) {
            if(webDriver.findElement(banner).isDisplayed()){
                webDriver.findElement(banner_close_button).click();
                product.findElement(linkToProductPage).click();
            }
        }
    }

    public void chooseProductCategory(String category){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li>a[href*="+category+"]"))).click();
    }

    public Product addToBasket(int number) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productOnSearchPage));
        listOfElements = webDriver.findElements(addToBasketButton);
        listOfElements.get(number).click();
        HeaderFunctionsPage.productsInBasketCount++;
        return new Product(webDriver.findElement(productName).getText(),
                            Integer.parseInt(webDriver.findElement(productPrice).getText().replace(" ", "")));
    }
    public void clickToTheFirstPhone(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(productOnSearchPage)).click();
    }


}
