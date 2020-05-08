package models;

public class Measures {

    private int id;
    private int deviceId;
    private String deviceName;
    private float temperature;
    private float humidity;
    private int luminosity;


    public Measures(){
        id = 10;
        deviceId = 1;
        deviceName = "sensorVäxtHuset";
        temperature = 11.3f;
        humidity = 84.7f;
        luminosity = 99;
    }

    public Measures(int id, int deviceId, String deviceName, float temperature, float humidity, int luminosity){
        this.id = id;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.luminosity = luminosity;

    }

    public int getId(){return id;}
    public int getDeviceId(){return deviceId;}
    public String getDeviceName(){
        return deviceName;
    }

    public float getTemperature(){ return temperature; }
    public float getHumidity(){ return humidity; }
    public int getLuminosity(){ return luminosity; }



    public void setId(int id){ this.id = id; }
    public void setDeviceName(String deviceName){ this.deviceName = deviceName; }
    public void setTemperature(float temperature){this.temperature = temperature; }
    public void setHumidity(float humidity){this.humidity = humidity; }
    public void setLuminosity(int luminosity){this.luminosity = luminosity; }

}