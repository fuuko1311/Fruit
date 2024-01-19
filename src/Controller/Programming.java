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
        ht = new Hashtable<>();
    }
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("fruit.txt"))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("fruit.csv"))) {
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

    public void displayFruit() {
        for (Fruit fruit : list_s) {
            System.out.println("%d\t|\t%s\t|\t%.2f\t|\t%d\t|\t%s");
        }
    }

    public void execute(int n) {
        switch (n) {
            case 1:
                create();
                break;
            case 2:
                viewListOrder();
                break;
            case 3:
                shopping();
                break;
            case 4:
                saveToFile();
                System.out.println("Bye~~~~");
                System.exit(0);
        }
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

    private void displayListOrder(ArrayList<Order> list_o) {
        double total = 0;
        for (Order o : list_o) {
            System.out.println("Id: " + o.getOrderID() + " - Customer of name: " + o.getOrderName() +
                                " - Quantity: " + o.getOrderQuantity() + " - Price: " + o.getOrderPrice());
            total += o.getOrderPrice() * o.getOrderQuantity();
        }
        System.out.println("Total: "+ total);
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

    /*public int generateID() {
        int id = 0;
        if (list_s.size() == 0) {
            return 1;
        } else {

            for (Fruit fruit : list_s) {
                if (fruit.getFruitID() == list_s.size()) {
                    id = fruit.getFruitID() + 1;
                }
            }
        }
        return id;
    }*/

    public void shopping() {
        viewListOrder();
        displayFruit();
        String name = week3.getString("Enter fruit name: ");
        int quantityOrder = week3.getInt("Please input quantity", 1, 10);
        ArrayList<Order> list_o = new ArrayList<>();
        for (Fruit f : list_s) {
            if (name.equals(f.getFruitName())) {
                int id = f.getFruitID();
                double price = f.getFruitPrice();
                int quantity = f.getFruitQuantity();
                if(quantityOrder>quantity){
                    System.out.println("Quantity Order more than quantity");
                    shopping();
                }
                list_o.add(new Order(id, name, price, quantityOrder));
                displayListOrder(list_o);
                String customer = week3.getString("Enter Customer of name: ");
                ht.put(customer, list_o);
                System.out.println("Add Successfull");
            }
        }
    }


}
