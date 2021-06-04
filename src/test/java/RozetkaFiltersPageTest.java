import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages_for_Rozetka.RozetkaHomePage;
import pages_for_Rozetka.SearchPage;
import pages_for_Rozetka.SearchWithFiltersPage;

import java.util.concurrent.TimeUnit;

public class RozetkaFiltersPageTest {

    private WebDriver driver;
    private String initialUrl = "https://rozetka.com.ua/";

    private RozetkaHomePage rozetkaHomePage;
    private SearchPage searchPage;
    private SearchWithFiltersPage searchWithFiltersPage;

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
        searchWithFiltersPage = new SearchWithFiltersPage(driver);
    }

    @Test
    public void test1FilterByManufacturer() {
        rozetkaHomePage.searchByName("samsung");
        searchPage.chooseProductCategory("mobile-phones");
        searchWithFiltersPage.addFilterByManufacturer("Apple");
        searchWithFiltersPage.addFilterByManufacturer("Huawei");
        Assert.assertTrue(searchWithFiltersPage.checkProductsManufacturer("Samsung", "Apple", "Huawei"));
    }

    @Test
    public void test2FilterByPrice() {
        rozetkaHomePage.searchByName("samsung");
        searchPage.chooseProductCategory("mobile-phones");
        searchWithFiltersPage.setMinPrice("5000");
        searchWithFiltersPage.setMaxPrice("15000");
        Assert.assertTrue(searchWithFiltersPage.checkPriceDiapason(5000, 15000));
    }

    @Test
    public void test3FilterByAvailability() {
        rozetkaHomePage.searchByName("samsung");
        searchPage.chooseProductCategory("mobile-phones");
        searchWithFiltersPage.setFilterReadyToDeliver();
        Assert.assertTrue(searchWithFiltersPage.checkAvailability());
    }
}

