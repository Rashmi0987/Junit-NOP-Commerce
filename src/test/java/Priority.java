import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Priority
{
    @BeforeAll
    public static void setUp()
    {
        System.out.println("System initialization");
    }

    @Test
    @Order(1)
    public void register()
    {
        System.out.println("Register User");
    }


    @Test
    @Order(2)
    public void logIn()
    {
        System.out.println("Login with same Register User");
    }

    @Test
    @Order(3)
    public void addItemsToCart()
    {
        System.out.println("Add items in cart");
    }


    @Test
    @Order(4)
    public void checkOut()
    {
        System.out.println("Check Out");
    }

    @AfterAll
    public static void tearDown()
    {
        System.out.println("Quit Driver");
    }

}
