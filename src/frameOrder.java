import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class frameOrder extends JFrame implements ActionListener {

    double total = 0.0;
    double endTotal=0.0;
    JButton cancel;
    JButton ok;
    ArrayList<Order> orderList;
    String namecustomer;

    public frameOrder(int n, ArrayList<Order> orderList,String namecustomer){
        this.namecustomer=namecustomer;
        this.orderList=orderList;
        this.setSize(900, 500);
        this.setResizable(false);
        this.setTitle("R&M.resturant");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        String columns[]={"name", "number", "porice", "tip", "total price"};
        Object[][] rows = new Object[n][5];
        for(int i=0 ; i<n ; i++){
            total=(orderList.get(i).tip)+(orderList.get(i).price);
            endTotal+=total;

            rows[i][0]=orderList.get(i).name;
            rows[i][1]=orderList.get(i).numberrequired;
            rows[i][2]=orderList.get(i).price;
            rows[i][3]=orderList.get(i).tip;
            rows[i][4]=total;
        }

        JTable mealtable = new JTable(rows,columns);
        mealtable.setFont(new Font("Arial",Font.PLAIN,16));
        mealtable.setRowHeight(40);
        mealtable.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
        mealtable.getTableHeader().setForeground(Color.BLACK);


        JScrollPane scrollPane = new JScrollPane(mealtable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,10));
        JLabel sum = new JLabel("TOTAL: "+endTotal+"$");
        sum.setFont(new Font("Arial", Font.BOLD, 18));
        totalPanel.add(sum, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
        cancel = new JButton("CANCEL");
        cancel.setFont(new Font("Arial",Font.BOLD,20));
        cancel.addActionListener(this);
        buttonPanel.add(cancel);

        ok = new JButton("OK");
        ok.setFont(new Font("Arial",Font.BOLD,20));
        ok.addActionListener(this);
        buttonPanel.add(ok);

        totalPanel.add(buttonPanel, BorderLayout.WEST);

        this.add(totalPanel,BorderLayout.SOUTH);


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
        if(e.getSource()==cancel){
            click("click.wav");
            for(Order temp:orderList){
                temp.status="Cancelled";
                temp.typeoforder="-----";
                temp.name="*"+temp.name;
            }
            for (Order o : orderList) {
                for (Meal m : insertMeal.mealArray) {
                    String tempe=o.name.substring(1);
                    if(tempe.equalsIgnoreCase(m.name.trim())) {
                        m.available += o.numberrequired;
                    }
                }
            }
            new frameMealCustomer(namecustomer);
            dispose();

        }
        if(e.getSource()==ok){
            click("click.wav");
            dispose();
            orderType ordertype = new orderType(orderList);
        }
    }
}
