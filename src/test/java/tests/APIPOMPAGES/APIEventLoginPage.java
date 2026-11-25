package tests.APIPOMPAGES;
import Util.JsonValueExtraction;
import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.naming.PartialResultException;
import java.util.HashMap;
import java.util.List;
public class APIEventLoginPage extends tests.APIPOMPAGES.APIBaseTest
{
    public  String token;
   public  String token2;

   /* @DataProvider
    public Object[][] getData() throws Exception
    {

        //Extracting the data from the JSON file using JsonValueExtraction class
        JsonValueExtraction jve = new JsonValueExtraction();
        List<HashMap<String, String>> input =jve.JsonToMap(System.getProperty("user.dir") + "/src/test/resources/Read.json");
        Object[][] arr = new Object[input.size()][1];    // row-2 , column - 1
        for (int i = 0; i < input.size(); i++)
        {
            arr[i][0] = input.get(i);   //0,0 - cmayank309@gmail.com , eventHub@123
            System.out.println("DataProvider - getData() - Row: " + i + " - Email: " + input.get(i).get("email") + " - Password: " + input.get(i).get("password"));
        }

        return arr;
    }*/

    @Test
    public void LoginCase() throws Exception
    {
        JsonValueExtraction jsonValueExtraction = new JsonValueExtraction();
        List<HashMap<String, String>> data = jsonValueExtraction.JsonToMap(System.getProperty("user.dir") + "/src/test/resources/Read.json");
            System.out.println(data.get(0).get("email"));
            System.out.println(data.get(0).get("password"));

            //Passing the URL & Payload (//APIResponse Object creation) for map1
        APIResponse loginResponse = reqContext.post("https://api.eventhub.rahulshettyacademy.com/api/auth/login",
                    RequestOptions.create().setData(data.get(0)));

            //Extracting the token from the response using JsonPath
            System.out.println("********Printing Login Token**********");
            token = JsonPath.read(loginResponse.text(), "$.token");
            System.out.println(token);

       //Passing the URL & Payload (//APIResponse Object creation) for map1
        APIResponse secResponse=reqContext.post("https://api.eventhub.rahulshettyacademy.com/api/auth/login",
                        RequestOptions.create().setData(data.get(1)));
        System.out.println(data.get(1).get("email"));
        System.out.println(data.get(1).get("password"));

        //Extracting the token from the response using JsonPath
        System.out.println("********sec response Login Token**********");
        token2 = JsonPath.read(secResponse.text(), "$.token");
        System.out.println(token2);


        /*HashMap<String,String> map=new HashMap<>();
        map.put("email", "cmayank309@gmail.com");
        map.put("password", "eventHub@123");*/

        //exploration of other APIRespose methods
        /*System.out.println(loginResponse.status());
        System.out.println(loginResponse.statusText());
        System.out.println(loginResponse.headers());*/
    }

}