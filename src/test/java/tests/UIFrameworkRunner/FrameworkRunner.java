package tests.UIFrameworkRunner;

import Util.JsonValueExtraction;
import Util.RetryAnalyzer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.UIPages.*;
import java.util.HashMap;
import java.util.List;

public class FrameworkRunner extends BaseUITest
{
    @DataProvider
    public Object[][] getData( ) throws Exception
    {
        JsonValueExtraction jsonValueExtraction = new JsonValueExtraction();
        List<HashMap<String, String>> input=jsonValueExtraction.JsonToMap(System.getProperty("user.dir") + "/src/test/resources/createEvent.json");
                                    // input object contains data like this ---->  {{map1},{map2}}

        Object[][]  data= new Object[][] {    {input.get(0)}   ,    {input.get(1)} };
                                             //map1                   map2
                                             // BOTH MAPS WILL Contains multiple key value pair
        return  data;  // it will be having both maps data
    }

    @Test(dataProvider="getData", groups={"smoke"}, retryAnalyzer = RetryAnalyzer.class)
    public void frameworkRun(HashMap<String,String> data) throws Exception
    {
        ILoginPage loginobj=new ILoginPage( page, pageURL);
        loginobj.loginPage();

        DashboardPage dash = new DashboardPage( page);
        dash.dashboard();

        AddEvents addEv = new AddEvents( page);
        addEv.addingEvents();
        addEv.createEvent(
                 data.get("titlePrefix"),
                 data.get("description"),
                 data.get("city"),
                 data.get("venue"),
                 data.get("dateTime"),
                 data.get("price"),
                 data.get("totalSeats")
            );

        EventsCardPage evcard = new EventsCardPage( page);
        evcard.navEvents();    // moving to view nav-events
        evcard.eventCards();   // book-now-btn clicked and wait for 2 seconds

        DoBookingOfSeat bookingset = new DoBookingOfSeat( page);
        bookingset.doBooking();

        page.waitForTimeout(3000);
    }
}