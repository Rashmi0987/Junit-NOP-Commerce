package assignment1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ClothPurchage
{
    public static WebDriver driver;
    public static Actions actions;
    public static WebDriverWait wait;
    public static Select size;
    public static Boolean size1, buttonClick, agreeStatementCheckBox;
  //  public static String productAddedToCartActualText;
    public static  WebElement text2, text3, text1;


    String homePageExpectedTitle = "nopCommerce demo store";
    String homePageActualTitle;

    String loginPageExpectedTitle = "nopCommerce demo store. Login";
    String loginPageActualTitle;

    String productSelectExpectedPageTitle = "nopCommerce demo store. Clothing";
    String productSelectActualPageTitle;

    String productTShirtExpectedPageTitle = "nopCommerce demo store. Nike Tailwind Loose Short-Sleeve Running Shirt";
    String productTShirtActualPageTitle;

    String shoppingCartExpectedPage = "nopCommerce demo store. Shopping Cart";
    String shoppingCartActualPage;

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
//        driver.quit();
    }

    @Test
    @Order(1)
    public void registerAndLogin()
    {

        homePageActualTitle = driver.getTitle();
        Assertions.assertEquals(homePageExpectedTitle,homePageActualTitle);
/*

        driver.findElement(By.linkText("Register")).click();
        String getRegisterLinkTitle = driver.getTitle();
        System.out.println("Register Link Title : " + getRegisterLinkTitle);
        driver.findElement(By.cssSelector("input#gender-female")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Ruby");
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Patel");
        Select day = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        day.selectByIndex(10);

        Select month = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        month.selectByIndex(4);

        Select year = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        year.selectByValue("1995");
        driver.findElement(By.id("Email")).sendKeys("abc0@gmail.com");
        driver.findElement(By.cssSelector("input#Company")).sendKeys("XYZ Company");
        boolean newsletterCheckBox = driver.findElement(By.id("Newsletter")).isSelected();
        System.out.println("Newsletter checkbox is selected : " + newsletterCheckBox);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("12345678");
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("12345678");
        driver.findElement(By.className("register-next-step-button")).click();

        // log out
        driver.findElement(By.linkText("Log out")).click();



 */



        // log in with registered email
        driver.findElement(By.linkText("Log in")).click();

        loginPageActualTitle = driver.getTitle();
        Assertions.assertEquals(loginPageExpectedTitle,loginPageActualTitle);

        driver.findElement(By.id("Email")).sendKeys("abc0@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("12345678");
        driver.findElement(By.className("login-button")).click();

        boolean logOut =  driver.findElement(By.className("ico-logout")).isDisplayed();
        Assertions.assertTrue(logOut,"Log out is displayed");

    }

    @Test
    @Order(2)
    public void clothPurchase()
    {

        //for mouse actions
        actions = new Actions(driver);
        WebElement apparel = driver.findElement(By.linkText("Apparel"));

        //mouse hover
        actions.moveToElement(apparel).perform();
       // click on the clothing option
        driver.findElement(By.linkText("Clothing")).click();

        // check reached on the clothing page
        productSelectActualPageTitle= driver.getTitle();
        Assertions.assertEquals(productSelectExpectedPageTitle,productSelectActualPageTitle);
//       System.out.println("  -----" + productSelectActualPageTitle);

        //select t shirt
//        driver.findElement(By.xpath("//div[@class='buttons'][1]")).click();
        driver.findElement(By.xpath("//button[@class='button-2 product-box-add-to-cart-button'][1]")).click();

        wait = new WebDriverWait(driver,30);

        text1 = driver.findElement(By.xpath("//button[@class='button-2 product-box-add-to-cart-button'][1]"));

        wait.until(ExpectedConditions.elementToBeClickable(text1));


//        driver.findElement(By.xpath("//div[@class='buttons'][1]")).click();
        driver.navigate().refresh();
        productTShirtActualPageTitle = driver.getTitle();
        Assertions.assertEquals(productTShirtExpectedPageTitle,productTShirtActualPageTitle);
        System.out.println(productTShirtActualPageTitle);

        wait = new WebDriverWait(driver,15);

        //select size (dropdown)
        size = new Select(driver.findElement(By.xpath("//select[@name='product_attribute_11']")));
        size.selectByIndex(1);

        text2 = driver.findElement(By.xpath("//select[@name='product_attribute_11']"));
       // wait.until(ExpectedConditions.elementToBeSelected(text2));
       wait.until(ExpectedConditions.visibilityOf(text2));

       //check size is selected in dropdown
        size1 = driver.findElement(By.xpath("//option[@value='28'][1]")).isSelected();
        System.out.println("Size selected " + size1);

        //click on the button (add to cart)
        driver.findElement(By.xpath("//button[@id='add-to-cart-button-27']")).click();
        buttonClick = driver.findElement(By.xpath("//button[@id='add-to-cart-button-27']")).isEnabled();
        System.out.println("Button is enabled " + buttonClick);

    }


    @Test
    @Order(3)
    public void checkOut()
    {
        driver.findElement(By.linkText("Shopping cart")).click();

        shoppingCartActualPage = driver.getTitle();
        Assertions.assertEquals(shoppingCartExpectedPage, shoppingCartActualPage);
        System.out.println( "Shopping cart actual page " + shoppingCartActualPage);

        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();

        agreeStatementCheckBox = driver.findElement(By.xpath("//input[@id='termsofservice']")).isSelected();
        System.out.println("agree Statement Check Box is selected " + agreeStatementCheckBox);

        driver.findElement(By.xpath("//button[@class='button-1 checkout-button']")).click();






    }

}
