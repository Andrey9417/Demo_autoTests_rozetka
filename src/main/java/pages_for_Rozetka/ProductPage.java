package pages_for_Rozetka;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

public class ProductPage {
    WebDriver webDriver;
    WebDriverWait wait;
    String imageFirstSrc;
    String imageSecondSrc;
    String zoomImgFirstPositionString;
    String zoomImgFSecondPositionString;
    Robot robot = new Robot();

    By compareListIcon = By.xpath("//button[@aria-label='Списки сравнения']");
    By linkOnModalWindow = By.cssSelector("div.modal__holder a");
    By compareButton = By.cssSelector("button.compare-button");
    By priceOfProduct = By.cssSelector("p.product-prices__big");
    By nameOfProduct = By.className("product__title");

    By colorOfProduct = By.className("goods-tile__colors"); //not needed yet - needed to verify the color of the product
    By colorWrapperFirst = By.cssSelector(".var-options__list > li:nth-child(1)");
    By imagePhoneScrollBar = By.cssSelector(".scrollbar__inner > li:nth-child(1) > a > img");
    By imagePhoneZoom = By.cssSelector(".product-photo > div >figure> img ");//By.className("product-photo__picture");
    By zoomActive = By.className("zoom-img");
    By zoomedImage = By.className("zoom-sticky");
    By zoomedImagePosition = By.cssSelector(".zoom-container-main > div > img");
    By headerOnPage = By.cssSelector(".product-tabs__content > h2");
    By headerChar = By.cssSelector(".tabs__list > li:nth-child(2)");

    public ProductPage(WebDriver webDriver) throws AWTException {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
    }

    public void navigateBack() {
        webDriver.navigate().back();
    }

    public void navigateToCompareList() {
        webDriver.findElement(compareListIcon).click();
        wait.until(ExpectedConditions.elementToBeClickable(linkOnModalWindow)).click();
    }

    public String getPrice() {
        return webDriver.findElement(priceOfProduct).getText().replaceAll("[ ₴]", "");
    }

    public String getName() {
        return webDriver.findElement(nameOfProduct).getText();
    }

    public void addToCompareList() {
        wait.until(ExpectedConditions.elementToBeClickable(compareButton)).click();
        HeaderFunctionsPage.productsInCompareListCount++;
    }

    public void checkImg() {
        WebElement pictureFirstColor = webDriver.findElement(imagePhoneScrollBar);
        imageFirstSrc = pictureFirstColor.getAttribute("src");
        wait.until(ExpectedConditions.elementToBeClickable(colorWrapperFirst)).click();
        WebElement pictureSecondColor = webDriver.findElement(imagePhoneScrollBar);
        imageSecondSrc = pictureSecondColor.getAttribute("src");

        if (imageFirstSrc.equals(imageSecondSrc)) {
            System.out.println("False");
        } else {
            System.out.println("Ok");
        }
    }

    public void moveToPicturePhone() {
        wait.until(ExpectedConditions.elementToBeClickable(imagePhoneZoom));

        robot.mouseMove(350, 500);


        Boolean isPresent = webDriver.findElements(zoomActive).size() > 0;
        if (isPresent == true) {
            System.out.println("Ok");
        } else {
            System.out.println("False");
        }
    }

    public void checkChangingWhenCoursorMove() throws InterruptedException {
        WebElement zoomImgFirstPosition = webDriver.findElement(zoomedImagePosition);
        zoomImgFirstPositionString = zoomImgFirstPosition.getAttribute("style");

        robot.mouseMove(350, 600);
        Thread.sleep(1500);
        WebElement zoomImgSecondPosition = webDriver.findElement(zoomedImagePosition);
        zoomImgFSecondPositionString = zoomImgSecondPosition.getAttribute("style");

        if (zoomImgFirstPositionString.equals(zoomImgFSecondPositionString)) {
            System.out.println("False");
        } else {
            System.out.println("Ok");
        }
    }

    public void checkWhenCoursorMoveAway() {
        robot.mouseMove(650, 600);
        Boolean isPresent = webDriver.findElements(zoomedImage).size() > 0;
        if (isPresent == true) {
            System.out.println("False");
        } else {
            System.out.println("Ok");
        }
    }

    public void headerCheckChar() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tabs__list > li:nth-child(2)"))).click();
        // wait.until(ExpectedConditions.elementToBeClickable(headerOnPage));
        WebElement headerCharacteristics = webDriver.findElement(headerOnPage);
        String headerCharacteristicStr = headerCharacteristics.getText();
        if (headerCharacteristicStr.contains("Характеристики")) {
            System.out.println("Ok");
        } else {
            System.out.println("False");
        }
    }

    public void headerCheckReviews() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tabs__list > li:nth-child(3)"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-comments__header > h2")));
        WebElement headerReviews = webDriver.findElement(By.cssSelector(".product-comments__header > h2"));
        String headerReviewsStr = headerReviews.getText();
        if (headerReviewsStr.contains("Отзывы")) {
            System.out.println("Ok");
        } else {
            System.out.println("False");
        }
    }

    public void headerCheckQuestions() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tabs__list > li:nth-child(4)"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product-questions__header > h2")));
        WebElement headerQuestions = webDriver.findElement(By.cssSelector(".product-questions__header > h2"));
        String headerQuestionsStr = headerQuestions.getText();
        if (headerQuestionsStr.contains("Вопросы")) {
            System.out.println("Ok");
        } else {
            System.out.println("False");
        }
    }

    public void headerCheckVideo() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tabs__list > li:nth-child(5)"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".layout > h3")));
        WebElement headerVideo = webDriver.findElement(By.cssSelector(".layout > h3"));
        String headerVideoStr = headerVideo.getText();
        if (headerVideoStr.contains("Видео")) {
            System.out.println("Ok");
        } else {
            System.out.println("False");
        }
    }

    public void headerCheckPhoto() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tabs__list > li:nth-child(6)"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(headerOnPage));
        WebElement headerPhoto = webDriver.findElement(headerOnPage);
        String headerPhotoStr = headerPhoto.getText();
        if (headerPhotoStr.contains("Фотографии")) {
            System.out.println("Ok");
        } else {
            System.out.println("False");
        }
    }

    public void headerCheckAccessories() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tabs__list > li:nth-child(7)"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".layout > h2")));
        WebElement headerAccessories = webDriver.findElement(By.cssSelector(".layout > h2"));
        String headerAccessoriesStr = headerAccessories.getText();
        if (headerAccessoriesStr.contains("Аксессуары")) {
            System.out.println("Ok");
        } else {
            System.out.println("False");
        }
    }
}


