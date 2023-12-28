import javax.swing.*;
import java.awt.*;

public class GUI {

    public static void main(String[] args){
        JFrame frame = new JFrame("Equilibrium Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(3 * (screenSize.width / 4), 3 * (screenSize.height / 4));

        // main sim window

        VerticalPanel sidebar = new VerticalPanel(frame.getWidth()/10, frame.getHeight());

        frame.getContentPane().add(BorderLayout.EAST, sidebar);
        frame.setVisible(true);

    }
}
