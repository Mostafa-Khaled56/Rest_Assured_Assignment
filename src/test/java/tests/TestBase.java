package tests;

import configs.Configs;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase
{
    @BeforeSuite
    public void setUp()
    {
        new Configs();
    }


    @AfterSuite
    public void tearDown()
    {
        System.out.println("=== Test Suite Finished ===");
    }

}
