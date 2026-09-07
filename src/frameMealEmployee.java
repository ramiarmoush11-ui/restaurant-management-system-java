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

public class frameMealEmployee extends JFrame implements ActionListener {
   //static insertMeal insertmeall=new insertMeal(3);
   public int mealnumber=0;
   JButton insertmeal;
   public static ArrayList<JButton> remove=new ArrayList<>();
   JButton returnbu;
   public static  ArrayList<JButton> arrayeditbutton=new ArrayList<>();
    boolean access;

    public frameMealEmployee(boolean access){
        this.access=access;
        this.setVisible(true);
        mealnumber=insertMeal.mealArray.size();
        System.out.println(mealnumber);
        this.setSize(1300,1000);
        this.setResizable(true);
        this.setTitle("R&M.resturant");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        JPanel panelbb=new JPanel();
        panelbb.setBounds(0,0,900,800);
        panelbb.setLayout(new GridLayout(3, 3, 10, 10));

        JPanel buttondown = new JPanel();
        Border border2 = BorderFactory.createLineBorder(new Color(156,142,133), 5);
        buttondown.setBackground(new Color(244,248,243));
        buttondown.setBounds(0, 800, 900, 70);
        buttondown.setBorder(border2);
        this.add(buttondown,BorderLayout.SOUTH);
        this.add(panelbb,BorderLayout.CENTER);


        insertmeal =new JButton("Insert new meal");
        insertmeal.setOpaque(true);
        insertmeal.setFocusable(true);
        insertmeal.setForeground(new Color(14,15,13));
        insertmeal.setBackground(new Color(244,248,243));
        insertmeal.setPreferredSize(new Dimension(200, 70));
        insertmeal.setFont(new Font("Serif", Font.BOLD, 25));
        insertmeal.setBorder(border2);
        insertmeal.addActionListener(this);

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



        buttondown.setLayout(new FlowLayout(FlowLayout.LEFT, 300, 10));
        buttondown.add(returnbu, BorderLayout.WEST);
        buttondown.add(insertmeal, BorderLayout.EAST);





        for(int i=0 ;i<mealnumber ; i++) {
            JPanel meal = new JPanel();
            meal.setLayout(new GridLayout(7, 1, 3, 3));
            meal.setBackground(Color.LIGHT_GRAY);


            JLabel image = new JLabel(new ImageIcon(insertMeal.mealArray.get(i).returnimagefood().getImage()));

            meal.add(image);
            JLabel name = new JLabel(insertMeal.mealArray.get(i).name);
            name.setFont(new Font("Serif", Font.BOLD, 20));
            meal.add(name);
            JLabel ingredients = new JLabel(insertMeal.mealArray.get(i).ingredients);
            meal.add(ingredients);
            JLabel price = new JLabel("Price=" + String.valueOf(insertMeal.mealArray.get(i).price));
            price.setFont(new Font("Serif", Font.BOLD, 20));
            meal.add(price);
            JLabel available = new JLabel("Available=" + String.valueOf(insertMeal.mealArray.get(i).available));
            available.setFont(new Font("Serif", Font.BOLD, 20));
            meal.add(available);

            remove.add(i,new JButton("Remove"));




            remove.get(i).setEnabled(true);
            remove.get(i).setFont(new Font("Serif", Font.PLAIN, 20));
            remove.get(i).setFocusable(true);

            remove.get(i).setForeground(Color.RED);
            remove.get(i).setBackground(Color.WHITE);

            remove.get(i).addActionListener(this);
            JButton editbutton = new JButton();
            editbutton =new JButton("Edit");
            editbutton.setBackground(Color.WHITE);
            editbutton.setForeground(Color.RED);
            editbutton.setFont(new Font("Serif", Font.PLAIN, 20));
            editbutton.addActionListener(this);
            editbutton.setOpaque(true);
            arrayeditbutton.add(i,editbutton);
            meal.add(arrayeditbutton.get(i));
            meal.add(remove.get(i));

            panelbb.add(meal);

        }
        if(mealnumber<12) {
            for(int i=0 ; i<12-mealnumber ; i++) {
                JPanel panelback=new JPanel();
                panelbb.add(panelback);

            }
        }


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
public void actionPerformed(ActionEvent e){
    if(e.getSource()==insertmeal){
        click("click.wav");
        this.dispose();
        new insertMeal(access);

    }
    else if(e.getSource()==returnbu){
        click("click.wav");
        this.dispose();
        new frameemployee(access);

    }
    else{

        for(int i=0;i< remove.size();i++){
            if(e.getSource()==remove.get(i)){
                click("click.wav");
                insertMeal.mealArray.remove(i);
                this.dispose();
                new frameMealEmployee(access);
               break;
            }
        }
        for(int i=0;i<arrayeditbutton.size();i++){
            if(e.getSource()==arrayeditbutton.get(i)){
                click("click.wav");
                this.dispose();
                new Editmeal(i,access);
                //insertMeal.mealArray
            }
        }
        }
    }

}

