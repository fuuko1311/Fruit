package Controller;

import Model.Fruit;
import Model.Library;
import Model.Order;
import View.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class Programming extends Menu {

    static String[] mchoice = {"Create Fruit", "View orders", "Shopping (for buyer)", "Exit"};
    Library week3;
    ArrayList<Fruit> list_s;
    Hashtable<String, ArrayList<Order>> ht;

    public Programming() {
        super("\nFRUIT SHOP SYSTEM", mchoice);
        loadFromFile();
        week3 = new Library();
        list_s = new ArrayList<>();
    }
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("fruit.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(" | ");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                int quantity = Integer.parseInt(data[3]);
                String origin = data[4];
                list_s.add(new Fruit(id, name, price, quantity, origin));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("stu.csv"))) {
            for (Fruit fruit : list_s) {
                String line = String.format("%d,%s,%.2f,%d,%s",
                                            fruit.getFruitID(), fruit.getFruitName(),
                                            fruit.getFruitPrice(), fruit.getFruitQuantity(),
                                            fruit.getFruitOrigin());
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }


    public void execute(int n) {
        switch (n) {
            case 1:
                create();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                saveToFile();
                System.out.println("Bye~~~~");
                System.exit(0);
        }
    }

    public void create() {
        int id = week3.getInt("Enter Fruit ID: ", 1, 1000);


        if (!checkID(list_s, id)) {
            String name = week3.getString("Enter Fruit name: ");
            double price = week3.getDouble("Enter Price: ", 0.0, 10000.0);
            int quantity = week3.getInt("Enter Quantity: ", 0, 10);
            String origin = week3.getString("Enter Origin: ");
            list_s.add(new Fruit(id, name, price, quantity, origin));
        }
    }

    public void viewListOrder(){
        if(ht.isEmpty()){
            System.out.println("No Order");
            return;
        }
        for(String name: ht.keySet()){
            System.out.println("Customer: "+ name);
            ArrayList<Order> array_o = ht.get(name);
            displayListOrder(array_o);
        }
    }

    public void displayFruit(ArrayList<Fruit> list_s) {
        for (Fruit fruit : list_s) {
            System.out.println("%d\t|\t%s\t|\t%.2f\t|\t%d\t|\t%s");
        }
    }

    public ArrayList<Fruit> listById(ArrayList<Fruit> list_s, int id) {
        ArrayList<Fruit> list_Found = new ArrayList<Fruit>();

        for (Fruit fruit : list_s) {
            if (fruit.getFruitID() == id) {
                list_Found.add(fruit);
            }
        }
        return list_Found;
    }

    public boolean checkID(ArrayList<Fruit> list, int id) {
        if (list.isEmpty()) {
            return false;
        } else {
            for (Fruit fruit : list_s) {
                if (fruit.getFruitID() == id) {
                    return true;
                }
            }
        }
        return false;
    }
}
