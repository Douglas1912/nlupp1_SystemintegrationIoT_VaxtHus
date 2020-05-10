package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Measures {

    private int id;
    private int deviceId;
    private String deviceName;
    private float temperature;
    private float humidity;
    private int luminosity;
    private LocalDateTime created;
    private String createdDateTime;


    public Measures(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        id = 10;
        deviceId = 1;
        deviceName = "sensorVäxtHuset";
        temperature = 11.3f;
        humidity = 84.7f;
        luminosity = 99;
        createdDateTime = formattedDate;
    }

    public Measures(int id, int deviceId, String deviceName, float temperature, float humidity, int luminosity, String createdDateTime){
        this.id = id;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.luminosity = luminosity;
        this.createdDateTime = createdDateTime;

    }

    public int getId(){return id;}
    public int getDeviceId(){return deviceId;}
    public String getDeviceName(){
        return deviceName;
    }
    public LocalDateTime getCreated(){
        return created;
    }

    public float getTemperature(){ return temperature; }
    public float getHumidity(){ return humidity; }
    public int getLuminosity(){ return luminosity; }



    public void setId(int id){ this.id = id; }
    public void setDeviceName(String deviceName){ this.deviceName = deviceName; }
    public void setCreated(LocalDateTime created){this.created = created; }
    public void setTemperature(float temperature){this.temperature = temperature; }
    public void setHumidity(float humidity){this.humidity = humidity; }
    public void setLuminosity(int luminosity){this.luminosity = luminosity; }

}