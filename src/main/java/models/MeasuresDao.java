package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MeasuresDao {
    public List<Measures> getAllMeasures(){

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        List<Measures> MeasuresList = new ArrayList<>();
        Measures m1 = new Measures(1, 1, "sensorVäxtHuset", 8.3f, 44.5f, 70, "01-05-2020 18:00:00");
        Measures m2 = new Measures(2, 1, "sensorVäxtHuset", 5.1f, 40.5f, 72, "02-05-2020 18:30:01");
        Measures m3 = new Measures(3, 1, "sensorVäxtHuset", 4.0f, 50.2f, 75, "03-05-2020 17:05:05");
        Measures m4 = new Measures(4, 1, "sensorVäxtHuset", 10.6f, 55.8f, 85, "04-05-2020 17:10:15");
        Measures m5 = new Measures(5, 1, "sensorVäxtHuset", 15.5f, 75.2f, 90, "05-05-2020 18:34:28");
        Measures m6 = new Measures(6, 1, "sensorVäxtHuset", 19.9f, 86.5f, 100, "06-05-2020 16:30:30");
        Measures m7 = new Measures(7, 1, "sensorVäxtHuset", 2.3f, 20.4f, 29, "07-05-2020 20:35:30");
        Measures m8 = new Measures(8, 1, "sensorVäxtHuset", 6.4f, 25.3f, 41, "08-05-2020 21:30:10");


        MeasuresList.add(m1);
        MeasuresList.add(m2);
        MeasuresList.add(m3);
        MeasuresList.add(m4);
        MeasuresList.add(m5);
        MeasuresList.add(m6);
        MeasuresList.add(m7);
        MeasuresList.add(m8);
       // MeasuresList.clear();

        return MeasuresList;

    }
}
