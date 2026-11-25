package tests.UIPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class AddEvents
{
    Page page;
    private static final String EVENT_TITLE_INPUT = "#event-title-input";
    private static final String DESCRIPTION_TEXTAREA = "#admin-event-form textarea";
    private static final String CITY_LABEL = "#city";
    private static final String VENUE_LABEL = "#venue";
    private static final String DATE_TIME_LABEL = "Event Date & Time";
    private static final String PRICE_LABEL = "Price ($)";
    private static final String TOTAL_SEATS_LABEL = "Total Seats";
    private static final String ADD_EVENT_BTN = "#add-event-btn";

    public AddEvents(Page page)
    {
        this.page=page;
    }

    public void addingEvents()
    {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Add New Event")).click();
        page.waitForTimeout(1000);
        page.navigate("https://eventhub.rahulshettyacademy.com/admin/events");
        page.waitForTimeout(2000);
    }

    public void createEvent(String title, String description, String city,String venue, String dateTime, String price, String totalSeats)
    {
            page.locator(EVENT_TITLE_INPUT).fill(title);          //values will be filled from json
            page.locator(DESCRIPTION_TEXTAREA).fill(description);
            page.locator(CITY_LABEL).fill(city);
            page.locator(VENUE_LABEL).fill(venue);
            Locator time=page.getByLabel(DATE_TIME_LABEL);
            page.waitForTimeout(2000);
            time.fill(dateTime);
            page.getByLabel(PRICE_LABEL).fill(price);
            page.getByLabel(TOTAL_SEATS_LABEL).fill(totalSeats);
            page.locator(ADD_EVENT_BTN).click();
    }
}