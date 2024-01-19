package Model;

public class Fruit {
    private int FruitID;
    private String FruitName;
    private double FruitPrice;
    private int FruitQuantity;
    private String FruitOrigin;

    public Fruit(int fruitID, String fruitName, double fruitPrice, int fruitQuantity, String fruitOrigin) {
        FruitID = fruitID;
        FruitName = fruitName;
        FruitPrice = fruitPrice;
        FruitQuantity = fruitQuantity;
        FruitOrigin = fruitOrigin;
    }

    public int getFruitID() {
        return FruitID;
    }

    public void setFruitID(int fruitID) {
        FruitID = fruitID;
    }

    public String getFruitName() {
        return FruitName;
    }

    public void setFruitName(String fruitName) {
        FruitName = fruitName;
    }

    public double getFruitPrice() {
        return FruitPrice;
    }

    public void setFruitPrice(double fruitPrice) {
        FruitPrice = fruitPrice;
    }

    public int getFruitQuantity() {
        return FruitQuantity;
    }

    public void setFruitQuantity(int fruitQuantity) {
        FruitQuantity = fruitQuantity;
    }

    public String getFruitOrigin() {
        return FruitOrigin;
    }

    public void setFruitOrigin(String fruitOrigin) {
        FruitOrigin = fruitOrigin;
    }
}
