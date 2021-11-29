import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class RegisterNewUser
{
    public static WebDriver driver;
    String loginPageExpectedTitle = "nopCommerce demo store. Login";
    String loginPageActualTitle;

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
    public void registerLink()
    {
        //for Register
        driver.findElement(By.linkText("Register")).click();
        String getRegisterLinkTitle = driver.getTitle();
        System.out.println("Register Link Title : " + getRegisterLinkTitle);
    }

    @Test
    @Order(2)
    public void radioButtonFemale()
    {
          driver.findElement(By.cssSelector("input#gender-female")).click();
    }

    @Test
    @Order(3)
    public void enterFirstName()
    {

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Ruby");
    }

    @Test
    @Order(4)
    public void enterLastName()
    {
        driver.findElement(By.cssSelector("input#LastName")).sendKeys("Patel");
    }

    @Test
    @Order(5)
    public void getDOB()
    {
        Select day = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        day.selectByIndex(10);

        Select month = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        month.selectByIndex(4);

        Select year = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        year.selectByValue("1995");
    }

    @Test
    @Order(6)
    public void getEmail()
    {
        driver.findElement(By.id("Email")).sendKeys("abc2@gmail.com");
    }

    @Test
    @Order(7)
    public void getCompanyName()
    {
        //for company Name text is displayed
        driver.findElement(By.cssSelector("input#Company")).sendKeys("XYZ Company");
    }

    @Test
    @Order(8)
    public void newsLetterSelected()
    {
        //for newsletter checkbox is selected
        boolean newsletterCheckBox = driver.findElement(By.id("Newsletter")).isSelected();
        System.out.println("Newsletter checkbox is selected : " + newsletterCheckBox);
    }

    @Test
    @Order(9)
    public void password()
    {
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("12345678");
    }


    @Test
    @Order(10)
    public void confirmPassword()
    {
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("12345678");
    }

    @Test
    @Order(11)
    public void registrationButton()
    {
        driver.findElement(By.className("register-next-step-button")).click();
    }

    @Test
    @Order(12)
    public void logOut()
    {
        driver.findElement(By.linkText("Log out")).click();
    }

    @Test
    @Order(13)
    public void logInRegisteredUser()
    {
        driver.findElement(By.linkText("Log in")).click();

        loginPageActualTitle = driver.getTitle();
        Assertions.assertEquals(loginPageExpectedTitle,loginPageActualTitle);


        driver.findElement(By.id("Email")).sendKeys("abc2@gmail.com");
        driver.findElement(By.id("Password")).sendKeys("12345678");
        driver.findElement(By.className("login-button")).click();

        boolean logOut =  driver.findElement(By.className("ico-logout")).isDisplayed();
        Assertions.assertTrue(logOut,"Log out is displayed");

    }






}
