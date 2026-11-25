package tests.APIPOMPAGES;

import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

public class GetAnySpecificEventDetails extends tests.APIPOMPAGES.CreateNewEvent
{

    @Test
    public void getAnySpecificEventDetails() throws Exception
    {
        APIResponse getSpecificEventsId =
        reqContext.get("https://api.eventhub.rahulshettyacademy.com/api/events/" + eventid,
        RequestOptions.create().setHeader("Authorization", "Bearer " + token));

        System.out.println("*********GetAnySpecificEventDetails Class************");
        System.out.println("Status: " + getSpecificEventsId.status());
        String retrievedEventTitle = JsonPath.read(getSpecificEventsId.text(), "$.data.title");
        System.out.println("Retrieved Event Title: " + retrievedEventTitle);

    }
}
