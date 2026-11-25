package tests.APIPOMPAGES;

import com.microsoft.playwright.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class APIBaseTest {

    protected Playwright playwright;
    protected APIRequestContext reqContext;

    public void initializeApi()
    {
        playwright = Playwright.create();
        reqContext = playwright.request().newContext();
    }

    @BeforeMethod(alwaysRun = true)
    public void loginPageCheck()
    {
        initializeApi();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()
      {
        if (reqContext != null)
        {
            reqContext.dispose();
            reqContext = null;
        }

        if (playwright != null)
        {
            playwright.close();
            playwright = null;
        }
    }
}