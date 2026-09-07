import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
public class framecurrentmeal extends JFrame implements ActionListener
{
    JButton returnbu;
    boolean access;


    public framecurrentmeal(boolean access)
{
    this.access=access;
    this.setSize(1000, 600);
    this.setResizable(false);
    this.setTitle("R&M.resturant");
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setLayout(new BorderLayout());

    int n = 0;
    for (int i = 0; i < frameMealCustomer.theallorder.size(); i++)
    { for (int j = 0; j < frameMealCustomer.theallorder.get(i).size(); j++) { n++; } }
    String columns[] = {"customer", "mealname", "number", "total price", "typeoforder", "status"};
    Object[][] rows = new Object[n][6];
    int t=0;//index from zero to (n)
    for (int i = 0; i < frameMealCustomer.theallorder.size(); i++)
    { ArrayList<Order> temp = frameMealCustomer.theallorder.get(i);
        for (int j = 0; j < temp.size(); j++)
        { rows[t][0] = temp.get(j).namecustomer;
            rows[t][1] = temp.get(j).name;
            rows[t][2] = temp.get(j).numberrequired;
            rows[t][3] = temp.get(j).price;
            rows[t][4] = temp.get(j).typeoforder;
            rows[t][5] = temp.get(j).status;
            t++;
        }
    }

    JTable mealtable = new JTable(rows, columns);
    mealtable.setFont(new Font("Arial", Font.PLAIN, 16));
    mealtable.setRowHeight(40);
    mealtable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
    mealtable.getTableHeader().setForeground(Color.BLACK);

    JScrollPane scrollPane = new JScrollPane(mealtable);
    this.add(scrollPane, BorderLayout.CENTER);

    JPanel Panel = new JPanel();
    Panel.setBackground(new Color(244,248,243) );

    Panel.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));

    ImageIcon b = new ImageIcon("back1.png");
    Image s2 = b.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon back = new ImageIcon(s2);
    returnbu =new JButton(back);
    returnbu.setBackground(new Color(244,248,243));
    returnbu.setBorderPainted(false);
    returnbu.setContentAreaFilled(false);
    returnbu.setFocusPainted(false);
    returnbu.setOpaque(false);
    returnbu.setBounds(50,670,50,50);
    returnbu.addActionListener(this);
    returnbu.setOpaque(true);

    Panel.add(returnbu, BorderLayout.WEST);

    this.add(Panel,BorderLayout.SOUTH);
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


@Override public void actionPerformed(ActionEvent e)
{ if(e.getSource()==returnbu){
    click("click.wav");
    this.dispose();
    new frameemployee(access); }

}
   /* public static void closeforupdatethestatus(){
frame.dispose();
new framereport();
    }*/

}
