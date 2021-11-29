import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchProduct
{
    public static WebDriver driver;
    String homePageExpectedTitle = "nopCommerce demo store";
    //String homePageExpectedTitle = "dfdsfdsfwefeffcw";
    String homePageActualTitle;

    String searchPageExpectedTitle = "nopCommerce demo store. Search";

    String searchPageActualTitle;

    @BeforeAll
    public static void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://demo.nopcommerce.com/");
        driver.manage().window().maximize();

    }

    @Test
    public void searchForProduct()
    {
        homePageActualTitle = driver.getTitle();
        Assertions.assertEquals(homePageExpectedTitle,homePageActualTitle);

        driver.findElement(By.id("small-searchterms")).sendKeys("mac");
        driver.findElement(By.className("search-box-button")).click();

        searchPageActualTitle = driver.getTitle();
        Assertions.assertEquals(searchPageExpectedTitle,searchPageActualTitle);

    }

    @AfterAll
    public static void tearDown()
    {
        driver.quit();
    }

}
