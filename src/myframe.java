import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class myframe extends JFrame implements ActionListener {
    static {
        insertMeal.defaultmeals();
    }


    JButton button1;
    JButton button2;
    public myframe(){
        this.setSize(1950,1080);
        this.setResizable(true);
        this.setTitle("R&M.resturant");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        background bg=new background();
        bg.setBounds(0,0,1920,1080);
        background2 bg2=new background2();
        bg2.setBounds(300,0,200,200);


        button1=new JButton();
        button2=new JButton();
        button1.addActionListener(this);
        button2.addActionListener(this);

        button1.setBounds(1100,550,150,150);

        Image image1 = new ImageIcon("employee logo.png").getImage();
        Image scaledImage1 = image1.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        button1.setIcon(scaledIcon1);

        Image image = new ImageIcon("customer logo.png").getImage();
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        button2.setIcon(scaledIcon);
        button2.setBounds(600,550,150,150);

        this.add(button1);
        this.add(button2);

        //this.add(bg2);
        this.add(bg);




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
            new signin();
            this.dispose();
        }
        else if(e.getSource()==button2){
            click("click.wav");
            new framecustomer();
            this.dispose();
        }

    }
}
