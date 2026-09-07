import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class insertMeal extends JFrame implements ActionListener {
    JButton enter;
    JTextField field1;
    JTextField field2;
    JTextField field3;
    JTextField field4;
    JButton returnbu;
    public static ArrayList<Meal> mealArray=new ArrayList<>();
    static ImageIcon defaultimage =new ImageIcon("ashimage.png");
    boolean access;


public static void defaultmeals(){



    ImageIcon image1 = new ImageIcon("Hamburger.jpg");
    ImageIcon image2 = new ImageIcon("shawrma.png");
    ImageIcon image3 = new ImageIcon("pizza.jpg");
    ImageIcon image4 = new ImageIcon("Crispy.png");

    Meal m1 = new Meal("HameBurger","meat + bread + tometo + ketchup ",1.5,5,image1);
    mealArray.add(m1);
    Meal m2 = new Meal("Shawrma","chicken + mayonnaise + pickles + bread",2,5,image2);
    mealArray.add(m2);
    Meal m3 = new Meal("Pizza","vegetables + dough + ketchup + cheese",4,5,image3);
    mealArray.add(m3);
    Meal m4 = new Meal("Crispy","chicken + Bread + Lettuce + French Fries",2,5,image4);
    mealArray.add(m4);
}
    public insertMeal(boolean access) {
        this.access=access;
        this.setSize(500, 600);
        this.getContentPane().setBackground(new Color(244, 248, 243));
        this.setResizable(false);
        this.setTitle("R&M.resturant");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);



        JPanel addmeal = new JPanel();
        addmeal.setBorder(BorderFactory.createTitledBorder("Add Meal"));
        addmeal.setLayout(null);
        addmeal.setBounds(50, 30, 390, 400);
        this.add(addmeal);

        JLabel name = new JLabel("Name");
        name.setBounds(30, 50, 100, 25);
        name.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel price = new JLabel("Price");
        price.setBounds(30, 90, 100, 25);
        price.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel ingredients = new JLabel("Ingredients");
        ingredients.setBounds(30, 130, 100, 25);
        ingredients.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel available = new JLabel("Available");
        available.setBounds(30, 220, 100, 25);
        available.setFont(new Font("Serif", Font.BOLD, 20));

        field1 = new JTextField();
        field1.setBounds(140, 50, 130, 25);
        field2 = new JTextField();
        field2.setBounds(140, 90, 130, 25);
        field3 = new JTextField();
        field3.setBounds(140, 130, 200, 75);
        field4 = new JTextField();
        field4.setBounds(140, 220, 130, 25);

        enter = new JButton("Enter");
        enter.setBounds(77, 310, 250, 50);
        enter.setFont(new Font("Serif", Font.PLAIN, 20));
        enter.addActionListener(this);

        ImageIcon b = new ImageIcon("back1.png");
        Image s2 = b.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon back = new ImageIcon(s2);
        returnbu =new JButton(back);
        returnbu.setBackground(new Color(244,248,243));
        returnbu.setBorderPainted(false);
        returnbu.setContentAreaFilled(false);
        returnbu.setFocusPainted(false);
        returnbu.setOpaque(false);
        returnbu.setBounds(30,450,100,100);
        returnbu.addActionListener(this);
        returnbu.setOpaque(true);
        this.add(returnbu);

        addmeal.add(name);
        addmeal.add(price);
        addmeal.add(ingredients);
        addmeal.add(available);
        addmeal.add(field1);
        addmeal.add(field2);
        addmeal.add(field3);
        addmeal.add(field4);
        addmeal.add(enter);


        this.revalidate();
        this.repaint();
        this.setVisible(true);


    }

    public void click(String s) {
        try {
            File sound = new File(s);
            AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == enter) {
            click("click.wav");
            if(field1.getText().isEmpty() || field2.getText().isEmpty() || field3.getText().isEmpty() || field4.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter the information!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    String name = field1.getText().trim();
                    String ingredients = field3.getText();
                    double price = Double.parseDouble(field2.getText());
                    int available = Integer.parseInt(field4.getText());
                    Meal m = new Meal(name, ingredients, price, available, defaultimage);
                    mealArray.add(m);

                    this.dispose();
                    JOptionPane.showMessageDialog(null, "Meal added successfully!");
                    new frameMealEmployee(access);

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for price and available!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    this.dispose();
                    new frameMealEmployee(access);

                }
            }
        }
        if (ev.getSource() == returnbu){
            click("click.wav");
            this.dispose();
            new frameMealEmployee(access);
        }
    }
}