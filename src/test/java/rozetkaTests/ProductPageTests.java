package rozetkaTests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages_for_Rozetka.ProductPage;
import pages_for_Rozetka.RozetkaHomePage;
import pages_for_Rozetka.SearchPage;

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
        productPage.checkImg();
    }

    @Test
    public void testProductPageSecond() throws InterruptedException {
        driver.manage().window().maximize();
        rozetkaHomePage.searchByName("samsung");
        searchPage.chooseProductCategory("mobile-phones");
        searchPage.clickToTheFirstPhone();
        productPage.moveToPicturePhone();
        productPage.checkChangingWhenCoursorMove();
        productPage.checkWhenCoursorMoveAway();
    }

    @Test
    public void testProductPageThird(){
        driver.manage().window().maximize();
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
