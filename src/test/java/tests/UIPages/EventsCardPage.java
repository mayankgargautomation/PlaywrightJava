package tests.UIPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class EventsCardPage
{
   Page page;
    public EventsCardPage(Page page)
    {
        this.page=page;
    }

    public void navEvents()
    {
        page.locator("#nav-events").click();
    }

 public void eventCards()
 {
     page.waitForTimeout(2000);
     Locator listofCards=page.locator("#event-card");

     for (int i=0; i<listofCards.count(); i++)
     {
         if(i==3)
         {
             listofCards.nth(i).locator("#book-now-btn").click();
             break;
         }
     }
     page.waitForTimeout(2000);
 }


}