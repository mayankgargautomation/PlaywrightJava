package tests.UIPractise;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class LoginPageContextTracingPractice
{
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;

    @BeforeMethod(alwaysRun=true)
    public void loginPageCheck()
    {
       playwright= Playwright.create(); // it will create a playwright object
         browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // it will create a browser object
        // Create a new incognito browser context.
         context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
        // Create a new page in a pristine context.
        page = context.newPage();
        page.navigate("https://rahulshettyacademy.com/loginpagePractise/");

    }


    /*
      Simple flow
        1. Find the link on the original page.
        2. Start waiting for a new tab.
        3. Click the link.
        4. Capture the newly opened tab as newPage.
        5. Wait for its page load.
     */

@Test
    public void loginPageCheckTest()
    {

        Locator blinkingTexts=page.locator(".blinkingText");   // step1
        Page newPage=context.waitForPage(()->blinkingTexts.first().click());  //step2 and step3 and step4
        newPage.waitForLoadState();//step5
        String newPageText =  newPage.locator(".im-para.red").textContent();
        String emailId=newPageText.split("at")[1].split("with")[0].trim();
        System.out.println("emailId: "+emailId);
        page.getByLabel("Username:").fill(emailId); // back to main page
        page.waitForTimeout(3000);
    }


  @Test
  public void UiValueFilled()
  {
   page.getByLabel("Username:").fill("rahulshettyacademy");
   page.getByLabel("Password:").fill("Learning@830$3mK2");
   page.locator("input[value=user]").click();
   page.getByRole(AriaRole.BUTTON , new Page.GetByRoleOptions().setName("Okay")).click();
   page.locator(".form-control").filter(new Locator.FilterOptions().setHasText("Student")).selectOption("Teacher");
   page.locator("#terms").click();
   page.locator("#signInBtn").click();

  }

    @AfterMethod()
    public void tearDown()
    {
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
    }

}