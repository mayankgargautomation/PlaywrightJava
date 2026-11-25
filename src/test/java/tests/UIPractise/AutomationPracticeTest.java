package tests.UIPractise;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AutomationPracticeTest
{

    @Test
    public void locatorsCheck()
    {
        Playwright playwright= Playwright.create();  // create playwright object
        Browser browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // browser object
        BrowserContext context = browser.newContext(); // browser context object
        Page page= context.newPage();
        page.navigate("https://rahulshettyacademy.com/AutomationPractice/"); // navigate to the URL
        System.out.println(page.title()); // print the title of the page
        assertThat(page).hasTitle("Practice Page"); // check if the title is correct or not

  /*
          These are the recommended built-in locators
            page.getByRole() to locate by explicit and implicit accessibility attributes.
            page.getByText() to locate by text content.
            page.getByPlaceholder() to locate an input by placeholder.
            page.getByAltText() to locate an element, usually image, by its text alternative.
            page.getByTitle() to    locate an element by its title attribute.
            page.getByLabel() to locate a form control by associated label's text.
            page.getByTestId() to locate an element based on its data-testid attribute (other attributes can be configured).
    */

        //for Radio use cssSelector in playwright
        page.locator("input[value='radio1']").check();

        //use placeholder for textfields
        page.getByPlaceholder("Type to Select Countries").fill("India");

        // way for handle auto-suggestive dropdowns
        page.getByText("India",new Page.GetByTextOptions().setExact(true)).click();

        //for clicking on dropdown
        page.locator("#dropdown-class-example").filter(new Locator.FilterOptions().setHasText("Select")).selectOption("option2");

        //for checkbox use Role
        page.locator("#checkBoxOption1").click();

        //getByRole should be used for buttons, link etc. ( basically to open new window in this case )
        //page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Open Window")).click();

        //getByRole should be used for buttons, link etc. ( basically to open new tab in this case )
        //page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("Open Tab")).click();

        //for textfield we can use page.getByLabel as well
        page.getByPlaceholder("Enter Your Name").fill("Mayank Garg");

        //for handling javascript alerts we can use page.onDialog
        page.onDialog(dialog -> dialog.accept());
        page.locator("#alertbtn").click();

        //page.onDialog(dialog -> dialog.dismiss());
        //page.locator("#confirmbtn").click();
        Locator totalAmountCheck = page.locator(".tableFixHead tr td:nth-child(4)");
        int sum = 0;
        for (int i = 0; i < totalAmountCheck.count(); i++)
        {
            String amountText = totalAmountCheck.nth(i).textContent().trim();
            System.out.println(amountText);
            int amount = Integer.parseInt(amountText);
            sum = sum + amount;
        }
        System.out.println("Total Count of all courses are :" +sum);

        page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Mouse Hover")).hover();
        page.getByText("Top", new Page.GetByTextOptions().setExact(true)).click();

        page.getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName("REST API")).click();
    }
}
