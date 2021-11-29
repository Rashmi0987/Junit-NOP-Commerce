import org.junit.jupiter.api.*;

public class Annotations
{
    @BeforeAll
    public static void beforeAll()
    {
        System.out.println("Before all test");
    }
    @AfterAll
    public static void afterAll()
    {
        System.out.println("After all test");
    }
    @BeforeEach
    public void beforeEach()
    {
        System.out.println("Before each test");
    }
    @AfterEach
    public void AfterEach()
    {
        System.out.println("After each test");
    }

    @Test
    public void testCase1()
    {
        System.out.println("Test 1");
    }
    @Test
    public void testCase2()
    {
        System.out.println("Test 2");
    }
    @Test
    public void testCase3()
    {
        System.out.println("Test 3");
    }

}

