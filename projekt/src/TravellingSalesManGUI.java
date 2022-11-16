import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TravellingSalesManGUI extends JFrame {

    private JButton Losowo = new JButton("Losowy");
    private JButton genetyczny = new JButton("Genetyczny");
    private JButton wyzarzanie = new JButton("Wyżarzanie");
    private JButton zachlanny = new JButton("Zachlanny");
    private JPanel mapPanel;
    private JLabel mapLabel;
    private Image map;
    private boolean paintcheck = false;
    private boolean geneticCheck = false;
    private boolean annealing = false;
    private boolean greedyCheck = false;
    private int index = 0;
    private int glownyIndex;
    private TravellingSalesManRandom random = new TravellingSalesManRandom("Waszyngton");
    private GeneticTravellingSalesman genetic = new GeneticTravellingSalesman();
    private SimulatedAnnealingTSM anneal = new SimulatedAnnealingTSM(100, 2000, (float) 0.985);
    private TravellingSalesmanGreedy greedy = new TravellingSalesmanGreedy();
    private List<List> listToPass = new ArrayList<>();

    public JPanel panel2 = new JPanel(){
        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(map, 0, 0, null);
            g2.setColor(Color.RED);
            if (paintcheck) {
                for (int i = 0; i < random.getFinalLIst().toArray().length - 1; i++) {
                    g2.drawLine(random.getFinalLIst().get(i).getX(), random.getFinalLIst().get(i).getY(),
                            random.getFinalLIst().get(i + 1).getX(), random.getFinalLIst().get(i + 1).getY());
                    g2.setColor(Color.BLACK);

                    g2.drawString(String.valueOf(i + 1), ((random.getFinalLIst().get(i).getX()) + random.getFinalLIst().get(i + 1).getX()) / 2,
                            ((random.getFinalLIst().get(i).getY()) + random.getFinalLIst().get(i + 1).getY()) / 2);
                    g2.setColor(Color.RED);
                }
                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(random.getWayLenght()), 100, 10);
                g2.setColor(Color.RED);
            }

            if(annealing){
                for(int i = 0; i < 15; i++){
                    g2.drawLine(((City) anneal.getFinalList().get(index).get(i)).getX(),
                            ((City) anneal.getFinalList().get(index).get(i)).getY(),
                            ((City) anneal.getFinalList().get(index).get(i + 1)).getX(),
                            ((City) anneal.getFinalList().get(index).get(i + 1)).getY());
                }
                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(anneal.getListOfLenghts().get(index)), 50, 10);
                g2.setColor(Color.RED);

                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(anneal.getListOfTemperatures().get(index)), 100, 10);
                g2.setColor(Color.RED);
            }

            if (geneticCheck) {
                for (int j = 0; j < genetic.getFinalList().get(index).toArray().length - 2; j++) {
                    g2.drawLine(((City) genetic.getFinalList().get(index).get(j)).getX(),
                            ((City) genetic.getFinalList().get(index).get(j)).getY(),
                            ((City) genetic.getFinalList().get(index).get(j + 1)).getX(),
                            ((City) genetic.getFinalList().get(index).get(j + 1)).getY());
                }
                g2.setColor(Color.BLACK);
                if(genetic.getFinalList().get(index).toArray().length == 17)
                g2.drawString(genetic.getFinalList().get(index).get(16).toString(), 50, 10);
                g2.setColor(Color.RED);

            }
            if(greedyCheck){
                for(int k=0;k < greedy.getFinalList().toArray().length - 1;k++){
                    g2.drawLine(greedy.getFinalList().get(k).getX(),
                            greedy.getFinalList().get(k).getY(),
                            greedy.getFinalList().get(k+1).getX(),
                            greedy.getFinalList().get(k+1).getY());
                }
                g2.setColor(Color.BLACK);
                g2.drawString(String.valueOf(greedy.getTotalDist()), 50, 10);
                g2.setColor(Color.RED);
            }
        }
    };

    public TravellingSalesManGUI() {
        ImageIcon mapa = new ImageIcon("PROJEKT/map/mapka.png");
        map = mapa.getImage();
        panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getX() + " " + e.getY());
            }
        });

        zachlanny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                greedy.greedyAlgorithm();
                greedyCheck = true;
                panel2.repaint();
            }
        });
        genetyczny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genetic.geneticAlgorithm();
                panel2.repaint();
                geneticCheck = true;
                Runnable paintController = new Runnable() {
                    @Override
                    public void run() {
                        int counter = genetic.numberInFirst / 2;
                        int pomoc = genetic.numberInFirst / 2;
                        int next;
                        for (int k = 0; k < genetic.getFinalList().toArray().length; k++) {
                            index = k;
                            try {
                                Thread.sleep(0,10);
                                panel2.repaint();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(k == counter) {
                                pomoc = pomoc/2;
                                next = pomoc;
                                counter += next;
                                listToPass.add(genetic.getFinalList().get(k));
                            }
                        }
                        new OnePathGUI(listToPass);
                    }
                };
                Thread paintThread = new Thread(paintController);
                paintThread.start();
            }

        });
        Losowo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                random.way();
                paintcheck = true;
                panel2.repaint();
            }
        });
        wyzarzanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                annealing = true;
                anneal.algorithm();
                panel2.repaint();
                Runnable paintController = new Runnable() {
                    @Override
                    public void run() {
                        for (int k = 0; k < anneal.getFinalList().toArray().length; k++) {
                            index = k;
                            try {
                                Thread.sleep(50);
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
        panel2.add(Losowo);
        panel2.add(genetyczny);
        panel2.add(wyzarzanie);
        panel2.add(zachlanny);

        this.setContentPane(this.panel2);
        this.pack();
        this.setVisible(true);
        this.setSize(798, 570);

    }

    public static void main(String[] args) throws IOException {
        TravellingSalesManGUI frame = new TravellingSalesManGUI();

    }
}

