import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;

public class dynamicGUI extends JFrame {

    DynamicBackpack dynamic;
    private Image[] images =  new Image[8];

    private JPanel panel5 = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int fontSize = 14;
            Font f = new Font("Arial", Font.BOLD, fontSize);
            g2d.setFont(f);
            g2d.drawString("Dynamic backpack matrix", 300, 20);
            g2d.drawLine(0, 25, 800, 25);
            int yincrement = 25;
            int xincrement;
            for(int i = 0; i < dynamic.getListOfItems().toArray().length; i++){
                yincrement += 20;
                xincrement = 5;
                for(int j = 0; j < dynamic.getCapacity(); j ++){
                    g2d.drawString(String.valueOf(dynamic.getPij()[i][j]), xincrement, yincrement);
                    xincrement += 20;
                }
            }
            g2d.drawLine(0, 175, 800, 175);
            yincrement = 170;
            for(int i = 0; i < dynamic.getListOfItems().toArray().length; i++){
                yincrement += 20;
                xincrement = 5;
                for(int j = 0; j < dynamic.getCapacity(); j ++){
                    g2d.drawString(String.valueOf(dynamic.getQij()[i][j]), xincrement, yincrement);
                    xincrement += 20;
                }
            }
            g2d.drawLine(0, 330, 800, 330);
            int m = 100;
            int n = 0;
            for (int k = 0; k < 7; k++) {
                if (dynamic.getFinalList().contains(dynamic.getListOfItems().get(k))) {
                    g2d.drawImage(images[k], m, 350, null);
                    g2d.drawString("Weight: " + dynamic.getListOfItems().get(k).getWeight(), m, 470);
                    n++;
                    m += 120;
                }
            }

        }
    };

    public dynamicGUI() {
        for (int i = 0; i < 7; i++) {
            int index = i + 1;
            ImageIcon img = new ImageIcon("PROJEKT/items/img" + index + ".png");
            this.images[i] = img.getImage();
            if(i == 6){
                this.images[i] = this.images[0];
                this.images[0] = img.getImage();
            }
        }

        this.dynamic = new DynamicBackpack();
        this.setContentPane(this.panel5);
        this.pack();
        this.setVisible(true);
        this.setSize(800, 570);
        dynamic.dynamic();
        dynamic.whichItems();
    }

    public static void main(String[] args) throws IOException {
        dynamicGUI frame = new dynamicGUI();
        frame.dynamic.dynamic();
        frame.dynamic.whichItems();
    }
}
