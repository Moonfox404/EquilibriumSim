import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel{
    private final JLabel mainLabel;
    private final OptionSelect buttons;
    private final JLabel inputLabel = new JLabel("Relative Abundance");
    private final JTextField textField;

    // constructor

    public MenuPanel(String name, String[] options, boolean naOption, int width, int height){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(width, height);

        mainLabel = new JLabel(name);
        this.add(mainLabel);

        buttons = new OptionSelect(options, naOption);
        this.add(buttons);

        this.add(inputLabel);

        textField = new JTextField(10);
        Dimension max = new Dimension(width, height/10);
        textField.setMaximumSize(max);
        this.add(textField);
    }

    // get methods (all fields are read only)

    public JLabel getMainLabel(){
        return mainLabel;
    }

    public JLabel getInputLabel(){
        return inputLabel;
    }

    public JRadioButton getButton(int index){
        return buttons.getButton(index);
    }

    public JTextField getTextField(){
        return textField;
    }
}