package rozetkaTests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages_for_Rozetka.ProductPage;
import pages_for_Rozetka.RozetkaHomePage;
import pages_for_Rozetka.SearchPage;

import static org.testng.Assert.assertTrue;

public class ProductPageTests extends BaseTestClass {

    private RozetkaHomePage rozetkaHomePage;
    private SearchPage searchPage;
    private ProductPage productPage;

    @BeforeMethod
    public void setupBrowserAndPages() {
        super.setupBrowserAndPages();
        rozetkaHomePage = new RozetkaHomePage(driver);
        searchPage = new SearchPage(driver);
        productPage = new ProductPage(driver);
    }

    @Test
    public void testProductPageFirst(){
        rozetkaHomePage.searchByName("samsung");
        searchPage.chooseProductCategory("mobile-phones");
        searchPage.clickToTheFirstPhone();
        assertTrue(productPage.checkScrollBarImg());
    }

    @Test
    public void testProductPageSecond() {
        driver.manage().window().maximize();
        rozetkaHomePage.searchByName("samsung");
        searchPage.chooseProductCategory("mobile-phones");
        searchPage.clickToTheFirstPhone();
        assertTrue(productPage.moveToPicturePhone());
        productPage.checkChangingWhenCoursorMove();
        productPage.checkWhenCoursorMoveAway();
    }

    @Test
    public void testProductPageThird(){
        rozetkaHomePage.searchByName("samsung");
        searchPage.chooseProductCategory("mobile-phones");
        searchPage.clickToTheFirstPhone();
        productPage.headerCheckChar();
        productPage.headerCheckReviews();
        productPage.headerCheckQuestions();
        productPage.headerCheckVideo();
        productPage.headerCheckPhoto();
        productPage.headerCheckAccessories();
    }
}
