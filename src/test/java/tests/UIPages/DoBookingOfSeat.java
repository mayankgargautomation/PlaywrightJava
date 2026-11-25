package tests.UIPages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DoBookingOfSeat
{
    Page page;
    public DoBookingOfSeat(Page page)
    {
        this.page=page;
    }
    public void doBooking()
    {
        page.locator("#customerName").fill("Manku Bhai");
        page.locator("#customer-email").fill("cmayank309@gmail.com");
        page.getByLabel("phone").fill("9897989798");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Confirm Booking")).click();
        page.waitForTimeout(2000);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View My Bookings")).click();
    }
}