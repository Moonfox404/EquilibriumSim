import javax.swing.*;
import java.awt.*;

public class GUI {

    static JPanel createSidebar(){
        // sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JPanel sMenu = createMenu("Solution", false);
        JPanel tMenu = createMenu("Titrant", true);

        // add solution menu and titrant menu to sidebar
        sidebar.add(sMenu);
        sidebar.add(tMenu);

        return sidebar;
    }

    static JPanel createMenu(String name, boolean naOption){
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
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

        menu.add(inputLabel);
        menu.add(tf);

        return menu;
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // main sim window

        JPanel sidebar = createSidebar();

        frame.getContentPane().add(sidebar);
        frame.setVisible(true);

    }
}
