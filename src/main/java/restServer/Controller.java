package restServer;

import com.google.gson.Gson;
import models.Measures;
import models.MeasuresDao;
import models.Response;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
    public void addCurrentMeasureValues(@RequestBody Measures m){

        measuresList.add(m);

    }

    @RequestMapping(value = "/measures", headers = "Accept=application/json")
    public List<Measures> index() {
        return measuresList;
    }


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
            writer.append(json);

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
