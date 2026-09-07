import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class CreditCardPayment extends JFrame implements ActionListener {
    JTextField numberinsert;
    JButton paybutton;
    ArrayList<String> correctnumbers;
    ArrayList<Order> orderList;
    String formattedString;

    public CreditCardPayment(ArrayList<Order> orderList, String formattedString) {
        this.formattedString = formattedString;
        this.orderList = orderList;
        correctnumbers = new ArrayList<>();
        correctnumbers.add("5425233430109903");
        correctnumbers.add("2222420000001113");
        this.setSize(500, 300);
        this.setResizable(false);
        this.setTitle("Credit Card Payment");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel numberlabel = new JLabel("The Number of credit card:");
        numberlabel.setFont(new Font("Serif", Font.BOLD, 16));
        numberlabel.setBounds(50, 80, 250, 30);
        numberinsert = new JTextField();
        numberinsert.setFont(new Font("Serif", Font.PLAIN, 18));
        numberinsert.setBounds(250, 80, 200, 30);
        paybutton = new JButton("Submit");
        paybutton.setBackground(Color.BLUE);
        paybutton.setForeground(Color.WHITE);
        paybutton.setFont(new Font("Serif", Font.BOLD, 18));
        paybutton.setFocusable(false);
        paybutton.addActionListener(this);
        paybutton.setBounds(200, 130, 100, 40);
        panel.add(numberlabel);
        panel.add(numberinsert);
        panel.add(paybutton);
        this.add(panel, BorderLayout.CENTER);
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
        if (e.getSource() == paybutton) {
            click("click.wav");
            String number = numberinsert.getText();
            if (correctnumbers.contains(number)) {
                JOptionPane.showMessageDialog(null, "Paid with Credit Card " + formattedString + "\n" + "Your order has been successfully completed, your request is In preparation.");
                new updatestatusoforder(orderList);
                this.dispose();
                new myframe();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Card Number", "Invalid number", JOptionPane.WARNING_MESSAGE);
                numberinsert.setText("");
            }
        }
    }
}