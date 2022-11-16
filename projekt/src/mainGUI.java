import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;

public class mainGUI extends JFrame {
    private JPanel panel1;
    private JButton backpackButton;
    private JButton TravellingSalesmanButton;

    public mainGUI() {
        TravellingSalesmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TravellingSalesManGUI();
            }
        });

        backpackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new backpackGUI();
                JFrame frame = new JFrame();
                frame.setContentPane(new backpackGUI().getPanel4());
                frame.setVisible(true);
                frame.pack();
                frame.setSize(500, 200);
            }
        });
    }

    public static void main(String[] args) throws IOException {
        mainGUI frame = new mainGUI();
        frame.setContentPane(new mainGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700, 200);
    }
}
