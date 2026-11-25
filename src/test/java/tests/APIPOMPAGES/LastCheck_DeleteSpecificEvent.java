package tests.APIPOMPAGES;

import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LastCheck_DeleteSpecificEvent extends tests.APIPOMPAGES.CreateNewEvent
{
    @Test
    public void deleteSpecificEventId() throws Exception
    {
        APIResponse deleteEvent=
                reqContext.delete("https://api.eventhub.rahulshettyacademy.com/api/events/" + eventid,
                RequestOptions.create().setHeader("Authorization", "Bearer " + token));

        System.out.println("*********DeleteSpecificEvent Class************");
        System.out.println("This "+eventid+" is not anymore"+deleteEvent.text());

        String message =JsonPath.read(deleteEvent.text(),"$.message");
        System.out.println(message);
        Assert.assertEquals(message,"Event deleted successfully");

       // Logic of Recheck that this eventid is deleted from list
        System.out.println("*******List Recheck Logic**********");
        APIResponse thisEventsId =
                reqContext.get("https://api.eventhub.rahulshettyacademy.com/api/events",
                RequestOptions.create().setHeader("Authorization", "Bearer " + token));

        List<String> eventsTitles =JsonPath.read(thisEventsId.text(),"$.data[*].title");
        Assert.assertFalse(eventsTitles.contains(eventid));
        System.out.println("This Event Id is already deleted successfully");

    }

}
