    import javax.swing.*;
    import java.awt.*;
    import java.io.BufferedWriter;
    import java.io.File;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;

    public class updatestatusoforder extends Thread {



        ArrayList<Order> orderList;

        public updatestatusoforder(ArrayList<Order> orderList) {
            this.orderList = orderList;
            writeOrdersToFile(orderList);
            this.start();
        }

        private void writeOrdersToFile(ArrayList<Order> orderList) {
            try (FileWriter f = new FileWriter("register.txt",true);
                 BufferedWriter w = new BufferedWriter(f)) {

                for (Order order : orderList) {
                    System.out.println(order);

                    w.write(order.toString());
                    w.newLine();

                }
                w.write("--------------------");
                w.newLine();

            } catch (IOException e) {
                System.out.println(e);
            }
        }

        public void run(){
            try{
                Thread.sleep(10000);
                //framereport.closeforupdatethestatus();
                for(int i=0;i<orderList.size();i++){
                    orderList.get(i).status="Delivered";
                }
                JOptionPane.showMessageDialog(null, "Thank you for your order! We are pleased to inform you that your order has been successfully delivered.");

            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
