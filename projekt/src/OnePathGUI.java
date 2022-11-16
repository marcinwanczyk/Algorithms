import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OnePathGUI extends JFrame {
    Image map;
    List<Object> finalList;
    List<List> lista;
    int index;
    JPanel panel = new JPanel(){
        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(map, 0, 0, null);
            g2.setColor(Color.RED);
            for (int j = 0; j < lista.get(index).toArray().length - 2; j++) {
                g2.drawLine(((City) lista.get(index).get(j)).getX(),
                        ((City) lista.get(index).get(j)).getY(),
                        ((City) lista.get(index).get(j + 1)).getX(),
                        ((City) lista.get(index).get(j + 1)).getY());
            }
            g2.setColor(Color.BLACK);
            if(lista.get(index).toArray().length == 17) {
                g2.drawString(lista.get(index).get(16).toString(), 50, 10);
                g2.drawString("Generacja: " + String.valueOf(index), 100, 10);
            }
            g2.setColor(Color.RED);
        }
    };
    private JButton Losowo = new JButton("");
    public OnePathGUI(List<List> lista){
        ImageIcon mapa = new ImageIcon("PROJEKT/map/mapka.png");
        map = mapa.getImage();
        panel.add(Losowo);
        this.lista = new ArrayList<>(lista);
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(798, 570);
        Runnable paintController = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < lista.toArray().length; i++){
                    index = i;
                    try {
                        Thread.sleep(500);
                        panel.repaint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(i == lista.toArray().length - 1)
                        i = -1;
                }
            }
        };
        Thread paintThread = new Thread(paintController);
        paintThread.start();
    }
}
