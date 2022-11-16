import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class backpackGUI {
    private JPanel panel4;
    private JButton greedy;
    private JButton dynamic;


    public backpackGUI() {

        greedy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new greedyGUI();
            }
        });

        dynamic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new dynamicGUI();
            }
        });

    }


    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setContentPane(new backpackGUI().panel4);
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
    }

    public JPanel getPanel4() {
        return panel4;
    }
}
