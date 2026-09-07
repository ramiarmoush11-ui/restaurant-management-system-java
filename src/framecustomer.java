import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class framecustomer extends JFrame implements ActionListener {

    JButton button1;
    JCheckBox checkbox;
    JTextField textField;
    JButton returnbu;
    public framecustomer(){
        this.setSize(806,835);
        this.setResizable(false);
        this.setTitle("R&M.resturant");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        JPanel panel1=new JPanel();
        panel1.setBackground(new Color(244,248,243));
        panel1.setBounds(0,0,800,800);
        Border border1 = BorderFactory.createLineBorder(new Color(156,142,133), 12);
        panel1.setBorder(border1);
        panel1.setLayout(null);

        JLabel label1 =new JLabel("Enter your name please");
        label1.setForeground(new Color(14,15,13));
        label1.setFont(new Font("Serif", Font.BOLD, 30));
        label1.setBackground(new Color(244,248,243));

        textField=new JTextField();
        textField.setFont(new Font("Serif", Font.PLAIN, 20));
        textField.setBackground(Color.white);
        textField.setForeground(new Color(14,15,13));

        Border border2 = BorderFactory.createLineBorder(new Color(156,142,133), 5);

        checkbox=new JCheckBox("Have you registered before");
        checkbox.setFocusable(true);
        checkbox.setForeground(Color.black);
        checkbox.setFont(new Font("Serif", Font.PLAIN, 20));
        checkbox.setFocusable(true);
        checkbox.addActionListener(this);

        button1 =new JButton("Enter");
        button1.setForeground(new Color(14,15,13));
        button1.setFont(new Font("Serif", Font.BOLD, 35));
        button1.setBackground(new Color(244,248,243));
        button1.setBorder(border2);
        button1.setFocusable(true);
        button1.addActionListener(this);

        button1.setBounds(250, 500, 300, 70);
        checkbox.setBounds(250, 250, 300, 40);
        textField.setBounds(250, 340, 300, 40);
        label1.setBounds(250, 290, 300, 40);


        panel1.add(button1);
        //panel1.add(checkbox);
        panel1.add(textField);
        panel1.add(label1);
        ImageIcon b = new ImageIcon("back1.png");
        Image s2 = b.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon back = new ImageIcon(s2);
        returnbu =new JButton(back);
        returnbu.setBackground(new Color(244,248,243));
        returnbu.setBorderPainted(false);
        returnbu.setContentAreaFilled(false);
        returnbu.setFocusPainted(false);
        returnbu.setOpaque(false);
        returnbu.setBounds(50,670,100,100);
        returnbu.addActionListener(this);
        returnbu.setOpaque(true);
        panel1.add(returnbu);

        this.add(panel1);





        revalidate();
        repaint();
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
        if(e.getSource()==button1){
            click("click.wav");
            String name=textField.getText();
            if(name.equals("")){
                JOptionPane.showMessageDialog(null,"You haven't entered anything","invalid name ",JOptionPane.WARNING_MESSAGE);
                return ;
            }
            this.dispose();
            new frameMealCustomer(name);

        }
        if(e.getSource()==returnbu){
            click("click.wav");
            this.dispose();
            new myframe();

        }

    }
}
