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

public class Editmeal extends JFrame implements ActionListener {
    JButton enter;
    JTextField field1;
    JTextField field2;
    JTextField field3;
    JTextField field4;
    JButton returnbu;
    static int i;
    boolean access;


    public Editmeal(int i,boolean access) {
        this.access=access;
        this.i=i;
        this.setSize(500, 600);
        this.getContentPane().setBackground(new Color(244, 248, 243));
        this.setResizable(false);
        this.setTitle("R&M.resturant");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);



        JPanel editmeal = new JPanel();
        editmeal.setBorder(BorderFactory.createTitledBorder("Edit meal"));
        editmeal.setLayout(null);
        editmeal.setBounds(50, 30, 390, 400);
        this.add(editmeal);

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

        field1 = new JTextField(insertMeal.mealArray.get(i).name);
        field1.setBounds(140, 50, 130, 25);
        field2 = new JTextField( String.valueOf(insertMeal.mealArray.get(i).price));
        field2.setBounds(140, 90, 130, 25);
        field3 = new JTextField(insertMeal.mealArray.get(i).ingredients);
        field3.setBounds(140, 130, 200, 75);
        field4 = new JTextField(String.valueOf(insertMeal.mealArray.get(i).available));
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

        editmeal.add(name);
        editmeal.add(price);
        editmeal.add(ingredients);
        editmeal.add(available);
        editmeal.add(field1);
        editmeal.add(field2);
        editmeal.add(field3);
        editmeal.add(field4);
        editmeal.add(enter);


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
            try {
                insertMeal.mealArray.get(i).name=field1.getText();
                insertMeal.mealArray.get(i).price=Double.parseDouble(field2.getText());
                insertMeal.mealArray.get(i).ingredients=field3.getText();
                insertMeal.mealArray.get(i).available=Integer.parseInt(field4.getText());

                this.dispose();
                JOptionPane.showMessageDialog(null, "Meal edited successfully!");
                new frameMealEmployee(access);

            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for price and available!", "Input Error", JOptionPane.ERROR_MESSAGE);
                this.dispose();
                new frameMealEmployee(access);


            }
        }
        if (ev.getSource() == returnbu){
            click("click.wav");
            this.dispose();
            new frameMealEmployee(access);
        }
    }
}