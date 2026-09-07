import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class frameReport extends JFrame implements ActionListener {


    JButton returnbu;
    boolean access;



    public frameReport(boolean access){
        this.access=access;

        new Report();

        this.setSize(1000, 600);
        this.setResizable(false);
        this.setTitle("R&M.resturant");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        JLabel title = new JLabel("Reports", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setForeground(Color.BLACK);
        this.add(title, BorderLayout.NORTH);

        String[] columns ={"Report Type","Details"};
        Object[][] rows = new Object[4][2];
        rows[0][0]="Number of daily orders";
        rows[1][0]="The most ordered meal";
        rows[2][0]="Daily returns";
        rows[3][0]="Most visited customer to the restaurant";

        rows[0][1]=Report.NumberOfDailyOrders;
        rows[1][1]=Report.TheMostOrderedMeal;
        rows[2][1]= Report.DailyReturns;
        rows[3][1]=Report.MostVisitedCustomerToTheRestaurant;



        JTable reportTable = new JTable(rows,columns);
        reportTable.setFont(new Font("Arial",Font.PLAIN,25));
        reportTable.setRowHeight(50);
        reportTable.getTableHeader().setFont(new Font("Arial",Font.BOLD,30));
        reportTable.getTableHeader().setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(reportTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel PanelR = new JPanel();
        PanelR.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
        PanelR.setBackground(new Color(244,248,243));
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

        PanelR.add(returnbu, BorderLayout.WEST);
        this.add(PanelR,BorderLayout.SOUTH);


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
        if(e.getSource()==returnbu){
            click("click.wav");
            this.dispose();
            new frameemployee(access);
        }

    }
}
