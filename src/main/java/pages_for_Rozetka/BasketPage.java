package pages_for_Rozetka;

import helpClassesRozetka.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasketPage {
    WebDriver webDriver;
    WebDriverWait wait;

    //By compareListIcon = By.xpath("//button[@aria-label='Списки сравнения']");
    By closeButton = By.cssSelector("button.modal__close");
    By cartItem = By.cssSelector("li.cart-list__item");
    By priceOfProduct = By.cssSelector("p.cart-product__price");
    By nameOfProduct = By.cssSelector("a.cart-product__title");
    By plusButton = By.cssSelector("button.cart-counter__button~button");

    public BasketPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
    }

    public void closeBasket(){
        webDriver.findElement(closeButton).click();

    }

    public int getNumberOfItems(){
        List<WebElement> listOfElements = wait.until(ExpectedConditions.visibilityOfAllElements(webDriver.findElements(cartItem)));
        return listOfElements.size();
    }

    public Product increaseQuantity(Product p) {
        List<WebElement> listOfElements = wait.until(ExpectedConditions.visibilityOfAllElements(webDriver.findElements(cartItem)));
        for( WebElement elem : listOfElements){
            if(elem.findElement(nameOfProduct).getText().equals(p.getName())) {
                elem.findElement(plusButton).click();
                p.setQuantity(p.getQuantity()+1);
                HeaderFunctionsPage.productsInBasketCount++;
                waitUntilPriceUpdated(p, listOfElements.indexOf(elem));
                break;
            }
        }

        return p;
    }
    private void waitUntilPriceUpdated(Product p, int i) {
        String text=p.getPrice()*p.getQuantity()+"";
        if(p.getPrice()*p.getQuantity()<1000){
            text +=" ₴";
        } else {
            text = text.substring(0, text.length() - 3) + " " + text.substring(text.length() - 3) + " ₴";
        }
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//p[@class='cart-product__price'])["+(i+1)+"]"), text));
    }

    public boolean containsProduct(Product p){
        boolean check = false;
        List<WebElement> listOfElements = wait.until(ExpectedConditions.visibilityOfAllElements(webDriver.findElements(cartItem)));
        for( WebElement elem : listOfElements){
            if(elem.findElement(nameOfProduct).getText().equals(p.getName()) &&
                    elem.findElement(priceOfProduct).getText().replaceAll("[ ₴]", "").equals(p.getPrice()*p.getQuantity() +"")) {
                check = true;
                break;
            }
        }
        return check;
    }
}
