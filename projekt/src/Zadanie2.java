import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Zadanie2 extends JFrame {
    private List<City> lista = new ArrayList<>();
    private JButton button =  new JButton("przycisk");
    private JButton button2 =  new JButton("zachlanny");
    private JButton button3 =  new JButton("genetyczny");
    TravellingSalesManRandom random = new TravellingSalesManRandom("1");
    TravellingSalesmanGreedy greedy = new TravellingSalesmanGreedy();
    GeneticTravellingSalesman genetic = new GeneticTravellingSalesman();
    private boolean paintcheck = false;
    private boolean geneticCheck = false;
    private boolean annealing = false;
    private boolean greedyCheck = false;
    private int counter;
    private int index;
    private Image map;
    private Image dot;
    public JPanel panel2 = new JPanel(){
        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(map, 0, 0, null);
            g2.setColor(Color.WHITE);
            for(int i = 0; i < lista.toArray().length; i++){
//                g2.setColor(Color.white);
                g2.drawImage(dot, lista.get(i).getX() - 10, lista.get(i).getY() - 10, null);
            }
            if (paintcheck) {
                for (int i = 0; i < random.getFinalLIst().toArray().length - 1; i++) {
                    g2.setColor(Color.WHITE);
                    g2.drawLine(random.getFinalLIst().get(i).getX(), random.getFinalLIst().get(i).getY(),
                            random.getFinalLIst().get(i + 1).getX(), random.getFinalLIst().get(i + 1).getY());
                    g2.setColor(Color.yellow);

                    g2.drawString(String.valueOf(i + 1), ((random.getFinalLIst().get(i).getX()) + random.getFinalLIst().get(i + 1).getX()) / 2,
                            ((random.getFinalLIst().get(i).getY()) + random.getFinalLIst().get(i + 1).getY()) / 2);
                    g2.setColor(Color.WHITE);
                }
                g2.setColor(Color.WHITE);
                g2.drawString(String.valueOf(random.getWayLenght()), 100, 10);
            }
            if(greedyCheck){
                for(int k=0;k < greedy.getFinalList().toArray().length - 1;k++){
                    g2.drawLine(greedy.getFinalList().get(k).getX(),
                            greedy.getFinalList().get(k).getY(),
                            greedy.getFinalList().get(k+1).getX(),
                            greedy.getFinalList().get(k+1).getY());

                    g2.setColor(Color.YELLOW);
                    g2.drawString(String.valueOf(k + 1), ((greedy.getFinalList().get(k).getX()) + greedy.getFinalList().get(k + 1).getX()) / 2,
                            ((greedy.getFinalList().get(k).getY()) + greedy.getFinalList().get(k + 1).getY()) / 2);
                    g2.setColor(Color.WHITE);
                }
                g2.setColor(Color.YELLOW);
                g2.drawString(String.valueOf(greedy.getTotalDist()), 50, 10);
                g2.setColor(Color.WHITE);
            }

            if (geneticCheck) {
                for (int j = 0; j < genetic.getFinalList().get(index).toArray().length - 2; j++) {
                    g2.drawLine(((City) genetic.getFinalList().get(index).get(j)).getX(),
                            ((City) genetic.getFinalList().get(index).get(j)).getY(),
                            ((City) genetic.getFinalList().get(index).get(j + 1)).getX(),
                            ((City) genetic.getFinalList().get(index).get(j + 1)).getY());
                }
                g2.setColor(Color.YELLOW);
                if(genetic.getFinalList().get(index).toArray().length == 17)
                    g2.drawString(genetic.getFinalList().get(index).get(16).toString(), 50, 10);
                g2.setColor(Color.WHITE);

            }
        }
    };

    public Zadanie2() {
        panel2.add(button);
        panel2.add(button2);
        panel2.add(button3);
        counter = 0;
        ImageIcon mapa = new ImageIcon("PROJEKT/map/black.jpg");
        map = mapa.getImage();
        mapa = new ImageIcon("PROJEKT/map/kropka.png");
        dot = mapa.getImage();
        panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getX() + " " + e.getY());
                counter ++;
                lista.add(new City(String.valueOf(counter), e.getX(), e.getY(), counter));
                panel2.repaint();
            }
        });
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                random.setList(new ArrayList<>(lista));
                random.setFirstCity("1");
                random.way();
                paintcheck = true;
                panel2.repaint();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greedyCheck = true;
                greedy.setList(new ArrayList<>(lista));
                greedy.greedyAlgorithm();
                panel2.repaint();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geneticCheck = true;
                genetic.getRandom().setList(new ArrayList<>(lista));
                genetic.getRandom().setFirstCity("1");
                genetic.geneticAlgorithm();
                panel2.repaint();

                Runnable paintController = new Runnable() {
                    @Override
                    public void run() {
                        for (int k = 0; k < genetic.getFinalList().toArray().length; k++) {
                            index = k;
                            try {
                                Thread.sleep(0,10);
                                panel2.repaint();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };
                Thread paintThread = new Thread(paintController);
                paintThread.start();
            }
        });
    }

    public static void main(String[] args) {
        Zadanie2 frame = new Zadanie2();
        frame.setContentPane(frame.panel2);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(798, 570);
    }
}
