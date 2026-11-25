package tests.APIPOMPAGES;

import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

public class GetAllEvents  extends  tests.APIPOMPAGES.APIEventLoginPage{
    @Test
    public void getAllEvents() throws Exception
    {
        APIResponse getEvents= reqContext.get("https://api.eventhub.rahulshettyacademy.com/api/events?limit=10",
               RequestOptions.create()
                       .setHeader("Authorization", "Bearer " + token));

        int firstEventId= JsonPath.read(getEvents.text(), "$.data[0].id");
        System.out.println("first EventId is: "+firstEventId);

        int secEventId=JsonPath.read(getEvents.text(), "$.data[1].id");
        System.out.println("second EventId is: "+secEventId);

        int thirdEventId=JsonPath.read(getEvents.text(), "$.data[2].id");
        System.out.println("third EventId is: "+thirdEventId);

        int fourthEventId=JsonPath.read(getEvents.text(), "$.data[3].id");
        System.out.println("fourth EventId is: "+fourthEventId);

        int fifthEventId=JsonPath.read(getEvents.text(), "$.data[4].id");
        System.out.println("fifth EventId is: "+fifthEventId);

        int sixthEventId=JsonPath.read(getEvents.text(), "$.data[5].id");
        System.out.println("sixth EventId is: "+sixthEventId);


        int totalEventsCount=JsonPath.read(getEvents.text(), "$.pagination.total");
        System.out.println("***total Events Count are: "+totalEventsCount);
    }
}
