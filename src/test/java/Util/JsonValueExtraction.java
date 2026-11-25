package Util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

public class JsonValueExtraction {

/*
    public static void main(String[] args) throws Exception
    {
        JsonValueExtraction jsonValueExtraction = new JsonValueExtraction();
        List<HashMap<String, String>> data = jsonValueExtraction.JsonToMap(System.getProperty("user.dir") + "/src/test/resources/Read.json");
        System.out.println(data.get(0).get("email"));  //  {map1}
        System.out.println(data.get(0).get("password"));  //  {map1}
    }
*/

    public List<HashMap<String, String>> JsonToMap(String filePath) throws Exception
    {
        //File file = new File(System.getProperty("user.dir"), "src/test/resources/Read.json");
        // Read the JSON file and convert it to a String
        File file = new File(filePath);
        String jsontoString = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

        // Read the JSON string variable and convert it to a HashMap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsontoString, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }


    }