package tests.APIPOMPAGES;
import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.Test;

public class GetBookingIds extends APIEventLoginPage {
    public int bookingIdOfFirstUser;
    public int bookingIdOfSecondUser;

    @Test
    public void bookingId()
    {
        // First User Booking
        APIResponse getBookingId =reqContext.get("https://api.eventhub.rahulshettyacademy.com/api/bookings",
                RequestOptions.create().setHeader("Authorization", "Bearer " +token));
        String firstBookingResponse = getBookingId.text(); //entire booking response will come for first user
        System.out.println(firstBookingResponse);

        bookingIdOfFirstUser =JsonPath.read(firstBookingResponse, "$.data[0].id"); //only first one will be grabbed
        System.out.println("GetBookingIds Class - First User Booking Id: " + bookingIdOfFirstUser);

        // Second User Booking
        APIResponse user2resp =
        reqContext.get("https://api.eventhub.rahulshettyacademy.com/api/bookings",
        RequestOptions.create().setHeader("Authorization", "Bearer " + token2));

        String secondBookingResponse = user2resp.text();
        System.out.println(secondBookingResponse);
        bookingIdOfSecondUser =JsonPath.read(secondBookingResponse, "$.data[0].id");
        System.out.println("GetBookingIds Class - Second User Booking Id: " + bookingIdOfSecondUser);

    }
}