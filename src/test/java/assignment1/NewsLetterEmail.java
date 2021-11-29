package assignment1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewsLetterEmail
{
    public static WebDriver driver;
    WebDriverWait wait;
    String homePageExpectedTitle ="nopCommerce demo store";
    String homePageActualTitle;
    Boolean newsLetterTextBox;
    String getMsgExpected = "Thank you for signing up! A verification email has been sent. We appreciate your interest.";
    String getMsgActual;

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
    public void newsLetterEmail()
    {
        homePageActualTitle = driver.getTitle();
        Assertions.assertEquals( homePageExpectedTitle,homePageActualTitle);

        newsLetterTextBox = driver.findElement(By.id("newsletter-email")).isDisplayed();
        Assertions.assertTrue(newsLetterTextBox, "Text box is displayed");

        driver.findElement(By.id("newsletter-email")).sendKeys("abc@gmail.com");

        driver.findElement(By.xpath("//button[@id='newsletter-subscribe-button']")).click();

        wait = new WebDriverWait(driver,30);

        WebElement text = driver.findElement(By.xpath("//div[@id='newsletter-result-block']"));
        wait.until(ExpectedConditions.visibilityOf(text));

        getMsgActual = driver.findElement(By.xpath("//div[@id='newsletter-result-block']")).getText();
        Assertions.assertEquals(getMsgExpected,getMsgActual);
//        System.out.println(getMsgActual);

    }

}


