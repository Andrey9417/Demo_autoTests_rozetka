package pages_for_Rozetka;

import helpClassesRozetka.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasketPage {
    WebDriver webDriver;
    WebDriverWait wait;
    Actions actions;

    By closeButton = By.cssSelector("button.modal__close");
    By cartItem = By.cssSelector("li.cart-list__item");
    By priceOfProduct = By.cssSelector("p.cart-product__price");
    By nameOfProduct = By.cssSelector("a.cart-product__title");
    By plusButton = By.cssSelector("button.cart-counter__button~button");
    By totalPrice = By.cssSelector("div.cart-receipt__sum-price");
    By productMenu = By.cssSelector("button.context-menu__toggle");
    By optionDelete = By.cssSelector("button.context-menu-actions__button");
    By emptyCartDummy = By.cssSelector("img.cart-dummy__illustration");

    public BasketPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
        actions = new Actions(webDriver);
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
                waitUntilItemPriceUpdated(p, listOfElements.indexOf(elem));
                break;
            }
        }

        return p;
    }
    private void waitUntilItemPriceUpdated(Product p, int i) {
        String text=p.getPrice()*p.getQuantity()+"";
        if(p.getPrice()*p.getQuantity()<1000){
            text +=" ₴";
        } else {
            text = text.substring(0, text.length() - 3) + " " + text.substring(text.length() - 3) + " ₴";
        }
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("(//p[@class='cart-product__price'])["+(i+1)+"]"), text));
    }

    private void waitUntilTotalPriceUpdated(int difference, int total) {
        String text=total + difference+" ₴";
        if(!text.equals("0 ₴")){
            wait.until(ExpectedConditions.textToBePresentInElementLocated(totalPrice, text));
        }
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

    public void deleteProduct(Product p) {
        int sum = getTotalPrice();
        List<WebElement> listOfElements = wait.until(ExpectedConditions.visibilityOfAllElements(webDriver.findElements(cartItem)));
        for( WebElement elem : listOfElements){
            if(elem.findElement(nameOfProduct).getText().equals(p.getName())){
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", elem.findElement(productMenu));
                elem.findElement(productMenu).click();
                webDriver.findElement(optionDelete).click();
                break;
            }
        }
        HeaderFunctionsPage.productsInBasketCount--;
        waitUntilTotalPriceUpdated(-p.getOrderedPrice(), sum);
    }

    public int getTotalPrice() {
        String price = webDriver.findElement(totalPrice).getText();
        price=price.replaceAll("[ ₴]", "");
        return Integer.parseInt(price);
    }

    public boolean isBasketEmpty(){
        return webDriver.findElements(emptyCartDummy).size() == 1;
    }

//    public void addAdditionalServiceByNumber(int number){
//        List<WebElement> listOfElements = wait.until(ExpectedConditions.visibilityOfAllElements(webDriver.findElements(cartItem)));
//        webDriver.findElement(cartItem).is
//    }
}
