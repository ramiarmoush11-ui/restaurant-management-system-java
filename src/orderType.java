import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class orderType extends JFrame implements ActionListener {
    JButton inside;
    JButton delivery;
    JButton special;
    ArrayList<Order> orderList;
    double price=0;

    public orderType(ArrayList<Order> orderList) {
        this.orderList = orderList;
        for(Order t:orderList){
            price=price+t.price+t.tip;
        }
        this.setSize(650, 500);
        this.setResizable(false);
        this.setTitle("R&M.resturant");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(5, 0));

        JLabel question = new JLabel("Select the type of order:");
        question.setFont(new Font("Arial", Font.BOLD, 25));
        this.add(question);

        JLabel note = new JLabel("Note: Free delivery on the occasion of freedom ^_^ ");
        note.setFont(new Font("Arial", Font.PLAIN, 20));
        this.add(note);

        inside = new JButton("INSIDE");
        inside.setFont(new Font("Arial", Font.PLAIN, 20));
        inside.addActionListener(this);
        this.add(inside);

        delivery = new JButton("DELIVERY");
        delivery.setFont(new Font("Arial", Font.PLAIN, 20));
        delivery.addActionListener(this);
        this.add(delivery);

        special = new JButton("SPECIAL");
        special.setFont(new Font("Arial", Font.PLAIN, 20));
        special.addActionListener(this);
        this.add(special);


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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inside) {
            click("click.wav");
            for(int i=0;i<orderList.size();i++){
                orderList.get(i).typeoforder="inside";
            }
            new payment(price,orderList);
            dispose();

        }
        if(e.getSource()==delivery){
            click("click.wav");
            for(int i=0;i<orderList.size();i++){
                orderList.get(i).typeoforder="delivery";
            }
            new payment(price,orderList);
            dispose();

        }
        if(e.getSource()==special){
            click("click.wav");
            for(int i=0;i<orderList.size();i++){
                orderList.get(i).typeoforder="special";
            }
            new payment(price,orderList);
            dispose();

        }


    }
}
