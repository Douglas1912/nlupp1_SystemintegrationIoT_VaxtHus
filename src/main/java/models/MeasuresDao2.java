package models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeasuresDao2 {
    public List<Object> getAllJsonMeasures() throws IOException, ParseException {

        List<Object> MeasuresListFromFile = new ArrayList<>();
        JSONParser parser = new JSONParser();
        Object jsonarray = parser.parse(new FileReader("src/main/java/repositories/AllMeasuresJSON.json"));
        ObjectMapper mapper = new ObjectMapper();
        String jsonFromFile = mapper.writeValueAsString(jsonarray);
        JSONArray jsonArr = new JSONArray(jsonFromFile);


        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject objects = jsonArr.getJSONObject(i);

            MeasuresListFromFile.add(jsonArr.getJSONObject(i));

            Iterator<String> iterator = objects.keys();

            if (iterator != null) {
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    Object value = objects.get(key);
                    String dataType = value.getClass().getSimpleName();

                }
            }
        }


        return MeasuresListFromFile;

    }
}
