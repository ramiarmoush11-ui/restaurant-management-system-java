        import javax.sound.sampled.AudioInputStream;
        import javax.sound.sampled.AudioSystem;
        import javax.sound.sampled.Clip;
        import javax.swing.*;
        import javax.swing.border.Border;
        import javax.swing.event.ChangeEvent;
        import javax.swing.event.ChangeListener;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.File;
        import java.util.ArrayList;
        import java.util.HashMap;

        public class frameMealCustomer extends JFrame implements ActionListener {
            HashMap<Meal, JSpinner > mealsordered = new HashMap<>();


            public int mealnumber = 2;
            JButton butorder;
            public static ArrayList<JSpinner> spinners = new ArrayList<>();
            JButton returnbu;
             String namecustomer;
             ArrayList<Order> orderList =new ArrayList<>();
            static ArrayList<ArrayList<Order>> theallorder=new ArrayList<>();

            public frameMealCustomer(String namecustomer) {
                this.namecustomer=namecustomer;
                this.setVisible(true);
                mealnumber = insertMeal.mealArray.size();
                //System.out.println(mealnumber);
                this.setSize(1300, 1000);
                this.setResizable(true);
                this.setTitle("R&M.resturant");
                this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                this.setLocationRelativeTo(null);
                this.setLayout(new BorderLayout());

                Border border1 = BorderFactory.createLineBorder(new Color(156,142,133), 7);


                JPanel mealPanel = new JPanel();
                mealPanel.setLayout(new BoxLayout(mealPanel, BoxLayout.X_AXIS));
                mealPanel.setBackground(new Color(244, 248, 243));

                JScrollPane scrollPane = new JScrollPane(mealPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setBorder(BorderFactory.createEmptyBorder());

                this.add(scrollPane, BorderLayout.CENTER);


                /*
                JPanel nullpanel = new JPanel();
                //nullpanel.setBorder(border1);
                nullpanel.setBounds(0, 0, 900, 800);
                nullpanel.setBackground(new Color(244,248,243));
                nullpanel.setLayout(new GridLayout(3, 3, 10, 10));
*/
                JPanel buttondown = new JPanel();
                Border border2 = BorderFactory.createLineBorder(new Color(156,142,133), 5);
                buttondown.setBackground(new Color(244,248,243));
                buttondown.setBounds(0, 800, 900, 70);
                buttondown.setBorder(border2);


                this.add(buttondown, BorderLayout.SOUTH);
                //this.add(nullpanel, BorderLayout.CENTER);

                butorder = new JButton("Order");
                butorder.setOpaque(true);
                butorder.setFocusable(true);
                butorder.setForeground(new Color(14,15,13));
                butorder.setBackground(new Color(244,248,243));
                butorder.setBorder(border2);
                butorder.setPreferredSize(new Dimension(200, 70));
                butorder.setFont(new Font("Serif", Font.BOLD, 25));
                butorder.addActionListener(this);

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
                buttondown.add(butorder, BorderLayout.EAST);



                for (int i = 0; i < mealnumber; i++) {
                    JPanel meal = new JPanel();
                    meal.setLayout(new BorderLayout());
                    meal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
                    meal.setBackground(new Color(244,248,243));

                    ImageIcon mealImage = new ImageIcon(insertMeal.mealArray.get(i).returnimagefood().getImage()
                            .getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                    JLabel imageLabel = new JLabel(mealImage);
                    imageLabel.setHorizontalAlignment(JLabel.CENTER);
                    meal.add(imageLabel, BorderLayout.NORTH);


                    JPanel textPanel = new JPanel();
                    textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                    textPanel.setBorder(border2);
                    textPanel.setBackground(Color.WHITE);


                    JLabel name = new JLabel(insertMeal.mealArray.get(i).name);
                    name.setFont(new Font("Serif", Font.BOLD, 50));
                    name.setAlignmentX(Component.CENTER_ALIGNMENT);

                    JLabel ingredients = new JLabel("<html>" + insertMeal.mealArray.get(i).ingredients + "</html>");
                    ingredients.setFont(new Font("Arial", Font.PLAIN, 35));
                    ingredients.setAlignmentX(Component.CENTER_ALIGNMENT);

                    JLabel price = new JLabel("Price: " + insertMeal.mealArray.get(i).price + " $");
                    price.setFont(new Font("Serif", Font.BOLD, 50));
                    price.setForeground(new Color(34, 139, 34));
                    price.setAlignmentX(Component.CENTER_ALIGNMENT);

                    textPanel.add(name);
                    textPanel.add(Box.createVerticalStrut(100));
                    textPanel.add(ingredients);
                    textPanel.add(Box.createVerticalStrut(130));
                    textPanel.add(price);



                    JSpinner Numberofmealsrequired = new JSpinner(new SpinnerNumberModel(0, 0, insertMeal.mealArray.get(i).available, 1));
                    Numberofmealsrequired.setPreferredSize(new Dimension(2, 50));
                    ((JSpinner.DefaultEditor) Numberofmealsrequired.getEditor()).getTextField().setEditable(false);

                    mealsordered.put(insertMeal.mealArray.get(i),Numberofmealsrequired);
                    meal.add(Numberofmealsrequired);

                    meal.add(imageLabel, BorderLayout.NORTH);
                    meal.add(textPanel, BorderLayout.CENTER);
                    meal.add(Numberofmealsrequired, BorderLayout.SOUTH);

                    mealPanel.add(meal);


                }
                if (mealnumber < 9) {
                    for (int i = 0; i < 9 - mealnumber; i++) {
                        JPanel panelback = new JPanel();
                        mealPanel.add(panelback);

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
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == returnbu) {
                    click("click.wav");
                    this.dispose();
                    new framecustomer();
                } else {
                    if (e.getSource() == butorder) {
                        boolean found = false;

                        click("click.wav");

                        for (Meal meal : mealsordered.keySet()) {
                            JSpinner s = mealsordered.get(meal);
                            int quantity = (int) s.getValue();
                            if (quantity > 0) {
                                found = true;
                                meal.available = meal.available - quantity;
                                double tip = (meal.price * quantity) * 2.5 / 100;
                                Order order = new Order(tip, meal.name, meal.price * quantity, quantity, namecustomer);
                                orderList.add(order);
                                //framecustomer.RegisterForAllOrderList.add(order);

                            }

                        }
                        if (found == false) {
                            JOptionPane.showMessageDialog(null, "You haven't chosen anything", "invalid order", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        theallorder.add(orderList);
                        int n = orderList.size();
                        this.dispose();
                        frameOrder framer = new frameOrder(n, orderList, namecustomer);
                    }

                }
            }
        }

