import helpClassesRozetka.Product;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages_for_Rozetka.*;

import java.util.concurrent.TimeUnit;

public class BasketTests {
    private WebDriver driver;
    private String initialUrl = "https://rozetka.com.ua/";


    RozetkaHomePage rozetkaHomePage;
    SearchPage searchPage;
    ProductPage productPage;
    CompareListPage compareListPage;
    HeaderFunctionsPage headerFunctionsPage;
    BasketPage basketPage;


    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateToSite() {
        driver.get(initialUrl);
        rozetkaHomePage = new RozetkaHomePage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        compareListPage = new CompareListPage(driver);
        headerFunctionsPage = new HeaderFunctionsPage(driver);
        basketPage = new BasketPage(driver);
    }

    @Test
    public void test() {
        rozetkaHomePage.searchForNotebooks();
        Product product1 = searchPage.addToBasket(0);
        Assert.assertEquals(headerFunctionsPage.getBasketCounter(), "1");
        headerFunctionsPage.openBasket();
        Assert.assertTrue(basketPage.containsProduct(product1));
        product1 = basketPage.increaseQuantity(product1);
        Assert.assertTrue(basketPage.containsProduct(product1));
        basketPage.closeBasket();
        Assert.assertEquals(headerFunctionsPage.getBasketCounter(), "2");
        driver.get(initialUrl);
        rozetkaHomePage.searchForPhones();
        Product product2 = searchPage.addToBasket(0);
        Assert.assertEquals(headerFunctionsPage.getBasketCounter(), "3");
        headerFunctionsPage.openBasket();
        Assert.assertTrue(basketPage.containsProduct(product2));
        Assert.assertEquals(basketPage.getNumberOfItems(), 2);
    }
}
