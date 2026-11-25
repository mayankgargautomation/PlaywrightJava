package tests.UIFrameworkRunner;

import com.microsoft.playwright.Route;
import org.testng.annotations.Test;
import tests.UIPages.DashboardPage;
import tests.UIPages.ILoginPage;

import java.nio.file.Paths;

public class IMockingResponseRouteFulfill extends BaseUITest
{
    @Test(groups={"smoke"})
    public void mockRequest() throws Exception
    {
        //Browser calls API with request -> API sends request
        // ->s with (route.fulfill) ->Modifies response renders on Front End.
       // cmayank309@gmail.com
       // eventHub@123
        ILoginPage loginobj=new ILoginPage( page, pageURL);
        loginobj.loginPage();

        DashboardPage dash = new DashboardPage( page);
        dash.dashboard();

        //here , a page will show tiles.json page , means manipulated json tiles will be shown.
      page.route("**api/events**", route -> route.fulfill(
              new Route.FulfillOptions().setPath(Paths.get("src/test/resources/tiles.json"))));

      page.navigate("https://eventhub.rahulshettyacademy.com/events");
      page.waitForTimeout(5000);


    }

}