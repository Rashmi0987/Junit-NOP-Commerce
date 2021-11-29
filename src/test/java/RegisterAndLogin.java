import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class RegisterAndLogin
{
    public static WebDriver driver;
    String homePageExpectedTitle = "nopCommerce demo store";
    String homePageActualTitle;

    String registerPageExpectedTitle = "nopCommerce demo store. Register";
    String registerPageActualTitle;

    String registerExpectedConfMsg = "Your registration completed";
    String registerActualCongMsg;


    String loginPageExpectedTitle = "nopCommerce demo store. Login";
    String loginPageActualTitle;

    WebDriverWait wait;

    @BeforeAll
    public static void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();

    }

    @Test
    @Order(1)
    public void registerNewUser()
    {
        homePageActualTitle = driver.getTitle();
        Assertions.assertEquals(homePageExpectedTitle,homePageActualTitle);


        boolean registerLink =  driver.findElement(By.linkText("Register")).isDisplayed();
        Assertions.assertTrue(registerLink,"Register link is displayed");

        driver.findElement(By.linkText("Register")).click();


        registerPageActualTitle = driver.getTitle();
        Assertions.assertEquals(registerPageExpectedTitle,registerPageActualTitle);


        driver.findElement(By.id("gender-male")).click();


        boolean maleRadioBtn =  driver.findElement(By.id("gender-male")).isSelected();
        Assertions.assertTrue(maleRadioBtn,"male radio button is selected");

        driver.findElement(By.id("FirstName")).sendKeys("Sara");
        driver.findElement(By.id("LastName")).sendKeys("Verma");
        driver.findElement(By.id("Email")).sendKeys("sss@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("12345678");
        driver.findElement(By.id("ConfirmPassword")).sendKeys("12345678");


        driver.findElement(By.id("Newsletter")).click();
        boolean newsLetterCheckbox = driver.findElement(By.id("Newsletter")).isSelected();
        Assertions.assertFalse(newsLetterCheckbox,"Newsletter Checkbox not selected");


        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByVisibleText("1");

        boolean day1 = driver.findElement(By.xpath("(//option[@value='1'])[1]")).isSelected();
        System.out.println("day 1 is selected: " + day1);


        boolean registerBtn = driver.findElement(By.id("register-button")).isEnabled();
        System.out.println("Register Button is enabled: " + registerBtn);


        driver.findElement(By.id("register-button")).click();

        wait = new WebDriverWait(driver,30);
        WebElement registerConfText = driver.findElement(By.className("result"));
        wait.until(ExpectedConditions.visibilityOf(registerConfText));

        registerActualCongMsg =  driver.findElement(By.className("result")).getText();
        Assertions.assertEquals(registerExpectedConfMsg,registerActualCongMsg);

        driver.findElement(By.className("ico-logout")).click();

        Assertions.assertEquals(homePageExpectedTitle,homePageActualTitle);

    }

    @Test
    @Order(2)
    public void login()
    {
        driver.findElement(By.linkText("Log in")).click();

        loginPageActualTitle = driver.getTitle();
        Assertions.assertEquals(loginPageExpectedTitle,loginPageActualTitle);


        driver.findElement(By.id("Email")).sendKeys("sss@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("12345678");
        driver.findElement(By.className("login-button")).click();

        boolean logOut =  driver.findElement(By.className("ico-logout")).isDisplayed();
        Assertions.assertTrue(logOut,"Log out is displayed");

    }

    @AfterAll
    public static void tearDown()
    {
        driver.quit();
    }
}
