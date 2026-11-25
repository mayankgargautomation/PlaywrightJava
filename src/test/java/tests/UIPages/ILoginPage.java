package tests.UIPages;

import Util.ReadExcel;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ILoginPage
{
    Page page;
    String pageURL;
    private static final String nameplaceholder="you@email.com";
    private static final String passwordplaceholder="Password";

    public ILoginPage(Page page,String pageURL)
    {
        this.page=page;
        this.pageURL=pageURL;
    }


public void loginPage() throws IOException
{
    //navigating to qa.baseUrl
    page.navigate(pageURL);

    ReadExcel read=new ReadExcel();
    List<HashMap<String, String>> data=read.getExcelData();

    //validate if the title is correct or not
    assertThat(page).hasTitle("EventHub — Discover & Book Events");
    page.getByPlaceholder(nameplaceholder).fill(data.get(0).get("email"));
    page.getByLabel(passwordplaceholder).fill(data.get(0).get("password"));
    page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
    page.waitForTimeout(5000);
}
}
