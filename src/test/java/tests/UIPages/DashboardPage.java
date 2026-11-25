package tests.UIPages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DashboardPage
{    Page page;
    public DashboardPage(Page page)
    {
        this.page=page;
    }

 public void dashboard()
 {
     page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browse Events →")).click();
     //page.waitForTimeout(1000);
 }
}