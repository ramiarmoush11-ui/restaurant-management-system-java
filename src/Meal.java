import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Meal {
    String name;
    String ingredients;
    double price;
    int available;
    ImageIcon image;



    public Meal(String name, String ingredients, double price, int available,ImageIcon image) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.available = available;
        this.image=image;

    }
    public ImageIcon returnimagefood(){

        Image imagee = this.image.getImage();
        Image scaledImage = imagee.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        return scaledIcon;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "name='" + name + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", image=" + image +
                '}';
    }
}
