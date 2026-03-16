package tests;

import configs.RestAssuredConfigurations;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase
{
    @BeforeSuite
    public void setUp()
    {
        new RestAssuredConfigurations();
    }


    @AfterSuite
    public static void tearDown()
    {
        System.out.println("=== Test Suite Finished ===");
    }

}
