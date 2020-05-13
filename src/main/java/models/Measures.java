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
    private LocalDateTime dateTime;
    private String createdDateTime;
    private Boolean manualPosting;


    public Measures(){
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        deviceId = 1;
        deviceName = "sensorVäxtHuset";
        createdDateTime = formattedDate;
        manualPosting = true;
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
    public String getCreatedDateTime(){ return createdDateTime; }
    public Boolean getManualPosting(){ return manualPosting; }


    public float getTemperature(){ return temperature; }
    public float getHumidity(){ return humidity; }
    public int getLuminosity(){ return luminosity; }



    public void setId(int id){ this.id = id; }
    public void setDeviceName(String deviceName){ this.deviceName = deviceName; }
    public void setCreatedDateTime(String createdDateTime){this.createdDateTime = createdDateTime; }
    public void setTemperature(float temperature){this.temperature = temperature; }
    public void setHumidity(float humidity){this.humidity = humidity; }
    public void setLuminosity(int luminosity){this.luminosity = luminosity; }
    public void setManualPosting(Boolean manualPosting){this.manualPosting = manualPosting; }

}