package restServer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import models.Measures;
import models.MeasuresDao;
import models.Response;
import org.apache.commons.logging.Log;
import org.json.JSONArray;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class Controller {

    Measures measures = new Measures();
    Response resp = new Response();
    MeasuresDao measuresDao = new MeasuresDao();
    List<Measures> measuresList = measuresDao.getAllMeasures();

    @PostMapping("/measures/temperature")
    public Measures updateCurrentTemperature(@RequestBody Measures m){
        float temp = m.getTemperature();
        measures.setTemperature(temp);
        return measures;
    }
    @PostMapping("/measures/humidity")
    public Measures updateCurrentHumidity(@RequestBody Measures m){
        float hum = m.getHumidity();
        measures.setHumidity(hum);
        return measures;
    }
    @PostMapping("/measures/luminosity")
    public Measures updateCurrentLuminosity(@RequestBody Measures m){
        int lum = m.getLuminosity();
        measures.setLuminosity(lum);
        return measures;
    }



    @PostMapping("/measures/add")
    public void addCurrentMeasureValues(@RequestBody Measures m) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        Object jsonarray = parser.parse(new FileReader("src/main/java/repositories/AllMeasuresJSON.json"));
        ObjectMapper mapper2 = new ObjectMapper();
        String jsonFromFile = mapper2.writeValueAsString(jsonarray);

        JSONArray jsonArr = new JSONArray(jsonFromFile);
        int counter = 0;
        for (int i = 0; i < jsonArr.length(); i++) {

            counter++;
        }

        String created = m.getCreatedDateTime();
        measures.setCreatedDateTimee(created);
        Boolean manualPosting = m.getManualPosting();

        measures.setCreatedDateTimee(created);
        measures.setManualPosting(manualPosting);
        measures.setId(counter+1);

        List<Object> newMeasureList = new ArrayList<>();
        newMeasureList.add(measures);


        ObjectMapper mapper = new ObjectMapper();
        String jsonMeasures = mapper.writeValueAsString(newMeasureList);



        JsonNode tree1 = mapper.readTree(jsonFromFile);
        JsonNode tree2 = mapper.readTree(jsonMeasures);

        ((ArrayNode) tree1).addAll((ArrayNode) tree2);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tree1);



        try {
            FileWriter fileWriter = new FileWriter("src/main/java/repositories/AllMeasuresJSON.json");
            fileWriter.write(String.valueOf(json));
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
