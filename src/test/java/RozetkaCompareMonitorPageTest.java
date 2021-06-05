import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages_for_Rozetka.*;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class RozetkaCompareMonitorPageTest {

    private WebDriver driver;
    private String initialUrl = "https://rozetka.com.ua/";

    private String priceOfFirstProduct;
    private String nameOfFirstProduct;
    private String priceOfSecondProduct;
    private String nameOfSecondProduct;

    RozetkaHomePage rozetkaHomePage;
    SearchPage searchPage;
    ProductPage productPage;
    CompareListPage compareListPage;
    HeaderFunctionsPage headerFunctionsPage;


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
    public void navigateToSite() throws AWTException {
        driver.get(initialUrl);
        rozetkaHomePage = new RozetkaHomePage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
        compareListPage = new CompareListPage(driver);
        headerFunctionsPage = new HeaderFunctionsPage(driver);
    }

    @Test
    public void test() throws Exception {
        rozetkaHomePage.searchForMonitors();
        searchPage.findProductWithPriceLessThan("4000");
        priceOfFirstProduct = productPage.getPrice();
        nameOfFirstProduct = productPage.getName();
        productPage.addToCompareList();
        Assert.assertEquals(headerFunctionsPage.getCompareListCounter(), "1");
        productPage.navigateBack();
        searchPage.findProductWithPriceLessThan(priceOfFirstProduct);
        priceOfSecondProduct = productPage.getPrice();
        nameOfSecondProduct = productPage.getName();
        productPage.addToCompareList();
        Assert.assertEquals(headerFunctionsPage.getCompareListCounter(), "2");
        productPage.navigateToCompareList();
        Assert.assertEquals(compareListPage.getNumberOfProducts(), 2);
        Assert.assertEquals(compareListPage.getPriceByNumber(1), priceOfFirstProduct);
        Assert.assertEquals(compareListPage.getNameByNumber(1), nameOfFirstProduct);
        Assert.assertEquals(compareListPage.getPriceByNumber(2), priceOfSecondProduct);
        Assert.assertEquals(compareListPage.getNameByNumber(2), nameOfSecondProduct);
    }
}