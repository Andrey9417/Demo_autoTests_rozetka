package pages_for_Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RozetkaHomePage {
    WebDriver webDriver;
    WebDriverWait wait;
    Actions actions;

    By menuElementComputers = By.xpath("//sidebar-fat-menu//a[contains (@href, 'computers-notebooks')]");
    By submenuElementMonitors = By.xpath("//div[@class='menu__main-cats']//a[contains (@href, 'monitors')]");
    By searchField = By.name("search");

    public RozetkaHomePage (WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
        actions = new Actions(webDriver);
    }

    public void moveToMonitorSearchPage() {
        WebElement linkComputersAndNotebooks = webDriver.findElement(menuElementComputers);
        actions.moveToElement(linkComputersAndNotebooks).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(submenuElementMonitors)).click();
    }

    public void searchByName(String name){
        webDriver.findElement(searchField).sendKeys(name + Keys.ENTER);
    }
}
