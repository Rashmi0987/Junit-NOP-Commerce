import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterUser
{
    public static WebDriver driver;
    String homePageExpectedTitle = "nopCommerce demo store";
    String homePageActualTitle;

    String registerPageExpectedTitle = "nopCommerce demo store. Register";
    String registerPageActualTitle;



    @BeforeAll
    public static void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();

    }

    @Test
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
        //driver.findElement(By.id("gender-female")).click();

        boolean maleRadioBtn =  driver.findElement(By.id("gender-male")).isSelected();
        Assertions.assertTrue(maleRadioBtn,"male radio button is selected");

        driver.findElement(By.id("Newsletter")).click();
        boolean newsLetterCheckbox = driver.findElement(By.id("Newsletter")).isSelected();
        Assertions.assertFalse(newsLetterCheckbox,"Newsletter Checkbox not selected");





        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByVisibleText("1");

        boolean day1 = driver.findElement(By.xpath("(//option[@value='1'])[1]")).isSelected();
        Assertions.assertTrue(day1,"Day 1 is selected");

        boolean registerBtn = driver.findElement(By.id("register-button")).isEnabled();
        Assertions.assertTrue(registerBtn, "Register button is enabled");
    }

    @AfterAll
    public static void tearDown()
    {
        driver.quit();
    }

}


