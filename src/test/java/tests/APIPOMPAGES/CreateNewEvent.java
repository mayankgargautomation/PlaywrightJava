package tests.APIPOMPAGES;

import Util.JsonValueExtraction;
import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;

public class CreateNewEvent extends tests.APIPOMPAGES.APIEventLoginPage
{
    int eventid;
    @Test
    public void NewEventCase() throws Exception
    {
        //Step1 : Calling JsonToMap method to extract the data from the JSON file by passing filepath
        //Extracting the data from the JSON file using JsonValueExtraction class
        JsonValueExtraction jsonValueExtraction = new JsonValueExtraction();
        List<HashMap<String, String>> data = jsonValueExtraction.JsonToMap(System.getProperty("user.dir") + "/src/test/resources/newEvent.json");
        System.out.println("*************CreateNewEvent*************");
        System.out.println("Extracted Payload: " + data.get(0));
        System.out.println("Payload Size: " + data.get(0).size());

        //passing the URL & Payload (//APIResponse Object creation)
        // Step 2: Send the POST request
        APIResponse eventResponse = reqContext.post("https://api.eventhub.rahulshettyacademy.com/api/events",
        RequestOptions.create()
                .setData(data.get(0))   // setting up the request body with the data from the JSON file
        .setHeader("Authorization", "Bearer " + token) // setting up the Authorization header with the token obtained from the login response
        );

        System.out.println("Status: " + eventResponse.status());
        System.out.println("Response: " + eventResponse.text());

        Assert.assertTrue(eventResponse.ok(), "Event creation failed: " + eventResponse.text());

        eventid=JsonPath.read(eventResponse.text(), "$.data.id");
        System.out.println("Eventid: " + eventid);

    }

}