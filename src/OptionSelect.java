import javax.swing.*;

public class OptionSelect extends JPanel{
    private final JRadioButton[] buttons;
    private final ButtonGroup group = new ButtonGroup();

    public OptionSelect(String[] options, boolean hasDefault){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        buttons = new JRadioButton[options.length];

        buttons[0] = new JRadioButton(options[0], hasDefault);
        group.add(buttons[0]);
        this.add(buttons[0]);

        for(int i = 1; i < options.length; i++){
            buttons[i] = new JRadioButton(options[i]);
            group.add(buttons[i]);
            this.add(buttons[i]);
        }
    }

    public OptionSelect(String[] options, boolean hasDefault, int indexDefault){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        buttons = new JRadioButton[options.length];

        for(int i = 0; i < options.length; i++){
            if(i == indexDefault){
                buttons[i] = new JRadioButton(options[i], hasDefault);
            } else {
                buttons[i] = new JRadioButton(options[i]);
            }
            group.add(buttons[i]);
            this.add(buttons[i]);
        }
    }

    public JRadioButton getButton(int index){
        return buttons[index];
    }
}