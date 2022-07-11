package es.udc.paproject.e2etests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

class AppTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        driver.get("http://localhost:3000/");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testLogin() {

        String title = driver.getTitle();
        Assertions.assertEquals("PA Project", title);

        WebElement navBarCollapsed = driver.findElement(By.id("navbar-toggler"));
        navBarCollapsed.click();

        WebElement loginLink = driver.findElement(By.id("login-link"));
        loginLink.click();

        WebElement userTextField = driver.findElement(By.id("userName"));
        userTextField.sendKeys("test");

        WebElement passTextFIeld = driver.findElement(By.id("password"));
        passTextFIeld.sendKeys("test" + Keys.ENTER);
    }

    /** Joaquín Solla Vázquez:
     *  Test ver detalles producto
     *  */
    @Test
    void testProductDetails() {

        // User login
        testLogin();

        // Product searching
        WebElement productSearchTextField = driver.findElement(By.id("keywords"));
        productSearchTextField.sendKeys("Pantalla 1");
        WebElement productSearchButton = driver.findElement(By.id("searchProductButton"));
        productSearchButton.click();
        WebElement productLink = driver.findElement(By.id("productLink"));
        productLink.click();

        // Check product fields
        driver.findElement(By.id("backLink"));
        WebElement productName = driver.findElement(By.id("productName"));
        driver.findElement(By.id("productCategory"));
        driver.findElement(By.id("productVendor"));
        driver.findElement(By.id("productPublicationDate"));
        driver.findElement(By.id("productMinutesLeft"));
        driver.findElement(By.id("productDescription"));
        driver.findElement(By.id("productShippingInfo"));
        driver.findElement(By.id("productInitialPrice"));
        driver.findElement(By.id("productCurrentPrice"));

        // Check bidding form
        driver.findElement(By.id("minimumBidMessage"));
        driver.findElement(By.id("myBidMessage"));
        driver.findElement(By.id("price"));
        driver.findElement(By.id("placeBidButton"));

        // Check product name
        Assertions.assertEquals("Pantalla 1", productName.getText());

    }

    /** Thomas R. Iñiguez Arias:
     *  Test para insertar un producto
     *  */
    @Test
    void testInsertProduct() {

        //User login
        testLogin();

        //Click on "insertProduct" option
        WebElement insertProductButton = driver.findElement(By.id("insertProductButton"));
        insertProductButton.click();

        //Fill the form
        Select categoryField = new Select(driver.findElement(By.id("productCategoryId")));
        categoryField.selectByVisibleText("Pantallas");
        /*WebElement categoryField = driver.findElement(By.id("categoryId"));
        categoryField.click();
        WebElement selectCategory = driver.findElement(By.name("Pantallas"));
        selectCategory.click();*/
        WebElement nameTextField = driver.findElement(By.id("name"));
        nameTextField.sendKeys("Pantalla 100");
        WebElement descriptionTextField = driver.findElement(By.id("description"));
        descriptionTextField.sendKeys("Pantalla 100");
        WebElement bidDurationTextField = driver.findElement(By.id("bidMinutesDuration"));
        bidDurationTextField.sendKeys("1000000000");
        WebElement initialPriceTextField = driver.findElement(By.id("initialPrice"));
        initialPriceTextField.sendKeys("100");
        WebElement shipInfoTextField = driver.findElement(By.id("shippingInfo"));
        shipInfoTextField.sendKeys("Info de envio Pantalla 100");

        //Click on confirm button
        WebElement confirmInsertProductButton = driver.findElement(By.id("confirmInsertProductButton"));
        confirmInsertProductButton.click();

        //Inserted product confirmation
        WebElement productName = driver.findElement(By.id("productName"));
        Assertions.assertEquals("Pantalla 100", productName.getText());

        //Click on user inserted products
        WebElement navBar = driver.findElement(By.id("navbar-toggler"));
        navBar.click();
        WebElement userProductsButton = driver.findElement(By.id("userProductsLink"));
        userProductsButton.click();

        //Product name check
        WebElement insertedProductName = driver.findElement(By.id("productLink"));
        Assertions.assertEquals("Pantalla 100", insertedProductName.getText());
    }

    /** Jorge González Fernández:
     *  Test pujar por un producto
     *  */
    @Test
    void testBidOnAProduct() {

        // User login
        testLogin();

        // Browser navigation
        WebElement productSearchTextField = driver.findElement(By.id("keywords"));
        productSearchTextField.sendKeys("Pantalla 2");
        WebElement productSearchButton = driver.findElement(By.id("searchProductButton"));
        productSearchButton.click();
        WebElement productLink = driver.findElement(By.id("productLink"));
        productLink.click();

        // Bid on a product
        WebElement bidPrice = driver.findElement(By.id("price"));
        bidPrice.sendKeys("200");
        WebElement placeBid = driver.findElement(By.id("placeBidButton"));
        placeBid.click();

        // Check message
        driver.findElement(By.id("myBidMessage"));

        // Done bids
        WebElement doneBids = driver.findElement(By.id("doneBidsLink"));
        doneBids.click();

        // Check product
        WebElement productName = driver.findElement(By.id("productLink"));
        Assertions.assertEquals("Pantalla 2", productName.getText());
    }

}
