package pages_for_Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchWithFiltersPage {
    WebDriver webDriver;
    WebDriverWait wait;

    By minPrice = By.cssSelector("input[formcontrolname=min]");
    By maxPrice = By.cssSelector("input[formcontrolname=max]");
    By readyToDeliver = By.xpath("//label[@for='Готов к отправке']");
    By productPrice = By.cssSelector("span.goods-tile__price-value");
    By productName = By.cssSelector("span.goods-tile__title");
    By availability = By.cssSelector("div.goods-tile__availability");
    By linkToProductPage = By.cssSelector("div.goods-tile__inner>a");

    public SearchWithFiltersPage (WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
    }

    public void addFilterByManufacturer(String company) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for="+company+"]"))).click();
    }

    public void setMinPrice(String price){
        webDriver.findElement(minPrice).sendKeys(Keys.chord(Keys.CONTROL, "a"), price);
    }

    public void setMaxPrice(String price){
        webDriver.findElement(maxPrice).sendKeys(Keys.chord(Keys.CONTROL, "a"), price, Keys.ENTER);
    }

    public void setFilterReadyToDeliver(){
        webDriver.findElement(readyToDeliver).click();
    }

    public boolean checkPriceDiapason(int min, int max){
        wait.until(ExpectedConditions.elementToBeClickable(linkToProductPage));
        List<WebElement> listOfElements = webDriver.findElements(productPrice);
        boolean check = true;
        for(WebElement webElem : listOfElements){
            String priceOfProduct = webElem.getText().replaceAll(" ", "");
            if(Integer.parseInt(priceOfProduct)>max && Integer.parseInt(priceOfProduct)<min) {check =false;}
        }
        return check;
    }

    public boolean checkAvailability(){
        wait.until(ExpectedConditions.elementToBeClickable(linkToProductPage));
        List<WebElement> listOfElements = webDriver.findElements(availability);
        boolean check = true;
        for(WebElement webElem : listOfElements){
            if(!webElem.getText().contains("Готов к отправке")) {check =false;}
        }
        return check;
    }

    public boolean checkProductsManufacturer(String... arr){
        wait.until(ExpectedConditions.elementToBeClickable(linkToProductPage));
        List<WebElement> listOfElements = webDriver.findElements(productName);
        boolean check = false;
        for(WebElement webElem : listOfElements){
            String nameOfProduct = webElem.getText();
            for(String word : arr){
                check=check || nameOfProduct.contains(word);
            }
            if(!check) {break;}

        }
        return check;
    }
}
