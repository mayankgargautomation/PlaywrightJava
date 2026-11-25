package Base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UIBaseTest {
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeMethod(alwaysRun = true)
    public void loginPageCheck()
    {
        playwright = Playwright.create(); // it will create a playwright object
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // it will create a browser object
        // Create a new incognito browser context.
        context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
        // Create a new page in a pristine context.
        page=   context.newPage();
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
        //validate if the title is correct or not
        assertThat(page).hasTitle("EventHub — Discover & Book Events");
        page.getByPlaceholder("you@email.com").fill("cmayank309@gmail.com");
        page.getByLabel("Password").fill("eventHub@123");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();

    }



    @AfterMethod()
    public void tearDown()
    {
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
    }

}