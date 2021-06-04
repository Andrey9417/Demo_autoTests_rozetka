import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages_for_Rozetka.ProductPage;
import pages_for_Rozetka.RozetkaHomePage;
import pages_for_Rozetka.SearchPage;

import java.util.concurrent.TimeUnit;

public class ProductPageTests {
    private WebDriver driver;
    private String initialUrl = "https://rozetka.com.ua/";

    private RozetkaHomePage rozetkaHomePage;
    private SearchPage searchPage;
    ProductPage productPage;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterClass
    public void closeBrowser() {
        //driver.quit();
    }

    @BeforeMethod
    public void navigateToSite() {
        driver.get(initialUrl);
        rozetkaHomePage = new RozetkaHomePage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
    }

    @Test
    public void testProductPageFirst(){
        rozetkaHomePage.searchByName("samsung");
        searchPage.chooseProductCategory("mobile-phones");
        searchPage.clickToTheFirstPhone();
        productPage.saveLinkImg();
        productPage.clicktoTheFirstChildOfColorWrapper();
        productPage.checkImg();

    }
}
