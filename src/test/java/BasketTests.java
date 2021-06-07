import helpClassesRozetka.Product;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages_for_Rozetka.*;

import java.util.concurrent.TimeUnit;

public class BasketTests {
    private WebDriver driver;
    private String initialUrl = "https://rozetka.com.ua/";


    RozetkaHomePage rozetkaHomePage;
    SearchPage searchPage;
    HeaderFunctionsPage headerFunctionsPage;
    BasketPage basketPage;


    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void killDriver() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateToSite(){
        driver.get(initialUrl);
        rozetkaHomePage = new RozetkaHomePage(driver);
        searchPage = new SearchPage(driver);
        headerFunctionsPage = new HeaderFunctionsPage(driver);
        basketPage = new BasketPage(driver);
    }

    @AfterMethod
    public void cleanCookies(){
        driver.manage().deleteAllCookies();
        headerFunctionsPage.refreshStatic();
    }

    @Test
    public void testAddProductsToBasket() {
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

    @Test
    public void testRemoveProductsFromBasket() {
        rozetkaHomePage.searchForNotebooks();
        Product product1 = searchPage.addToBasket(0);
        Product product2 = searchPage.addToBasket(1);
        Product product3 = searchPage.addToBasket(2);
        Assert.assertEquals(headerFunctionsPage.getBasketCounter(), "3");
        headerFunctionsPage.openBasket();
        Assert.assertTrue(basketPage.containsProduct(product1));
        Assert.assertTrue(basketPage.containsProduct(product2));
        Assert.assertTrue(basketPage.containsProduct(product3));
        basketPage.deleteProduct(product1);
        basketPage.closeBasket();
        Assert.assertEquals(headerFunctionsPage.getBasketCounter(), "2");
        headerFunctionsPage.openBasket();
        Assert.assertTrue(basketPage.containsProduct(product2));
        Assert.assertTrue(basketPage.containsProduct(product3));
        Assert.assertEquals(basketPage.getTotalPrice(), product2.getOrderedPrice()+product3.getOrderedPrice());
        basketPage.deleteProduct(product2);
        basketPage.deleteProduct(product3);
        Assert.assertFalse(headerFunctionsPage.isBasketCounterDisplayed());
        Assert.assertTrue(basketPage.isBasketEmpty());
    }

//    @Test
//    public void testAdditionalServices() {
//        rozetkaHomePage.searchForNotebooks();
//
//    }
}
