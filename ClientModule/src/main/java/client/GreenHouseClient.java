package client;


import models.*;

import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GreenHouseClient {
    static List<Measures> MeasuresList = new ArrayList<>();


    public static void logAllCurrentValues() {
        final String uri = "http://localhost:8080/measures/add";
        Measures measures = new Measures();
        RestTemplate restTemplate = new RestTemplate();
        Measures m = restTemplate.postForObject(uri, measures.getTemperature(), Measures.class);
        System.out.println("\nLogged Current temp/hum/lum right now");
    }

    public static Measures getCurrentValueReport(){
        final String uri = "http://localhost:8080/currentMeasuresJSON";
        RestTemplate restTemplate = new RestTemplate();
        Measures result = restTemplate.getForObject(uri, Measures.class);
        System.out.println("\nOverall report on hum/tem/lum right now");
        System.out.println("DeviceId: " + result.getDeviceId() + " [" +
                result.getDeviceName() + "] Temperature = " + result.getTemperature() + "°C Humidity = " + result.getHumidity() + "% Luminocity = " +
                result.getLuminosity()+" L⊙");
        return result;
    }
    public static void updateCurrentTempValue(float temp){
        final String uri = "http://localhost:8080/measures/temperature";
        Measures m = new Measures();
        RestTemplate restTemplate = new RestTemplate();
        m.setTemperature(temp);
        m = restTemplate.postForObject(uri, m, Measures.class);

        MeasuresList.add(m);
        System.out.println("\nUpdated Temperature value" );

    }
    public static void updateCurrentHumValue(float hum){
        final String uri = "http://localhost:8080/measures/humidity";
        Measures m = new Measures();
        RestTemplate restTemplate = new RestTemplate();
        m.setHumidity(hum);
        m = restTemplate.postForObject(uri, m, Measures.class);
        System.out.println("\nUpdated Humidity value" );

    }
    public static void updateCurrentLumValue(int lum){
        final String uri = "http://localhost:8080/measures/luminosity";
        Measures m = new Measures();
        RestTemplate restTemplate = new RestTemplate();
        m.setLuminosity(lum);
        m = restTemplate.postForObject(uri, m, Measures.class);
        System.out.println("\nUpdated Luminosity value" );

    }
    public static void getCurrentTemperature(){
        final String uri = "http://localhost:8080/currentMeasuresJSON";
        RestTemplate restTemplate = new RestTemplate();
        Measures result = restTemplate.getForObject(uri, Measures.class);
        System.out.println("\nCurrent Temperature value = "+result.getTemperature()+" °C");
    }
    public static void getCurrentHumidity(){
        final String uri = "http://localhost:8080/currentMeasuresJSON";
        RestTemplate restTemplate = new RestTemplate();
        Measures result = restTemplate.getForObject(uri, Measures.class);
        System.out.println("\nCurrent Humidity value = "+result.getHumidity()+" %");
    }
    public static void getCurrentLuminosity(){
        final String uri = "http://localhost:8080/currentMeasuresJSON";
        RestTemplate restTemplate = new RestTemplate();
        Measures result = restTemplate.getForObject(uri, Measures.class);
        System.out.println("\nCurrent Luminosity value = "+result.getLuminosity()+" L⊙");
    }


    private static void getLastWeekTemperature(){
        float average = 0;
        final String uri = "http://localhost:8080/measures";
        RestTemplate restTemplate = new RestTemplate();
        Measures[] resultArray = restTemplate.getForObject(uri, Measures[].class);
        List<Measures> result = Arrays.asList(resultArray);

        System.out.println("\nTemperature , day by day, last week and average for the week");
        for(Measures b : result) {
            System.out.println("Id: "+b.getId()+". Temperature = "+b.getTemperature()+" °C");
            average += b.getTemperature();
        }
        System.out.print("Average Temperature for the week = ");
        System.out.printf("%.1f",  + average/8);
        System.out.println(" °C");
    }
    private static void getLastWeekHumidity(){
        float average = 0;
        final String uri = "http://localhost:8080/measures";
        RestTemplate restTemplate = new RestTemplate();
        Measures[] resultArray = restTemplate.getForObject(uri, Measures[].class);
        List<Measures> result = Arrays.asList(resultArray);

        System.out.println("\nHumidity , day by day, last week and average for the week");
        for(Measures b : result) {
            System.out.println("Id: "+b.getId()+". Temperature = "+b.getHumidity()+" %");
            average += b.getHumidity();
        }
        System.out.print("Average Humidity for the week = ");
        System.out.printf("%.1f",  + average/8);
        System.out.println(" %");
    }
    private static void getLastWeekLuminosity(){
        float average = 0;
        final String uri = "http://localhost:8080/measures";
        RestTemplate restTemplate = new RestTemplate();
        Measures[] resultArray = restTemplate.getForObject(uri, Measures[].class);
        List<Measures> result = Arrays.asList(resultArray);

        System.out.println("\nLuminosity , day by day, last week and average for the week");
        for(Measures b : result) {
            System.out.println("Id: "+b.getId()+". Luminosity = "+b.getLuminosity()+" L⊙");
            average += b.getLuminosity();
        }
        System.out.print("Average Luminosity for the week = ");
        System.out.printf("%.1f",  + average/8);
        System.out.println(" L⊙");
    }





    public static void Temperature() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        float temp;

        while (true) {
            System.out.println("\n\t--- TEMPERATURE ---\n" +
                    "\t1. Set new Temperature value.\n" +
                    "\t2. Get Temperature right now.\n" +
                    "\t3. Report on the Temperature , day by day, last week and average..\n" +
                    "\t4. Return.\n");

            System.out.print("Make a Choice > ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Input new Temperature value: ");
                    temp = input.nextFloat();
                    updateCurrentTempValue(temp);
                    break;
                case 2:
                    getCurrentTemperature();
                    break;
                case 3:
                    getLastWeekTemperature();
                    break;
                case 4:
                    return;
            }
        }
    }
    public static void Humidity() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        float hum;

        while (true) {
            System.out.println("\n\t--- HUMIDITY ---\n" +
                    "\t1. Set new Humidity value.\n" +
                    "\t2. Get Humidity right now.\n" +
                    "\t3. Report on the Humidity , day by day, last week and average..\n" +
                    "\t4. Return.\n");

            System.out.print("Make a Choice > ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Input new Humidity value: ");
                    hum = input.nextFloat();
                    updateCurrentHumValue(hum);
                    break;
                case 2:
                    getCurrentHumidity();
                    break;
                case 3:
                    getLastWeekHumidity();
                    break;
                case 4:
                    return;


            }
        }
    }
    public static void Luminosity() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        int lum;

        while (true) {
            System.out.println("\n\t--- LUMINOSITY ---\n" +
                    "\t1. Set new Luminosity value.\n" +
                    "\t2. Get Luminosity right now.\n" +
                    "\t3. Report on the Luminosity , day by day, last week and average..\n" +
                    "\t4. Return.\n");

            System.out.print("Make a Choice > ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Input new Luminosity value: ");
                    lum = input.nextInt();
                    updateCurrentLumValue(lum);
                    break;
                case 2:
                    getCurrentLuminosity();
                    break;
                case 3:
                    getLastWeekLuminosity();
                    break;
                case 4:
                    return;
            }
        }
    }


    public static void getDailyElectricityConsumption() {
        final String uri = "http://localhost:8080/currentResponseJSON";
        RestTemplate restTemplate = new RestTemplate();
        Response result = restTemplate.getForObject(uri, Response.class);

        float devicePower = result.getDevicePower();
        float electricityCost = result.getElectricityCost();
        float sum = devicePower/1000 * 24 * electricityCost;


        System.out.printf("\nElectricityConsumption for a day costs = %.2f ", sum);
        System.out.print("kr\n");
    }

    public static void calculateElctricityCost(float cost) {
        final String uri = "http://localhost:8080/currentResponseJSON";
        RestTemplate restTemplate = new RestTemplate();
        Response result = restTemplate.getForObject(uri, Response.class);

        float devicePower = result.getDevicePower();
        float sum = devicePower/1000 * (24*7) * cost;


        System.out.printf("\nElectricityConsumption for last week costs = %.2f ", sum);
        System.out.print("kr\n");
    }

    public static void ElectricityConsumption() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        float cost = 0;

        while (true) {
            System.out.println("\n\t--- ELECTRICITY CONSUMPTION ---\n" +
                    "\t1. Get Electricity consumption for the past 24 hours .\n" +
                    "\t2. Calculate electricity cost (SEK / kWh) .\n" +
                    "\t3. Return.\n");

            System.out.print("Make a Choice > ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    getDailyElectricityConsumption();
                    break;
                case 2:
                    System.out.print("Input cost (SEK / kWh) > ");
                    cost = input.nextFloat();
                    calculateElctricityCost(cost);
                    break;
                case 3:
                    return;
            }
        }
    }


    public static void mainMenu() throws InterruptedException {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        while (true){
            System.out.println("\n\t*** Main Menu ***\n" +
                    "\t1. Temperature.\n" +
                    "\t2. Humidity.\n" +
                    "\t3. Luminosity.\n" +
                    "\t4. Electricity consumption.\n" +
                    "\t5. Overall report on hum/tem/lum right now.\n"+
                    "\t6. log all current parameters right now. \n"+
                    "\t7. SET NEW DEFAULT log values (temp/hum/lum) . \n"+
                    "\t8. EXIT. \n");

            System.out.print("Make a Choice > ");
            choice = input.nextInt();
            switch(choice) {
                case 1:
                    Temperature();
                    break;
                case 2:
                    Humidity();
                    break;
                case 3:
                    Luminosity();
                    break;
                case 4:
                    ElectricityConsumption();
                    break;
                case 5:
                    getCurrentValueReport();
                    break;
                case 6:
                    logAllCurrentValues();
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("Logging out..... Bye");
                    return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        mainMenu();
    }
}
