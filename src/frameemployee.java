import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class frameemployee extends JFrame implements ActionListener {
    JButton button1;
    JButton button2;
    JButton button3;
    JButton returnbu;
    boolean access;

    public frameemployee(boolean access){
        this.access=access;
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

        ImageIcon t = new ImageIcon("title e.png");
        JLabel titleE = new JLabel(t);
        titleE.setBounds(200, 35, 400, 205);


        JPanel panel2=new JPanel();
        panel2.setBounds(200,180,400,400);
        button1 =new JButton("EDIT");
        button2 =new JButton("Current Meals");
        button3 =new JButton("REPORT");
        button1.setFocusable(true);
        button2.setFocusable(true);
        button3.setFocusable(true);

        button1.setFont(new Font("Serif", Font.BOLD, 30));
        button2.setFont(new Font("Serif", Font.BOLD, 30));
        button3.setFont(new Font("Serif", Font.BOLD, 30));

        Border border2 = BorderFactory.createLineBorder(new Color(156,142,133), 10);

        button1.setBackground(new Color(244,248,243));
        button1.setBorder(border2);
        button2.setBackground(new Color(244,248,243));
        button2.setBorder(border2);
        button3.setBackground(new Color(244,248,243));
        button3.setBorder(border2);

        button1.setForeground(new Color(14,15,13));
        button2.setForeground(new Color(14,15,13));
        button3.setForeground(new Color(14,15,13));

        button1.setFocusPainted(false);
        button2.setFocusPainted(false);
        button3.setFocusPainted(false);

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);

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


        panel2.setLayout(new GridLayout(3,1,10,10));
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        //panel1.add(titleE);
        panel1.add(panel2);
        panel1.add(returnbu);



        this.add(panel1);


        revalidate();
        repaint();
        this.setVisible(true);
        if(access==false){
            button1.setEnabled(false);
            button3.setEnabled(false);
        }
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
        if(e.getSource()==returnbu){
            click("click.wav");
            new myframe();
            this.dispose();
        }
        if(e.getSource()==button1){
            click("click.wav");
            new frameMealEmployee(access);
            this.dispose();
        }
        if(e.getSource()==button2){
            click("click.wav");
            this.dispose();
            new framecurrentmeal(access);
        }
        if(e.getSource()==button3){
            click("click.wav");
            this.dispose();
            new frameReport(access);

        }

    }
}
