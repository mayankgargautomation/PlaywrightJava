package tests.UIFrameworkRunner;

import Util.JsonValueExtraction;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;
import tests.APIPOMPAGES.GetBookingIds;
import tests.UIPages.*;
import java.util.HashMap;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class IMockingRequestRouteResume extends BaseUITest
{

    @Test
    public void mockRequest() throws Exception
    {
        //Browser calls API with request -> Request is modified with route.resume() -> API sends response
        // ->Modifies response renders on Front End.
        //  cmayank309@gmail.com
        //  eventHub@123
        JsonValueExtraction jsonValueExtraction = new JsonValueExtraction();
        List<HashMap<String, String>> valExtr = jsonValueExtraction.JsonToMap(System.getProperty("user.dir") + "/src/test/resources/createEvent.json");


        ILoginPage loginobj=new ILoginPage( page, pageURL);
        loginobj.loginPage();

        DashboardPage dash = new DashboardPage( page);
        dash.dashboard();

        AddEvents addEv = new AddEvents( page);
        addEv.addingEvents();
        addEv.createEvent(
                valExtr.get(0).get("titlePrefix"),
                valExtr.get(0).get("description"),
                valExtr.get(0).get("city"),
                valExtr.get(0).get("venue"),
                valExtr.get(0).get("dateTime"),
                valExtr.get(0).get("price"),
                valExtr.get(0).get("totalSeats")
        );


        EventsCardPage evcard = new EventsCardPage( page);
        evcard.navEvents();    // moving to view nav-events
        evcard.eventCards();   // book-now-btn clicked and wait for 2 seconds

        DoBookingOfSeat bookingset = new DoBookingOfSeat( page);
        bookingset.doBooking();

        // API ki 3 classes ki calling kar li
        GetBookingIds values = new GetBookingIds();
        try
        {
            values.initializeApi();    // class name is : APIBaseTest
            // Login using second user's credentials
            values.LoginCase();        // class name is : APIEventLoginPage
            values.bookingId();        // class name is : GetBookingIds
            System.out.println("************IMockingRequestRouteResume Class*************");
            System.out.println("Second User Booking ID: "+values.bookingIdOfSecondUser);

            // Read the actual second user's booking ID
            int secondUserBookingId = values.bookingIdOfSecondUser;

            // Intercepting the request itself
            page.route("**/api/bookings/**", route -> route.resume(new Route.ResumeOptions().setUrl("https://api.eventhub.rahulshettyacademy.com/api/bookings/"+secondUserBookingId)));
            System.out.println("Intercepting the request itself using route");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View Details")).first().click();
            page.waitForTimeout(3000);
            assertThat(page.getByText("Access denied")).isVisible();
        }

        finally
        {
            values.tearDown();
        }

        //Request will be modified here then API will send response due to modified request .
        //  garg.mayank400@gmail.com
        //  eventHub@123
    }

}