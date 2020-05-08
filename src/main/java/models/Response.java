package models;

public class Response {
    private String message;
    private Boolean status;
    private float electricityCost;
    private boolean manualLog;
    private int devicePower;

    public Response(){
        electricityCost = 1.3f;
        devicePower = 500;
    }

    public Response(String message, Boolean status){
        this.message = message;
        this.status = status;
    }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public boolean getManualLog(){ return manualLog;}
    public int getDevicePower(){return devicePower;}
    public float getElectricityCost(){return electricityCost;}
}