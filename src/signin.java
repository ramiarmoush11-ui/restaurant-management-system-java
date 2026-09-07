import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;



    public class signin extends JFrame implements ActionListener {
        static HashMap<String, String> employee = new HashMap<>();
        static HashMap<String, String> manger = new HashMap<>();

        static {
            manger.put("rami", "rami2006");
            manger.put("mohmadzn", "mohmad2005");
            employee.put("tarek", "tarek2004");
            employee.put("faisel", "faisel1997");
        }

        JButton loginbutton = new JButton("LOGIN");
        JTextField nameinsert;
        JTextField passwordinert;
        JButton returnbu;

        public signin() {
            this.setSize(806, 835);
            this.setResizable(false);
            this.setTitle("R&M.resturant");

            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setLayout(null);
            Border border1 = BorderFactory.createLineBorder(new Color(156,142,133), 12);
            this.getContentPane().setBackground(new Color(244,248,243));

            JPanel panel = new JPanel();
            panel.setBackground(new Color(244,248,243));
            panel.setBounds(0,0,800,800);
            panel.setBorder(border1);
            panel.setLayout(null);


            JLabel email = new JLabel("Name");
            email.setFont(new Font("Serif", Font.BOLD, 25));
            email.setForeground(new Color(14,15,13));

            nameinsert = new JTextField();

            JLabel password = new JLabel("Password");
            password.setFont(new Font("Serif", Font.BOLD, 23));


            passwordinert = new JTextField();



            ImageIcon b = new ImageIcon("back1.png");
            Image s2 = b.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon back = new ImageIcon(s2);
            returnbu =new JButton(back);
            returnbu.setBackground(new Color(244,248,243));
            returnbu.setBorderPainted(false);
            returnbu.setContentAreaFilled(false);
            returnbu.setFocusPainted(false);
            returnbu.setOpaque(false);
            returnbu.setBounds(50,630,100,100);
            returnbu.addActionListener(this);
            returnbu.setOpaque(true);

            Border border2 = BorderFactory.createLineBorder(new Color(156,142,133), 5);

            loginbutton.setBackground(new Color(244,248,243));
            loginbutton.setFocusable(true);
            loginbutton.setForeground(new Color(14,15,13));
            loginbutton.setFont(new Font("Arial", Font.BOLD, 30));
            loginbutton.setBorder(border2);



            loginbutton.addActionListener(this);
            email.setBounds(150, 200, 100, 30);
            nameinsert.setBounds(300, 200, 300, 30);
            password.setBounds(150, 300, 100, 30);
            passwordinert.setBounds(300, 300, 300, 30);
            loginbutton.setBounds(300, 400, 200, 60);
            panel.add(email);
            panel.add(nameinsert);
            panel.add(password);
            panel.add(passwordinert);
            panel.add(loginbutton);
            panel.add(returnbu);
            JLabel labelimage = new JLabel();
            ImageIcon icone = new ImageIcon("account-24.png");
            Image image = icone.getImage();
            Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            labelimage.setIcon(scaledIcon);
            labelimage.setBounds(350, 50, 100, 100);
            panel.add(labelimage);

            this.add(panel);

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
                new myframe();
                this.dispose();
            }
            if (e.getSource() == loginbutton) {
                click("click.wav");
                String emaile = nameinsert.getText();
                String passworde = passwordinert.getText();
                for (Map.Entry<String, String> r : manger.entrySet()) {
                    if (r.getKey().equals(emaile) && r.getValue().equals(passworde)) {
                        new frameemployee(true);
                        this.dispose();
                        return;
                    }

                }
                for (Map.Entry<String, String> r : employee.entrySet()) {
                    if (r.getKey().equals(emaile) && r.getValue().equals(passworde)) {
                        new frameemployee(false);
                        this.dispose();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Wrong password. Try again", "invalid login", JOptionPane.WARNING_MESSAGE);
                nameinsert.setText("");
                passwordinert.setText("");
            }
        }
    }