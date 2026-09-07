import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Report {
    static int NumberOfDailyOrders=0;
    static String TheMostOrderedMeal="No orders yet";
    static double DailyReturns=0.0;
    static String MostVisitedCustomerToTheRestaurant="No customers yet";

    HashMap<String , Integer> mostMeal = new HashMap<>();
    HashMap<String , Integer> mostCustomer = new HashMap<>();

    public Report() {
        NumberOfDailyOrders=0;
        DailyReturns=0;
        readOrdersFromFile();
        searchMostMeal();
        searchMostCustomer();
    }

    private void readOrdersFromFile() {
        try (FileReader f = new FileReader("register.txt");
             BufferedReader r = new BufferedReader(f)) {
            String l;
                while ((l = r.readLine()) != null) {
                    if (l.startsWith("Order{")) {
                        extractInformation(l);
                    }
                }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    private void extractInformation(String l){
        try {
            String[] part = l.split(",");
            String name = part[0].split("=")[1].replace("'", "").replace("}", "").trim();
            String meal = part[1].split("=")[1].replace("'", "").replace("}", "").trim();
            double price = Double.parseDouble(part[2].split("=")[1].replace("'", "").replace("}", "").trim());
            int number = Integer.parseInt(part[3].split("=")[1].replace("'", "").replace("}", "").trim());
            if(meal.startsWith("*")){
                return ;
            }
            mostMeal.put(meal, mostMeal.getOrDefault(meal, 0) + number);
            mostCustomer.put(name, mostCustomer.getOrDefault(name, 0) + 1);
            DailyReturns += price;
            NumberOfDailyOrders++;
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    private void searchMostMeal(){
    int max=0;
    String maxKey="null";

        for (String key : mostMeal.keySet()) {
            int value = mostMeal.get(key);
            if (value>max) {
                max = value;
                maxKey = key;
            }
        }
        TheMostOrderedMeal = maxKey;
    }

    private void searchMostCustomer(){
    int max=0;
        String maxKey="null";

        for (String key : mostCustomer.keySet()) {
            int value = mostCustomer.get(key);
            if (value>max) {
                max = value;
                maxKey = key;
            }
        }
        MostVisitedCustomerToTheRestaurant = maxKey;
    }
}
