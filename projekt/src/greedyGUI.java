import javax.swing.*;
import java.io.IOException;
import java.awt.*;

public class greedyGUI extends JFrame {
    public Image[] images = new Image[8];
    private GreedyBackpack greedy = new GreedyBackpack();

    private JPanel panel3 = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            greedy.greedyBackpack();
            Graphics2D g2d = (Graphics2D) g;
            int fontSize = 14;
            Font f = new Font("Arial", Font.BOLD, fontSize);

            g2d.setFont(f);
            g2d.setColor(Color.BLACK);
            g2d.setBackground(Color.BLUE);
            g2d.drawString("Items before implementing greedy backpack:", 300, 20);
            g2d.drawString("Items in backpack after solving the problem:", 300, 260);
            g2d.drawLine(0, 25, 900, 25);
            g2d.drawLine(0, 240, 900, 240);
            g2d.drawLine(0, 270, 900, 270);
            int j = 0;
            int m = 100;
            int n = 0;
            for (int k = 0; k < 7; k++) {
                if (k % 2 == 0) {
                    g2d.drawImage(images[k], 50 + j, 40, null);
                    g2d.drawString("Weight: " + (double) (greedy.listOfItems.get(k).getWeight()), 60 + j, 140);
                    g2d.drawString("Wage: " + (double) (greedy.listOfItems.get(k).getValue()/greedy.listOfItems.get(k).getWeight()), 60 + j, 160);
                } else {
                    g2d.drawImage(images[k], 50 + j, 120, null);
                    g2d.drawString("Weight: " + (double) (greedy.listOfItems.get(k).getWeight()), 60 + j, 210);
                    g2d.drawString("Wage: " + (double) (greedy.listOfItems.get(k).getValue()/greedy.listOfItems.get(k).getWeight()), 60 + j, 230);
                }
                j += 120;


                if (greedy.backpack.contains(greedy.listOfItems.get(k))) {
                    g2d.drawImage(images[k], m, 300, null);
//                    g2d.drawString("Weight: " + (double)greedy.backpack.get(n).getWeight(),m,450);
                    g2d.drawString("Weight: " + (double) (greedy.listOfItems.get(k).getWeight()),m,450);
//                    g2d.drawString("Wage: " + (double)(greedy.backpack.get(n).getValue() / greedy.backpack.get(n).getWeight()), m, 470);
                    g2d.drawString("Wage: " + (double) (greedy.listOfItems.get(k).getValue() / greedy.listOfItems.get(k).getWeight()), m, 470);
                    n++;
                    m += 120;
                }
            }
        }
    };


    public greedyGUI() {
        for (int i = 0; i < 7; i++) {
            int index = i + 1;
            ImageIcon img = new ImageIcon("PROJEKT/items/img" + index + ".png");
            images[i] = img.getImage();
        }

        this.setContentPane(this.panel3);
        this.pack();
        this.setVisible(true);
        this.setSize(900, 570);
        this.setBackground(Color.cyan);
    }


    public static void main(String[] args) throws IOException {
        greedyGUI frame = new greedyGUI();
    }
}