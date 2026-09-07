import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;


    public class payment extends JFrame implements ActionListener {
        private JTextField amountField;
        private JButton cashButton;
        private JButton creditCardButton;
        double amount;
        ArrayList<Order> orderList;
        public payment(double amount, ArrayList<Order> orderList) {
            this.orderList=orderList;
            this.amount=amount;
            this.setSize(400, 400);
            this.setResizable(false);
            this.setTitle("payment system");
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setLayout(new BorderLayout());

            JPanel panel = new JPanel();
            panel.setLayout(null);
            String formattedString = String.format("%.02f", amount);
            JLabel amountLabel = new JLabel("Payment Amount: "+formattedString+" $");
            amountLabel.setFont(new Font("Serif", Font.BOLD, 20));
            cashButton = new JButton("Pay with Cash");
            cashButton.setFont(new Font("Serif", Font.BOLD, 15));
            cashButton.setFocusable(true);
            cashButton.setBackground(new Color(173, 216, 230));
            cashButton.setFocusable(false);
            cashButton.addActionListener(this);



            creditCardButton = new JButton("Pay with Credit Card");
            creditCardButton.setBackground(new Color(173, 216, 230));
            creditCardButton.setFont(new Font("Serif", Font.BOLD, 15));
            creditCardButton.setFocusable(false);
            creditCardButton.addActionListener(this);
            creditCardButton.setFocusable(true);
            cashButton.setFocusable(true);
            creditCardButton.setBounds(100, 250, 200, 40);
            cashButton.setBounds(100, 150, 200, 40);
            amountLabel.setBounds(100, 50, 300, 30);

            panel.add(creditCardButton);
            panel.add(cashButton);
            panel.add(amountLabel);


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
            String formattedString = String.format("%.02f", amount);
            if (e.getSource() == cashButton) {
                click("click.wav");
                JOptionPane.showMessageDialog(null, "Paid with Cash " + formattedString+"\n"+"Your order has been successfully completed, your request is In preparation.");

                new updatestatusoforder(orderList);
                this.dispose();
                new myframe();
            } else if (e.getSource() == creditCardButton) {
                click("click.wav");
                new CreditCardPayment(orderList,formattedString);
                this.dispose();



            }
        }

    }

