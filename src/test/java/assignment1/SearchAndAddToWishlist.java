package assignment1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class SearchAndAddToWishlist
{
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static WebElement addToCartButton, addToWishListButton, shippingCartButton, finalButton;
    public static Boolean productCheckBox, termsOfServiceCheckBox;

    String homePageExpectedTitle = "nopCommerce demo store";
    String homePageActualTitle;

    String searchPageExpectedTitle = "nopCommerce demo store. Search";
    String searchPageActualTitle;

    String macBookProductExpectedlPage = "nopCommerce demo store. Apple MacBook Pro 13-inch";
    String macBookProductActualPage;

    String shippingCartExpectedPageTitle = "nopCommerce demo store. Shopping Cart";
    String shippingCartActualPageTitle;

    @BeforeAll
    public static void setUp()
    {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();

        driver.navigate().to("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();

    }

    @AfterAll
    public static void tearDown()
    {
       driver.quit();
    }

    @Test
    @Order(1)
    public void searchProduct()
    {
        homePageActualTitle = driver.getTitle();
        Assertions.assertEquals(homePageExpectedTitle,homePageActualTitle);

        driver.findElement(By.id("small-searchterms")).sendKeys("mac");
        driver.findElement(By.className("search-box-button")).click();

        searchPageActualTitle = driver.getTitle();
        Assertions.assertEquals(searchPageExpectedTitle,searchPageActualTitle);
        System.out.println(searchPageActualTitle);

    }

    @Test
    @Order(2)
    public void addToCart()
    {

        wait = new WebDriverWait(driver, 30);

        addToCartButton = driver.findElement(By.className("product-box-add-to-cart-button"));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));


        driver.findElement(By.className("product-box-add-to-cart-button")).click();

        driver.navigate().refresh();

        macBookProductActualPage = driver.getTitle();
        Assertions.assertEquals(macBookProductExpectedlPage,macBookProductActualPage);

    }


    @Test
    @Order(3)
    public void addToWishList()
    {
        wait = new WebDriverWait(driver, 30);

        addToWishListButton = driver.findElement(By.className("product-box-add-to-cart-button"));
        wait.until(ExpectedConditions.elementToBeClickable(addToWishListButton));

        driver.findElement(By.id("add-to-wishlist-button-4")).click();

        driver.navigate().refresh();

        driver.findElement(By.linkText("Wishlist")).click();

    }

    @Test
    @Order(4)
    public void checkboxSelected()
    {
        driver.findElement(By.xpath("//input[@name='addtocart']")).click();
        productCheckBox = driver.findElement(By.xpath("//input[@name='addtocart']")).isSelected();
      //  System.out.println("Check Box is selected " + productCheckBox);
        Assertions.assertTrue(productCheckBox, "Check Box is selected" );

    }

    @Test
    @Order(5)
    public void shippingCart()
    {

        wait = new WebDriverWait(driver, 30);

        shippingCartButton = driver.findElement(By.name("addtocartbutton"));
        wait.until(ExpectedConditions.elementToBeClickable(shippingCartButton));

        //click on add to cart button
        driver.findElement(By.name("addtocartbutton")).click();

        driver.navigate().refresh();

        shippingCartActualPageTitle = driver.getTitle();
        Assertions.assertEquals(shippingCartExpectedPageTitle,shippingCartActualPageTitle);

    }


    @Test
    @Order(6)
    public void finalCheckout()
    {

        driver.navigate().refresh();
//        wait = new WebDriverWait(driver, 30);
//
//        finalButton = driver.findElement(By.name("//input[@id='termsofservice']"));
//        wait.until(ExpectedConditions.elementToBeClickable(finalButton));

       driver.findElement(By.xpath("//input[@id='termsofservice']")).click();

        termsOfServiceCheckBox = driver.findElement(By.xpath("//input[@id='termsofservice']")).isSelected();
        System.out.println("terms Of Service CheckBox is selected " + termsOfServiceCheckBox);


        driver.findElement(By.xpath("//button[@id='checkout']")).click();
    }


}
