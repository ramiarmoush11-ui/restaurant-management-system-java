import javax.swing.*;
import java.awt.*;

public class background extends JPanel {
    background() {
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = new ImageIcon("start.png").getImage();
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
