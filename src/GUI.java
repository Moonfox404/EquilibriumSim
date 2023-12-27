import javax.swing.*;
import java.awt.*;

public class GUI {

    static JPanel createSidebar(int width, int height){
        // sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setSize(width, height);

        JPanel sMenu = createMenu("Solution", false, width, height/3);
        JPanel tMenu = createMenu("Titrant", true, width, height/3);
        JButton button = new JButton("Run");

        // add solution menu and titrant menu to sidebar
        sidebar.add(sMenu);
        sidebar.add(tMenu);
        sidebar.add(button);

        return sidebar;
    }

    static JPanel createMenu(String name, boolean naOption, int width, int height){
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setSize(width, height);

        JLabel mainLabel = new JLabel(name);
        menu.add(mainLabel);

        // radio buttons
        ButtonGroup buttons = new ButtonGroup();
        if(naOption){
            JRadioButton notApplicable = new JRadioButton("N/A", true); buttons.add(notApplicable);
            menu.add(notApplicable);
        }
        JRadioButton strongAcid = new JRadioButton("Strong Acid"); buttons.add(strongAcid);
        JRadioButton strongBase = new JRadioButton("Strong Base"); buttons.add(strongBase);
        JRadioButton weakAcid = new JRadioButton("Weak Acid"); buttons.add(weakAcid);
        JRadioButton weakBase = new JRadioButton("Weak Base"); buttons.add(weakBase);

        menu.add(strongAcid);
        menu.add(strongBase);
        menu.add(weakAcid);
        menu.add(weakBase);

        // input field
        JLabel inputLabel = new JLabel("Relative Abundance:");
        JTextField tf = new JTextField(10);
//        Dimension max = new Dimension(width, height/10);
//        tf.setMaximumSize(max);

        menu.add(inputLabel);
        menu.add(tf);

        return menu;
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width / 2, screenSize.height / 2);

        // main sim window

        JPanel sidebar = createSidebar(frame.getWidth()/10, frame.getHeight());

        frame.getContentPane().add(BorderLayout.EAST, sidebar);
        frame.setVisible(true);

    }
}
