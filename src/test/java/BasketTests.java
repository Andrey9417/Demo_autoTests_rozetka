import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages_for_Rozetka.CompareListPage;
import pages_for_Rozetka.ProductPage;
import pages_for_Rozetka.RozetkaHomePage;
import pages_for_Rozetka.SearchPage;

import java.util.concurrent.TimeUnit;

public class BasketTests {
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
    }

    @Test
    public void test() {
        rozetkaHomePage.searchForNotebooks();
    }
}