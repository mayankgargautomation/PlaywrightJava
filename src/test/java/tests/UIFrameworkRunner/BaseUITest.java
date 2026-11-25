package tests.UIFrameworkRunner;

import com.microsoft.playwright.*;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseUITest
{
    //Invoke browser ->invoke tab
    //enter URL and submit
    Playwright playwright;
    Browser browser;
    BrowserContext context;
    Page page;
    String browserName;
    String envName;
    String pageURL;

    @BeforeMethod(alwaysRun = true)
    public void baseUITests() throws IOException
    {
        // Read config.properties file
        Properties prop=new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
        prop.load(fis);

        //browserName=prop.getProperty("browser");
        //mvn test -PSmoke -Dbrowser=chrome -Denv=qa
        browserName=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
        envName=System.getProperty("env")!=null ? System.getProperty("env") : prop.getProperty("env");


        // it will return a Playwright object AND whose return type is Playwright
        /*Playwright*/ playwright = Playwright.create();
        if("firefox".equalsIgnoreCase(browserName))
        {
           /*Browser*/ browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        }
        else
        {
            /*Browser*/  browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // it will return a Browser object AND whose return type is Browser
        }

        /*BrowserContext*/ context = browser.newContext(); // it will return a BrowserContext object
        /*Page*/ page = context.newPage();

        //reading url from config.property file & passing into page.navigate("");
        //  mvn test -PSmoke -Dbrowser=chrome -Denv=qa
        pageURL=prop.getProperty(envName+".baseURL");

    }
}
