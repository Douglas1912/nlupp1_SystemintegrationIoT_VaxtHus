package restServer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import models.Measures;
import models.MeasuresDao;
import models.Response;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.event.MouseWheelEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

@RestController
public class Controller {

    Measures measures = new Measures();
    Response resp = new Response();
    MeasuresDao measuresDao = new MeasuresDao();
    List<Measures> measuresList = measuresDao.getAllMeasures();

    @PostMapping("/measures/temperature")
    public Measures updateCurrentTemperature(@RequestBody Measures m){
        float t = m.getTemperature();
        measures.setTemperature(t);
        return measures;
    }
    @PostMapping("/measures/humidity")
    public Measures updateCurrentHumidity(@RequestBody Measures m){
        float h = m.getHumidity();
        measures.setHumidity(h);
        return measures;
    }
    @PostMapping("/measures/luminosity")
    public Measures updateCurrentLuminosity(@RequestBody Measures m){
        int l = m.getLuminosity();
        measures.setLuminosity(l);
        return measures;
    }








    @PostMapping("/measures/add")
    public void addCurrentMeasureValues(@RequestBody Measures m) throws IOException, ParseException {

        //measuresList.add(m);
        List<Object> newMeasureList = new ArrayList<>();
        newMeasureList.add(measures);
        JSONParser parser = new JSONParser();
        Measures mm = new Measures();
        File file = new File("src/main/java/repositories/AllMeasuresJSON.json");

        ObjectMapper mapper = new ObjectMapper();
        String jsonMeasures = mapper.writeValueAsString(newMeasureList);

        Object jsonarray = parser.parse(new FileReader("src/main/java/repositories/AllMeasuresJSON.json"));
        ObjectMapper mapper2 = new ObjectMapper();
        String jsonFromFile = mapper2.writeValueAsString(jsonarray);

        //JSONObject jsonobject = new JSONObject(jsonMeasures);

        JsonNode tree1 = mapper.readTree(jsonFromFile);
        JsonNode tree2 = mapper.readTree(jsonMeasures);

        ((ArrayNode) tree1).addAll((ArrayNode) tree2);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree1);

        try {
            FileWriter fileWriter = new FileWriter("src/main/java/repositories/AllMeasuresJSON.json");         // writing back to the file
            fileWriter.write(String.valueOf(json));
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*

        final ObjectMapper mapper = new ObjectMapper();
        final String jsonMeasures = mapper.writeValueAsString(measures);

        try {

        Files.write(new File(String.valueOf(file)).toPath(), Arrays.asList(jsonMeasures),StandardOpenOption.APPEND, StandardOpenOption.CREATE);

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
*/
        }

        @RequestMapping(value = "/measures", headers = "Accept=application/json")
    public List<Measures> index() {
        return measuresList;
    }









    /*@RequestMapping(value = "/measures", headers = "Accept=application/json")
    public List<Measures> index() {
        return measuresList;
    }*/


    @RequestMapping(value = "/currentMeasuresJSON", headers = "Accept=application/json")
    public Measures currentMeasuresJSON() {
        return measures;
    }

    @RequestMapping(value = "/currentResponseJSON", headers = "Accept=application/json")
    public Response currentResponseJSON() {
        return resp;
    }

    @RequestMapping(value = "/SaveAllMeasuresToJSONFile", headers = "Accept=application/json")
    public MeasuresDao logCurrentValuesToFile() {
        Gson gson = new Gson();

        String json = gson.toJson(measuresDao.getAllMeasures());

        try (FileWriter writer = new FileWriter(
                "src/main/java/repositories/AllMeasuresJSON.json");) {
            writer.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return measuresDao;
    }



    @RequestMapping("/measuresHTML")
    public String getBooksHTML() {
        String res = "<HTML><HEAD><TITLE>Books</TITLE></HEAD><BODY><TABLE>";
        for (Measures b : measuresList) {
            res += "<TR><TD>" + b.getId() + "</TD><TD>" + b.getDeviceName() + "</TD><TD>" + b.getTemperature() + "</TD></TR>";
        }
        res += "</TABLE></HTML>";
        return res;
    }




}
