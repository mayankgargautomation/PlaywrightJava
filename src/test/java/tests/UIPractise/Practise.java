package tests.UIPractise;

import java.util.List;
import java.util.ListIterator;

public class Practise
{

    public static <Hashmap> void main(String[] args)
    {
    /* Pass : tc_101 , TC_103 , TC_106
 Fail : TC_102 , TC_104
 sKIP : TC_105 */
        List<String> results = List.of("TC_101:PASS", "TC_102:FAIL", "TC_103:PASS", "TC_104:FAIL", "TC_105:SKIP", "TC_106:PASS");
        int passCount=0;
        int failCount=0;
        int skipCount=0;

        ListIterator<String> iterator = results.listIterator();
        while( iterator.hasNext())
        {
            String result = iterator.next();
            String[] parts = result.split(":");
            String testCase = parts[0];
            String status = parts[1];

            if (status.equals("PASS"))
            {
                // System.out.println(testCase + " is Passed");
                passCount++;

            }
            else if (status.equals("FAIL"))
            {
                // System.out.println(testCase + " is Failed");
                failCount++;
                testCase = testCase + " is Failed";
                System.out.println(testCase);
            }
            else if (status.equals("SKIP"))
            {
                //System.out.println(testCase + " is Skipped");
                skipCount++;
            }
        }

        System.out.println( "passcount is"+passCount);
        System.out.println( "failcount is"+failCount);
        System.out.println("skipcount is"+skipCount);
    }
}